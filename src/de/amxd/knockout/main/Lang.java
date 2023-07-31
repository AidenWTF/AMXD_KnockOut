package de.amxd.knockout.main;

public class Lang {
    public static String PREFIX =  "[§3AMXD§f] ";
    public static String NOT_ENOUGH_COINS = PREFIX + "Not enough coins";

    public static String BOUGHT(String item,String price){
        return PREFIX + "You bought §5 "+ item +" §f for " + price;
    }
    public static String OWNED = PREFIX + "You already own this";
    public static String OWNED_NO_PREFIX = "You already own this";

/*
* ITEM NAMES AND DESCRIPTIONS USED IN InventoryHelper.java
*/
    public static String NAME_BOW = "§5Bow";
    public static String PRICE_BOW = "§a20 Coins";
    public static String LORE_BOW = "§bShoot em all";

    public static String NAME_BLOCKS = "§bBlocks";
    public static String PRICE_BLOCKS = "§a1 Coin";
    public static String LORE_BLOCKS = "§bAscend";

    public static String NAME_ARROW = "§5Arrow";
    public static String PRICE_ARROW = "§a1 Coin";
    public static String LORE_ARROW = "";

    public static String NAME_DJ = "§5DoubleJump";
    public static String PRICE_DJ = "§a15 Coins";
    public static String LORE_DJ = "Keep it until you leave";

    public static String NAME_GRA = "§6Grappling Hook";
    public static String PRICE_GRA = "§a10 Coins";
    public static String LORE_GRA = "§bFly like a bird";








}

