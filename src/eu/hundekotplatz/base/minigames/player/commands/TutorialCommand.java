package eu.hundekotplatz.base.minigames.player.commands;

import java.util.ArrayList;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import eu.hundekotplatz.base.main.Main;

public class TutorialCommand implements CommandExecutor {

	private String tutorial = "§e§lTutorial §8» §7";
	private ArrayList<Player> blockingTut = new ArrayList<Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (blockingTut.contains(p))
				return false;
			
			p.sendMessage(tutorial + "§lIch helfe dir zu koten!");
			startTut(p);
			blockingTut.add(p);
		}
		return false;
	}

	public void startTut(Player p) {
		new BukkitRunnable() {
			int cnt = 2;

			@Override
			public void run() {
				switch (cnt) {
				case 1:
					p.sendMessage(tutorial + "Auf der Karte laufen Hunde herum, die §6Kothaufen");
					p.sendMessage(tutorial + "§7fallen lassen. Diese musst du einsammeln.");
					break;
				case 2:
					p.sendMessage(tutorial + "Sammle Kakaobohnen auf um §6Kothaufen §7zu sammeln.");
					p.sendMessage(tutorial + "Mit §e/kot §7kannst du deine gesammelten Kothaufen");
					p.sendMessage(tutorial + "anzeigen.");
					break;
				case 3:
					p.sendMessage(tutorial + "Um selber zu koten");
					p.sendMessage(tutorial + "➜ Halte sneaken gedrückt bis deine Levelbar sich");
					p.sendMessage(tutorial + "aufgefühlt hat.");
					break;
				case 4:
					p.sendMessage(tutorial + "Diese Funktion kannst du mit §e/togglekoten");
					p.sendMessage(tutorial + "§aaktivieren§7/§cdeaktivieren§7.");
					break;
				case 5:
					p.sendMessage(tutorial + "Mit §e/shop §7siehst du hilfreiche Features");
					p.sendMessage(tutorial + "um effektiver Kot zu sammeln.");
					break;
				case 6:
					p.sendMessage(tutorial + "Mit §e/rank §7siehst du deinen aktuellen §dRang§7.");
					p.sendMessage(tutorial + "Dort kannst du deinen §dRang §7auch upgraden");
					p.sendMessage(tutorial + "um mehr und schneller §6Kothaufen §7zu bekommen.");
					break;
				case 7:
					p.sendMessage(tutorial + "Eine Top-Koter Liste kannst du mit");
					p.sendMessage(tutorial + "§e/top §7aufrufen.");
					break;
				case 8:
					p.sendMessage(tutorial + "Das Tutorial kannst du jederzeit mit");
					p.sendMessage(tutorial + "§e/tutorial §7wiederholen.");
					break;

				default:
					this.cancel();
					blockingTut.remove(p);
					return;
				}
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 0.533F);
				p.sendMessage(tutorial+"§8⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊⚊");
				cnt++;
			}
		}.runTaskTimer(Main.instance, 60, 60);
	}

}
