package de.amxd.knockout.events;



import de.amxd.knockout.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;




public class EventBlockPlace implements Listener {

    @EventHandler
    public void blockPlace(BlockPlaceEvent e){

        final Player p = e.getPlayer();
        final Block b = e.getBlock();
        FileConfiguration config = Main.pl.getConfig();

        if (p.getLocation().getY() <= config.getDouble("safezoneHeight")) {
            if (e.getBlockPlaced().getType().equals(Material.SANDSTONE)) {


                Bukkit.getScheduler().runTaskLater(Main.pl, new Runnable() {
                    public void run() {
                        e.getBlock().getLocation().getBlock().setType(Material.BEDROCK);
                        Bukkit.getScheduler().runTaskLater(Main.pl, new Runnable() {

                            public void run() {
                                e.getBlock().getLocation().getBlock().setType(Material.AIR);
                            }
                        }, 20L);
                    }
                }, 100L);


                return;
            }
        }

        else if (!e.getPlayer().hasPermission("desado.knockout.build")){
            e.setCancelled(true);
           // e.getPlayer().sendMessage(Main.PREFIX+"§cWo hast du diesen Block her? Du kannst hier nur Sandstein platzieren.");
            return;
        }

    }
}
