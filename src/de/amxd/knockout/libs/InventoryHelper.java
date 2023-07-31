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

import static de.amxd.knockout.main.Lang.*;
import static org.bukkit.Material.BLACK_STAINED_GLASS_PANE;

public class InventoryHelper implements Listener {
    private Inventory inv;


    public void createGui(int size, String title) {
        inv = Bukkit.createInventory(null, size, title);

        initializeItems();
    }

    public void initializeItems() {


        inv.addItem(createGuiItem(Material.SANDSTONE,NAME_BLOCKS, PRICE_BLOCKS, LORE_BLOCKS));
        inv.addItem(createGuiItem(Material.FISHING_ROD, NAME_GRA, PRICE_GRA, LORE_GRA));
        inv.addItem(createGuiItem(Material.BOW, NAME_BOW, PRICE_BOW, LORE_BOW));
        inv.addItem(createGuiItem(Material.ARROW, NAME_ARROW, PRICE_ARROW, LORE_ARROW));
        inv.addItem(createGuiItem(Material.FEATHER,NAME_DJ, PRICE_DJ,LORE_DJ ));

    }

    protected ItemStack createGuiItem(final Material material, final String name, final String...lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    public void openInventory(final HumanEntity ent) {

        Player p = Bukkit.getPlayer(ent.getName());
        if (p.getAllowFlight() == true) // checks if Doublejump already bought
        {
            inv.setItem(4, ItemUtil.createItem(BLACK_STAINED_GLASS_PANE, OWNED_NO_PREFIX, 0, 1));

        }
        ent.openInventory(inv);
    }

    @EventHandler

    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        int coins = p.getLevel();

        if (e.getRawSlot() == 0) {
            if (coins < 1) {
                e.setCancelled(true);
                e.getWhoClicked().sendMessage(NOT_ENOUGH_COINS);
                p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1.0F, 1.0F);
            } else {
                e.setCancelled(true);
                p.getInventory().addItem(createGuiItem(Material.SANDSTONE, "§bBlocks"));

                p.setLevel(coins - 1);
                p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1.0F, 1.0F);
                e.getWhoClicked().sendMessage(BOUGHT("Blocks","1 Coin"));
                ScoreboardUtil.updateAll();
            }

        }
        if (e.getRawSlot() == 1) {
            if (coins < 10) {
                e.setCancelled(true);
                e.getWhoClicked().sendMessage(NOT_ENOUGH_COINS);
                p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1.0F, 1.0F);
            } else {
                e.setCancelled(true);
                p.getInventory().addItem(createGuiItem(Material.FISHING_ROD, NAME_GRA, LORE_GRA));
                p.setLevel(coins - 10);
                p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1.0F, 1.0F);
                e.getWhoClicked().sendMessage(BOUGHT("Grappling Hook","10 Coins"));
                ScoreboardUtil.updateAll();
            }

        }
        if (e.getRawSlot() == 2) {
            if (coins < 20) {
                e.setCancelled(true);
                e.getWhoClicked().sendMessage(NOT_ENOUGH_COINS);
                p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1.0F, 1.0F);
            } else {
                e.setCancelled(true);
                p.getInventory().addItem(createGuiItem(Material.BOW, NAME_BOW, LORE_BOW));
                p.setLevel(coins - 20);
                p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1.0F, 1.0F);
                e.getWhoClicked().sendMessage(BOUGHT("Bow","20 Coins"));
                ScoreboardUtil.updateAll();
            }

        }
        if (e.getRawSlot() == 3) {
            if (coins < 1) {
                e.setCancelled(true);
                e.getWhoClicked().sendMessage(NOT_ENOUGH_COINS);
                p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1.0F, 1.0F);
            } else {
                e.setCancelled(true);
                p.getInventory().addItem(createGuiItem(Material.ARROW, NAME_ARROW, LORE_ARROW));
                p.setLevel(coins - 1);
                p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1.0F, 1.0F);
                e.getWhoClicked().sendMessage(BOUGHT("Arrow","1 Coin"));
                ScoreboardUtil.updateAll();
            }
        }
        if (e.getRawSlot() == 4) { // DoubleJump

            if (coins < 15) {
                e.setCancelled(true);
                e.getWhoClicked().sendMessage(NOT_ENOUGH_COINS);

                p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1.0F, 1.0F);

            } else if (e.getCurrentItem().getType().equals(BLACK_STAINED_GLASS_PANE)) {
                e.setCancelled(true);
                e.getWhoClicked().sendMessage(OWNED);

                p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_BREAK, 1.0F, 1.0F);
            } else {
                e.setCancelled(true);
                //
                p.setAllowFlight(true);
                p.setFlying(false);
                inv.setItem(4, ItemUtil.createItem(BLACK_STAINED_GLASS_PANE, OWNED_NO_PREFIX, 0, 1));
                p.setLevel(coins - 15);
                p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 1.0F, 1.0F);
                e.getWhoClicked().sendMessage(BOUGHT("DoubleJump","15 Coins"));
                ScoreboardUtil.updateAll();
            }

            if (!e.getInventory().equals(inv)) {
                return;
            } else {
                e.setCancelled(true);
            }
            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
        }

    }
    @EventHandler
    public void onItemChange(PlayerInteractEvent e) {
        if (e.getPlayer().getItemInHand().getType().equals(Material.NETHER_STAR)) {

            createGui(9, "Shop");

            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_BAMBOO_HIT, 1.0F, 1.0F);
            openInventory(e.getPlayer());
            ScoreboardUtil.updateAll();
        }

    }
}