// 
// Decompiled by Procyon v0.5.36
// 

package pl.pijok.spawners.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import pl.pijok.spawners.Spawners;
import pl.pijok.spawners.essentials.ChatUtils;

public class SpawnerCommand implements CommandExecutor
{
    public boolean onCommand(@NotNull final CommandSender sender, @NotNull final Command command, @NotNull final String label, @NotNull final String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player)sender;
            if (!player.hasPermission("spawners.admin")) {
                ChatUtils.sendMessage(player, "&cNie masz dostepu do tej komendy!");
                return true;
            }
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("clearall")) {
            Spawners.getSpawnersController().clearAll();
            ChatUtils.sendMessage(sender, "&7Cleared spawners!");
            return true;
        }
        if (args.length != 5 || !args[0].equalsIgnoreCase("give")) {
            ChatUtils.sendMessage(sender, "&7/" + label + " give <type> <nickname> <amount> <days>");
            ChatUtils.sendMessage(sender, "&7/" + label + " clearall");
            return true;
        }
        final String type = args[1];
        final String nickname = args[2];
        final int amount = Integer.parseInt(args[3]);
        final int days = Integer.parseInt(args[4]);
        final Player target = Bukkit.getPlayer(nickname);
        if (target == null || !target.isOnline()) {
            ChatUtils.sendMessage(sender, "&cTen gracz jest offline!");
            return true;
        }
        final ItemStack spawnerSample = Spawners.getSpawnersController().createSpawner(type, days);
        if (spawnerSample == null) {
            ChatUtils.sendMessage(sender, "&cWrong spawner type!");
            return true;
        }
        final ItemStack spawner = new ItemStack(spawnerSample);
        spawner.setAmount(amount);
        target.getInventory().addItem(new ItemStack[] { spawner });
        ChatUtils.sendMessage(sender, "&7Gracz &e" + nickname + " &7otrzymal spawner!");
        ChatUtils.sendMessage(target, "&eOtrzymujesz spawner!");
        return true;
    }
}
