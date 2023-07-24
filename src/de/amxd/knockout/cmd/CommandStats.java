package de.amxd.knockout.cmd;


import de.amxd.knockout.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

import static de.amxd.knockout.main.Main.MY_SQL;


public class CommandStats implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
        if(name.equals("stats")) {
            Player p = (Player)sender;
            if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage("Du musst ein Spieler sein.");
                return false;
            }
            if(args.length == 0) {
                ResultSet result = MY_SQL.query("SELECT kills,deaths,assists FROM `stats` WHERE uuid ="+"'"+p.getUniqueId()+"'");
                try {
                    if(result.next())
            {
                String kills = result.getString("kills");
                String deaths = result.getString("deaths");
                String assists = result.getString("assists");
                sender.sendMessage(Main.PREFIX+"Deine Stats: "+kills+" Kills, "+deaths+" Tode, "+assists+" Assists.");
            }
            else
            {
                sender.sendMessage(Main.PREFIX+"Keine Stats");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

            }
            if(args.length == 1) {

               ResultSet result = MY_SQL.query("SELECT kills,deaths,assists FROM `stats` WHERE username ="+"'"+args[0]+"'");
                try {
                 if(result.next())
                    {


                        String kills = result.getString("kills");
                        String deaths = result.getString("deaths");
                        String assists = result.getString("assists");
                        sender.sendMessage( Main.PREFIX+"Stats von §6"+args[0]+"§f: " +kills+" Kills, "+deaths+" Tode, "+assists+" Assists.");
                    }
                    else
                    {
                        sender.sendMessage(Main.PREFIX+"Keine Stats für "+args[0]);
                    }


                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


            }
            if(args.length > 1){
                p.sendMessage("Syntax: /stats <username>");
            }

        }

            return false;
    }

}
