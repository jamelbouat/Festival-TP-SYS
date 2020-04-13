package TP_Part_1;

public class Navette extends Thread {
	
	private Site[] sites;
    private int duree_deplacement_navette  = 200;
	private int duree_arret_navette = 200;
	private int id_navette;
	private int nbr_places_libres_navette;
	private int nbr_max_places_libres;

	/**
	 * Constructeur navette, avec un nombre maximum de places et,
	 * @param id_navette : identifiant navette
	 * @param sites : référence de la liste des sites
	 * @param nbr_places
	 * @param duree_deplacement
	 * @param duree_arret
	 */
	public Navette(int id_navette, Site[] sites, int nbr_places, int duree_deplacement, int duree_arret) {
		this.id_navette = id_navette;
		this.nbr_places_libres_navette = nbr_places;
		this.nbr_max_places_libres = nbr_places;
		this.duree_deplacement_navette = duree_deplacement;
		this.duree_arret_navette = duree_arret; 
		this.sites = sites;
	}
	
	/**
	 * @return l'identifiant de la navette
	 */
	public int getIdNavette() {
		return id_navette;
	}
	
	/**
	 * @return le nombre de places libres de la navette 
	 */
	public int getNbrPlacesLibresNavette() {
		return nbr_places_libres_navette;
	}
	
	/**
	 * @param placesPrises : mettre à jour le nombre de places disponibles
	 */
	public void setNbrPlacesLibresNavette(int placesPrises) {
		this.nbr_places_libres_navette += placesPrises;
	}
	
	/**
	 * Vider la navette à l'entrée du festival, donc remettre toutes les places disponibles
	 */
	public void viderNavetteAuSiteEntree() {
		this.nbr_places_libres_navette = this.nbr_max_places_libres;
	}
	
	/**
	 * Faire fonctionner la navette en boucle, qui commence sa course au site id=1 (sites[0]) et,
	 * se termine au site id=sites.length (sites[sites.length - 1]), puis rejoindre
	 * le site 1, et recommencer sa tournée. Tant que il y a des festivaliers, la navette ou les 
	 * navettes tournent sans fin grace le setDeamon du Thread navette 
	 * duree_deplacement_navette = temps entre deux sites
	 * duree_arret_navette = temps d'arret de la navette à chaque site
	 */
	public void run() {	
		while(true) {
    		for (int i = 0; i < sites.length; i++) {
        		Site site = sites[i];
        		
        		try {
    				sleep(duree_deplacement_navette);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
        		site.navetteArriveAuSite(this);
        		
        		try {
    				sleep(duree_arret_navette);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
        		site.navetteQuitteLeSite(this);
        	}
    	}
	}

}
