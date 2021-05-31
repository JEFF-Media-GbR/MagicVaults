package de.jeff_media.magicvaults.commands;

import de.jeff_media.magicvaults.MagicVaults;
import de.jeff_media.magicvaults.config.Messages;
import de.jeff_media.magicvaults.config.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand {

    @SuppressWarnings("SameReturnValue")
    public static boolean run(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        MagicVaults main = MagicVaults.getInstance();

        if(!commandSender.hasPermission(Permissions.RELOAD)) {
            commandSender.sendMessage(command.getPermissionMessage());
            return true;
        }
        main.reload();
        commandSender.sendMessage(Messages.CONFIG_RELOADED);
        return true;
    }

}
