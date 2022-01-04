package eu.hundekotplatz.base.minigames.player.commands;


import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import eu.hundekotplatz.base.main.Main;
import eu.hundekotplatz.base.minigames.player.Spieler;
import eu.hundekotplatz.base.system.inventoryhelper.InventoryHelper;

public class ShopCommand implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			p.openInventory(createShopGUI());
			return true;
		}
		return false;
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		// Player player = (Player)e.getWhoClicked();

		if (e.getInventory().getTitle().equals("§6Shop")) {
			e.setCancelled(true);
			String name = e.getCurrentItem().getItemMeta().getDisplayName();
			Player p = (Player) e.getWhoClicked();
			Spieler sp = Spieler.getSpieler(Main.instance.getSpieler(), p);
			if(name.equals("§8» §aKotbeutel")) {
				if(sp.getKotstand() < 250) {
					p.sendMessage(Main.prefix + "Du hast nicht genügend §6Kothaufen");
					return;
				}
				
				sp.setKotstand(sp.getKotstand()-250);
				sp.setBeutel(sp.getBeutel()+500);
				p.sendMessage(Main.prefix+ "Du hast dir §6500 Kotbeutel §7gekauft.");
					
			}
			else if(name.equals("§8» §3Kaffee und Kippe")) {
				if(sp.getKotstand() < 100) {
					p.sendMessage(Main.prefix + "Du hast nicht genügend §6Kothaufen");
					return;
				}
				
				sp.setKotstand(sp.getKotstand()-100);
				p.sendMessage(Main.prefix+ "Du hast dir §6Kaffee und Kippen §7gekauft.");
			}
			else if(name.equals("§8» §cKotsammler")) {
				if(sp.isSammler()) {
					p.sendMessage(Main.prefix + "Du hast bereits einen §cKotsammler");
					return;
				}
				if(sp.getKotstand() < 10000) {
					p.sendMessage(Main.prefix + "Du hast nicht genügend §6Kothaufen");
					return;
				}
				
				sp.setKotstand(sp.getKotstand()-10000);
				sp.setSammler(true);
				p.sendMessage(Main.prefix+ "Du hast dir einen §6Kotsammler §7gekauft.");
			}else if(name.equals("§8» §fVerschimmelter Kot")) {
				if(sp.getKotstand() < 1000) {
					p.sendMessage(Main.prefix + "Du hast nicht genügend §6Kothaufen");
					return;
				}
				String preisPref = "§8» §7Preis: §d";
				String benutzbarkeitPref = "§8» §7Benutzbar: §a";
				String upgradablePref = "§8» §7Verbesserbar: §c";
				
				p.getInventory().addItem(
						InventoryHelper.createInvItem(Material.SNOW_BALL, "§8» §fVerschimmelter Kot",
								"§7Tötet einen Spieler sofort, wenn du ihn damit triffst.",
								"§7Du erhältst dadurch §61100 Kothaufen§7", preisPref + " 1000 Kothaufen", benutzbarkeitPref+1, upgradablePref+false));
				sp.setKotstand(sp.getKotstand()-1000);
				sp.setSammler(true);
				p.sendMessage(Main.prefix+ "Du hast dir einen §6verschimmelten Kot §7gekauft.");
			}
			/*
			 * if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aSpawn")) {
			 * player.performCommand("spawn"); }
			 */

		}

	}

	public Inventory createShopGUI() {
		Inventory shop = InventoryHelper.createInventory("§6Shop");

		// Items des Shops
		String preisPref = "§8» §7Preis: §d";
		String benutzbarkeitPref = "§8» §7Benutzbar: §a";
		String upgradablePref = "§8» §7Verbesserbar: §c";
		/*shop.setItem(10,
				InventoryHelper.createInvItem(Material.MILK_BUCKET, "§8» §fMilch",
						"§7Erhöht deine Chance", "§6Laktoseintollerant §7zu werden",
						preisPref + " 75 Kothaufen", benutzbarkeitPref + 1, upgradablePref+false));*/
		
		shop.setItem(10,
				InventoryHelper.createInvItem(Material.BUCKET, "§8» §aKotbeutel",
						"§7Gibt dir die §cdoppelte §7Anzahl an Kothaufen,",
						"§7wenn du Kothaufen einsammelst.", preisPref + " 250 Kothaufen", benutzbarkeitPref+500, upgradablePref+false));
		
		shop.setItem(11,
				InventoryHelper.createInvItem(Material.TORCH, "§8» §3Kaffee und Kippe",
						"§7Mit Kaffee und Kippe flutschts.",
						"§7Bringt dir absolut nichts.", preisPref + " 100 Kothaufen", benutzbarkeitPref+1, upgradablePref+false));
		shop.setItem(12,
				InventoryHelper.createInvItem(Material.SNOW_BALL, "§8» §fVerschimmelter Kot",
						"§7Tötet einen Spieler sofort, wenn du ihn damit triffst.",
						"§7Du erhältst dadurch §61100 Kothaufen§7", preisPref + " 1000 Kothaufen", benutzbarkeitPref+1, upgradablePref+false));
		shop.setItem(13,
				InventoryHelper.createInvItem(Material. DISPENSER, "§8» §cKotsammler",
						"§7Sammelt alle §620 Sekunden",
						"§7automatisch einen Kothaufen.", preisPref + " 10000 Kothaufen", benutzbarkeitPref+"/", upgradablePref+true));

		return shop;
	}


}
