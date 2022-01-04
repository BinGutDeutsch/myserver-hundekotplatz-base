package eu.hundekotplatz.base.system.chatsystem;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import eu.hundekotplatz.base.main.Main;

public class GlobalmuteSystem implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if (GlobalmuteCMD.getGlobalmute()) {
			if (!e.getPlayer().hasPermission("system.globalmute.bypass")) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(Main.prefix+"§cGlobalmute ist aktiviert!");
			}
		}
	}
	
}
