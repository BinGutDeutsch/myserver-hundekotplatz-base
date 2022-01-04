package eu.hundekotplatz.base.minigames.player;

import java.io.Serializable;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import eu.hundekotplatz.base.main.Main;

public class Spieler implements Serializable {

	private static final long serialVersionUID = 1L;
	private String player;
	private int kotstand;
	private boolean isKotenEnabled;
	private int rank;
	private boolean sammler;
	private int beutel;

	public Spieler(String p) {
		this.player = p;
		this.kotstand = 0;
		this.isKotenEnabled = true;
		this.rank = 0;
		this.sammler = false;
		this.beutel = 0;
	}

	public void transferKothaufen(Spieler sp, int amount) {
		if (this.kotstand - amount < 0) {
			this.getBukkitPlayer().sendMessage(Main.prefix + "Du hast nicht genug §6Kothaufen");
			return;
		}

		this.setKotstand(this.getKotstand() - amount);
		sp.addKotstand(amount);
		this.getBukkitPlayer().sendMessage(Main.prefix + "Du hast §e" + sp.getBukkitPlayer().getDisplayName() + " §6"
				+ amount + " Kothaufen §7überwiesen");
		sp.getBukkitPlayer().sendMessage(Main.prefix + "§e" + this.getBukkitPlayer().getDisplayName() + " §7hat dir §6"
				+ amount + " Kothaufen §7überwiesen");
	}

	@SuppressWarnings("deprecation")
	public static Spieler getSpieler(List<Spieler> spieler, Player p) {
		for (Spieler sp : spieler) {
			if (Bukkit.getOfflinePlayer(sp.getPlayer()).getUniqueId().equals(p.getUniqueId())) {
				return sp;
			}
		}
		return null;
	}

	public void toggleKoten() {
		if (isKotenEnabled) {
			this.setKotenEnabled(false);
		} else {
			this.setKotenEnabled(true);
		}
	}

	public Player getBukkitPlayer() {
		return Bukkit.getPlayer(this.getPlayer());
	}

	public void addKotstand(int betrag) {
		this.setKotstand(this.getKotstand() + betrag);
	}

	public boolean isKotenEnabled() {
		return isKotenEnabled;
	}

	public void setKotenEnabled(boolean isKotenEnabled) {
		this.isKotenEnabled = isKotenEnabled;
	}

	public int getKotstand() {
		return kotstand;
	}

	public String getPlayer() {
		return player;
	}

	public void setKotstand(int kotstand) {
		this.kotstand = kotstand;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getBeutel() {
		return beutel;
	}

	public Spieler() {
		// TODO Auto-generated constructor stub
	}

	public boolean isSammler() {
		return sammler;
	}

	public void setBeutel(int beutel) {
		this.beutel = beutel;
	}

	public void setSammler(boolean sammler) {
		this.sammler = sammler;
	}
}
