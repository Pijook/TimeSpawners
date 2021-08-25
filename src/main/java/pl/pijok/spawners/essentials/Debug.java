// 
// Decompiled by Procyon v0.5.36
// 

package pl.pijok.spawners.essentials;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class Debug
{
    private static final ConsoleCommandSender console;
    private static String prefix;
    
    public static void setPrefix(final String a) {
        Debug.prefix = a;
    }
    
    public static void log(String a) {
        a = Debug.prefix + a;
        Debug.console.sendMessage(a.replace("&", "§"));
    }
    
    public static void sendError(final String a) {
        log("&c============");
        log("&c" + a);
        log("&c============");
    }
    
    public static void log(Object a) {
        a = Debug.prefix + a;
        Debug.console.sendMessage(String.valueOf(a).replace("&", "§"));
    }
    
    static {
        console = Bukkit.getConsoleSender();
        Debug.prefix = "[SkyWarsFixes]";
    }
}
