package TP_Part_1;

public class Navette extends Thread {

	private int id_navette;
	private Site site_depart;
	private Site site_arrivee;
	private int places_navette;

	public Navette(int id_navette, Site site_depart, Site site_arrivee) {
		this.id_navette = id_navette;
		this.site_depart = site_depart;
		this.site_arrivee = site_arrivee;
	}

	public void run() {
		
	}

}
