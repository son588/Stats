package me.block3.Stats.Events;

import me.block3.Stats.Base;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

/**
 * Created by Dominic on 1/25/2015.
 */
public class onPlayerFirstJoin {
    Base base = new Base();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerFirstJoin(PlayerJoinEvent e){
        UUID uuid = e.getPlayer().getUniqueId();
        String name = uuid.toString();

        if(!base.getPlayersFile().contains(name)){
            base.getPlayersFile().set(name + ".kills", 0);
            base.getPlayersFile().set(name + ".deaths", 0);
            base.getPlayersFile().set(name + ".placed", 0);
            base.getPlayersFile().set(name + ".breaked", 0);
            base.getPlayersFile().set(name + ".logged", 0);
            base.getPlayersFile().set(name + ".level", 0);
            base.savePlayersFile();
            base.reloadPlayersConfig();
        }else{
            String username = base.getPlayersFile().getString(name + ".username");
            String usernameloggedwith = e.getPlayer().getName().toString();

            if(username != usernameloggedwith){
                base.getPlayersFile().set(name + ".username", usernameloggedwith);
                base.savePlayersFile();
                base.reloadPlayersConfig();
            }
        }

        if(base.getPlayersFile().contains(name + ".logged")){
            int logged = base.getPlayersFile().getInt(name + ".logged");
            int loggedafter = logged + 1;
            base.getPlayersFile().set(name + ".logged", loggedafter);
            base.savePlayersFile();
            base.reloadPlayersConfig();
        }


    }
}
