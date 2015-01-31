package me.block3.Stats.API;

import me.block3.Stats.Base;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

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

    public void GetStats(String s){


        if(base.getPlayersFile().contains(s)){
            try{
                sender.sendMessage(ChatColor.GREEN + s + " stats:");
                sender.sendMessage(ChatColor.GOLD + "Kills: " + ChatColor.BLUE + kills);
                sender.sendMessage(ChatColor.GOLD + "Deaths: " + ChatColor.BLUE + deaths);
                sender.sendMessage(ChatColor.GOLD + "Placed Blocks: " + ChatColor.BLUE + placed);
                sender.sendMessage(ChatColor.GOLD + "Destroyed Blocks: " + ChatColor.BLUE + breaked);
                sender.sendMessage(ChatColor.GOLD + "Time logged on: " + ChatColor.BLUE + logged);
                sender.sendMessage(ChatColor.GOLD + "Level: " + ChatColor.BLUE + level);
            }catch(NullPointerException ex){
                sender.sendMessage(ChatColor.RED + "Player " + ChatColor.DARK_RED + s + ChatColor.RED + " does not exist!");
            }

        }

    }
}
