package eu.hundekotplatz.base.minigames.player;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import eu.hundekotplatz.base.main.Main;

public class TopCommand implements CommandExecutor {

	private String prefix = "§6§lTop §8» ";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;

			try {
				p.sendMessage(prefix + "§c➀ " + Main.instance.getTop5().get(0).getPlayer() + "§8 - §6"
						+ Main.instance.getTop5().get(0).getKotstand());
				p.sendMessage(prefix + "§e➁ " + Main.instance.getTop5().get(1).getPlayer() + "§8 - §6"
						+ Main.instance.getTop5().get(1).getKotstand());
				p.sendMessage(prefix + "§6➂ " + Main.instance.getTop5().get(2).getPlayer() + "§8 - §6"
						+ Main.instance.getTop5().get(2).getKotstand());
				p.sendMessage(prefix + "§7➃ " + Main.instance.getTop5().get(3).getPlayer() + "§8 - §6"
						+ Main.instance.getTop5().get(3).getKotstand());
				p.sendMessage(prefix + "§7➄ " + Main.instance.getTop5().get(4).getPlayer() + "§8 - §6"
						+ Main.instance.getTop5().get(4).getKotstand());
			} catch (Exception e) {
				p.sendMessage(Main.prefix + "Fehler Code: 1101");
				e.printStackTrace();
			}
		}
		return false;
	}

	public static void generateTop() {
		Main.instance.getTop5().clear();
		for (Spieler sp : Main.instance.getSpieler()) {
			try {
				if (sp.getKotstand() > Main.instance.getTop5().get(0).getKotstand()
						|| Main.instance.getTop5().get(0) == null) {
					if (!Main.instance.getTop5().contains(sp)) {
						Main.instance.getTop5().add(0, sp);
					}	
				
				} else if (sp.getKotstand() > Main.instance.getTop5().get(1).getKotstand()
						|| Main.instance.getTop5().get(1) == null) {
					if (!Main.instance.getTop5().contains(sp)) {
						Main.instance.getTop5().add(1, sp);
					}	
				} else if (sp.getKotstand() > Main.instance.getTop5().get(2).getKotstand()
						|| Main.instance.getTop5().get(2) == null) {
					if (!Main.instance.getTop5().contains(sp)) {
						Main.instance.getTop5().add(2, sp);
					}
				} else if (sp.getKotstand() > Main.instance.getTop5().get(3).getKotstand()
						|| Main.instance.getTop5().get(3) == null) {
					if (!Main.instance.getTop5().contains(sp)) {
						Main.instance.getTop5().add(3, sp);
					}
				} else if (sp.getKotstand() > Main.instance.getTop5().get(4).getKotstand()
						|| Main.instance.getTop5().get(4) == null) {
					if (!Main.instance.getTop5().contains(sp)) {
						Main.instance.getTop5().add(4, sp);
					}
				}
				
			} catch (Exception e) {
				if (!Main.instance.getTop5().contains(sp)) {
					Main.instance.getTop5().add( sp);
				}
			}
			
			
		}
		for(int i = 5; i<Main.instance.getTop5().size(); i++) {
			Main.instance.getTop5().remove(i);
		}
		
		
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		try {
			String command = "hd setline top5 2 "+"§c➀ " + Main.instance.getTop5().get(0).getPlayer() + "§8 - §6"
					+ Main.instance.getTop5().get(0).getKotstand();
			String command1 = "hd setline top5 3 "+"§e➁ " + Main.instance.getTop5().get(1).getPlayer() + "§8 - §6"
					+ Main.instance.getTop5().get(1).getKotstand();
			String command2 = "hd setline top5 4 "+"§6➂ " + Main.instance.getTop5().get(2).getPlayer() + "§8 - §6"
					+ Main.instance.getTop5().get(2).getKotstand();
			String command3 = "hd setline top5 5 "+"§7➃ " + Main.instance.getTop5().get(3).getPlayer() + "§8 - §6"
					+ Main.instance.getTop5().get(3).getKotstand();
			String command4 = "hd setline top5 6 "+"§7➄ " + Main.instance.getTop5().get(4).getPlayer() + "§8 - §6"
					+ Main.instance.getTop5().get(4).getKotstand();
			Bukkit.dispatchCommand(console, command);
			Bukkit.dispatchCommand(console, command1);
			Bukkit.dispatchCommand(console, command2);
			Bukkit.dispatchCommand(console, command3);
			Bukkit.dispatchCommand(console, command4);
		} catch (Exception e) {
			String command = "hd setline top5 2 §cFehler beim Laden der Topliste"; 
			Bukkit.dispatchCommand(console, command);
			e.printStackTrace();
		}
	}

	public static void startTopUpdater() {
		new BukkitRunnable() {

			@Override
			public void run() {
				generateTop();
			}
		}.runTaskTimer(Main.instance, 1200, 1200);
	}

}
