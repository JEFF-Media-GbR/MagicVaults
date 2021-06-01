package de.jeff_media.magicvaults.sql;

import de.jeff_media.magicvaults.MagicVaults;
import de.jeff_media.magicvaults.config.Config;
import de.jeff_media.magicvaults.enums.StorageType;

public interface SQLManager {

    void createTables();

    void execute(String query);

    void execute(String[] queries);

    static SQLManager init() {
        StorageType storageType = StorageType.parseStorageType(MagicVaults.getInstance().getConfig().getString(Config.STORAGE_TYPE));
        switch(storageType) {
            case SQLITE:
                return new SQLiteManager();
            default:
                throw new UnsupportedOperationException();
        }
    }
}
