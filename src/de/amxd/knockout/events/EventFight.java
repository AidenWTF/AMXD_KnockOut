package de.amxd.knockout.events;

import de.amxd.knockout.libs.Spawn;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.projectiles.ProjectileSource;

public class EventFight implements Listener {

    @EventHandler
    public void onPlayerHit(EntityDamageEvent e) {
        if (e.getEntity().getType().equals(EntityType.PLAYER)) {
            e.setDamage(0.0d);
            Player p = (Player) e.getEntity();
            if (Spawn.isPlayerInSpawn(p)) {
                e.setCancelled(true);
                return;
            }
            if (e instanceof EntityDamageByEntityEvent) {
                EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
                if (event.getDamager().getType().equals(EntityType.ARROW)) {
                    Arrow arrow = (Arrow) event.getDamager();
                    ProjectileSource source = arrow.getShooter();
                    if (source instanceof Player) {
                        Player shooter = (Player) source;
                        if (Spawn.isPlayerInSpawn(shooter)) {
                            e.setCancelled(true);
                            return;
                        }
                    }
                }
                if (event.getDamager().getType().equals(EntityType.PLAYER)) {
                    Player damager = (Player) event.getDamager();
                    if (Spawn.isPlayerInSpawn(damager)) {
                        e.setCancelled(true);
                        return;
                    }
                }
            }
        }
    }
}