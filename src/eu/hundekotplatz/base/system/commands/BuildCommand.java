package eu.hundekotplatz.base.system.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.hundekotplatz.base.main.Main;

public class BuildCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("system.build")) {
				if (args.length == 0) {
					if (Main.instance.getBuildMode().contains(p)) {
						Main.instance.getBuildMode().remove(p);
						p.sendMessage(Main.prefix+"§cDu bist nun nicht mehr im Buildmodus");
					}
					else {
						Main.instance.getBuildMode().add(p);
						p.sendMessage(Main.prefix+"§aDu bist nun im Buildmodus");
					}
				} else if (args.length == 1) {
					if (p.hasPermission("system.build.others")) {
						Player target = Bukkit.getPlayer(args[0]);
						if (target !=null) {
							if (Main.instance.getBuildMode().contains(target)) {
								Main.instance.getBuildMode().remove(target);
								target.sendMessage(Main.prefix+"§cDu bist nun nicht mehr im Buildmodus");
								p.sendMessage(Main.prefix+"§e"+args[0]+" befindet sich nun nicht mehr im Buildmodus");
							}
							else {
								Main.instance.getBuildMode().add(target);
								target.sendMessage(Main.prefix+"§aDu bist nun im Buildmodus");
								p.sendMessage(Main.prefix+"§e"+args[0]+" befindet sich nun im Buildmodus");
							}
						}
						else {
							p.sendMessage(Main.prefix+"Dieser Spieler wurde nicht gefunden");
						}
					} else {
						p.sendMessage(Main.prefix+"Du hast keine Berechtigung um jemanden in den Buildmodus zu setzen");
					}
					
				} else {
					p.sendMessage(Main.prefix+"Bitte benutze '/build'");
				}
			} else {
				p.sendMessage(Main.prefix+"§7Du hast §ckeine §7Berechtigung für diesen Befehl");
			}

		}
		return false;
	}

}
