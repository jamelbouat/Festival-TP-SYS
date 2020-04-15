package TP_Part_2;

public class Site {
	
	// Identifiant du site
	private int id_site;

	// Guichet du site
	private Guichet[] guichets;
	
	// True : c'est une entr�e, False : site ordinaire (n'est pas une entr�e)
	public boolean is_entree;
	
	// Arret du site qui peut contenir une navette
	private Navette arret;
	
	// Nombre de billets affect� � ce site
	public int nbr_billets_par_site;
	
	/**
	 * Constructeur du site
	 * @param id_site : identifiant site
	 * @param is_entree : site d'entr�e ou autre
	 * @param nbr_guichets 
	 * @param nbr_billets_par_site
	 */
	public Site(int id_site, boolean is_entree, int nbr_billets_par_site, int nbr_guichets) {
		this.id_site = id_site;
		this.is_entree = is_entree;
		this.nbr_billets_par_site = nbr_billets_par_site;
		creerGuichetsPourCeSite(nbr_guichets);
	}

	/**
	 * Cr�ation de guichets pour le site
	 * i + 1 = id du guichet compris entre 1 et nbr_guichets
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
	 * Achat d'un billet par un client.
	 * synchronized est utilis� pour permettre l'achat 
	 * d'un seul billet � fois au niveau d'un guichet
	 * @param festivalier
	 * @param index_guichet_choisi 
	 */
	public boolean festivalierAcheteUnBillet(Festivalier festivalier, int index_guichet_choisi) {
		
		/* 
		 * Le site est l'entr�e du festival, le festivalier entre dans le festival sans prendre de navette donc
		 * son parcours se termine.
		 */
		if (is_entree && guichets[index_guichet_choisi].acheterUnBillet()) {
			System.out.println("Festivalier N� " + festivalier.getIdFestivalier() + " est d�j� � l'entr�e,"
					+ " c'est le site N� " + this.id_site);
			return false;
			
		} else if (guichets[index_guichet_choisi].acheterUnBillet()) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Le festivalier essaye d'acc�der dans la navette s'il y a une place libre, puis
	 * entame son parcours jusqu'� l'entr�e du festival.
	 * Un festivalier qui prend une place, une mise � jour est effectu�e sur le nombre de places dans la navette
	 * Autrement, le festivalier se met en attente s'il n'y a pas de place libre 
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
	 * La navette se pr�sente au site courant
	 * La navette est affect�e � l'arret du site si
	 * ce dernier ne contient pas de navette d�j� stationn�e
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
		notifyAll();

		if (this.is_entree) {
			navette.viderNavetteAuSiteEntree();
		}
	}
	
	/* 
	 * Retourne l'identifiant du site
	 */
	public int getIdSite() {
		return this.id_site;		
	}	

}
