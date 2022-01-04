package eu.hundekotplatz.base.minigames.entitys;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.hundekotplatz.base.main.Main;

public class DogSpawningCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(!p.hasPermission("system.entity.togglespawning")) {
				return false;
			}
			
			if(KotHandler.spawningEnabled) {
				p.sendMessage(Main.prefix+"Hund und Kot spawning wurde §cdeaktiviert");
				KotHandler.spawningEnabled = false;
			}else {
				p.sendMessage(Main.prefix+"Hund und Kot spawning wurde §aaktiviert");
				KotHandler.spawningEnabled = true;
				DogspawnScheduler.startSpawnScheduler();
				DogspawnScheduler.startKotScheduler();
			}
		}
		return false;
	}

}
