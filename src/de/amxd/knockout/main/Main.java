package de.amxd.knockout.main;


import de.amxd.knockout.cmd.CommandStats;
import de.amxd.knockout.events.*;
import de.amxd.knockout.libs.Deathzone;
import de.amxd.knockout.libs.InventoryHelper;
import de.amxd.knockout.libs.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Main extends JavaPlugin {
    public static Plugin pl;

    public static String PREFIX = Lang.PREFIX;

    public static final MySQL MY_SQL = new MySQL("45.131.66.248","knockout","knockout","ZFd6AGtAabvi");



    @Override
    public void onEnable() {
        pl = this;
        this.getLogger().log(Level.INFO, " _____ _____ __ __ ____  ");
        this.getLogger().log(Level.INFO,"|  _  |     |  |  |    \\ " + "   "+getDescription().getName() + " v" + getDescription().getVersion());
        this.getLogger().log(Level.INFO,"|     | | | |-   -|  |  |" + "   "+"Running on "+ Bukkit.getVersion());
        this.getLogger().log(Level.INFO,"|__|__|_|_|_|__|__|____/ ");

        FileConfiguration config = this.getConfig();
        config.addDefault("safezoneHeight", 98.0d);
        config.addDefault("deathHeight", 80.0d);
        config.addDefault("spawnX", 0.0d);
        config.addDefault("spawnY", 0.0d);
        config.addDefault("spawnZ", 0.0d);



        config.options().copyDefaults(true);
        saveConfig();

        MY_SQL.connect();
        MY_SQL.update("CREATE TABLE IF NOT EXISTS stats (uuid CHAR(36), username TEXT, kills INT, deaths INT, assists INT,PRIMARY KEY (uuid))");








         getServer().getPluginManager().registerEvents(new EventPlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new EventDrop(), this);
        getServer().getPluginManager().registerEvents(new EventFood(), this);
        getServer().getPluginManager().registerEvents(new EventFight(), this);
        //getServer().getPluginManager().registerEvents(new EventProjectileHit(), this);
        getServer().getPluginManager().registerEvents(new Deathzone(), this);
        getServer().getPluginManager().registerEvents(new InventoryHelper(), this);
        getServer().getPluginManager().registerEvents(new EventBlockBreak(), this);
        getServer().getPluginManager().registerEvents(new EventBlockPlace(), this);
        getServer().getPluginManager().registerEvents(new EventDoubleJump(), this);
        getServer().getPluginManager().registerEvents(new EventGrapple(), this);
        //getServer().getPluginManager().registerEvents(new ShopOld(), this);
        Bukkit.getPluginCommand("stats").setExecutor(new CommandStats());

        for(World world : Bukkit.getWorlds())
        {
            world.setGameRuleValue("doMobSpawning", "false");
            world.setGameRuleValue("doDaylightCycle", "false");
        }
    }

    @Override
    public void onDisable() {

    }

}
