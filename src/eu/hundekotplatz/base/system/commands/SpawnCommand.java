package eu.hundekotplatz.base.system.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import eu.hundekotplatz.base.main.Main;


public class SpawnCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("setspawn")) {
				if (p.hasPermission("system.setspawn")) {
					File file = new File("spawnConfig.yml");
					if (!file.exists()) {
						try {
							file.createNewFile();
						} catch (IOException e) {
							p.sendMessage(Main.prefix+"§cDie spawnConfig.yml konnte nicht ersellt werden!");
						}
					}
					YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
					Location loc = p.getLocation();
					double x = loc.getX();
					double y = loc.getY();
					double z = loc.getZ();
					double yaw = loc.getYaw();
					double pitch = loc.getPitch();
					String worldname = loc.getWorld().getName();

					cfg.set("X", x);
					cfg.set("Y", y);
					cfg.set("Z", z);
					cfg.set("Yaw", yaw);
					cfg.set("Pitch", pitch);
					cfg.set("Worldname", worldname);

					try {
						cfg.save(file);
					} catch (IOException e) {
						p.sendMessage(Main.prefix+"§cDie spawnConfig.yml konnte nicht gefunden werden!");
					}
					p.sendMessage(Main.prefix+"§aDer Spawn wurde erfolgreich gesetzt");

				}
				else {
					p.sendMessage(Main.prefix+"§7Du hast §ckeine §7Berechtigung für diesen Befehl");
				}
			}
			else if (cmd.getName().equalsIgnoreCase("spawn")) {
				File file = new File("spawnConfig.yml");
				if (!file.exists()) {
					p.sendMessage(Main.prefix+"§7Es wurde noch kein Spawn gesetzt");
					return true;
				}
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				Location loc = p.getLocation();
				double x = cfg.getDouble("X");
				double y = cfg.getDouble("Y");
				double z = cfg.getDouble("Z");;
				double yaw = cfg.getDouble("Yaw");
				double pitch = cfg.getDouble("Pitch");
				String worldname = cfg.getString("Worldname");
				
				World welt = Bukkit.getWorld(worldname);
				loc.setX(x);
				loc.setY(y);
				loc.setZ(z);
				loc.setYaw((float)yaw);
				loc.setPitch((float)pitch);
				loc.setWorld(welt);
				p.teleport(loc);
				p.sendMessage(Main.prefix+"§7Du wurdest zum Spawn teleportiert");
			}
		}
		return false;
	}

}
