package de.amxd.knockout.libs;





import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.sql.ResultSet;
import java.sql.SQLException;

import static de.amxd.knockout.main.Main.MY_SQL;


public class ScoreboardUtil {

        public static void updateAll() {
            for(Player p : Bukkit.getOnlinePlayers()) {

                showScoreboard(p);
            }
        }

        public static void showScoreboard(Player player) {
            ResultSet result = MY_SQL.query("SELECT kills,deaths,assists FROM `stats` WHERE uuid ="+"'"+player.getUniqueId()+"'");
            try {
                if(result.next())
                {
                    String kills = result.getString("kills");
                    String deaths = result.getString("deaths");
                    String assists = result.getString("assists");

                    int coins = player.getLevel();
                    if(coins < 0){
                        coins = 0;
                        player.setLevel(0);
                    }

                    Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
                    Objective objective = board.registerNewObjective("aaa", "bbb");
                    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                    objective.setDisplayName("§3AMXD.DE");
                    objective.getScore("§8-= §6KnockOut §8=-").setScore(14);
                    objective.getScore("  §a ").setScore(13);
                    objective.getScore("§eCoins:").setScore(12);
                    objective.getScore("   §6"+coins).setScore(11);
                    objective.getScore(" ").setScore(10);
                    objective.getScore("§9Kills:").setScore(9);
                    objective.getScore("  §b"+kills).setScore(8);
                    objective.getScore("  ").setScore(7);
                    objective.getScore("§2Tode:").setScore(6);
                    objective.getScore("  §a" +deaths).setScore(5);
                    objective.getScore("   ").setScore(4);
                    objective.getScore("§5Assists:").setScore(3);
                    objective.getScore("  §d" +assists).setScore(2);
                    objective.getScore("    ").setScore(1);
                    objective.getScore("§8-= §6KnockOut §8=- ").setScore(0);
                    player.setScoreboard(board);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

    }
