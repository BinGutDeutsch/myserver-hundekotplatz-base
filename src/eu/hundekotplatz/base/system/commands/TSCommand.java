package eu.hundekotplatz.base.system.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.hundekotplatz.base.main.Main;


public class TSCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			p.sendMessage(Main.prefix+"§7Du kannst dich auf unseren TeamSpeak verbinden, mit folgender IP: §aHundekotplatz.eu");	
		}
		return false;
	}

}
