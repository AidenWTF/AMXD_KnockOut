package de.amxd.knockout.libs;



import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemUtil {

    public static ItemStack createItem(Material material, String name, int data, int amount) {
        ItemStack i = new ItemStack(material, amount, (short)data);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(name);
        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack createItem(Material material, String name, int data, String[] lore, int amount) {
        ItemStack i = new ItemStack(material, amount, (short)data);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack createItem(Material material, String name, int data, Enchantment enchantment, int level,int amount) {
        ItemStack i = new ItemStack(material, amount, (short)data);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(name);
        meta.addEnchant(enchantment,level,false);
        i.setItemMeta(meta);
        return i;
    }

}
