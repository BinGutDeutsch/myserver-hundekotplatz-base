package eu.hundekotplatz.base.system.commands;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import eu.hundekotplatz.base.main.Main;


public class BuildAPI implements Listener {

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (Main.instance.getBuildMode().contains(e.getPlayer())) {
			return;
		}
		else {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (Main.instance.getBuildMode().contains(e.getPlayer())) {
			return;
		}
		else {
			e.setCancelled(true);
		}
	}
	
}
