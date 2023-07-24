package de.amxd.knockout.events;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;



public class EventGrapple  implements Listener {
    @EventHandler
    public void onFish(PlayerFishEvent e) {
        Player p = e.getPlayer();
        if (p.getItemInHand().getType().equals(Material.FISHING_ROD)) {
            Location loc = e.getHook().getLocation();
            loc.setY((double)(loc.getBlockY() - 1));
            if (!loc.getBlock().getType().equals(Material.AIR)) {
                p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1.0F, 1.0F);
                Vector v = p.getLocation().getDirection();
                v.multiply(3);
                v.setY(v.getY() + 1.2D);
                p.setVelocity(v);
            }
        }
    }
}
