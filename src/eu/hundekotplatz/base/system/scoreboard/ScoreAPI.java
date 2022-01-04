package eu.hundekotplatz.base.system.scoreboard;

import java.util.List;

//import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
//import org.bukkit.ChatColor;
//import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import eu.hundekotplatz.base.main.Main;
import eu.hundekotplatz.base.minigames.player.Spieler;
import eu.hundekotplatz.base.minigames.ranks.RankHandler;

public class ScoreAPI {

	public static void setScoreboard(Player p) {
		Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = sb.getObjective("aaa");
		if (obj == null) {
			obj = sb.registerNewObjective("aaa", "bbb");
		}

		// Scoreboard
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("§8» §a§lHundekotplatz §7× §eb1.0");
		obj.getScore("§a").setScore(13);
		obj.getScore("§7Username").setScore(11);
		obj.getScore("§8» §a" + p.getName()).setScore(10);
		obj.getScore("§c").setScore(9);
		obj.getScore("§7Rang").setScore(8);
		obj.getScore("§r").setScore(6);
		obj.getScore("§7Kothaufen").setScore(5);
		obj.getScore("§e").setScore(3);
		obj.getScore("§7Spieler online").setScore(2);
		obj.getScore("§k").setScore(0);
		obj.getScore("§7Teamspeak:").setScore(-1);
		obj.getScore("§8» §9§lHundekotplatz.eu").setScore(-2);
		// ende Scoreboard

		Team online = getTeam(sb, "online", "");
		online.addEntry(ChatColor.AQUA + "" + ChatColor.RED);
		obj.getScore(ChatColor.AQUA + "" + ChatColor.RED).setScore(2);
		online.setPrefix("§a" + Bukkit.getOnlinePlayers().size() + "§8/§c20");

		Team kothaufen = getTeam(sb, "kothaufen", "");
		kothaufen.addEntry(ChatColor.BLACK + "" + ChatColor.BOLD);
		obj.getScore(ChatColor.BLACK + "" + ChatColor.BOLD).setScore(4);
		kothaufen.setPrefix("§8» §6" + Spieler.getSpieler(Main.instance.getSpieler(), p).getKotstand());

		Team rang = getTeam(sb, "rang", "");
		rang.addEntry(ChatColor.BLUE + "" + ChatColor.YELLOW);
		obj.getScore(ChatColor.BLUE + "" + ChatColor.YELLOW).setScore(7);
		rang.setPrefix("§8» §d" + getRang(p));

		p.setScoreboard(sb);
	}

	@SuppressWarnings("deprecation")
	public static void updateScoreboard(Player p) {
		if (p.getScoreboard() == null) {
			setScoreboard(p);
		}
		Scoreboard sb = p.getScoreboard();

		Team online = getTeam(sb, "online", "");
		online.setPrefix("§8» §a" + Bukkit.getOnlinePlayers().size() + "§8/§c20");

		Team kothaufen = getTeam(sb, "kothaufen", "");
		kothaufen.setPrefix("§8» §6" + Spieler.getSpieler(Main.instance.getSpieler(), p).getKotstand());

		Team rang = getTeam(sb, "rang", "");
		rang.setPrefix("§8» §d" + getRang(p));

		Team admin = getTeam(sb, "0Admin", "§cAdmin §8× §c");
		Team teambl = getTeam(sb, "1Team-BL", "§6Team-BL §8× §6");
		Team team = getTeam(sb, "2Team", "§6Team §8× §6");
		Team freund = getTeam(sb, "3Freund", "§dFreund §8× §d");
		Team spieler = getTeam(sb, "4Spieler", "§7Spieler §8× §7");

		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.hasPermission("weight.10")) {
				admin.addPlayer(player);
			} else if (player.hasPermission("weight.8")) {
				teambl.addPlayer(player);
			} else if (player.hasPermission("weight.7")) {
				team.addPlayer(player);
			} else if (player.hasPermission("weight.5")) {
				freund.addPlayer(player);
			} else {
				spieler.addPlayer(player);
			}
		}

	}

	public static void startScheduler() {
		new BukkitRunnable() {

			@Override
			public void run() {
				@SuppressWarnings("unchecked")
				List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
				for (Player on : players) {
					updateScoreboard(on);

				}
			}
		}.runTaskTimer(Main.instance, 20, 20);
	}

	public static Team getTeam(Scoreboard sb, String Team, String prefix) {
		Team team = sb.getTeam(Team);
		if (team == null) {
			team = sb.registerNewTeam(Team);
		}
		team.setPrefix(prefix);
		return team;
	}

	public static String getRang(Player player) {
		return RankHandler.getRank(Spieler.getSpieler(Main.instance.getSpieler(), player).getRank()).getBezeichnung();

	}

}
