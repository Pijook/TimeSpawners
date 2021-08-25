// 
// Decompiled by Procyon v0.5.36
// 

package pl.pijok.spawners.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import pl.pijok.spawners.Spawners;
import pl.pijok.spawners.essentials.ChatUtils;

public class DebugCommand implements CommandExecutor
{
    public boolean onCommand(@NotNull final CommandSender sender, @NotNull final Command command, @NotNull final String label, @NotNull final String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("show")) {
                Spawners.getSpawnersController().showSpawners();
                return true;
            }
            if (args[0].equalsIgnoreCase("check")) {
                Spawners.getSpawnersController().checkSpawners();
                return true;
            }
        }
        ChatUtils.sendMessage(sender, "&7/" + label + " show");
        ChatUtils.sendMessage(sender, "&7/" + label + " check");
        return true;
    }
}
