package eu.hundekotplatz.base.system.spysystem;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.hundekotplatz.base.main.Main;


public class SpyCommand implements CommandExecutor {

	public static List<Player> agenten = new ArrayList<>();


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("system.spy")) {
				if (args.length == 0) {
					if (agenten.contains(p)) {
						agenten.remove(p);
						p.sendMessage(Main.prefix+"§cDu bist nun nicht mehr im Spionagemodus");
					}
					else {
						agenten.add(p);
						p.sendMessage(Main.prefix+"§aDu bist nun im Spionagemodus");
					}
				} else {
					p.sendMessage(Main.prefix+"§7Bitte benutze '/spy'");
				}
			} else {
				p.sendMessage(Main.prefix+"§7Du hast §ckeine §7Berechtigung für diesen Befehl");
			}

		}
		return false;
	}
	

	public static List<Player> getAgenten() {
		return agenten;
	}

}
