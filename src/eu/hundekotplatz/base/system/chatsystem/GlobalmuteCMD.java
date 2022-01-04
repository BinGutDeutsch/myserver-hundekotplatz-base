package eu.hundekotplatz.base.system.chatsystem;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.hundekotplatz.base.main.Main;


public class GlobalmuteCMD implements CommandExecutor {

	public static boolean globalmute = false;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("system.globalmute")) {
				if (globalmute) {
					globalmute = false;
					Bukkit.getServer()
							.broadcastMessage(Main.prefix+"-------------------------------");
					Bukkit.getServer().broadcastMessage(
							Main.prefix+"§7Globalmute §adeaktiviert");
					Bukkit.getServer()
							.broadcastMessage(Main.prefix+"---------------------------------");
				} else {
					globalmute = true;
					for (int i = 0; i < 105; i++) {
						for (Player on : Bukkit.getOnlinePlayers()) {
							if (!on.hasPermission("system.globalmute")) {
								on.sendMessage("");
							}
						}

					}
					Bukkit.getServer()
					.broadcastMessage(Main.prefix+"§c-------------------------------");
					Bukkit.getServer().broadcastMessage(
							Main.prefix+"§7Globalmute §caktiviert");
					Bukkit.getServer()
					.broadcastMessage(Main.prefix+"§c-------------------------------");
				}
			} else {
				p.sendMessage(Main.prefix+"§7Du hast §ckeine §7Berechtigung für diesen Befehl");
			}
		}

		return false;
	}

	public static Boolean getGlobalmute() {
		return globalmute;
	}

}
