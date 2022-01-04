package eu.hundekotplatz.base.minigames.ranks;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import eu.hundekotplatz.base.main.Main;
import eu.hundekotplatz.base.minigames.player.Spieler;

public class RankHandler implements Listener {

	public static String prefix = "§a§lUpgrade §8» §7";

	public static Rank[] ranks = {
			new Rank(0, "Welpe", 20, 1, 5000),
			new Rank(1, "Terrier", 20, 2, 15000),
			new Rank(2, "Dackel", 20, 4, 40000), 
			new Rank(3, "Mops", 20, 8, 100000),
			new Rank(4, "Beagle", 20, 12, 220000), 
			new Rank(5, "Shepherd", 20, 15, 500000),
			new Rank(6, "Pudel", 10, 20, 750000), 
			new Rank(7, "Dalmatiner", 20, 50, 1000000),
			new Rank(8, "Boxer", 10, 75, 2000000), 
			new Rank(9, "Husky", 10, 150, 4000000),
			new Rank(10, "Dobermann", 10, 200, 5000000),
			new Rank(11, "Retriever", 10, 250, 8000000),
			new Rank(12, "Rottweiler", 10, 500, 10000000)
			};

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();

		if (e.getInventory().getTitle().equals("§aRanks")) {
			e.setCancelled(true);
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §aNächster Rang§8:")) {
				upgradeRank(Spieler.getSpieler(Main.instance.getSpieler(), p));
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §dAktueller Rang§8:")) {
				showRank(Spieler.getSpieler(Main.instance.getSpieler(), p));
			}
			p.closeInventory();
		}

	}

	public void showRank(Spieler sp) {
		Rank aktRang = RankHandler.getRank(sp.getRank());
		sp.getBukkitPlayer().sendMessage(prefix + "§8» §a" + aktRang.getBezeichnung());
		sp.getBukkitPlayer().sendMessage(
				prefix + "§8» §7Stufe: §c" + aktRang.getStufe() + " §8/§a" + RankHandler.getRanks().length);
		sp.getBukkitPlayer().sendMessage(prefix + "§8» §7Kotdauer: §e" + aktRang.getKotcooldown());
		sp.getBukkitPlayer().sendMessage(prefix + "§8» §7Kothaufen pro Kot: §6" + aktRang.getKotProKack());
	}

	public void upgradeRank(Spieler sp) {
		Rank nextRank = null;
		try {
			nextRank = RankHandler.getRank(sp.getRank() + 1);
		} catch (Error e) {
			sp.getBukkitPlayer().sendMessage(prefix + "Du hast bereits den höchsten Rang");
		}
		if (nextRank.getPreis() > sp.getKotstand()) {
			sp.getBukkitPlayer().sendMessage(prefix + "Du hast nicht genügend §6Kothaufen");
			return;
		}

		sp.setKotstand(sp.getKotstand() - nextRank.getPreis());
		setRank(sp, sp.getRank() + 1);
		sp.getBukkitPlayer().sendMessage(
				prefix + "Du hast §aerfolgreich §7den Rang §e" + nextRank.getBezeichnung() + " §7erworben");

	}

	public static Rank getRank(int stufe) {
		return ranks[stufe];
	}

	public static void setRank(Spieler sp, int rank) {
		sp.setRank(rank);
	}

	public static Rank[] getRanks() {
		return ranks;
	}
}
