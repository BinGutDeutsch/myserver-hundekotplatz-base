package eu.hundekotplatz.base.system.pvp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.hundekotplatz.base.main.Main;

public class PvPModeCMD implements CommandExecutor{

	public static boolean pvpStatus = false;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if (args.length == 0) {
				if(pvpStatus) {
					p.sendMessage(Main.prefix+"PvP ist derzeit §aaktiviert");
				}else {
					p.sendMessage(Main.prefix+"PvP ist derzeit §cdeaktiviert");
				}	
			}
			else if(args.length == 1) {
				if(!p.hasPermission("system.pvp.change")) {
					p.sendMessage(Main.prefix+"Dafür hast du keine Berechtigungen");
					return false;
				}
				switch (args[0]) {
				case "enable":
					pvpStatus = true;
					p.sendMessage(Main.prefix+"PvP wurde §aaktiviert");
					break;
				case "disable":
					pvpStatus = false;
					p.sendMessage(Main.prefix+"PvP wurde §cdeaktiviert");
					break;
				case "change":
					if (pvpStatus) {
						pvpStatus = false;
						p.sendMessage(Main.prefix+"PvP wurde §cdeaktiviert");
					}else {
						pvpStatus = true;
						p.sendMessage(Main.prefix+"PvP wurde §aaktiviert");
					}
					break;
				default:
					break;
				}
				
			}
			
			
			
		}
		return false;
	}

}
