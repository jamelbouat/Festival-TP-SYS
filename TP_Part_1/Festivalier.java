package TP_Part_1;

public class Festivalier extends Thread {
	
	private int id_festivalier;
	private Site site_depart;
	private Site entree;
	
	public Festivalier(int id_festivalier, Site site_depart, Site entree) {
		this.id_festivalier = id_festivalier;
		this.site_depart = site_depart;
		this.entree = entree;
	}
	
	public int selectionner_guichet() {
		int nombre_guichet_site = site_depart.nombre_guichets();
		return (int) Math.random() * nombre_guichet_site;
	}
	
	public void run() {
		int numero_guichet = selectionner_guichet();
		site_depart.acheter_billet(numero_guichet);
		
		if (! site_depart.equals(entree)) {
			// durée de déplacement vers le site d'entrée ....

		}
		
	}

	
}
