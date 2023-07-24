package de.amxd.knockout.libs;

import de.amxd.knockout.main.Main;
import org.bukkit.Bukkit;

import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class Deathzone implements Listener {

    public static class DamageEntry
    {
        public long time;
        public Player player;

        public DamageEntry(long time, Player player)
        {
            this.time = time;
            this.player = player;
        }
    }

    private HashMap<Player, ArrayList<DamageEntry>> damageEntries;

    public Deathzone() {
        damageEntries = new HashMap<>();
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event)
    {
        FileConfiguration config = Main.pl.getConfig();
        if(event.getTo().getY() < config.getDouble("deathHeight"))
        {
            handleDeath(event.getPlayer());
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onDamage(EntityDamageByEntityEvent event)
    {
        if(!event.isCancelled())
        {
            if(event.getEntity().getType().equals(EntityType.PLAYER))
            {
                if(event.getDamager().getType().equals(EntityType.PLAYER))
                {
                    Player from = (Player) event.getDamager();
                    Player target = (Player) event.getEntity();
                    insure(target);
                    ArrayList<DamageEntry> entries = damageEntries.get(target);
                    entries.add(new DamageEntry(System.currentTimeMillis(), from));
                    while (entries.size() > 20) {
                        entries.remove(0);
                    }
                    damageEntries.put(target, entries);
                }
            }
        }
    }

    private void insure(Player player)
    {
        if(!damageEntries.containsKey(player))
        {
            damageEntries.put(player, new ArrayList<>());
        }
    }

    private void spawnPlayer(Player player)
    {
        player.setHealth(20);
        player.teleport(player.getWorld().getSpawnLocation());
    }

    private void handleDeath(Player player)
    {
        Player killer = null;
        Player helper = null;
        insure(player);
        for(int i = damageEntries.get(player).size() - 1; i >= 0; i--)
        {
            DamageEntry entry = damageEntries.get(player).get(i);
            if(killer == null)
            {
                if(entry.time + 2000 >= System.currentTimeMillis())
                {
                    killer = entry.player;
                }
                else
                {
                    break;
                }
            }
            else
            {
                if(!entry.player.equals(killer))
                {
                    if(entry.time + 2000 >= System.currentTimeMillis())
                    {
                        helper = entry.player;
                    }
                    break;
                }
            }
        }
        if(killer != null)
        {
            int coins = killer.getLevel();
            killer.setLevel(coins + 2);
            killer.sendMessage(Main.PREFIX+"§fDu hast §6" + player.getName() + "§f getötet.");
            Main.MY_SQL.update("UPDATE stats SET kills = kills + 1 WHERE uuid ='"+killer.getUniqueId()+"'");
            ScoreboardUtil.updateAll();
            killer.playSound(killer.getLocation(), Sound.BLOCK_LAVA_POP, 1.0f, 1.0f);
        }
        if(helper != null)
        {
            int coins = helper.getLevel();
            helper.setLevel(coins + 1);
            helper.sendMessage(Main.PREFIX+"§fDu hast §6" + killer.getName() + " §fgeholfen §6" + player.getName() + "§f zu töten.");
            Main.MY_SQL.update("UPDATE stats SET assists = assists + 1 WHERE uuid ='"+helper.getUniqueId()+"'");ScoreboardUtil.updateAll();
            helper.playSound(helper.getLocation(), Sound.BLOCK_LAVA_POP, 1.0f, 1.0f);
        }
        String message = getDeathMessage(player, killer, helper);
        for(Player p : Bukkit.getOnlinePlayers())
        {
            p.sendMessage(message);
        }
        spawnPlayer(player);
        int level = player.getLevel();
        if(player.getLevel() <= 0){
            player.setLevel(0);

        }else {
            player.setLevel(level - 1);
          
        }


        Main.MY_SQL.update("UPDATE stats SET deaths = deaths + 1 WHERE uuid ='"+player.getUniqueId()+"'");
        ScoreboardUtil.updateAll();
        player.getPlayer().getInventory().clear();
        Items.shop(player.getPlayer());
        Items.stick(player.getPlayer());
        Items.block(player.getPlayer());
    }

    private String getDeathMessage(Player player, Player killer, Player helper)
    {
        String message = Main.PREFIX+"§fDer Spieler §6" + player.getDisplayName();
        if(killer == null)
        {
            message += " §fist gestorben.";
        }
        else
        {
            message += " §fwurde von §6" + killer.getDisplayName();
            if(helper != null)
            {
                message += " §fund §6" + helper.getDisplayName();
            }
            message += " §fgetötet.";
        }
        return message;
    }

}
