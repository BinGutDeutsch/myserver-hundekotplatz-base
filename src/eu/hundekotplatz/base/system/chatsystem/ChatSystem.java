package eu.hundekotplatz.base.system.chatsystem;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatSystem implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		String msg = e.getMessage().replace("%", "%%");

		if (player.hasPermission("weight.10")) {
			e.setFormat("§cAdmin §8× §c" + player.getName() + "§8 » §7" + msg);
		} else if (player.hasPermission("weight.8")) {
			e.setFormat("§6Team-BL §8× §6" + player.getName() + "§8 » §7" + msg);
		} else if (player.hasPermission("weight.7")) {
			e.setFormat("§6Team §8× §6" + player.getName() + "§8 » §7" + msg);
		} else if (player.hasPermission("weight.5")) {
			e.setFormat("§dFreund §8× §d" + player.getName() + "§8 » §7" + msg);
		} else {
			e.setFormat("§7Spieler §8× §7" + player.getName() + "§8 » §7" + msg);
		}

	}
}
