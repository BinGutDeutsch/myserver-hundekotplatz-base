package eu.hundekotplatz.base.system.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.hundekotplatz.base.main.Main;

public class BCCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if (sender instanceof Player) {
			Player p= (Player) sender;
			if (p.hasPermission("system.bc")) {
				if (args.length>=1) {
					String msg ="";
					for (int i = 0; i < args.length; i++) {
						msg+=args[i]+" ";
					}
					Bukkit.getServer().broadcastMessage(Main.prefix+"-----------------------------");
					Bukkit.getServer().broadcastMessage(Main.prefix+"§a"+msg);
					Bukkit.getServer().broadcastMessage(Main.prefix+"-----------------------------");
				}
				else {
					p.sendMessage(Main.prefix+"§7Bitte benutze '/bc <Nachricht>'");
				}
			}
			else {
				p.sendMessage(Main.prefix+"§7Du hast §ckeine §7Berechtigung für diesen Befehl");
			}
		}
		
		
		return false;
	}

	
	
}
