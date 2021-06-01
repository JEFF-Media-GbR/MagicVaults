package de.jeff_media.magicvaults.sql;

import de.jeff_media.magicvaults.MagicVaults;
import org.bukkit.Bukkit;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteManager implements SQLManager {

    private static final boolean DEBUG = true;
    private static File databaseFile = new File(MagicVaults.getInstance().getDataFolder(), "vaults.db");
    private static String databasePath = databaseFile.getPath();
    private static String url = "jdbc:sqlite:" + databasePath;

    private static void debug(String text) {
        Bukkit.getLogger().warning(text);
    }

    public void createTables() {
        final String[] createTableQueries = {
                "CREATE TABLE `vaults` (" +
                        "`vault_id` INT unsigned NOT NULL, " +
                        "`owner_uuid` VARCHAR(36) NOT NULL," +
                        "`alias` VARCHAR(128) DEFAULT NULL," +
                        "`contents` LONGBLOB NOT NULL," +
                        "PRIMARY KEY (`vault_id`)" +
                        ");",

                "CREATE TABLE `vault_permissions` (" +
                        "`vault_id` INT unsigned NOT NULL," +
                        "`uuid` VARCHAR(36) NOT NULL," +
                        "`can_take` BOOLEAN," +
                        "`can_add` BOOLEAN," +
                        "PRIMARY KEY (`vault_id`, `uuid`)" +
                        ");",

        };

        execute(createTableQueries);
    }

    public void execute(String query) {
        try (Connection conn = DriverManager.getConnection(SQLiteManager.url); Statement stmt = conn.createStatement()) {
            SQLiteManager.debug(query);
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void execute(String[] queries) {
        try (Connection conn = DriverManager.getConnection(SQLiteManager.url); Statement stmt = conn.createStatement()) {
            for (String query : queries) {
                SQLiteManager.debug(query);
                stmt.execute(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
