package eu.hundekotplatz.base.minigames.ranks;

public class Rank {

	private int stufe;
	private String bezeichnung;
	private int kotcooldown;
	private int kotProKack;
	private int preis;
	
	public Rank(int stufe, String bezeichnung, int kotcooldown,int kotProKack, int preis) {
		this.stufe = stufe;
		this.bezeichnung = bezeichnung;
		this.kotcooldown = kotcooldown;
		this.kotProKack = kotProKack;
		this.preis = preis;
	}

	public int getPreis() {
		return preis;
	}
	public void setPreis(int preis) {
		this.preis = preis;
	}
	
	public int getStufe() {
		return stufe;
	}

	public void setStufe(int stufe) {
		this.stufe = stufe;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public int getKotcooldown() {
		return kotcooldown;
	}

	public void setKotcooldown(int kotcooldown) {
		this.kotcooldown = kotcooldown;
	}

	public int getKotProKack() {
		return kotProKack;
	}

	public void setKotProKack(int kotProKack) {
		this.kotProKack = kotProKack;
	}
	
	
}
