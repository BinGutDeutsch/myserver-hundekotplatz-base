package eu.hundekotplatz.base.minigames.entitys;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import eu.hundekotplatz.base.main.Main;

public class SpawnEntityCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;

			if (!p.hasPermission("system.entity.spawn")) {
				return false;
			}

			if (args.length == 0) {
				p.sendMessage(Main.prefix + "Befehlaufbau:");
				p.sendMessage(Main.prefix + "/spawnmob Type Name ColorCode");
				p.sendMessage(Main.prefix + "- Types -");
				p.sendMessage(Main.prefix + "- Hund");
				p.sendMessage(Main.prefix + "- Zombie");
				p.sendMessage(Main.prefix + "- Skelet");
				p.sendMessage(Main.prefix + "- Hase");
			} else if (args.length == 3) {
				switch (args[0].toLowerCase()) {
				case "hund":
					SpawnEntity.spawnEntity(p.getWorld(), p.getLocation(), EntityType.WOLF, args[1], args[2], true);
					break;
				case "zombie":
					SpawnEntity.spawnEntity(p.getWorld(), p.getLocation(), EntityType.ZOMBIE, args[1], args[2], true);
					break;
				case "skelet":
					SpawnEntity.spawnEntity(p.getWorld(), p.getLocation(), EntityType.SKELETON, args[1], args[2], true);
					break;
				case "hase":
					SpawnEntity.spawnEntity(p.getWorld(), p.getLocation(), EntityType.RABBIT, args[1], args[2], true);
					break;

				default:
					p.sendMessage(Main.prefix + "Unbekannter Type");
					break;
				}
			}
		}
		return false;
	}

}
