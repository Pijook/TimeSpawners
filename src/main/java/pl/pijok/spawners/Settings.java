// 
// Decompiled by Procyon v0.5.36
// 

package pl.pijok.spawners;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import pl.pijok.spawners.essentials.ConfigUtils;

public class Settings
{
    public static String resetHour;
    
    public static void load() {
        final YamlConfiguration configuration = ConfigUtils.load("config.yml", (Plugin)Spawners.getInstance());
        Settings.resetHour = configuration.getString("resetHour");
    }
}
