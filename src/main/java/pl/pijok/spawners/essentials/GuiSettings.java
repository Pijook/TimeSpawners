// 
// Decompiled by Procyon v0.5.36
// 

package pl.pijok.spawners.essentials;

import java.util.ArrayList;
import java.util.List;

public class GuiSettings
{
    private String title;
    private int rows;
    private List<Integer> slots;
    
    public GuiSettings() {
        this.slots = new ArrayList<Integer>();
    }
    
    public GuiSettings(final String title, final int rows, final List<Integer> slots) {
        this.title = title;
        this.rows = rows;
        this.slots = slots;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public int getRows() {
        return this.rows;
    }
    
    public void setRows(final int rows) {
        this.rows = rows;
    }
    
    public List<Integer> getSlots() {
        return this.slots;
    }
    
    public void setSlots(final List<Integer> slots) {
        this.slots = slots;
    }
}
