package eu.hundekotplatz.base.system.joinsystem;

import org.bukkit.Bukkit;
//import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import eu.hundekotplatz.base.main.Main;
import eu.hundekotplatz.base.system.commands.WhitelistCMD;
import eu.hundekotplatz.base.system.scoreboard.ScoreAPI;

//import bingutdeutsch.PlayerSystem.Scoreboard_TabSystem.ScoreAPI;

public class JoinSystem implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();

		// Check whitelist
		if (WhitelistCMD.isWhitelistON) {
			if (p.hasPermission("system.whitelist.bypass") || Main.instance.getWhitelisted().contains(p.getDisplayName())) {
				p.sendMessage(Main.prefix + "§7Die Whitelist ist §aaktiviert§7, du konntest jedoch joinen");
			} else {
				p.sendMessage(Main.prefix + "⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊");
				p.sendMessage(Main.prefix + "Die Whitelist ist aktuell §aaktiviert");
				p.sendMessage(Main.prefix + "DC: §6https://discord.gg/3HReekrVB4");
				p.sendMessage(Main.prefix + "TS: §9§lHundekotplatz.eu");
				p.sendMessage(Main.prefix + "⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊");

				p.kickPlayer("");
				e.setJoinMessage("");
				for (Player on : Bukkit.getOnlinePlayers()) {
					on.sendMessage(Main.prefix + "§e" + p.getDisplayName() + " §7 hat gerade versucht zu joinen");
				}
				return;
			}
			
		}
		// Whitelist off / whitelisted and ready to join
		p.sendMessage(Main.prefix + "Herzlich Willkommen auf dem §a§lHundekotplatz§7!");
		p.sendMessage(Main.prefix + "Mit dem Befehl §7\"§c/help§7\"§7 siehst du eine");
		p.sendMessage(Main.prefix + "Übersicht der Befehle!");
		String joinMsg = "§8» %s §7ist dem Server beigetreten";
		String prefix;
		if (p.hasPermission("weight.10")) {
			prefix = "§cAdmin §8× §c";
		} else if (p.hasPermission("weight.8")) {
			prefix = "§6Team-BL §8× §6";
		}else if (p.hasPermission("weight.7")) {
			prefix = "§6Team §8× §6";
		}else if (p.hasPermission("weight.5")) {
			prefix = "§dFreund §8× §d";
		} else {
			prefix = "§7Spieler §8× §7";
		}
		prefix = prefix + p.getName();

		e.setJoinMessage(String.format(joinMsg, prefix));
		e.getPlayer().performCommand("spawn");
		ScoreAPI.setScoreboard(p);

	}

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		String prefix;
		String leaveMsg = "§7 hat den Server verlassen";
		if (player.hasPermission("weight.10")) {
			prefix = "§8» §cAdmin §8× §c";
		}else if (player.hasPermission("weight.8")) {
			prefix = "§8» §6Team-BL §8× §6";
		}else if (player.hasPermission("weight.7")) {
			prefix = "§8» §6Team §8× §6";
		} else if (player.hasPermission("weight.5")) {
			prefix = "§8» §dFreund §8× §d";
		} else {
			prefix = "§8» §7Spieler §8× §7";
		}
		e.setQuitMessage(prefix + player.getName() + leaveMsg);
	}
	
	@EventHandler
	public void onKick(PlayerKickEvent e) {
		if(e.getReason().equals("")) {
			e.setLeaveMessage("");
		}
	}

}
