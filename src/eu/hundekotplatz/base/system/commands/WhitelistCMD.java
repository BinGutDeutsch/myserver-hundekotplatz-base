package eu.hundekotplatz.base.system.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.hundekotplatz.base.main.Main;

public class WhitelistCMD implements CommandExecutor {

	public static boolean isWhitelistON = false;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 0) {
				if (!player.hasPermission("system.whitelist.toggle")) {
					player.sendMessage(Main.prefix + "§7Du hast §ckeine §7Berechtigung für diesen Befehl");
					return false;
				}
				if (isWhitelistON) {
					isWhitelistON = false;
					player.sendMessage(Main.prefix + "Du hast die Whitelist §cdeaktiviert");
				} else {
					isWhitelistON = true;
					player.sendMessage(Main.prefix + "Du hast die Whitelist §aaktiviert");
				}
			} else if (args.length == 1) {
				if (!player.hasPermission("system.whitelist.add")) {
					return false;
				}
				String target = args[0];
					
				if(Main.instance.getWhitelisted().contains(target)) {
					player.sendMessage(Main.prefix+"Du hast §e"+target+" §7von der Whitelist §centfernt");
					Main.instance.getWhitelisted().remove(target);
				}else {
					player.sendMessage(Main.prefix+"Du hast §e"+target+" §7auf die Whitelist §ahinzugefügt");
					Main.instance.getWhitelisted().add(target);
				}
				
				
			}

		}
		return false;
	}

}
