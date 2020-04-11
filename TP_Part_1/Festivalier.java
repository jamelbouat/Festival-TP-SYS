package TP_Part_1;

public class Festivalier extends Thread {
	
	private int id_festivalier;
	private Site site_depart;
	
	public Festivalier(int id_festivalier, Site site_depart_pour_festivalier) {
		this.id_festivalier = id_festivalier;
		this.site_depart = site_depart_pour_festivalier;
	}	
	
	public int getId_festivalier() {
		return id_festivalier;
	}


	public Site getSite_depart() {
		return site_depart;
	}

	/*
	 * Festivalier ach�te un billet et essaye de monter d'une navette s'il
	 * le site o� se pr�sente n'est pas l'entr�e du festival
	 */
	public void run() {

		site_depart.festivalier_achete_billet(this);
	}
	
}
