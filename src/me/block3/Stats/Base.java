package me.block3.Stats;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by Dominic on 1/24/2015.
 */
public class Base extends JavaPlugin{
    Plugin plugin;

    File configFile = new File(this.getDataFolder(), "config.yml");
    FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    File playersFile = new File(this.getDataFolder(), "players.yml");
    FileConfiguration players = YamlConfiguration.loadConfiguration(playersFile);

    public void onEnable(){
        plugin = this;

        this.config.options().copyDefaults(true);
        this.config.addDefault("Enabled", true);
        this.saveCustomConfig();

        this.players.options().copyDefaults(true);
        this.players.addDefault("Enabled", true);
        this.savePlayersFile();

        this.getLogger().log(Level.FINE, "[Block3] Stats Hooked");

    }

    public void onDisable(){
        plugin = null;
    }

    public void saveCustomConfig() {
        try{
            config.save(configFile);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void savePlayersFile() {
        try{
            players.save(playersFile);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public FileConfiguration getCustomConfig(){
        if(config == null){
            saveCustomConfig();
        }
        return config;
    }

    public FileConfiguration getPlayersFile(){
        if(players == null){
            savePlayersFile();
        }
        return players;
    }

    public void reloadCustomConfig(){
        if(configFile == null){
            configFile = new File(this.getDataFolder(), "config.yml");
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void reloadPlayersConfig(){
        if(configFile == null){
            configFile = new File(this.getDataFolder(), "config.yml");
        }
        players = YamlConfiguration.loadConfiguration(playersFile);
    }
}
