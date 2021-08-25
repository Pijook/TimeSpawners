// 
// Decompiled by Procyon v0.5.36
// 

package pl.pijok.spawners;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pl.pijok.spawners.commands.DebugCommand;
import pl.pijok.spawners.commands.SpawnerCommand;
import pl.pijok.spawners.essentials.ChatUtils;
import pl.pijok.spawners.essentials.ConfigUtils;
import pl.pijok.spawners.essentials.Debug;
import pl.pijok.spawners.listeners.BlockPlaceListener;
import pl.pijok.spawners.spawner.SpawnersController;

public class Spawners extends JavaPlugin
{
    private static Spawners instance;
    private static SpawnersController spawnersController;
    
    public void onEnable() {
        Spawners.instance = this;
        Spawners.spawnersController = new SpawnersController();
        Debug.setPrefix("[Spawners]");
        ChatUtils.setPrefix("&7[&aSpawners&7]&r ");
        ConfigUtils.setPlugin((Plugin)this);
        this.getCommand("spawners").setExecutor((CommandExecutor)new SpawnerCommand());
        //this.getCommand("debug").setExecutor((CommandExecutor)new DebugCommand());
        this.getServer().getPluginManager().registerEvents((Listener)new BlockPlaceListener(), (Plugin)this);
        this.loadStuff();
    }
    
    public void onDisable() {
        this.saveStuff();
    }
    
    public void loadStuff() {
        Debug.log("Loading Spawners v1.0 by Pijok");
        Debug.log("Loading settings...");
        Settings.load();
        Debug.log("Loading spawners types...");
        Spawners.spawnersController.loadTypes();
        Debug.log("Loading saved spawners...");
        Spawners.spawnersController.loadTemp();
        Debug.log("Starting timers...");
        spawnersController.startTimer();
    }
    
    public void saveStuff() {
        Debug.log("Saving Spawners v1.0 by Pijok");
        Debug.log("Saving loaded spawners...");
        Spawners.spawnersController.save();
    }
    
    public static Spawners getInstance() {
        return Spawners.instance;
    }
    
    public static SpawnersController getSpawnersController() {
        return Spawners.spawnersController;
    }
}
