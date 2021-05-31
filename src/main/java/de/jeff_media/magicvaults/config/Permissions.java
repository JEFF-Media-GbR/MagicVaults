package de.jeff_media.magicvaults.config;

import de.jeff_media.magicvaults.MagicVaults;

import java.util.Locale;

public class Permissions {

    private static final String PREFIX = MagicVaults.getInstance().getName().toLowerCase(Locale.ROOT) + ".";

    public static final String RELOAD = PREFIX + "reload";

}
