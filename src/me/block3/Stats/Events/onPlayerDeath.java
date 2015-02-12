package me.block3.Stats.Events;

/**
 * Created by Dominic on 1/25/2015.
 */
public class onPlayerDeath {
  Base base = new Base();
  
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onPlayerDeath(PlayerDeathEvent e){
    UUID uuid = e.getPlayer().getUniqueId();
    String name = uuid.toString();
    
    if(base.getPlayersFile().contains(name)){
      int deathcount = base.getPlayersFile().getInt(name + ".deaths");
      /*deathcount++;*/
      base.getPlayersFile().set(name + ".deaths", deathcount++);
      base.savePlayersFile();
      base.reloadPlayersConfig();
    }
  }
}
