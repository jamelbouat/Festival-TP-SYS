package TP_Part_1;

public class Festivalier extends Thread {
	
	private int id_festivalier;
	private Site site_depart;
	private Site site_entree;
	private int temps_parcours_interSitesPlusArret;
	
	/**
	 * Constructeur festivalier
	 * @param id_festivalier
	 * @param site_depart_pour_festivalier
	 * @param site_entree
	 * @param temps_parcours_interSitesPlusArret 
	 */
	public Festivalier(int id_festivalier, Site site_depart_pour_festivalier, Site site_entree, int temps_parcours_interSitesPlusArret) {
		this.id_festivalier = id_festivalier;
		this.site_depart = site_depart_pour_festivalier;
		this.site_entree = site_entree;
		this.temps_parcours_interSitesPlusArret = temps_parcours_interSitesPlusArret;
	}	
	
	/**
	 * @return l'identifiant (id) du festivalier
	 */
	public int getIdFestivalier() {
		return id_festivalier;
	}

	/**
	 * @return le site de d�part du festivalier
	 */
	public Site getSiteDepart() {
		return site_depart;
	}
	
	/**
	 * calcul le temps de deplacement entre le site de d�part du festivalier et le site d'entr�e
	 * @return la dur�e de d�placement
	 */
	public int getDureeDeplacementFestivalier() {
        return Math.abs(site_entree.getIdSite() - site_depart.getIdSite()) * temps_parcours_interSitesPlusArret;
    }

	/*
	 * Festivalier ach�te un billet et essaye de monter dans une navette s'il
	 * le site o� il se pr�sente n'est pas l'entr�e du festival
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
