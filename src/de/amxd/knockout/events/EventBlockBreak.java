package de.amxd.knockout.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class EventBlockBreak implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        if (!e.getPlayer().hasPermission("desado.knockout.build")) {
            e.setCancelled(true);
            return;
        }
    }
}
