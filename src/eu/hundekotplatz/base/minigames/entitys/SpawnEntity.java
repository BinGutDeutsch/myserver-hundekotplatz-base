package eu.hundekotplatz.base.minigames.entitys;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class SpawnEntity {
	
	public static void spawnEntity(World world,Location location, EntityType type, String name, String colorCode, boolean nameVisible) {
		Entity entity = world.spawnEntity(location, type);
		entity.setCustomName(name);
		entity.setCustomNameVisible(nameVisible);
	}
}
