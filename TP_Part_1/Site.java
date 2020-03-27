package TP_Part_1;

public class Site {
	
	/*
	 * Nombre de guichets du site
	 */
	public Guichet[] guichets;
	
	/*
	 * Nombre de navettes présentes simultanément à l'arrêt du site
	 */
	public int nombre_navettes;
		
	/*
	 * True : entrée, False : site ordinaire(pas une entrée)
	 */
	public boolean is_entree;
	
	/*
	 * Nombre de billets du site
	 */
	public int nbr_billets_par_site;
	
	/**
	 * Constructeur d'un site
	 * @param nombre_guichets
	 * @param nombre_navettes
	 * @param is_entree
	 */
	public Site(int nombre_guichets, int nombre_navettes, boolean is_entree, int nbr_billets) {
		
		ajout_de_guichets(nombre_guichets);
		this.nombre_navettes = nombre_navettes;
		this.is_entree = is_entree;
		this.nbr_billets_par_site = nbr_billets;
	}
	
	/**
	 * Création de nbr guichets avec un nbr de billets pour chaque guichet
	 * @param n : est le nombre de guichets du site
	 */
	public void ajout_de_guichets(int nbr_guichets) {
		
		// On assume que la division est entière et que tous les guichets ont un nombre égal en billets
		int nbr_billets_par_guichet = nbr_billets_par_site / nbr_guichets;
		
		for(int i=0; i <nbr_guichets; i++) {
			this.guichets[0] = new Guichet(nbr_billets_par_guichet);
		}
	}

	/*
	 * Achat d'un billet correspondant au guichet numero_guichet
	 */
	public synchronized void acheter_billet(int numero_guichet) {
		guichets[numero_guichet].acheter_un_billet();		
	}

	/*
	 * Retourne le nombre de guichets du site
	 */
	public int nombre_guichets() {
		return guichets.length;
	}
	
	

}
