package TP_Part_1;

public class Navette extends Thread {
	
	private int id_navette;
	private Site[] sites;
	private int nbr_places_libres_navette;
	private int nbr_max_places_libres_navette;
    private int duree_deplacement_navette;
	private int duree_arret_navette;

	/**
	 * Constructeur navette avec un nombre maximum de places,
	 * @param id_navette : identifiant navette
	 * @param sites : référence de la liste des sites
	 * @param nbr_places
	 * @param duree_deplacement
	 * @param duree_arret
	 */
	public Navette(int id_navette, Site[] sites, int nbr_places, int duree_deplacement, int duree_arret) {
		this.id_navette = id_navette;
		this.sites = sites;
		this.nbr_places_libres_navette = nbr_places;
		this.nbr_max_places_libres_navette = nbr_places;
		this.duree_deplacement_navette = duree_deplacement;
		this.duree_arret_navette = duree_arret; 
	}
	
	/**
	 * @return l'identifiant (id) de la navette
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
	 * Mettre à jour le nombre de places disponibles
	 * @param places_prises
	 */
	public void setNbrPlacesLibresNavette(int places_prises) {
		this.nbr_places_libres_navette += places_prises;
	}
	
	/**
	 * Vider la navette à l'entrée du festival, donc remettre toutes ses places disponibles
	 */
	public void viderNavetteAuSiteEntree() {
		this.nbr_places_libres_navette = this.nbr_max_places_libres_navette;
	}
	
	/**
	 * Faire fonctionner la navette en boucle, qui commence sa course au site id=1 (sites[0]) et,
	 * se termine au site id=sites.length (sites[sites.length - 1]), puis rejoindre
	 * le site 1, et recommencer sa tournée. Tant que il y a des festivaliers, la navette ou les 
	 * navettes tournent sans fin, et cela grace au true de setDeamon du Thread navette 
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
