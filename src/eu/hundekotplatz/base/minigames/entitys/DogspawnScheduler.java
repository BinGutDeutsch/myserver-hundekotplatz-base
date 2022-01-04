package eu.hundekotplatz.base.minigames.entitys;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

import eu.hundekotplatz.base.main.Main;

public class DogspawnScheduler {

	private static String[] namen = { "§dIven", "§aJan", "§cDomme", "§3Nick", "§2Ben", "§eTobias", "§8Marc", "§9Josie",
			"§5Ella", "§7Pascal", "§8Lorenz", "§bFabso", "§2Lennard", "§dPaulo", "§3Marv", "§cBen", "§eTom" };
	private static Random rand = new Random();
	// private static int runTime = new Random().nextInt((300) + 60) * 20;

	public static void startSpawnScheduler() {
		new BukkitRunnable() {

			@Override
			public void run() {
				if (!KotHandler.spawningEnabled) {
					this.cancel();
				}
				int randomNum = rand.nextInt((namen.length - 0) + 1) + 0;
				SpawnEntity.spawnEntity(Bukkit.getWorld("hundekot"),
						new Location(Bukkit.getWorld("hundekot"), -13, 52, 10), EntityType.WOLF, namen[randomNum], "",
						true);
			}
		}.runTaskTimer(Main.instance, 500, 500);
	}

	public static void startKotScheduler() {
		new BukkitRunnable() {

			@Override
			public void run() {
				// runTime = new Random().nextInt((300) + 60) * 20;
				List<Entity> entitys = Bukkit.getWorld("hundekot").getEntities();

				for (Entity entity : entitys) {
					if (entity.getType().equals(EntityType.WOLF)) {
						if (entity.getLocation().getBlock().getType().equals(Material.AIR)) {
							KotHandler.spawnKot(entity.getLocation(), entity.getWorld());
						}
					}
				}
			}
		}.runTaskTimer(Main.instance, 150, 150);
	}

}
