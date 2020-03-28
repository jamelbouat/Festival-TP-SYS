package TP_Part_2;

public class Navette extends Thread {

	private int id_navette;
	private Site site_depart;
	private Site site_arrivee;
	private int nbr_places_navette;

	public Navette(int id_navette, Site site_depart, Site site_arrivee, int nbr_places_navette) {
		this.id_navette = id_navette;
		this.site_depart = site_depart;
		this.site_arrivee = site_arrivee;
		this.nbr_places_navette = nbr_places_navette;
	}

	public void run() {
		
	}

}
