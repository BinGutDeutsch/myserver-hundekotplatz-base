package eu.hundekotplatz.base.system.commands;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import eu.hundekotplatz.base.main.Main;

public class HelpCommand implements Listener {

	private String prefix = Main.prefix;

	@EventHandler
	public boolean onHelp(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();

		String helpmsg = prefix + "§eAllgemeine Befehle\n"+
				prefix + "§b/ts §8» §7Zeige den TeamSpeak\n"+
				prefix + "§b/dc §8» §7Zeige den Discord\n"+
				prefix + "§b/spawn §8» §7Teleportiere zum Spawn\n";
		
		if (e.getMessage().startsWith("/help") ||e.getMessage().startsWith("/?") || e.getMessage().startsWith("/bukkit:help") || e.getMessage().startsWith("/bukkit:?")) {
			e.setCancelled(true);
			if (hasPerm(p)) {
				String message = 
						prefix + "§eWichtige Team-befehle" + "\n" + 
						prefix + "§b/v §8» §7Setzt dich in den Vanishmodus" + "\n"+
						prefix + "§b/fly §8» §7Setzt dich in den Flugmodus \n"+ 
						prefix + "§b/gm §8» §7Setzt dich in einen beliebigen Spielmodus\n"+
						prefix + "§b/spy §8» §7Setzt dich in den Spionagemodus\n"+
						prefix + "§b/broadcast §8» §7Schickt eine Nachricht an den Server\n"+
						prefix + "§b/globalmute §8» §7Aktiviert den Globalmute\n";
				p.sendMessage(message);				
			} 
			p.sendMessage(helpmsg);
		}

		return false;
	}
	
	private boolean hasPerm(Player p) {
		if (p.hasPermission("weight.10") || p.hasPermission("weight.5")) {
			return true;
		}
		return false;
	}
	

}
