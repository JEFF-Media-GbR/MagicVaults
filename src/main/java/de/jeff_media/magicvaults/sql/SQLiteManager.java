package de.jeff_media.magicvaults.sql;

import de.jeff_media.magicvaults.MagicVaults;

import java.io.File;
import java.sql.*;

public class SQLiteManager {

    private static File databaseFile = new File(MagicVaults.getInstance().getDataFolder(),"vaults.db");
    private static String databasePath = databaseFile.getPath();
    private static String url = "jdbc:sqlite:" + databasePath;

    public static void connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void createDatabase() {
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createContentsTable() {
        final String sql = "CREATE TABLE IF NOT EXISTS vaultcontents ("
                + "	id varchar(36) PRIMARY KEY,"
                + "	data blob"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
