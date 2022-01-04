package eu.hundekotplatz.base.minigames.player;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import eu.hundekotplatz.base.main.Main;

public class JoinListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		for(Spieler sp: Main.instance.getSpieler()) {
			
			if(Bukkit.getOfflinePlayer(sp.getPlayer()).getUniqueId().equals(e.getPlayer().getUniqueId())) {
				return;
			}else {
				continue;
			}
		}
		Main.instance.getSpieler().add(new Spieler(e.getPlayer().getDisplayName()));
	}
	


}
