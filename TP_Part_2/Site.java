package TP_Part_1;

public class Site {
	
	/*
	 * Nombre de guichets du site
	 */
	public int nombre_guichets;
	
	/*
	 * Présence ou pas de navette à l'arrêt du site
	 */
	public int nombre_navettes;
	
	public int nombre_billets;
	
	/*
	 * True : entrée, False : site ordinaire(pas une entrée)
	 */
	public boolean is_entree;
	
	
	public Site(int nombre_guichets, int nombre_navettes, boolean is_entree) {
		this.nombre_guichets = nombre_guichets;
		this.nombre_navettes = nombre_navettes;
		this.is_entree = is_entree;
	}
	
	

}
