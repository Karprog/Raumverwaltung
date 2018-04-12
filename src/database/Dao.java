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
            "com.mysql.jdbc.driver",
            "jdbc:mysql://localhost/reparatur "
    );
    private PreparedStatement preparedStatement;
    private Connection connection = daoManager.getConnection();

    public ArrayList getHardware() {
        ArrayList<Hardware> hardwareList = new ArrayList<>();
        String sql = "SELECT * FROM hardware AS h INNER JOIN hardware_rechner AS hr ON h.id = hr.hardware_id" +
                " UNION ALL " +
                "SELECT * FROM hardware AS h INNER JOIN hardware_drucker AS hd ON h.id = hr.hardware_id;";

        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if ("Rechner".equals(resultSet.getString("typ"))) {
                    Rechner rechner = new Rechner(
                            resultSet.getString("typ"),
                            resultSet.getString("seriennummer"),
                            resultSet.getString("inventarnummer"),
                            resultSet.getString("hersteller"),
                            resultSet.getString("modell"),
                            resultSet.getInt("status"),
                            resultSet.getString("imagepfad"),
                            resultSet.getString("raumid")
                    );
                    hardwareList.add(rechner);
                }
                if ("Drucker".equals(resultSet.getString("typ"))) {
                    Drucker drucker = new Drucker(
                            resultSet.getString("typ"),
                            resultSet.getString("seriennummer"),
                            resultSet.getString("inventarnummer"),
                            resultSet.getString("hersteller"),
                            resultSet.getString("modell"),
                            resultSet.getInt("status"),
                            resultSet.getString("betriebsmittel"),
                            resultSet.getString("raumid")
                    );
                    hardwareList.add(drucker);
                }
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

    public void saveHardware(Hardware hardware) {
        String sql = "INSERT INTO hardware (" +
                "typ, seriennummer, inventarnummer, hersteler, modell, status)" +
                "VALUES (?, ?, ?, ?, ?, ?);";
        try {
            preparedStatement.setString(1, hardware.getTyp());
            preparedStatement.setString(2, hardware.getSeriennummer());
            preparedStatement.setString(3, hardware.getInventarnummer());
            preparedStatement.setString(4, hardware.getHersteller());
            preparedStatement.setString(5, hardware.getModell());
            preparedStatement.setString(6, hardware.getStatus() + "");
            preparedStatement.executeUpdate(sql);

            if (hardware instanceof Rechner) {
                sql = "INSERT INTO rechner (imagepfad) VALUES (?)";
                connection.prepareStatement(sql);
                preparedStatement.setString(1, ((Rechner) hardware).getImagepfad());
                preparedStatement.executeUpdate();

                sql = "SELECT MAX(h.id) AS hardwareid, MAX(r.id) AS rechnerid FROM rechner AS r, hardware AS h";
                preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                String rechnerid = resultSet.getString("rechnerid");
                String hardwareid = resultSet.getString("hardwareid");

                insertMapping(rechnerid, hardwareid, "hardware_rechner");
            }
            if (hardware instanceof Drucker) {
                sql = "INSERT INTO drucker (betriebsmittel) VALUES (?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, ((Drucker) hardware).getBetriebsmittel());
                preparedStatement.executeUpdate();

                sql = "SELECT MAX(h.id) AS hardwareid, MAX(d.id) AS druckerid FROM drucker AS d, hardware AS h";
                preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                String druckerid = resultSet.getString("rechnerid");
                String hardwareid = resultSet.getString("hardwareid");

                insertMapping(druckerid, hardwareid, "hardware_drucker");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertMapping(String specificid, String hardwareid, String table) throws SQLException {
        String sql;
        sql = "INSERT INTO " + table + " (hardware_id, rechner_id) VALUES (?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, hardwareid);
        preparedStatement.setString(2, specificid);
        preparedStatement.executeUpdate();
    }

    public void saveRaum(Raum raum) {
        String sql = "INSERT INTO raum (bezeichnung, typ, anzahl_arbeitsplaetze) VALUES (?, ?, ?);";
        try {
            connection.prepareStatement(sql);
            preparedStatement.setString(1, raum.getBezeichnung());
            preparedStatement.setString(2, raum.getTyp());
            preparedStatement.setString(3, raum.getAnzahlArbeitsplaetze() + "");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
