// 
// Decompiled by Procyon v0.5.36
// 

package pl.pijok.spawners.spawner;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import pl.pijok.spawners.Spawners;
import pl.pijok.spawners.essentials.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class SpawnersController
{
    private List<Spawner> placedSpawners;
    private HashMap<String, String> typesPrefixes;
    
    public void loadTypes() {
        this.typesPrefixes = new HashMap<String, String>();
        final YamlConfiguration configuration = ConfigUtils.load("types.yml", (Plugin)Spawners.getInstance());
        for (final String type : configuration.getConfigurationSection("types").getKeys(false)) {
            if (!Utils.isEntityType(type)) {
                Debug.sendError("Wrong entity type: " + type);
            }
            else {
                this.typesPrefixes.put(type, configuration.getString("types." + type));
            }
        }
    }
    
    public void loadTemp() {
        this.placedSpawners = new ArrayList<Spawner>();
        final YamlConfiguration configuration = ConfigUtils.load("temp.yml", (Plugin)Spawners.getInstance());
        if (!configuration.contains("spawners")) {
            return;
        }
        for (final String key : configuration.getConfigurationSection("spawners").getKeys(false)) {
            final int days = configuration.getInt("spawners." + key + ".days");
            final Location location = ConfigUtils.getLocationFromConfig(configuration, "spawners." + key + ".location");
            final String placeTime = configuration.getString("spawners." + key + ".placeTime");
            this.placedSpawners.add(new Spawner(days, location, placeTime));
        }
    }
    
    public void startTimer() {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SpawnersController.this.checkSpawners();
            }
        }, TimeUnit.SECONDS.toMillis(60L), TimeUnit.SECONDS.toMillis(60L));
    }
    
    public void showSpawners() {
        final int i = 1;
        for (final Spawner spawner : this.placedSpawners) {
            Debug.log(i + "| days: " + spawner.getDays());
            Debug.log(i + "| chunk loaded: " + spawner.getLocation().getChunk().isLoaded());
            Debug.log(i + "| x: " + spawner.getLocation().getX() + " y: " + spawner.getLocation().getY() + " z: " + spawner.getLocation().getZ() + " world: " + spawner.getLocation().getWorld().getName());
            Debug.log(i + "| place time: " + spawner.getPlaceTime());
        }
    }
    
    public void checkSpawners() {
        final Date date = new Date();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        final int hours = calendar.get(Calendar.HOUR);
        final int minutes = calendar.get(Calendar.MINUTE);
        String a = hours + ":" + minutes;
        Debug.log("Time for checking spawners!");
        final List<Spawner> toRemove = new ArrayList<Spawner>();
        final int i = 1;
        for (final Spawner spawner : this.placedSpawners) {
            Debug.log(i + "| days: " + spawner.getDays());
            Debug.log(i + "| x: " + spawner.getLocation().getX() + " y: " + spawner.getLocation().getY() + " z: " + spawner.getLocation().getZ() + " world: " + spawner.getLocation().getWorld().getName());
            Debug.log(i + "| chunk loaded: " + spawner.getLocation().getChunk().isLoaded());
            Debug.log(i + "| place time: " + spawner.getPlaceTime());
            //a = spawner.getPlaceTime();
            if (!spawner.getPlaceTime().equalsIgnoreCase(a)) {
                continue;
            }
            Debug.log("Decreasing days!");
            spawner.decreaseDays(1);
            if (spawner.getDays() > 0) {
                continue;
            }
            Debug.log("Removing spawner! (" + i + ")");
            final Location location = spawner.getLocation();
            if (!location.getChunk().isLoaded()) {
                location.getChunk().load();
            }
            location.getBlock().setType(Material.AIR);
            toRemove.add(spawner);
        }
        for (final Spawner to : toRemove) {
            this.placedSpawners.remove(to);
        }
    }
    
    public void save() {
        final YamlConfiguration configuration = ConfigUtils.load("temp.yml", (Plugin)Spawners.getInstance());
        configuration.set("spawners", (Object)null);
        int i = 1;
        for (final Spawner spawner : this.placedSpawners) {
            configuration.set("spawners." + i + ".days", (Object)spawner.getDays());
            ConfigUtils.saveLocationToConfig(configuration, "spawners." + i + ".location", spawner.getLocation());
            configuration.set("spawners." + i + ".placeTime", (Object)spawner.getPlaceTime());
            ++i;
        }
        ConfigUtils.save(configuration, "temp.yml");
    }
    
    public String getPrefixByType(final String a) {
        return this.typesPrefixes.get(a);
    }
    
    public String getTypeByPrefix(final String a) {
        for (final String type : this.typesPrefixes.keySet()) {
            if (this.typesPrefixes.get(type).equalsIgnoreCase(a)) {
                return type;
            }
        }
        return null;
    }
    
    public ItemStack createSpawner(final String type, final int days) {
        if (!this.typesPrefixes.containsKey(type)) {
            return null;
        }
        final ItemCreator itemCreator = new ItemCreator(Material.SPAWNER);
        itemCreator.setName(ChatUtils.fixColor("&c&lSpawner"));
        itemCreator.setLore("", ChatUtils.fixColor("&7Typ: &e" + this.typesPrefixes.get(type)), ChatUtils.fixColor("&7Dni: &e" + days));
        return itemCreator.toItemStack();
    }
    
    public void clearAll() {
        for (final Spawner spawner : this.placedSpawners) {
            spawner.getLocation().getBlock().setType(Material.AIR);
        }
        this.placedSpawners.clear();
    }
    
    public void addSpawner(final ItemStack itemStack, final Location location) {
        final List<String> lore = (List<String>)itemStack.getItemMeta().getLore();
        final String typeLine = lore.get(1);
        final String daysLine = lore.get(2);
        final String type = this.getTypeByPrefix(typeLine.replace("§7Typ: §e", ""));
        final int days = Integer.parseInt(daysLine.replace("§7Dni: §e", ""));
        final Date date = new Date();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        final int hours = calendar.get(Calendar.HOUR);
        final int minutes = calendar.get(Calendar.MINUTE);
        final String a = hours + ":" + minutes;
        this.placedSpawners.add(new Spawner(days, location, a));
        Spawners.getInstance().getServer().getScheduler().scheduleSyncDelayedTask((Plugin)Spawners.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
                final Block block = location.getBlock();
                final BlockState blockState = block.getState();
                final CreatureSpawner creatureSpawner = (CreatureSpawner)blockState;
                creatureSpawner.setSpawnedType(EntityType.valueOf(type));
                blockState.update();
            }
        }, 1L);
    }
    
    public void removeSpawner(final Location location) {
        final Iterator iterator = this.placedSpawners.iterator();
        while (iterator.hasNext()) {
            final Spawner spawner = (Spawner) iterator.next();
            if (spawner.getLocation().equals((Object)location)) {
                iterator.remove();
                break;
            }
        }
    }
}
