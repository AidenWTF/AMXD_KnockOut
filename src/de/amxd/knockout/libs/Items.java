package de.amxd.knockout.libs;



import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class Items {
    public static void stick(Player e){
        e.getInventory().setItem(0, ItemUtil.createItem(Material.STICK,"§6Heiliger Stab",0, Enchantment.KNOCKBACK,1,1));

    }
    public static void block(Player e){
        e.getInventory().setItem(1,ItemUtil.createItem(Material.SANDSTONE,"§7Block",0,5));
    }
    public static void shop(Player e){
        e.getInventory().setItem(8,ItemUtil.createItem(Material.NETHER_STAR,"§6Shop",0,1));

    }

}
