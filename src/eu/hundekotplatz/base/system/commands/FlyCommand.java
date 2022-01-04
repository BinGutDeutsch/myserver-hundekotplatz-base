package eu.hundekotplatz.base.system.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.hundekotplatz.base.main.Main;


public class FlyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("system.fly")) {
				if (args.length ==0) {
					if (p.getAllowFlight()) {
						p.setAllowFlight(false);
						p.sendMessage(Main.prefix+"§cDu kannst nun nicht mehr fliegen!");
					}
					else {
						p.setAllowFlight(true);
						p.sendMessage(Main.prefix+"§aDu kannst nun fliegen!");
					}
				}
				if (args.length ==1) {
					Player other= Bukkit.getPlayer(args[0]);
					if (other == null) {
						p.sendMessage(Main.prefix+"§7Dieser Spieler wurde nicht gefunden");
					}
					else {
						if (other.getAllowFlight()) {
							other.setAllowFlight(false);
							other.sendMessage(Main.prefix+"§cDu kannst nun nicht mehr fliegen!");
							p.sendMessage(Main.prefix+"§c"+ args[0]+ " kann nun nicht mehr fliegen!");
						}
						else {
							other.setAllowFlight(true);
							other.sendMessage(Main.prefix+"§aDu kannst nun fliegen!");
							p.sendMessage(Main.prefix+"§a"+ args[0]+ " kann nun fliegen!");
						}
					}
				}
			}
			else {
				p.sendMessage(Main.prefix+"§7Du hast §ckeine §7Berechtigung für diesen Befehl");
			}
		}
		return false;
	}

}
