package TP_Part_2;

public class Site {
	
	// Identifiant du site
	private int id_site;

	// Guichets du site
	private Guichet[] guichets;
	
	// True : c'est une entr�e, False : site ordinaire (n'est pas une entr�e)
	public boolean is_entree;
	
	// Arret du site qui peut contenir une navette
	private Navette arret;
	
	// Nombre de billets du site
	public int nbr_billets_par_site;
	
	// Nombre de billets affect�s au d�part pour ce site
	public int nbr_billets_pour_ce_site;
	
	/**
	 * Constructeur du site
	 * @param id_site : identifiant site
	 * @param is_entree : site d'entr�e ou autre
	 * @param nbr_billets_par_site
	 * @param nbr_guichets 
	 */
	public Site(int id_site, boolean is_entree, int nbr_billets_par_site, int nbr_guichets) {
		this.id_site = id_site;
		this.is_entree = is_entree;
		this.nbr_billets_par_site = nbr_billets_par_site;
		this.nbr_billets_pour_ce_site = nbr_billets_par_site;
		creerGuichetsPourCeSite(nbr_guichets);
	}

	/**
	 * Cr�ation de guichets pour le site
	 * i + 1 : id du guichet compris entre 1 et nbr_guichets
	 * @param nbr_guichets
	 */
	private void creerGuichetsPourCeSite(int nbr_guichets) {
		this.guichets = new Guichet[nbr_guichets];
		
		for (int i = 0 ; i < nbr_guichets; i++) {
			guichets[i] = new Guichet(i + 1, this);
		}
	}
	
	// Retourne le nombre de guichets d�di�s � ce site
	public int getNbrGuichetsDuSite() {
		return this.guichets.length;
	}
	
	// Retourne le nombre de billets affect�s � ce site
	public int getNbrBilletsDuSite() {
		return nbr_billets_par_site;
	}

	// Mettre � jour le nombre de billets du site
	public void setNbrBilletsDuSite(int billet_pris) {
		nbr_billets_par_site += billet_pris;
	}
	
	/**
	 * Achat d'un billet par un festivalier.
	 * Si le site est l'entr�e, le festivalier entre dans le festival sans prendre de navette,
	 * donc son parcours se termine.
	 * Si le site n'est pas l'entr�e, il ach�te un billet et essaye d'acc�der dans une navette
	 * @param festivalier
	 * @param index_guichet_choisi 
	 * @return false : si le site est une entr�e ou il n'y a plus de billets � vendre, donc le parcours (Thread)
	 * du festivalier se termine. true : achat d'un billet est effectu�
	 */
	public boolean festivalierAcheteUnBillet(Festivalier festivalier, int index_guichet_choisi) {	
		
		if (is_entree && guichets[index_guichet_choisi].acheterUnBillet()) {
			System.out.println("Festivalier N� " + festivalier.getIdFestivalier() + " est d�j� � l'entr�e,"
					+ " c'est le site N� " + this.id_site);
			return false;
			
		} else if (guichets[index_guichet_choisi].acheterUnBillet()) {
			return true;
		}
		
		System.out.println("Festivalier N� " + festivalier.getIdFestivalier() + " s'est pr�sent� au site N� " + this.id_site
				+ ", mais il n'y a plus de billets � vendre. "
				+ "Ce site a re�u que " + nbr_billets_pour_ce_site + " billets");
		return false;
	}
	
	/**
	 * Le festivalier essaye d'acc�der dans la navette s'elle est pr�sente � l'arret et a une place libre, puis
	 * entame son parcours jusqu'� l'entr�e du festival.
	 * Un festivalier qui prend une place, implique une mise � jour sur le nombre de places dans la navette
	 * Autrement, le festivalier se met en attente s'il n'y a pas de place libre ou non pr�sence de la navette
	 * synchronized est utilis� pour permette un seul acc�s � la fois dans la navette
	 * @param festivalier
	 * @param index_guichet 
	 */
	public synchronized void festivalierAccedeLaNavette(Festivalier festivalier, int index_guichet) {
		
		while (arret == null || arret.getNbrPlacesLibresNavette() < 1) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		arret.setNbrPlacesLibresNavette(-1);
		
		System.out.println("Festivalier N� " + festivalier.getIdFestivalier() 
							+ " se pr�sente au site N� " + this.id_site
							+ " et monte dans la navette N� " + arret.getIdNavette()
							+ ", il a achet� son billet au guichet N� " + this.guichets[index_guichet].getIdGuichet()
							+ ", il arrive � l'entr�e du festival en " + festivalier.getDureeDeplacementFestivalier()
							+ " unit�s de temps");
	}
	
	/**
	 * La navette se pr�sente au site courant. La navette est affect�e � l'arret du site si
	 * ce dernier ne contient pas de navette d�j� stationn�e, sinon elle se met en attente
	 * On notifie tous les festivaliers en attente
	 * @param navette
	 */
	public synchronized void navetteArriveAuSite(Navette navette) {
		
		while (arret != null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		arret = navette;
		notifyAll();
	}
		
	/**
	 * La navette quitte le site courant
	 * L'arret du site est remis � null
	 * Si le site courant est l'entr�e du festival, la navette se vide, donc
	 * toutes ses places sont libres, puis elle recommence son parcours du site id=1
	 * @param navette
	 */
	public synchronized void navetteQuitteLeSite(Navette navette) {
		arret = null;

		if (this.is_entree) {
			navette.viderNavetteAuSiteEntree();
		}
	}
	
	/**
	 * @return identifiant du site
	 */
	public int getIdSite() {
		return this.id_site;		
	}	

}
