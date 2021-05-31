package de.jeff_media.magicvaults.data;

import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class PlayerVault {

    private final UUID uuid;
    private List<ItemStack> items;

    public PlayerVault(UUID uuid) {
        this.uuid = uuid;
    }
}
