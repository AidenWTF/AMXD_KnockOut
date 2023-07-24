package de.amxd.knockout.events;

import de.amxd.knockout.libs.Items;
import de.amxd.knockout.libs.ScoreboardUtil;
import de.amxd.knockout.main.Main;
import org.bukkit.Location;

import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


import static de.amxd.knockout.main.Main.MY_SQL;


public class EventPlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        e.setJoinMessage("§f[§a+§f] "+e.getPlayer().getName());
        FileConfiguration config = Main.pl.getConfig();


        ScoreboardUtil.showScoreboard(e.getPlayer());
        MY_SQL.update("INSERT INTO stats (uuid, username, kills,deaths,assists) VALUES ("+"'"+e.getPlayer().getUniqueId()+"'"+","+"'"+e.getPlayer().getName()+"',0,0,0)"+"ON DUPLICATE KEY UPDATE uuid = uuid");
        ScoreboardUtil.updateAll();
        e.getPlayer().getInventory().clear();
        Items.shop(e.getPlayer());
        Items.stick(e.getPlayer());
        Items.block(e.getPlayer());

        Location loc = new Location(e.getPlayer().getWorld(), config.getDouble("spawnX"),  config.getDouble("spawnY"),   config.getDouble("spawnZ"));
        e.getPlayer().teleport(loc);
        e.getPlayer().setLevel(0);
        e.getPlayer().setExp(0f);
        e.getPlayer().setFoodLevel(40);

    }
    @EventHandler
    public void onPlayerQuit (PlayerQuitEvent e){
        e.setQuitMessage("§f[§c-§f] "+e.getPlayer().getName());

    }
}
