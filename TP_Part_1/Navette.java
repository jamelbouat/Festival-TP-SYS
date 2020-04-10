package TP_Part_1;

public class Navette extends Thread {
	
	public Site[] sites;
    static final int duree_deplacement_plus_arret  = 100;

	private int id_navette;
	private Site site_depart_navette;
	private Site site_entree;
	private int nbr_places_libres_navette;
	private int nbr_max_places_navette;

	public Navette(int id_navette, Site[] sites, Site site_depart, Site site_arrivee, int nbr_places) {
		this.id_navette = id_navette;
		this.site_depart_navette = site_depart;
		this.site_entree = site_arrivee;
		this.nbr_places_libres_navette = nbr_places;
		this.nbr_max_places_navette = nbr_places;
		this.sites = sites;
	}
	
	public int getNbrPlacesLibresNavette() {
		return nbr_places_libres_navette;
	}
	
	public void setNbrPlacesLibresNavette(int placesPrises) {
		nbr_places_libres_navette += placesPrises;
	}
	
	// Faire descencdre tous les clients de la navette
	// Ce qui veut dire remettre les places de la navette toutes libres
	public void faireViderNavetteAuSiteEntree() {
		nbr_places_libres_navette = nbr_max_places_navette;
	}

	public void run() {
		
		while(true) {
    		for (int i = 0; i < sites.length; i++) {
        		Site site = sites[i];
        		
        		site.monterClientsDansNavette(this);
        		
        		try {
    				sleep(duree_deplacement_plus_arret);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
        		
        		
        	}
    	}
	}

}
