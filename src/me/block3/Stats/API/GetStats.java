package me.block3.Stats.API;

import me.block3.Stats.Base;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.*;

/**
 * Created by Dominic on 1/25/2015.
 */
public class GetStats {
    Base base = new Base();
    CommandSender sender;

    public int kills;
    public int deaths;
    public int placed;
    public int breaked;
    public int logged;
    public int level;

    public List<String> list = new ArrayList<String>();

    public void GetStats(String s){
        if(base.getPlayersFile().contains(s)){
            try{
                /*sender.sendMessage(ChatColor.GREEN + s + " stats:");
                sender.sendMessage(ChatColor.GOLD + "Kills: " + ChatColor.BLUE + kills);
                sender.sendMessage(ChatColor.GOLD + "Deaths: " + ChatColor.BLUE + deaths);
                sender.sendMessage(ChatColor.GOLD + "Placed Blocks: " + ChatColor.BLUE + placed);
                sender.sendMessage(ChatColor.GOLD + "Destroyed Blocks: " + ChatColor.BLUE + breaked);
                sender.sendMessage(ChatColor.GOLD + "Time logged on: " + ChatColor.BLUE + logged);
                sender.sendMessage(ChatColor.GOLD + "Level: " + ChatColor.BLUE + level);
                */

                UUID uuid;
                Player player = Bukkit.getPlayer(s);
                if(player.isOnline()){
                    uuid = player.getUniqueId();
                }else{
                    uuid = Bukkit.getServer().getOfflinePlayer(s).getUniqueId();
                }

                String uuids = uuid.toString();

                String username = base.getPlayersFile().getString(uuids + ".username");
                

                kills = base.getPlayersFile().getInt(uuid + ".kills");
                deaths = base.getPlayersFile().getInt(uuid + ".deaths");
                placed = base.getPlayersFile().getInt(uuid + ".placed");
                breaked = base.getPlayersFile().getInt(uuid + ".breaked");
                logged = base.getPlayersFile().getInt(uuid + ".logged");
                level = base.getPlayersFile().getInt(uuid + ".level");


                list.add("&6Kills: &9" + kills);
                list.add("&6Deaths: &9" + deaths);
                list.add("&6Placed Blocks: &9" + placed);
                list.add("&6Destroyed Blocks: &9" + breaked);
                list.add("&6Times Logged On: &9" + logged);
                list.add("&6Level: &9" + level);
            }catch(NullPointerException ex){
                sender.sendMessage(ChatColor.RED + "Player " + ChatColor.DARK_RED + s + ChatColor.RED + " does not exist!");
            }

        }
    }
}
