package TP_Part_1;

import java.util.ArrayList;
import java.util.List;

public class Site {
	
	// Identifiant du site
	private int id_site;

	// Guichet du site
	public Guichet guichet;
	
	// True : c'est une entrée, False : site ordinaire (n'est pas une entrée)
	public boolean is_entree;
	
	// Liste contenant la liste des navettes présentes à l'arret du site
	private List<Navette> arret;
	
	/**
	 * Constructeur du site
	 * @param id_site : identifiant site
	 * @param is_entree : site d'entrée ou autre
	 */
	public Site(int id_site, boolean is_entree) {
		this.id_site = id_site;
		this.guichet = new Guichet(id_site);
		this.is_entree = is_entree;
		arret = new ArrayList<Navette>();
	}

	/**
	 * Achat d'un billet par un client.
	 * synchronized est utilisé pour permettre l'achat 
	 * d'un seul billet à fois au niveau d'un guichet
	 * @param festivalier
	 */
	public synchronized void festivalierAcheteUnBillet(Festivalier festivalier) {
		
		/* 
		 * Le site est l'entrée du festival, le festivalier entre dans le festival sans prendre de navette donc
		 * son parcours se termine.
		 */
		if (guichet.acheter_un_billet() && is_entree) {
			System.out.println("Festivalier N° " + festivalier.getIdFestivalier() + " est déjà à l'entrée,"
					+ " c'est le site N° " + this.id_site);
		} else {
			guichet.acheter_un_billet();
		}
	}
	
	/**
	 * Le festivalier essaye d'accéder dans une navette s'il y a une place libre.
	 * La liste des navettes présentes à l'arret du site est parcouru et dès que 
	 * une première place libre se présente le festivalier la prend, et entame son parcours jusqu'à 
	 * l'entrée du festival.
	 * Un festivalier qui prend une place, une mise à jour est effectuée sur le nombre de places dans la navette
	 * Autrement, le festivalier se met en attente s'il n'y a pas de place libre 
	 * @param festivalier
	 */
	public synchronized void festivalierAccedeLaNavette(Festivalier festivalier) {
		Navette navette_non_complete = null;
		
		while (navette_non_complete == null) {
			for (Navette navette : arret) {
				if(navette.getNbrPlacesLibresNavette() > 0) {
					navette_non_complete = navette;
					break;
				}
			}
			
			if (navette_non_complete == null) {
				try {
	    			wait();
	    		} catch(InterruptedException e) {}
			}
		}
		
		navette_non_complete.setNbrPlacesLibresNavette(-1);
		System.out.println("Festivalier N° " + festivalier.getIdFestivalier() 
							+ " se présente au site N° " + this.id_site
							+ " et monte dans la navette N° " + navette_non_complete.getIdNavette()
							+ ", il arrive à l'entrée du festival en " + festivalier.getDureeDeplacementFestivalier()
							+ " unités de temps");
	}
	
	/**
	 * La navette se présente au site courant
	 * La navette est ajoutée à la liste des navettes présentes à l'arret du site
	 * @param navette
	 */
	public synchronized void navetteArriveAuSite(Navette navette) {
		arret.add(navette);
		notifyAll();
	}
		
	/**
	 * La navette quitte le site courant
	 * La navette est retirée de la liste des navettes présentes à l'arret du site
	 * Si le site courant est l'entrée du festival, la navette se vide, donc
	 * toutes ses places sont libres, puis la recommence son parcours du site id=1
	 * @param navette
	 */
	public synchronized void navetteQuitteLeSite(Navette navette) {
		arret.remove(navette);
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
