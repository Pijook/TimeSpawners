// 
// Decompiled by Procyon v0.5.36
// 

package pl.pijok.spawners.essentials;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatUtils
{
    private static String prefix;
    
    public static void setPrefix(final String a) {
        ChatUtils.prefix = a;
    }
    
    public static String fixColor(String message) {
        message = message.replace("&", "§");
        return message;
    }
    
    public static void broadcast(final String message) {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            sendMessage(player, message);
        }
    }
    
    public static void sendMessage(final Player player, final String message) {
        player.sendMessage(fixColor(ChatUtils.prefix + message));
    }
    
    public static void sendMessage(final CommandSender player, final String message) {
        player.sendMessage(fixColor(ChatUtils.prefix + message));
    }
}
