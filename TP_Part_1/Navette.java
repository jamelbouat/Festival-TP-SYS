package TP_Part_1;

import java.util.ArrayList;
import java.util.List;

public class Navette extends Thread {
	
	private Site[] sites;
    private static final int duree_deplacement_plus_arret  = 100;
	private int id_navette;
	private int nbr_places_libres_navette;
	private int nbr_max_places_libres;
	private List<Festivalier> listeFestivaliersAtransporter;

	public Navette(int id_navette, Site[] sites, int nbr_places) {
		this.id_navette = id_navette;
		this.nbr_places_libres_navette = nbr_places;
		this.nbr_max_places_libres = nbr_places;
		this.sites = sites;
		this.listeFestivaliersAtransporter = new ArrayList<>();
	}
	
	public boolean verifierPlacesLibresDispo() {
		return nbr_places_libres_navette != 0;
	}
	
	public void setNbrPlacesLibresNavette(int placesPrises) {
		this.nbr_places_libres_navette += placesPrises;
	}
	
	/* 
	* Faire descencdre tous les clients de la navette
	* Ce qui veut dire remettre les places de la navette toutes libres
	*/
	public void faireViderNavetteAuSiteEntree() {
		afficherLesfestivaliserArrives();
		this.nbr_places_libres_navette = this.nbr_max_places_libres;
		this.listeFestivaliersAtransporter.clear();
	}
	
	// Méthode appelée ci-dessus, pour afficher les festivaliers arrivés
	private void afficherLesfestivaliserArrives() {
		for(Festivalier festivalier : listeFestivaliersAtransporter) {
			System.out.println("Le festivalier N° " + festivalier.getId_festivalier() + " vient d'arriver à l'entrée du festival"
					+ " au départ du site N° " + festivalier.getSite_depart());
		}		
	}
	
	/*
	 * La navette prend un festivalier pour le transporter
	 */
	public void faireMonterUnFestivalier(Festivalier festivalier) {
		this.listeFestivaliersAtransporter.add(festivalier);		
	}

	public void run() {	
		while(true) {
    		for (int i = 0; i < sites.length; i++) {
        		Site site = sites[i];
        		site.navetteSePresenteAuSite(this);
        		
        		try {
    				sleep(duree_deplacement_plus_arret);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
        	}
    	}
	}

}
