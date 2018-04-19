package database;

import model.Drucker;
import model.Hardware;
import model.Raum;
import model.Rechner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dao {

    private DaoManager daoManager = new DaoManager(
            "com.mysql.jdbc.Driver",
            "jdbc:mysql://127.0.0.1:3306/reparatur?allowMultiQueries=true"
    );
    private PreparedStatement preparedStatement;
    private Connection connection = null;

    public Dao () {
        this.connection = daoManager.getConnection();
    }

    public ArrayList getHardware() {
        ArrayList<Hardware> hardwareList = new ArrayList<>();
        String sql = "SELECT h.*, r.imagepfad AS `Imagepfad/Betriebsmittel` " +
                "FROM hardware AS h " +
                "INNER JOIN hardware_rechner AS hr " +
                "ON h.id = hr.hardware_id " +
                "INNER JOIN rechner AS r " +
                "ON hr.rechner_id = r.id";

        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                    Rechner rechner = new Rechner(
                            resultSet.getInt("id"),
                            resultSet.getString("typ"),
                            resultSet.getString("seriennummer"),
                            resultSet.getString("inventarnummer"),
                            resultSet.getString("hersteller"),
                            resultSet.getString("modell"),
                            resultSet.getInt("status"),
                            resultSet.getString("raumid"),
                            resultSet.getString("Imagepfad/Betriebsmittel")
                    );
                    hardwareList.add(rechner);
            }

            sql = "SELECT h.*, d.betriebsmittel AS `Imagepfad/Betriebsmittel` " +
                    "FROM hardware AS h " +
                    "INNER JOIN hardware_drucker AS hd " +
                    "ON h.id = hd.hardware_id " +
                    "INNER JOIN drucker AS d " +
                    "ON hd.drucker_id = d.id";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                    Drucker drucker = new Drucker(
                            resultSet.getInt("id"),
                            resultSet.getString("typ"),
                            resultSet.getString("seriennummer"),
                            resultSet.getString("inventarnummer"),
                            resultSet.getString("hersteller"),
                            resultSet.getString("modell"),
                            resultSet.getInt("status"),
                            resultSet.getString("raumid"),
                            resultSet.getString("Imagepfad/Betriebsmittel")
                    );
                    hardwareList.add(drucker);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return hardwareList;
    }

    public ArrayList getRaumList() {
        ArrayList<Raum> raumList = new ArrayList<>();

        String sql = "SELECT * FROM raum";
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Raum raum = new Raum(
                    resultSet.getInt("raumid"),
                    resultSet.getString("bezeichnung"),
                    resultSet.getString("typ"),
                    resultSet.getInt("anzahlArbeitsplaetze")
                );
                raumList.add(raum);
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return raumList;
    }

    public void saveHardware(Hardware hardware) {
        String sql = "INSERT INTO hardware (" +
                "typ, seriennummer, inventarnummer, hersteller, modell, status, raumid)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            boolean isRechner = hardware instanceof Rechner;
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, hardware.getTyp());
            preparedStatement.setString(2, hardware.getSeriennummer());
            preparedStatement.setString(3, hardware.getInventarnummer());
            preparedStatement.setString(4, hardware.getHersteller());
            preparedStatement.setString(5, hardware.getModell());
            preparedStatement.setString(6, hardware.getStatus() + "");
            preparedStatement.setString(7, hardware.getRaumid());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            if (isRechner) {
                String rechnerSql = "INSERT INTO rechner (imagepfad) VALUES (?)";
                PreparedStatement preparedStatementRechner = connection.prepareStatement(rechnerSql);
                preparedStatementRechner.setString(1, ((Rechner) hardware).getImagepfad());
                preparedStatementRechner.executeUpdate();
                preparedStatementRechner.close();

                sql = "SELECT MAX(h.id) AS hardwareid, MAX(r.id) AS rechnerid FROM rechner AS r, hardware AS h";
                PreparedStatement preparedStatementMaxId = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatementMaxId.executeQuery();
                String rechnerid = "";
                String hardwareid = "";
                if (resultSet.next()) {
                    rechnerid = resultSet.getString("rechnerid");
                    hardwareid = resultSet.getString("hardwareid");
                    insertMapping(rechnerid, hardwareid, "hardware_rechner", "rechner_id");
                }
                preparedStatementMaxId.close();
            }
            if (hardware instanceof Drucker) {
                sql = "INSERT INTO drucker (betriebsmittel) VALUES (?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, ((Drucker) hardware).getBetriebsmittel());
                preparedStatement.executeUpdate();
                preparedStatement.close();

                sql = "SELECT MAX(h.id) AS hardwareid, MAX(d.id) AS druckerid FROM drucker AS d, hardware AS h";
                PreparedStatement preparedStatementMaxId  = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatementMaxId.executeQuery();
                String druckerid = "";
                String hardwareid = "";
                if (resultSet.next()) {
                    druckerid = resultSet.getString("druckerid");
                    hardwareid = resultSet.getString("hardwareid");
                    insertMapping(druckerid, hardwareid, "hardware_drucker", "drucker_id");
                }
                preparedStatementMaxId.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void insertMapping(String specificid, String hardwareid, String table, String fieldLabel) throws SQLException {
        String sql;
        sql = "INSERT INTO " + table + " (hardware_id, " +  fieldLabel + ") VALUES (?, ?)";
        PreparedStatement preparedStatementIds = connection.prepareStatement(sql);
        preparedStatementIds.setString(1, hardwareid);
        preparedStatementIds.setString(2, specificid);
        //preparedStatement.addBatch();
        preparedStatementIds.executeUpdate();
    }

    public void saveRaum(Raum raum) {
        String sql = "INSERT INTO raum (bezeichnung, typ, anzahl_arbeitsplaetze) VALUES (?, ?, ?);";
        try {
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, raum.getBezeichnung());
            preparedStatement.setString(2, raum.getTyp());
            preparedStatement.setString(3, raum.getAnzahlArbeitsplaetze() + "");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
