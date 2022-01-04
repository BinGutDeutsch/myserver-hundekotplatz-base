package eu.hundekotplatz.base.minigames.ranks;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import eu.hundekotplatz.base.main.Main;
import eu.hundekotplatz.base.minigames.player.Spieler;
import eu.hundekotplatz.base.system.inventoryhelper.InventoryHelper;

public class RankCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(args.length == 0) {
				p.openInventory(createRankGUI(p));
				return true;
			}
			else if(args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if(target == null) {
					p.sendMessage(Main.prefix+"§e"+args[0]+" §7ist nicht online");
					return false;
				}
				p.sendMessage(Main.prefix+"§e"+args[0]+" §7ist §d"+RankHandler.getRank(Spieler.getSpieler(Main.instance.getSpieler(), target).getRank()).getBezeichnung());
				return true;
			}
			else if(args.length == 3) {
				if(!p.hasPermission("system.player.rank.modify")) {
					return false;
				}
				Player target = Bukkit.getPlayer(args[0]);
				if(target == null) {
					p.sendMessage(Main.prefix+"§e"+args[0]+" §7ist nicht online");
					return false;
				}
				int neuRank;
				try {
					neuRank = Integer.parseInt(args[2]);
				} catch (NumberFormatException e) {
					p.sendMessage(Main.prefix+"/rank §e<Name> §aset §cNummer");
					return false;
				}
				
				switch (args[1]) {
				case "set":
					RankHandler.setRank(Spieler.getSpieler(Main.instance.getSpieler(), target), neuRank);
					p.sendMessage(Main.prefix+"Rang von §e"+args[0]+"  §7wurde §aerfolgreich §7geändert");
					break;

				default:
					p.sendMessage(Main.prefix+"/rank §e<Name> §aset §cNummer");
					break;
				}
			}
			
			
		}
		return false;
	}

	
	
	public Inventory createRankGUI(Player p) {
		Rank playerrank = RankHandler.getRank(Spieler.getSpieler(Main.instance.getSpieler(), p).getRank());
		Rank nextrank = null;
		Inventory rank = InventoryHelper.createInventory("§aRanks");
		try {
			nextrank = RankHandler.getRank(Spieler.getSpieler(Main.instance.getSpieler(), p).getRank()+1);
			ItemStack nextRank = new ItemStack(Material.INK_SACK, 1, (byte) 10);
			ItemMeta nextMeta = nextRank.getItemMeta();
			nextMeta.setDisplayName("§8» §aNächster Rang§8:");
	        ArrayList<String> loreNext = new ArrayList<String>();
	        loreNext.add("");
	        loreNext.add("§8» §a"+nextrank.getBezeichnung());
	        loreNext.add("§8» §7Preis: §c"+nextrank.getPreis());
	        loreNext.add("§8» §7Kotdauer: §e"+nextrank.getKotcooldown());
	        loreNext.add("§8» §7Kothaufen pro Kot: §6"+nextrank.getKotProKack());
	        nextMeta.setLore(loreNext);
	        nextRank.setItemMeta(nextMeta);
	        
	        rank.setItem(12, nextRank);
		} catch (Exception e) {
			p.sendMessage(RankHandler.prefix+"Du hast bereits den höchsten Rang");
		}
		
		ItemStack border = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 8);
		ItemMeta bmeta = border.getItemMeta();
		bmeta.setDisplayName("§a");
		border.setItemMeta(bmeta);
		
		rank.setItem(10, border);
		rank.setItem(11, border);
		rank.setItem(13, border);
		rank.setItem(15, border);
		rank.setItem(16, border);
		
		
		ItemStack aktRang = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta playerheadmeta = (SkullMeta) aktRang.getItemMeta();
        playerheadmeta.setOwner("Dog");
        playerheadmeta.setDisplayName("§8» §dAktueller Rang§8:");
        aktRang.setItemMeta(playerheadmeta);
        ArrayList<String> loreAktRang = new ArrayList<String>();
        loreAktRang.add("");
        loreAktRang.add("§8» §a"+playerrank.getBezeichnung());
        loreAktRang.add("§8» §7Stufe: §c"+playerrank.getStufe()+ " §8/§a"+(RankHandler.getRanks().length-1));
        loreAktRang.add("§8» §7Kotdauer: §e"+playerrank.getKotcooldown());
        loreAktRang.add("§8» §7Kothaufen pro Kot: §6"+playerrank.getKotProKack());
        playerheadmeta.setLore(loreAktRang);
        aktRang.setItemMeta(playerheadmeta);
        
        rank.setItem(14, aktRang);
        
		
		return rank;
	}
}
