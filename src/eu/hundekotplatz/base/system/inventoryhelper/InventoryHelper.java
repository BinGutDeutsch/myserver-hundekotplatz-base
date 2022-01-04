package eu.hundekotplatz.base.system.inventoryhelper;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryHelper {

	public static Inventory createInventory(String name) {
		Inventory inv = Bukkit.getServer().createInventory(null, 3 * 9, name);

		// Ränder der Nav

		ItemStack border = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 8);
		ItemMeta bmeta = border.getItemMeta();
		bmeta.setDisplayName("§a");
		border.setItemMeta(bmeta);

		for (int i = 0; i <= 9; i++) {
			inv.setItem(i, border);
		}
		inv.setItem(17, border);
		for (int i = 18; i <= 26; i++) {
			inv.setItem(i, border);
		}
		
		
		return inv;
	}
	
	public static ItemStack createInvItem(Material material, String name, String beschreibung, String beschreibung2,
			String preis, String benutzbarkeit, String upgradeable) {
		ItemStack item = new ItemStack(material, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(beschreibung);
		lore.add(beschreibung2);
		lore.add("");
		lore.add(preis);
		lore.add(benutzbarkeit);
		lore.add(upgradeable);
		meta.setLore(lore);
		item.setItemMeta(meta);

		return item;
	}
	
}
