package TP_Part_2;

public class Festivalier extends Thread {
	
	private int id_festivalier;
	private Site site_depart;
	private Site site_entree;
	
	public Festivalier(int id_festivalier, Site site_depart, Site site_entree) {
		this.id_festivalier = id_festivalier;
		this.site_depart = site_depart;
		this.site_entree = site_entree;
	}
	
	/*
	 * Le festivalier sélectionne un guichet parmi les guichets du site
	 * La méthode suivante retourne un guichet quelconque
	 */
	public int selectionner_guichet() {
		int nbr_guichets_du_site = site_depart.nbr_guichets_du_site();
		return (int) (Math.random() * nbr_guichets_du_site);
	}
	
	public void run() {
		int numero_guichet = selectionner_guichet();
		site_depart.client_achete_billet(numero_guichet);
		
		if (! site_depart.equals(site_entree)) {
			
			site_depart.client_accede_navette();
			// durée de déplacement vers le site d'entrée ....

		}
		
	}

	
}
