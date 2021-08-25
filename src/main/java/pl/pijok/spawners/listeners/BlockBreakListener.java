// 
// Decompiled by Procyon v0.5.36
// 

package pl.pijok.spawners.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import pl.pijok.spawners.Spawners;

public class BlockBreakListener implements Listener
{
    @EventHandler
    public void onBreak(final BlockBreakEvent event) {
        final Player player = event.getPlayer();
        final Block block = event.getBlock();
        if (block.getType().equals((Object)Material.SPAWNER)) {
            Spawners.getSpawnersController().removeSpawner(block.getLocation());
        }
    }
}
