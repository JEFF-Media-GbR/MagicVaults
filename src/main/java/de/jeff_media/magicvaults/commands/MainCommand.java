package de.jeff_media.magicvaults.commands;

import de.jeff_media.magicvaults.MagicVaults;
import de.jeff_media.magicvaults.serialization.ItemStackSerializer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class MainCommand implements CommandExecutor {

    private final MagicVaults main = MagicVaults.getInstance();
    private byte[] DEBUG_ITEMSTACK = null;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        Player player = null;
        if(sender instanceof Player) {
            player = (Player) sender;
        }

        if(args.length == 0) {
            return false;
        }

        //noinspection SwitchStatementWithTooFewBranches
        switch (args[0].toLowerCase()) {

            case "reload":
                return ReloadCommand.run(sender, command, alias, args);

            case "save":
                byte[] serializedItem = ItemStackSerializer.serializeItemStack(player.getInventory().getItemInMainHand());
                System.out.println(serializedItem);
                DEBUG_ITEMSTACK = serializedItem;
                return true;
            case "load":
                ItemStack deserializedItem = ItemStackSerializer.deserializeItemStack(DEBUG_ITEMSTACK);
                System.out.println(deserializedItem);
                player.getInventory().setItemInMainHand(deserializedItem);
                return true;
            default:
                return false;
        }
    }
}
