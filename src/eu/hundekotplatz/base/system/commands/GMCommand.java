package eu.hundekotplatz.base.system.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.hundekotplatz.base.main.Main;
public class GMCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("system.gm")) {
				if (args.length==1) {
					if (args[0].equals("1")) {
						p.setGameMode(GameMode.CREATIVE);
						p.sendMessage(Main.prefix+"§aDein Spielmodus wurde erfolgreich in den Kreativmodus gesetzt");
					}
					else if (args[0].equals("0")) {
						p.setGameMode(GameMode.SURVIVAL);
						p.sendMessage(Main.prefix+"§aDein Spielmodus wurde erfolgreich in den Survivalmodus gesetzt");
					}
					else if (args[0].equals("3")) {
						p.setGameMode(GameMode.SPECTATOR);
						p.sendMessage(Main.prefix+"§aDein Spielmodus wurde erfolgreich in den Spectatormodus gesetzt");
					}
				}
				else {
					p.sendMessage(Main.prefix+"§7Bitte benutze '/gm <Modus>'");
				}
			}
			else {
				p.sendMessage(Main.prefix+"§7Du hast §ckeine §7Berechtigung für diesen Befehl");
			}
			
		}
		
		
		return false;
	}

	
	
}
