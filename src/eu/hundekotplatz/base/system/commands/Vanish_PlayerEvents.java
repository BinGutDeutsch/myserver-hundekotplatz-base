package eu.hundekotplatz.base.system.commands;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Vanish_PlayerEvents implements Listener{

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		for(Player vanish: VanishCommand.vanished) {
			if (!e.getPlayer().hasPermission("system.vanish.bypass")) {
				e.getPlayer().hidePlayer(vanish);
			}
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if (VanishCommand.vanished.contains(e.getPlayer())) {
			VanishCommand.vanished.remove(e.getPlayer());
		}
	}
	
}
