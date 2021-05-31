package de.jeff_media.magicvaults;

import de.jeff_media.magicvaults.commands.MainCommand;
import de.jeff_media.magicvaults.config.Config;
import de.jeff_media.magicvaults.config.ConfigUpdater;
import de.jeff_media.magicvaults.config.Messages;
import de.jeff_media.magicvaults.sql.SQLiteManager;
import de.jeff_media.updatechecker.UpdateChecker;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;

public class MagicVaults extends JavaPlugin {

    private static final int SPIGOT_RESOURCE_ID = 1;
    private static final int BSTATS_ID = 1;
    private static final String UPDATECHECKER_LINK_API = "https://api.jeff-media.de/magicvaults/latest-version.txt";
    private static final String UPDATECHECKER_LINK_DONATE = "https://paypal.me/mfnalex";

    private static MagicVaults instance;

    public static MagicVaults getInstance() {
        return instance;
    }

    public int getBstatsId() {
        return BSTATS_ID;
    }

    @Override
    public void onEnable() {
        instance = this;
        reload();
        getCommand(getName().toLowerCase(Locale.ROOT)).setExecutor(new MainCommand());
        SQLiteManager.createDatabase();
        SQLiteManager.createContentsTable();
    }

    public void reload() {
        saveDefaultConfig();
        ConfigUpdater.updateConfig();
        new Config();
        reloadConfig();

        new Messages();

        initUpdateChecker();
    }

    private void initUpdateChecker() {
        final UpdateChecker updateChecker = UpdateChecker.init(this, UPDATECHECKER_LINK_API)
                .setDonationLink(UPDATECHECKER_LINK_DONATE)
                .setDownloadLink(SPIGOT_RESOURCE_ID)
                .setChangelogLink(SPIGOT_RESOURCE_ID);

        switch(getConfig().getString(Config.CHECK_FOR_UPDATES).toLowerCase()) {
            case "true":
                updateChecker.checkNow().checkEveryXHours(getConfig().getDouble(Config.CHECK_FOR_UPDATES_INTERVAL));
                break;
            case "on-startup":
                updateChecker.checkNow();
                break;
        }
    }
}
