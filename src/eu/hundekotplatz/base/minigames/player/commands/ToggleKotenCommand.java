package eu.hundekotplatz.base.minigames.player.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.hundekotplatz.base.main.Main;
import eu.hundekotplatz.base.minigames.player.Spieler;

public class ToggleKotenCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {
				if (Spieler.getSpieler(Main.instance.getSpieler(), p).isKotenEnabled()) {
					Spieler.getSpieler(Main.instance.getSpieler(), p).setKotenEnabled(false);
					p.sendMessage(Main.prefix + "Koten wurde §cdeaktiviert");
				} else {
					Spieler.getSpieler(Main.instance.getSpieler(), p).setKotenEnabled(true);
					p.sendMessage(Main.prefix + "Koten wurde §aaktiviert");
				}
				return true;
			} else if (args.length == 1) {
				if (!p.hasPermission("system.player.koten.others")) {
					return false;
				}
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					p.sendMessage(Main.prefix + "§e" + args[0] + " §7wurde nicht gefunden");
					return false;
				}
				if (Spieler.getSpieler(Main.instance.getSpieler(), target).isKotenEnabled()) {
					Spieler.getSpieler(Main.instance.getSpieler(), target).setKotenEnabled(false);
					p.sendMessage(Main.prefix + "Koten von §e" + args[0] + " §7wurde §cdeaktiviert");
					target.sendMessage(Main.prefix + "Koten wurde §cdeaktiviert");
				} else {
					Spieler.getSpieler(Main.instance.getSpieler(), target).setKotenEnabled(true);
					p.sendMessage(Main.prefix + "Koten von §e" + args[0] + " §7wurde §aaktiviert");
					target.sendMessage(Main.prefix + "Koten wurde §aaktiviert");
				}

				return true;
			}
		}
		return false;
	}

}
