package eu.hundekotplatz.base.system.spysystem;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import eu.hundekotplatz.base.main.Main;


public class SpyAPI implements Listener {

	public static String prefix = Main.prefix;

	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (e.getMessage().startsWith("/")) {
			if (!p.hasPermission("system.spy.bypass")) {
				for (Player on : Bukkit.getOnlinePlayers()) {
					if (on.hasPermission("system.spy.watch")) {
						if (SpyCommand.getAgenten().contains(on)) {
							on.sendMessage(prefix + "§e" + p.getName() + "§7 hat gerade den Befehl §c" + e.getMessage()
									+ " §7ausgeführt.");
						}
					}
				}
			}
		}

	}

}
