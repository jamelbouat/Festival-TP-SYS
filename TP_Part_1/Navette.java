package TP_Part_1;

public class Navette extends Thread {
	
	public Site[] sites;

	private int id_navette;
	private Site site_depart_navette;
	private Site site_entree;
	private int nbr_places_navette;

	public Navette(int id_navette, Site[] sites, Site site_depart, Site site_arrivee, int nbr_places) {
		this.id_navette = id_navette;
		this.site_depart_navette = site_depart;
		this.site_entree = site_arrivee;
		this.nbr_places_navette = nbr_places;
		this.sites = sites;
	}

	public void run() {
		
		// boucle for sur tous sites du site 1 jusqu'à l'entrée 
		// pour le déplacement de la navette
	}

}
