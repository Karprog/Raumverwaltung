package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DaoManager {

    private Connection connection = null;
    private String className;
    private String url;

    DaoManager(String className, String url) {
        this.className = className;
        this.url = url;
    }

    public Connection getConnection() {
        try {
            Class.forName(className).newInstance();

            connection = DriverManager.getConnection(url, "root", "");

        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    protected void finalize() {
        try {
            super.finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
