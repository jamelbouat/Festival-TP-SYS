package TP_Part_1;

import java.util.ArrayList;
import java.util.List;

public class Site {
	
	private int id_site;

	// Guichet du site
	public Guichet guichet;
	
	// l'arr�t a un �tat soit pr�sence de navette(true) ou absence de navette(false);
	public boolean arret = false;
	
	// True : c'est une entr�e, False : site ordinaire(pas une entr�e)
	public boolean is_entree;
	
	private List<Festivalier> listeFestivaliersAvecBillets;
	
	/**
	 * Constructeur du site
	 * @param id_site : identifiant site
	 * @param is_entree : site d'entr�e ou autre
	 */
	public Site(int id_site, boolean is_entree) {
		this.id_site = id_site;
		this.guichet = new Guichet(id_site);
		this.is_entree = is_entree;
		listeFestivaliersAvecBillets = new ArrayList<>();
	}

	/*
	 * Achat d'un billet par un client.
	 * synchronized est utilis� pour permettre l'achat 
	 * d'un seul billet � fois au niveau d'un guichet
	 */
	public synchronized void festivalier_achete_billet(Festivalier festivalier) {
		
		if (guichet.acheter_un_billet() && is_entree) {
			System.out.println("Festivalier N� " + festivalier.getId_festivalier() + " est d�j� � l'entr�e,"
					+ " c'est le site N� " + this.id_site);
		}
		if (guichet.acheter_un_billet() && !is_entree) {
			listeFestivaliersAvecBillets.add(festivalier);
		}
	}
	
	/*
	 * La navette se pr�sente au site courant
	 */
	public synchronized void navetteSePresenteAuSite(Navette navette) {
		/*
		 * Interdire la mont�e des clients vu que c'est l'entr�e du festival,
		 * et appeler la m�thode qui fait descencdre les festivaliers et 
		 * donc faire vider la navette.
		 */
		if(is_entree) {
			navette.faireViderNavetteAuSiteEntree();
			
		} else {
			faireMonterClientsDansNavette(navette);			
		}
		
	}

	/*
	 * Pour faire monter un festivalier dans la navette, il faut avoir au moins une place libre dans la navette, et
	 * la liste de festivaliers avec des billets non vide.
	 */
	private void faireMonterClientsDansNavette(Navette navette) {
		while (navette.verifierPlacesLibresDispo() && !listeFestivaliersAvecBillets.isEmpty()) {
			navette.faireMonterUnFestivalier(listeFestivaliersAvecBillets.remove(0));
			
			// Mettre � jour le nombre de places libres dans la navette
			navette.setNbrPlacesLibresNavette(-1);
		}
		
	}

	// Retourne l'identifiant du site
	public int retourner_id_site() {
		return this.id_site;		
	}	

}
