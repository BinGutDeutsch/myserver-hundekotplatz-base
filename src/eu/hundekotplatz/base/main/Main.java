package eu.hundekotplatz.base.main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import eu.hundekotplatz.base.minigames.entitys.DogSpawningCommand;
import eu.hundekotplatz.base.minigames.entitys.KotHandler;
import eu.hundekotplatz.base.minigames.entitys.SpawnEntityCommand;
import eu.hundekotplatz.base.minigames.player.JoinListener;
import eu.hundekotplatz.base.minigames.player.Spieler;
import eu.hundekotplatz.base.minigames.player.TopCommand;
import eu.hundekotplatz.base.minigames.player.commands.BeutelCommand;
import eu.hundekotplatz.base.minigames.player.commands.KotCommand;
import eu.hundekotplatz.base.minigames.player.commands.PayCommand;
import eu.hundekotplatz.base.minigames.player.commands.ShopCommand;
import eu.hundekotplatz.base.minigames.player.commands.ToggleKotenCommand;
import eu.hundekotplatz.base.minigames.player.commands.TutorialCommand;
import eu.hundekotplatz.base.minigames.ranks.RankCommand;
import eu.hundekotplatz.base.minigames.ranks.RankHandler;
import eu.hundekotplatz.base.system.chatsystem.ChatSystem;
import eu.hundekotplatz.base.system.chatsystem.GlobalmuteCMD;
import eu.hundekotplatz.base.system.chatsystem.GlobalmuteSystem;
import eu.hundekotplatz.base.system.commands.BCCommand;
import eu.hundekotplatz.base.system.commands.BuildAPI;
import eu.hundekotplatz.base.system.commands.BuildCommand;
import eu.hundekotplatz.base.system.commands.DCCommand;
import eu.hundekotplatz.base.system.commands.FlyCommand;
import eu.hundekotplatz.base.system.commands.GMCommand;
import eu.hundekotplatz.base.system.commands.HelpCommand;
import eu.hundekotplatz.base.system.commands.SpawnCommand;
import eu.hundekotplatz.base.system.commands.TSCommand;
import eu.hundekotplatz.base.system.commands.VanishCommand;
import eu.hundekotplatz.base.system.commands.Vanish_PlayerEvents;
import eu.hundekotplatz.base.system.commands.WhitelistCMD;
import eu.hundekotplatz.base.system.holograms.HologramCommand;
import eu.hundekotplatz.base.system.joinsystem.JoinSystem;
import eu.hundekotplatz.base.system.listener.AchievementCancler;
import eu.hundekotplatz.base.system.listener.BlocksPhysics;
import eu.hundekotplatz.base.system.listener.DamageHandler;
import eu.hundekotplatz.base.system.listener.FoodlevelChange;
import eu.hundekotplatz.base.system.pvp.PvPAPI;
import eu.hundekotplatz.base.system.pvp.PvPModeCMD;
import eu.hundekotplatz.base.system.scoreboard.ScoreAPI;
import eu.hundekotplatz.base.system.spysystem.SpyAPI;
import eu.hundekotplatz.base.system.spysystem.SpyCommand;

public class Main extends JavaPlugin {

	public static Main instance;
	public final static String prefix = "§8» §a§lHundekotplatz§8 × §7";

	private final List<Spieler> top5 = new ArrayList<Spieler>();
	private final List<Player> buildMode = new ArrayList<Player>();
	private final List<String> whitelisted = new ArrayList<String>();
	private List<Spieler> spieler;

