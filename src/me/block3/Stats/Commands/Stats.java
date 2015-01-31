package me.block3.Stats.Commands;

import me.block3.Stats.API.GetStats;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Dominic on 1/25/2015.
 */
public class Stats implements CommandExecutor{
    GetStats getStats = new GetStats();

    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args){
        Player player = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("stats") && player.hasPermission("stats.view")){
            if(args.length >= 1){

            }
            getStats.GetStats(((Player) sender).getDisplayName());
        }
        return false;
    }
}
