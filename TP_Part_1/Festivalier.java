package TP_Part_1;

public class Festivalier extends Thread {
	
	private int id_festivalier;
	private Site site_depart;
	private Site site_entree;
	private int temps_parcours_portion_festivalier;
	
	/**
	 * Constructeur festivalier :
	 * @param id_festivalier
	 * @param site_depart_pour_festivalier
	 * @param site_entree
	 * @param temps_parcours_portion_festivalier 
	 */
	public Festivalier(int id_festivalier, Site site_depart_pour_festivalier, Site site_entree, int temps_parcours_portion_festivalier) {
		this.id_festivalier = id_festivalier;
		this.site_depart = site_depart_pour_festivalier;
		this.site_entree = site_entree;
		this.temps_parcours_portion_festivalier = temps_parcours_portion_festivalier;
	}	
	
	/**
	 * @return l'identifiant du festivalier
	 */
	public int getIdFestivalier() {
		return id_festivalier;
	}

	/**
	 * @return le site de départ du festivalier
	 */
	public Site getSiteDepart() {
		return site_depart;
	}
	
	 /**
	  * calcul le temps de deplacement entre le site de départ du festivalier et le site d'entrée
	  * @return la durée en milisecondes
	  */
    public int getDureeDeplacementFestivalier() {
        return Math.abs(site_entree.getIdSite() - site_depart.getIdSite()) * temps_parcours_portion_festivalier;
    }

	/*
	 * Festivalier achète un billet et essaye de monter dans une navette s'il
	 * le site où il se présente n'est pas l'entrée du festival
	 */
	public void run() {

		site_depart.festivalierAcheteUnBillet(this);

		if(!site_depart.is_entree) {

			site_depart.festivalierAccedeLaNavette(this);
			try {
				sleep(getDureeDeplacementFestivalier());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
