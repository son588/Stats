package me.block3.Stats.Commands;

import me.block3.Stats.API.GetStats;
import me.block3.Stats.Base;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by Dominic on 1/25/2015.
 */
public class Stats implements CommandExecutor{
    GetStats getStats = new GetStats();
    Base base = new Base();

    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args){
        Player player = (Player) sender;

        ChatColor gold = ChatColor.GOLD;
        ChatColor green = ChatColor.GREEN;
        ChatColor blue = ChatColor.BLUE;

        if(cmd.getName().equalsIgnoreCase("stats") && player.hasPermission("stats.view")){
            if(sender instanceof Player){
                if(args.length >= 1){
                    if(args[0].equalsIgnoreCase("admin") && player.hasPermission("stats.admin")){
                        if(args[1].equalsIgnoreCase("view")){
                            if(args[2].isEmpty()){
                                sender.sendMessage(ChatColor.RED + "Usage: /stats admin view {username}");
                            }else{
                                getStats.GetStats(((Player) sender).getDisplayName());
                                for(String lists : getStats.list){
                                    ChatColor.translateAlternateColorCodes('&', lists);
                                    sender.sendMessage(lists);
                                }
                            }
                        }else if(args[1].equalsIgnoreCase("set")){
                            if(args[2].isEmpty()){
                                sender.sendMessage(gold + "Usage:");
                                sender.sendMessage(green + "/stats admin set {username} {stat} {value}");
                            }else {
                                if(args[3].isEmpty()){
                                    sender.sendMessage(gold + "Usage:");
                                    sender.sendMessage(green + "/stats admin set {username} {stat} {value}");
                                }else{
                                    UUID uuid;
                                    Player target = Bukkit.getPlayer(args[3]);
                                    if(target.isOnline()){
                                        uuid = player.getUniqueId();
                                    }else{
                                        uuid = Bukkit.getServer().getOfflinePlayer(s).getUniqueId();
                                    }
                                    String uuids = uuid.toString();


                                    if(args[4].equalsIgnoreCase("kills")){
                                        if(args[5].isEmpty()){
                                            sender.sendMessage(gold + "Usage:");
                                            sender.sendMessage(green + "/stats admin set {username} {stat} {value}");
                                        }else{
                                            try{
                                                int value = Integer.parseInt(args[5]);

                                                if(base.getPlayersFile().contains(uuids)){
                                                    base.getPlayersFile().set(uuids + ".kills", value);
                                                    base.savePlayersFile();
                                                    base.reloadPlayersConfig();

                                                    sender.sendMessage(gold + "User " + blue + args[3] + gold + " has their stat " + green + "'kills'" + gold + ", set to " + green + value + gold + "!");
                                                }else{
                                                    sender.sendMessage(ChatColor.RED + "Player " + blue + args[3] + ChatColor.RED + " does not exist!");
                                                }
                                            }catch(NumberFormatException e){
                                                sender.sendMessage(ChatColor.RED + "The {value} has to be a number!");
                                            }
                                        }
                                    }
                                }
                            }

                        }else if(args[1].equalsIgnoreCase("help")){
                            sender.sendMessage(gold + "Stats Usage Commands:");
                            sender.sendMessage(green + "/stats admin view {username}" + blue + " View another players stats.");
                            sender.sendMessage(green + "/stats admin set {username} {stat}" + blue + " Set another players stats level.");
                        }
                    }
                }
                getStats.GetStats(((Player) sender).getDisplayName());
                for(String lists : getStats.list){
                    ChatColor.translateAlternateColorCodes('&', lists);
                    sender.sendMessage(lists);
                }
            }else{
                sender.sendMessage(ChatColor.RED + "You have to be a player to execute this command.");
            }

        }
        return false;
    }
}
