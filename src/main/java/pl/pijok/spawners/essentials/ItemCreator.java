// 
// Decompiled by Procyon v0.5.36
// 

package pl.pijok.spawners.essentials;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ItemCreator
{
    private ItemStack is;
    
    public ItemCreator(final Material m) {
        this(m, 1);
    }
    
    public ItemCreator(final ItemStack is) {
        this.is = is;
    }
    
    public ItemCreator(final Material m, final int amount) {
        this.is = new ItemStack(m, amount);
    }
    
    public ItemCreator(final Material m, final int amount, final byte durability) {
        this.is = new ItemStack(m, amount, (short)durability);
    }
    
    public ItemCreator clone() {
        return new ItemCreator(this.is);
    }
    
    public ItemCreator setDurability(final short dur) {
        this.is.setDurability(dur);
        return this;
    }
    
    public ItemCreator setName(final String name) {
        final ItemMeta im = this.is.getItemMeta();
        im.setDisplayName(name);
        this.is.setItemMeta(im);
        return this;
    }
    
    public ItemCreator addUnsafeEnchantment(final Enchantment ench, final int level) {
        this.is.addUnsafeEnchantment(ench, level);
        return this;
    }
    
    public ItemCreator removeEnchantment(final Enchantment ench) {
        this.is.removeEnchantment(ench);
        return this;
    }
    
    public ItemCreator setSkullOwner(final String owner) {
        try {
            final SkullMeta im = (SkullMeta)this.is.getItemMeta();
            im.setOwner(owner);
            this.is.setItemMeta((ItemMeta)im);
        }
        catch (ClassCastException ex) {}
        return this;
    }
    
    public ItemCreator addEnchant(final Enchantment ench, final int level) {
        final ItemMeta im = this.is.getItemMeta();
        im.addEnchant(ench, level, true);
        this.is.setItemMeta(im);
        return this;
    }
    
    public ItemCreator addEnchantments(final Map<Enchantment, Integer> enchantments) {
        this.is.addEnchantments((Map)enchantments);
        return this;
    }
    
    public ItemCreator setInfinityDurability() {
        this.is.setDurability((short)32767);
        return this;
    }
    
    public ItemCreator setLore(final String... lore) {
        final ItemMeta im = this.is.getItemMeta();
        im.setLore((List)Arrays.asList(lore));
        this.is.setItemMeta(im);
        return this;
    }
    
    public ItemCreator setLore(final List<String> lore) {
        final ItemMeta im = this.is.getItemMeta();
        im.setLore((List)lore);
        this.is.setItemMeta(im);
        return this;
    }
    
    public ItemCreator removeLoreLine(final String line) {
        final ItemMeta im = this.is.getItemMeta();
        final List<String> lore = new ArrayList<String>(im.getLore());
        if (!lore.contains(line)) {
            return this;
        }
        lore.remove(line);
        im.setLore((List)lore);
        this.is.setItemMeta(im);
        return this;
    }
    
    public ItemCreator removeLoreLine(final int index) {
        final ItemMeta im = this.is.getItemMeta();
        final List<String> lore = new ArrayList<String>(im.getLore());
        if (index < 0 || index > lore.size()) {
            return this;
        }
        lore.remove(index);
        im.setLore((List)lore);
        this.is.setItemMeta(im);
        return this;
    }
    
    public ItemCreator addLoreLine(final String line) {
        final ItemMeta im = this.is.getItemMeta();
        List<String> lore = new ArrayList<String>();
        if (im.hasLore()) {
            lore = new ArrayList<String>(im.getLore());
        }
        lore.add(line);
        im.setLore((List)lore);
        this.is.setItemMeta(im);
        return this;
    }
    
    public ItemCreator addLoreLine(final String line, final int pos) {
        final ItemMeta im = this.is.getItemMeta();
        final List<String> lore = new ArrayList<String>(im.getLore());
        lore.set(pos, line);
        im.setLore((List)lore);
        this.is.setItemMeta(im);
        return this;
    }
    
    public ItemCreator setLeatherArmorColor(final Color color) {
        try {
            final LeatherArmorMeta im = (LeatherArmorMeta)this.is.getItemMeta();
            im.setColor(color);
            this.is.setItemMeta((ItemMeta)im);
        }
        catch (ClassCastException ex) {}
        return this;
    }
    
    public ItemStack toItemStack() {
        return this.is;
    }
}
