package de.amxd.knockout.events;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class EventDrop implements Listener {
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (!e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            e.setCancelled(true);
        }

    }
}