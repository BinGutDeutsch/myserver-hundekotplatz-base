package eu.hundekotplatz.base.system.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class DamageHandler implements Listener{

	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		DamageCause cause = event.getCause();
		if(cause == DamageCause.FALL) {
			event.setCancelled(true);
		}
	}
}
