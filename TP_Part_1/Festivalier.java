package TP_Part_1;

public class Festivalier extends Thread {
	
	private int id_festivalier;
	private Site site_depart;
	private Site site_entree;
	
	public Festivalier(int id_festivalier, Site site_depart, Site site_entree) {
		this.id_festivalier = id_festivalier;
		this.site_depart = site_depart;
		this.site_entree = site_entree;
	}
	
	public void run() {

		site_depart.client_achete_billet();
		
		if (! site_depart.equals(site_entree)) {
			
			site_depart.client_accede_navette();
			// dur�e de d�placement vers le site d'entr�e ....

		}
		
	}

	
}
