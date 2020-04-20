package TP_Part_1;

import java.util.ArrayList;
import java.util.List;

public class Site {
	
	// Identifiant du site
	private int id_site;

	// Guichet du site
	public Guichet guichet;
	
	// True : c'est une entr�e, False : site ordinaire (n'est pas une entr�e)
	public boolean is_entree;
	
	// Liste contenant les navettes pr�sentes au m�me moment � l'arret du site
	private List<Navette> arret;
	
	/**
	 * Constructeur du site avec un guichet et un arret,
	 * @param id_site : identifiant site
	 * @param is_entree : site d'entr�e ou autre
	 */
	public Site(int id_site, boolean is_entree) {
		this.id_site = id_site;
		this.guichet = new Guichet(id_site);
		this.is_entree = is_entree;
		arret = new ArrayList<Navette>();
	}

	/**
	 * Achat d'un billet par un festivalier. Synchronized est utilis� pour permettre l'achat 
	 * d'un seul billet � fois au niveau d'un guichet.
	 * Si le site est l'entr�e, le festivalier entre dans le festival sans prendre de navette,
	 * donc son parcours se termine.
	 * Si le site n'est pas l'entr�e, il ach�te un billet et essaye d'acc�der dans une navette
	 * @param festivalier
	 */
	public synchronized void festivalierAcheteUnBillet(Festivalier festivalier) {
		
		if (is_entree && guichet.acheter_un_billet()) {
			System.out.println("Festivalier N� " + festivalier.getIdFestivalier() + " est d�j� � l'entr�e,"
					+ " c'est le site N� " + this.id_site);
		} else {
			guichet.acheter_un_billet();
		}
	}
	
	/**
	 * Le festivalier essaye d'acc�der dans une navette s'il y a une place libre.
	 * La liste des navettes pr�sentes � l'arret du site est parcouru et d�s que 
	 * une premi�re place libre se pr�sente le festivalier la prend, et entame son parcours jusqu'� 
	 * l'entr�e du festival.
	 * Un festivalier qui prend une place, une mise � jour est effectu�e sur le nombre de places dans la navette.
	 * Autrement, le festivalier se met en attente s'il n'y a pas de place libre 
	 * synchronized est utilis� pour permette un seul acc�s � la fois dans la navette
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
			// Aucune place libre, il se met en attente
			if (navette_non_complete == null) {
				try {
	    			wait();
	    		} catch(InterruptedException e) {}
			}
		}
		// Prend place dans la navette
		navette_non_complete.setNbrPlacesLibresNavette(-1);
		System.out.println("Festivalier N� " + festivalier.getIdFestivalier() 
							+ " se pr�sente au site N� " + this.id_site
							+ " et monte dans la navette N� " + navette_non_complete.getIdNavette()
							+ ", il arrive � l'entr�e du festival en " + festivalier.getDureeDeplacementFestivalier()
							+ " unit�s de temps");
	}
	
	/**
	 * La navette se pr�sente au site courant
	 * La navette est ajout�e � la liste des navettes pr�sentes � l'arret du site.
	 * synchronized est utilis� pour permettre l'ajout d'une navette � fois � l'arret du site.
	 * On notifie tous les festivaliers en attente
	 * @param navette
	 */
	public synchronized void navetteArriveAuSite(Navette navette) {
		arret.add(navette);
		notifyAll();
	}
		
	/**
	 * La navette quitte le site courant
	 * La navette est retir�e de la liste des navettes pr�sentes � l'arret du site
	 * Si le site courant est l'entr�e du festival, la navette se vide, donc toutes 
	 * ses places sont libres, puis elle recommence son parcours en allant au site id=1
	 * synchronized est utilis� pour permettre le retrait d'une navette � fois � l'arret du site.
	 * @param navette
	 */
	public synchronized void navetteQuitteLeSite(Navette navette) {
		arret.remove(navette);

		if (this.is_entree) {
			navette.viderNavetteAuSiteEntree();
		}
	}
	
	/**
	 * @return l'identifiant (id) du site
	 */
	public int getIdSite() {
		return this.id_site;		
	}	

}
