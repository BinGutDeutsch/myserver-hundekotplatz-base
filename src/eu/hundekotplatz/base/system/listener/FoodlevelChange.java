package eu.hundekotplatz.base.system.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodlevelChange implements Listener{

	
	@EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event)
    {
        event.setCancelled(true);
    }
}
