package eu.hundekotplatz.base.minigames.entitys;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import eu.hundekotplatz.base.main.Main;
import eu.hundekotplatz.base.minigames.player.Spieler;
import eu.hundekotplatz.base.minigames.ranks.Rank;
import eu.hundekotplatz.base.minigames.ranks.RankHandler;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public class KotHandler implements Listener {

	public static ArrayList<Location> kothaufen = new ArrayList<Location>();
	public static boolean spawningEnabled = false;
	public static ArrayList<Player> amKoten = new ArrayList<Player>();

	// selber koten
	@EventHandler
	public void onPlayerSneak(PlayerToggleSneakEvent e) {
		Player p = e.getPlayer();
		if (Spieler.getSpieler(Main.instance.getSpieler(), p).isKotenEnabled()) {
			if (amKoten.contains(p))
				return;
			startSneakKotScheduler(p);
			amKoten.add(p);
		}
	}

	public void startSneakKotScheduler(Player p) {
		Rank pRank = RankHandler.getRank(Spieler.getSpieler(Main.instance.getSpieler(), p).getRank());
		new BukkitRunnable() {

			@Override
			public void run() {
				Location loc = p.getLocation();
				Packet<?> surv = new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, false, (float) loc.getX(),
						(float) loc.getY(), (float) loc.getZ(), (float) 0.001, (float) 0.001, (float) 0, (float) 1, 0, 1);
				
				for (Player online : Bukkit.getOnlinePlayers()) {
					((CraftPlayer) online).getHandle().playerConnection.sendPacket(surv);
				}
				if (p.getLevel() == 1) {
					p.sendMessage(Main.prefix + "Du hast gekotet! ^-^");
					if (p.getLocation().getBlock().getType().equals(Material.AIR)) {

						spawnKot(p.getLocation(), p.getWorld());
					} else {
						p.sendMessage(Main.prefix + "Du kannst hier nicht hinkoten! >:(");
					}
					p.setLevel(0);
					this.cancel();
					amKoten.remove(p);
				}
				if (p.isSneaking()) {
					p.giveExp(1);
				}
				if ((!p.isSneaking())) {
					p.setExp(0);
					this.cancel();
					amKoten.remove(p);
				}
			}
		}.runTaskTimer(Main.instance, pRank.getKotcooldown(), pRank.getKotcooldown());
	}

	public static void startKotSammler() {
		
		new BukkitRunnable() {

			private List<Spieler> online;
			@Override
			public void run() {
				online  = Main.instance.getSpieler();
				
				for(Spieler sp: online) {
					if(sp.isSammler()) {
						sp.addKotstand(1);
					}
				}
				
			}
		}.runTaskTimer(Main.instance,400, 400);
	}

	
	
	@EventHandler
	public void onItemPickup(PlayerPickupItemEvent e) {
		ItemStack kot = new ItemStack(Material.INK_SACK, 1, (byte) 3);
		if (e.getItem().getItemStack().equals(kot)) {
			e.getItem().remove();
			e.setCancelled(true);
			collectedKot(e.getPlayer());
			return;
		}
	}

	public static void spawnKot(Location loc, World w) {
		ItemStack kot = new ItemStack(Material.INK_SACK, 1, (byte) 3);
		w.dropItemNaturally(loc, kot);
	}

	public void collectedKot(Player p) {
		Spieler sp = Spieler.getSpieler(Main.instance.getSpieler(), p);
		Rank pRank = RankHandler.getRank(sp.getRank());
		p.playSound(p.getLocation(), Sound.DIG_GRASS, 3.0F, 0.533F);
		if(sp.getBeutel() > 0) {
			sp.setBeutel(sp.getBeutel() - 1);
			Spieler.getSpieler(Main.instance.getSpieler(), p).addKotstand(pRank.getKotProKack()*2);
			p.sendMessage(Main.prefix + "§6+ " + pRank.getKotProKack()*2 + " Kot §7(Kotbeutel)");
			return;
		}
		Spieler.getSpieler(Main.instance.getSpieler(), p).addKotstand(pRank.getKotProKack());
		p.sendMessage(Main.prefix + "§6+ " + pRank.getKotProKack() + " Kot");
	}

	public void collectedKot(Location loc, Player p) {
		Rank pRank = RankHandler.getRank(Spieler.getSpieler(Main.instance.getSpieler(), p).getRank());
		p.playSound(loc, Sound.DIG_GRASS, 3.0F, 0.533F);
		removeKot(loc);
		Spieler.getSpieler(Main.instance.getSpieler(), p).addKotstand(pRank.getKotProKack());
		p.sendMessage(Main.prefix + "§6" + pRank.getKotProKack() + " Kot");
	}

	public static void clearKothaufen() {
		for (int i = 0; i < kothaufen.size(); i++) {
			removeKot(kothaufen.get(i));
		}
	}

	public static void removeKot(Location loc) {
		if (Bukkit.getWorld("hundekot").getBlockAt(loc).getType().equals(Material.WOOD_BUTTON)) {
			Bukkit.getWorld("hundekot").getBlockAt(loc).setType(Material.AIR);
			for (int i = 0; i < kothaufen.size(); i++) {
				if (loc.equals(kothaufen.get(i))) {
					kothaufen.remove(i);
				}
			}
		}
	}

}

//hundekot aufsammeln mit buttons -> outdatet
/*
 * p.getLocation().getBlock().setType(Material.WOOD_BUTTON);
 * p.getLocation().getBlock().setData((byte) 5); Location loc =
 * p.getLocation().getBlock().getLocation(); KotHandler.kothaufen.add(loc);
 */
/*
 * @EventHandler public void onInteract(PlayerInteractEvent event) { if
 * (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) { if
 * (event.getClickedBlock().getType().equals(Material.WOOD_BUTTON)) { for (int i
 * = 0; i < kothaufen.size(); i++) { if
 * (event.getClickedBlock().getLocation().equals(kothaufen.get(i))) {
 * collectedKot(event.getClickedBlock().getLocation(), event.getPlayer());
 * event.setCancelled(true); } } } } }
 */
