package eu.hundekotplatz.base.minigames.player.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.hundekotplatz.base.main.Main;
import eu.hundekotplatz.base.minigames.player.Spieler;

public class KotCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {
				Spieler sp = Spieler.getSpieler(Main.instance.getSpieler(), p);
				if (sp == null) {
					p.sendMessage(Main.prefix + "Es ist ein Fehler aufgetreten, bitte kontaktiere einen Admin");
					p.sendMessage(Main.prefix + "§cCode: 1001");
					return false;
				}
				p.sendMessage(Main.prefix + "§6" + sp.getKotstand() + " Kothaufen");
				return true;
			}
			else if(args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if(target == null) {
					p.sendMessage(Main.prefix + "§e" + args[0] + " §7ist nicht online");
					return false;
				}
				Spieler targetSp = Spieler.getSpieler(Main.instance.getSpieler(), target);
				p.sendMessage(Main.prefix+"§e"+args[0]+ " §7hat §6"+targetSp.getKotstand()+ " Kothaufen");
			}
			else if (args.length == 3) {
				if (!p.hasPermission("system.player.kot.modify")) {
					return false;
				}
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					p.sendMessage(Main.prefix + "§e" + args[0] + " §7ist nicht online");
					return false;
				}
				Spieler targetSp = Spieler.getSpieler(Main.instance.getSpieler(), target);
				int kothaufen;
				try {
					kothaufen = Integer.parseInt(args[2]);
				} catch (NumberFormatException e) {
					p.sendMessage(Main.prefix+"Falsche Kothaufen angegeben");
					e.printStackTrace();
					return false;
				}

				switch (args[1]) {
				case "add":
					targetSp.addKotstand(kothaufen);
					p.sendMessage(Main.prefix+"Kothaufen hinzugefügt");
					target.sendMessage(Main.prefix+"Dir wurden §6"+kothaufen+" Kothaufen §7hinzugefügt");
					break;
				case "remove":
					targetSp.setKotstand(targetSp.getKotstand()-kothaufen);
					p.sendMessage(Main.prefix+"Kothaufen entfernt");
					target.sendMessage(Main.prefix+"Dir wurden §6"+kothaufen+" Kothaufen §7entfernt");
					break;
				case "set":
					targetSp.setKotstand(kothaufen);
					p.sendMessage(Main.prefix+"Kothaufen gesetzt");
					target.sendMessage(Main.prefix+"Deine Kothaufen wurden auf §6"+kothaufen+" Kothaufen §7gesetzt");
					break;
				default:
					p.sendMessage(Main.prefix+"Modify parameter falsch");
					break;
				}
				
				return true;
			}

		}
		return false;
	}

}
