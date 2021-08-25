// 
// Decompiled by Procyon v0.5.36
// 

package pl.pijok.spawners.essentials;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigUtils
{
    private static Plugin plugin;
    
    public static YamlConfiguration load(final String configName, final Plugin plugin) {
        final File file = new File(plugin.getDataFolder() + File.separator + configName);
        if (!file.exists()) {
            plugin.saveResource(configName, false);
        }
        final YamlConfiguration config = new YamlConfiguration();
        try {
            config.load(file);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        catch (InvalidConfigurationException e2) {
            e2.printStackTrace();
            return null;
        }
        return config;
    }
    
    public static YamlConfiguration load(final String configName, final String folder, final Plugin plugin) {
        final File file = new File(plugin.getDataFolder() + File.separator + folder + File.separator + configName);
        if (!file.exists()) {
            plugin.saveResource(folder + File.separator + configName, false);
        }
        final YamlConfiguration config = new YamlConfiguration();
        try {
            config.load(file);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        catch (InvalidConfigurationException e2) {
            e2.printStackTrace();
            return null;
        }
        return config;
    }
    
    public static void save(final YamlConfiguration c, final String file) {
        try {
            c.save(new File(ConfigUtils.plugin.getDataFolder(), file));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void save(final YamlConfiguration c, final String folder, final String file) {
        try {
            c.save(new File(ConfigUtils.plugin.getDataFolder() + File.separator + folder, file));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static Location getLocationFromConfig(final YamlConfiguration configuration, final String path) {
        final double locationX = configuration.getDouble(path + ".x");
        final double locationY = configuration.getDouble(path + ".y");
        final double locationZ = configuration.getDouble(path + ".z");
        final String worldName = configuration.getString(path + ".world");
        final Location location = new Location(Bukkit.getWorld(worldName), locationX, locationY, locationZ);
        return location;
    }
    
    public static void saveLocationToConfig(final YamlConfiguration configuration, final String path, final Location location) {
        configuration.set(path + ".x", (Object)location.getX());
        configuration.set(path + ".y", (Object)location.getY());
        configuration.set(path + ".z", (Object)location.getZ());
        configuration.set(path + ".world", (Object)location.getWorld().getName());
    }
    
    public static ItemStack getItemstack(final YamlConfiguration configuration, final String path) {
        final Material material = Material.valueOf(configuration.getString(path + ".material"));
        final List<String> lore = new ArrayList<String>();
        if (configuration.contains(path + ".lore")) {
            for (final String a : configuration.getStringList(path + ".lore")) {
                lore.add(ChatUtils.fixColor(a));
            }
        }
        int amount = 1;
        if (configuration.contains(path + ".amount")) {
            amount = configuration.getInt(path + ".amount");
        }
        String itemName = material.name();
        if (configuration.contains(path + ".name")) {
            itemName = ChatUtils.fixColor(configuration.getString(path + ".name"));
        }
        final ItemCreator creator = new ItemCreator(material, amount).setName(itemName).setLore(lore);
        if (configuration.contains(path + ".enchants")) {
            for (final String enchant : configuration.getConfigurationSection(path + ".enchants").getKeys(false)) {
                creator.addUnsafeEnchantment(Enchantment.getByName(enchant), configuration.getInt(path + ".enchants." + enchant));
            }
        }
        return creator.toItemStack();
    }
    
    public static void setPlugin(final Plugin plugin) {
        ConfigUtils.plugin = plugin;
    }
}
