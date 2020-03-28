package TP_Part_2;

import TP_Part_1.Guichet;

public class Site {
	
	/*
	 * Guichets du site
	 */
	public Guichet[] guichets;
	
//	/*
//	 * Nombre de navettes présentes simultanément à l'arrêt du site
//	 */
//	public int nombre_navettes;
		
	/*
	 * True : c'est une entrée, False : site ordinaire(pas une entrée)
	 */
	public boolean is_entree;
	
	/*
	 * Nombre de billets destinés au site
	 */
	//public int nbr_billets_par_site;
	
	/**
	 * Constructeur d'un site
	 * @param nombre_guichets
	 * @param nombre_navettes
	 * @param is_entree
	 */
	public Site(int nbr_guichets, boolean is_entree) {
		this.guichets = new Guichet[nbr_guichets];
		ajout_de_guichets(nbr_guichets);
		this.is_entree = is_entree;
//		this.nombre_navettes = nombre_navettes;
//		this.nbr_billets_par_site = nbr_billets;
	}
	
	/**
	 * Création de nbr guichets avec un nbr de billets pour chaque guichet
	 * @param n : est le nombre de guichets du site
	 */
	public void ajout_de_guichets(int nbr_guichets) {
//		// On assume que la division est entière et que tous les guichets ont un nombre égal en billets
//		int nbr_billets_par_guichet = nbr_billets_par_site / nbr_guichets;
//		
//		for(int i=0; i < nbr_guichets; i++) {
//			this.guichets[0] = new Guichet(nbr_billets_par_guichet);
//		}
	}

	/*
	 * Achat d'un billet correspondant au guichet numero_guichet
	 */
	public synchronized void client_achete_billet(int numero_guichet) {
		guichets[numero_guichet].acheter_un_billet();		
	}

	/*
	 * Retourne le nombre de guichets du site
	 */
	public int nbr_guichets_du_site() {
		return guichets.length;
	}

	/*
	 * Le client accède à la navette 
	 */
	public synchronized void client_accede_navette() {
		// TODO Auto-generated method stub
		
	}
	
	

}
