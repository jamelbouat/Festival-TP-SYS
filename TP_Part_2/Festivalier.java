package TP_Part_2;

public class Festivalier extends Thread {
	
	private int id_festivalier;
	private Site site_depart;
	private Site site_entree;
	private int temps_parcours_interSitesPlusArret;
	
	/**
	 * Constructeur festivalier :
	 * @param id_festivalier
	 * @param site_depart_pour_festivalier
	 * @param site_entree
	 * @param temps_parcours_portion_festivalier 
	 */
	public Festivalier(int id_festivalier, Site site_depart_pour_festivalier, Site site_entree, int temps_parcours_interSitesPlusArret) {
		this.id_festivalier = id_festivalier;
		this.site_depart = site_depart_pour_festivalier;
		this.site_entree = site_entree;
		this.temps_parcours_interSitesPlusArret = temps_parcours_interSitesPlusArret;
	}	
	
	/**
	 * @return l'identifiant du festivalier
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
	  * @return la dur�e en milisecondes
	  */
    public int getDureeDeplacementFestivalier() {
        return Math.abs(site_entree.getIdSite() - site_depart.getIdSite()) * temps_parcours_interSitesPlusArret;
    }
    
    /**
     * G�n�ration d'un index guichet compris entre 0 et (nbr_guichets du site de d�part - 1)
     * Avec l'index g�n�r�, un guichet est s�lectionn� dans le tableau des guichets de ce site
     * @return index guichet g�n�r�
     */
    public int genererIndexGuichetPourAchatBillet() {
    	int nbr_guichets = site_depart.getNbrGuichetsDuSite();
    	
    	return (int) (Math.random() * nbr_guichets) ;
    }

	/*
	 * Festivalier ach�te un billet et essaye de monter dans une navette s'il
	 * le site o� il se pr�sente n'est pas l'entr�e du festival
	 */
	public void run() {
		
		int index_guichet_genere = genererIndexGuichetPourAchatBillet();
		boolean valeur_retournee_achat_billet = site_depart.festivalierAcheteUnBillet(this, index_guichet_genere);

		// Valeur = true : billet achet� et le site n'est pas une entr�e 
		if(valeur_retournee_achat_billet) {

			site_depart.festivalierAccedeLaNavette(this, index_guichet_genere);
			try {
				sleep(getDureeDeplacementFestivalier());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
