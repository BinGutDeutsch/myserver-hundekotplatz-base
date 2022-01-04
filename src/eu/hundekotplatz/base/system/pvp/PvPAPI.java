package eu.hundekotplatz.base.system.pvp;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import eu.hundekotplatz.base.main.Main;
import eu.hundekotplatz.base.minigames.player.Spieler;
import eu.hundekotplatz.base.system.inventoryhelper.InventoryHelper;

public class PvPAPI implements Listener {

	@EventHandler
	public void onEntityHitEntity(EntityDamageByEntityEvent event) {
		if (PvPModeCMD.pvpStatus) {
			return;
		}
		event.setCancelled(true);
	}

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Snowball) {
			Snowball snowball = (Snowball) event.getDamager();
			Entity hitBySnowball = event.getEntity();
			Player shooter = (Player) snowball.getShooter();
			if (hitBySnowball instanceof Player) {
				Player player = (Player) hitBySnowball;
				try {
					Spieler hitter = Spieler.getSpieler(Main.instance.getSpieler(), shooter);
					Spieler hitted = Spieler.getSpieler(Main.instance.getSpieler(), player);

					hitter.addKotstand(1100);
					hitted.setKotstand(hitted.getKotstand() - 100);
					shooter.sendMessage(Main.prefix + "Du hast einen Spieler getroffen");
					shooter.sendMessage(Main.prefix + "§6+ 1100 Kothaufen");
					player.sendMessage(Main.prefix + "Du wurden von einem verschimmelten Kothaufen getroffen");
					player.sendMessage(Main.prefix + "§c- 100 Kothaufen");

				} catch (Exception e) {
					String preisPref = "§8» §7Preis: §d";
					String benutzbarkeitPref = "§8» §7Benutzbar: §a";
					String upgradablePref = "§8» §7Verbesserbar: §c";

					shooter.getInventory().setItem(0,
							InventoryHelper.createInvItem(Material.SNOW_BALL, "§8» §fVerschimmelter Kot",
									"§7Tötet einen Spieler sofort, wenn du ihn damit triffst.",
									"§7Du erhältst dadurch §61100 Kothaufen§7", preisPref + " 1000 Kothaufen",
									benutzbarkeitPref + 1, upgradablePref + false));
				}
			}
		}
	}
}
