package de.jeff_media.magicvaults.enums;

import org.bukkit.entity.EntityType;

import java.util.Locale;

public enum StorageType {
    FLAT_FILE, MULTIPLE_FILES, SQLITE, MYSQL;

    public static StorageType parseStorageType(String name) {
        if(name.toLowerCase(Locale.ROOT).startsWith("flat")) {
            return FLAT_FILE;
        } else if(name.toLowerCase(Locale.ROOT).startsWith("multi")) {
            return MULTIPLE_FILES;
        } else if(name.toLowerCase(Locale.ROOT).startsWith("sqlite")) {
            return SQLITE;
        } else if(name.toLowerCase(Locale.ROOT).startsWith("mysql")) {
            return MYSQL;
        }
        throw new IllegalArgumentException("Unknown StorageType: " + name);
    }
}
