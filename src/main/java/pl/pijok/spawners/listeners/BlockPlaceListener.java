// 
// Decompiled by Procyon v0.5.36
// 

package pl.pijok.spawners.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import pl.pijok.spawners.Spawners;

public class BlockPlaceListener implements Listener
{
    @EventHandler
    public void onPlace(final BlockPlaceEvent event) {
        final Player player = event.getPlayer();
        if (event.getBlockPlaced().getType().equals((Object)Material.SPAWNER)) {
            Spawners.getSpawnersController().addSpawner(event.getItemInHand(), event.getBlockPlaced().getLocation());
        }
    }
}
