// 
// Decompiled by Procyon v0.5.36
// 

package pl.pijok.spawners.essentials;

import org.bukkit.entity.EntityType;

public class Utils
{
    public static boolean isDouble(final String a) {
        try {
            Double.parseDouble(a);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean isInteger(final String a) {
        try {
            Integer.parseInt(a);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean isEntityType(final String a) {
        try {
            EntityType.valueOf(a);
            return true;
        }
        catch (IllegalArgumentException e) {
            return false;
        }
    }
}
