package de.amxd.knockout.libs;

import de.amxd.knockout.main.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Spawn {
    public static boolean isPlayerInSpawn(Player player) {
        FileConfiguration config = Main.pl.getConfig();
        return player.getLocation().getY() >= config.getDouble("safezoneHeight");
    }

}
