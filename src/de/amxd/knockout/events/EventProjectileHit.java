package de.amxd.knockout.events;


import org.bukkit.entity.Entity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class EventProjectileHit implements Listener {
    @EventHandler
    public void ProjectileHit(ProjectileHitEvent e){
        Entity entity = e.getEntity();
        entity.remove();



    }
}