	@SuppressWarnings("unchecked")
	@Override
	public void onEnable() {
		System.out.println("Enabling hundekotplatz-base");
		instance = this;
		ScoreAPI.startScheduler();
		KotHandler.startKotSammler();

		spieler = (ArrayList<Spieler>) loadData(new File("PlayersInfo.dat"));
		if (spieler == null) {
			spieler = new ArrayList<Spieler>();
		}
		TopCommand.generateTop();
		TopCommand.startTopUpdater();

		PluginManager pm = Bukkit.getPluginManager();
		getCommand("globalmute").setExecutor(new GlobalmuteCMD());
		getCommand("spy").setExecutor(new SpyCommand());
		getCommand("bc").setExecutor(new BCCommand());
		getCommand("build").setExecutor(new BuildCommand());
		getCommand("dc").setExecutor(new DCCommand());
		getCommand("fly").setExecutor(new FlyCommand());
		getCommand("gm").setExecutor(new GMCommand());
		getCommand("ts").setExecutor(new TSCommand());
		getCommand("v").setExecutor(new VanishCommand());
		getCommand("spawn").setExecutor(new SpawnCommand());
		getCommand("setspawn").setExecutor(new SpawnCommand());
		getCommand("weiseliste").setExecutor(new WhitelistCMD());
		getCommand("pvp").setExecutor(new PvPModeCMD());
		getCommand("spawnmob").setExecutor(new SpawnEntityCommand());
		getCommand("togglespawning").setExecutor(new DogSpawningCommand());
		getCommand("kot").setExecutor(new KotCommand());
		getCommand("togglekoten").setExecutor(new ToggleKotenCommand());
		getCommand("pay").setExecutor(new PayCommand());
		getCommand("tutorial").setExecutor(new TutorialCommand());
		getCommand("shop").setExecutor(new ShopCommand());
		getCommand("top").setExecutor(new TopCommand());
		getCommand("hologram").setExecutor(new HologramCommand());
		getCommand("rank").setExecutor(new RankCommand());
		getCommand("kotbeutel").setExecutor(new BeutelCommand());

		pm.registerEvents(new ChatSystem(), this);
		pm.registerEvents(new GlobalmuteSystem(), this);
		pm.registerEvents(new SpyAPI(), this);
		pm.registerEvents(new JoinSystem(), this);
		pm.registerEvents(new AchievementCancler(), this);
		pm.registerEvents(new BuildAPI(), this);
		pm.registerEvents(new Vanish_PlayerEvents(), this);
		pm.registerEvents(new HelpCommand(), this);
		pm.registerEvents(new BlocksPhysics(), this);
		pm.registerEvents(new DamageHandler(), this);
		pm.registerEvents(new PvPAPI(), this);
		pm.registerEvents(new FoodlevelChange(), this);
		pm.registerEvents(new KotHandler(), this);
		pm.registerEvents(new JoinListener(), this);
		pm.registerEvents(new ShopCommand(), this);
		pm.registerEvents(new RankHandler(), this);
	}

	@Override
	public void onDisable() {
		System.out.println("disabling hundekotplatz-base");
		KotHandler.clearKothaufen();
		saveData(spieler, new File("PlayersInfo.dat"));
		instance = null;
	}


	@SuppressWarnings("resource")
	public void saveData(Object o, File f) {

		String encodedObject;
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			ByteArrayOutputStream io = new ByteArrayOutputStream();
			BukkitObjectOutputStream os = new BukkitObjectOutputStream(io);
			os.writeObject(o);
			os.flush();

			byte[] object = io.toByteArray();
			encodedObject = Base64.getEncoder().encodeToString(object);

			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(encodedObject);
			oos.flush();
			oos.close();
			System.out.println("DATEN WURDEN GESPEICHERT");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public Object loadData(File f) {
		try {

			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			String result = (String) ois.readObject();
			ois.close();
			byte[] decoded = Base64.getDecoder().decode(result);
			ByteArrayInputStream in = new ByteArrayInputStream(decoded);
			BukkitObjectInputStream is = new BukkitObjectInputStream(in);
			System.out.println("DATEN WURDEN GELADEN");
			return is.readObject();
		} catch (IOException | ClassNotFoundException e) {
			return null;
		}

	}

	public List<Spieler> getSpieler() {
		return spieler;
	}

	public List<Player> getBuildMode() {
		return buildMode;
	}

	public void setSpieler(List<Spieler> spieler) {
		this.spieler = spieler;
	}

	public List<String> getWhitelisted() {
		return whitelisted;
	}

	public List<Spieler> getTop5() {
		return top5;
	}
}
