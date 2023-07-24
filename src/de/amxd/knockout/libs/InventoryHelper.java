package de.amxd.knockout.libs;

import de.amxd.knockout.main.Main;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import java.util.Arrays;

public class InventoryHelper implements Listener {
    private Inventory inv;

    public  void createGui(int size, String title) {
        inv = Bukkit.createInventory(null, size, title);


        initializeItems();
    }




    public void initializeItems() {
        inv.addItem(createGuiItem(Material.SANDSTONE, "§bBlöcke", "§a1 Coin", "§bBau dich hoch"));
        inv.addItem(createGuiItem(Material.FISHING_ROD, "§6Enterhaken", "§a10 Coins", "§bFly like a bird"));
        inv.addItem(createGuiItem(Material.BOW, "§5Bogen", "§a20 Coins", "§bShoot em all"));
        inv.addItem(createGuiItem(Material.ARROW, "§5Pfeil", "§a1 Coin", ""));

    }


    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();


        meta.setDisplayName(name);


        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }


    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inv);
    }


    @EventHandler

    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        int coins = p.getLevel();
            if (e.getRawSlot() == 0){
          if (coins < 1){
              e.setCancelled(true);
                e.getWhoClicked().sendMessage(Main.PREFIX+"Nicht genug Coins");
              p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1.0F, 1.0F);
            }else{
              e.setCancelled(true);
              p.getInventory().addItem(createGuiItem(Material.SANDSTONE, "§bBlöcke", "§a1 Coin", "§bBau dich hoch"));

              p.setLevel(coins -1);
              p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1.0F, 1.0F);
              e.getWhoClicked().sendMessage(Main.PREFIX+"Du hast §6Blöcke§f für 1 Coin gekauft");
              ScoreboardUtil.updateAll();
          }

        }
            if (e.getRawSlot() == 1) {
                if (coins < 10) {
                    e.setCancelled(true);
                    e.getWhoClicked().sendMessage(Main.PREFIX + "Nicht genug Coins");
                    p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1.0F, 1.0F);
                } else {
                    e.setCancelled(true);
                    p.getInventory().addItem(createGuiItem(Material.FISHING_ROD, "§6Enterhaken", "§a10 Coins", "§bFly like a bird"));
                    p.setLevel(coins - 10);
                    p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1.0F, 1.0F);
                    e.getWhoClicked().sendMessage(Main.PREFIX + "Du hast §6Enterhaken§f für 10 Coins gekauft");
                    ScoreboardUtil.updateAll();
                }

            }
            if (e.getRawSlot() == 2) {
                if (coins < 20) {
                    e.setCancelled(true);
                    e.getWhoClicked().sendMessage(Main.PREFIX + "Nicht genug Coins");
                    p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1.0F, 1.0F);
                } else {
                    e.setCancelled(true);
                    p.getInventory().addItem(createGuiItem(Material.BOW, "§5Bogen", "§a20 Coins", "§bShoot em all"));
                    p.setLevel(coins - 20);
                    p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1.0F, 1.0F);
                    e.getWhoClicked().sendMessage(Main.PREFIX + "Du hast §5Bogen§f für 20 Coins gekauft");
                    ScoreboardUtil.updateAll();
                }

            }
            if (e.getRawSlot() == 3) {
                if (coins < 1) {
                    e.setCancelled(true);
                    e.getWhoClicked().sendMessage(Main.PREFIX + "Nicht genug Coins");
                    p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1.0F, 1.0F);
                } else {
                    e.setCancelled(true);
                    p.getInventory().addItem(createGuiItem(Material.ARROW, "§5Pfeil", "§a1 Coin", ""));
                    p.setLevel(coins - 1);
                    p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1.0F, 1.0F);
                    e.getWhoClicked().sendMessage(Main.PREFIX + "Du hast §5Pfeil§f für 1 Coin gekauft");
                    ScoreboardUtil.updateAll();
                }

            }


        if (!e.getInventory().equals(inv)) {
            return;
        }
        else {
            e.setCancelled(true);
        }
        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
            return;
        }





    }
    @EventHandler
    public void onItemChange(PlayerInteractEvent e){
        if(e.getPlayer().getItemInHand().getType().equals(Material.NETHER_STAR)){

            createGui(9,"Shop");

            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_BAMBOO_HIT, 1.0F, 1.0F);
            openInventory(e.getPlayer());
            ScoreboardUtil.updateAll();
        }


    }
}