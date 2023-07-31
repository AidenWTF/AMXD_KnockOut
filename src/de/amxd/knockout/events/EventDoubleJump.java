package de.amxd.knockout.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class EventDoubleJump implements Listener {

    @EventHandler
    public static void onDoubleJump(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();

        if (p.getGameMode() == GameMode.SURVIVAL) {
            if (e.isFlying()) {
                if (p.getLocation().subtract(0, 2, 0).getBlock().getType() != Material.AIR)
                    p.setVelocity(p.getLocation().getDirection().multiply(1).setY(1));

                e.setCancelled(true);
            }
        }
    }
}