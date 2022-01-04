package eu.hundekotplatz.base.minigames.player.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.hundekotplatz.base.main.Main;
import eu.hundekotplatz.base.minigames.player.Spieler;

public class PayCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(!p.hasPermission("system.player.pay")) {
				return false;
			}
			switch (args.length) {
			case 0:
				p.sendMessage(Main.prefix+"/pay §e<Name> §6<Kothaufen>");
				break;
			case 2:
				Player target = Bukkit.getPlayer(args[0]);
				if(target == null) {
					p.sendMessage(Main.prefix+"§e"+args[0]+" §7wurde nicht gefunden");
					break;
				}
				int amount;
				try {
					amount =  Integer.parseInt(args[1]);
				} catch (NumberFormatException e) {
					p.sendMessage(Main.prefix+"Keine gültige Anzahl");
					e.printStackTrace();
					break;
				}
				
				Spieler.getSpieler(Main.instance.getSpieler(), p).transferKothaufen(Spieler.getSpieler(Main.instance.getSpieler(), target), amount);
				
				break;

			default:
				p.sendMessage(Main.prefix+"/pay §e<Name> §6<Kothaufen>");
				break;
			}
			
		}
		return false;
	}

}
