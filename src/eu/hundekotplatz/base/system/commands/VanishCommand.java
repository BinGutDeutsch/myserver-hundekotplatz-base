package eu.hundekotplatz.base.system.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.hundekotplatz.base.main.Main;


public class VanishCommand implements CommandExecutor {

	public static List<Player> vanished = new ArrayList<>();
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
			Player p= (Player) sender;
			if (p.hasPermission("system.vanish")) {
				if (args.length==0) {
					if (vanished.contains(p)) {
						vanished.remove(p);
						for (Player on: Bukkit.getOnlinePlayers()) {
							on.showPlayer(p);
							
						}
						p.sendMessage(Main.prefix+"§cDu bist nun nicht mehr im Vanish!");
					}
					else {
						vanished.add(p);
						for (Player on: Bukkit.getOnlinePlayers()) {
							if (!on.hasPermission("system.vanish.bypass")) {
								on.hidePlayer(p);
							}
						}
						p.sendMessage(Main.prefix+"§aDu bist nun im Vanish!");
					}
				}
				else {
					p.sendMessage(Main.prefix+"§7Bitte benutze '/v'");
				}
			}
			else {
				p.sendMessage(Main.prefix+"§7Du hast §ckeine §7Berechtigung für diesen Befehl");
			}
		}
		
        return false;
    }
	
}
