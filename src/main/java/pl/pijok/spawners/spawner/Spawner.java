// 
// Decompiled by Procyon v0.5.36
// 

package pl.pijok.spawners.spawner;

import org.bukkit.Location;

public class Spawner
{
    private int days;
    private Location location;
    private String placeTime;
    
    public Spawner(final int days, final Location location, final String placeTime) {
        this.days = days;
        this.location = location;
        this.placeTime = placeTime;
    }
    
    public int getDays() {
        return this.days;
    }
    
    public void setDays(final int days) {
        this.days = days;
    }
    
    public Location getLocation() {
        return this.location;
    }
    
    public void setLocation(final Location location) {
        this.location = location;
    }
    
    public void decreaseDays(final int amount) {
        this.days -= amount;
    }
    
    public String getPlaceTime() {
        return this.placeTime;
    }
    
    public void setPlaceTime(final String placeTime) {
        this.placeTime = placeTime;
    }
}
