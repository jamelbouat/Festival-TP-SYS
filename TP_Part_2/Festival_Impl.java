package TP_Part_2;

public class Festival_Impl {
	
	public static final int nbr_de_sites = 7;
	public static final int nbr_de_festivaliers = 50;
	public static final int nbr_de_navettes = 30;
//	public static final int nbr_de_billets = 100;
	
	
	public static void main(String args[]) {
		
		Site[] sites = new Site[nbr_de_sites]; 
		Festivalier[] festivaliers = new Festivalier[nbr_de_festivaliers]; 
		Navette[] navettes = new Navette[nbr_de_navettes]; 
		
		/*
		 * nombre_guichets : Un seul guichet
		 * nombre_navettes : 5 nombre de navettes simultanée possible par site (à en discuter)
		 * is_entree : True (c'est le site entrée)
		 */
		sites[0] = new Site(0, true); // 8 pour essai
		
		/*
		 * La création des 6 autres sites ordinaires(pas d'entrée au festival)
		 */
//		for(int i=1; i <7; i++) {
//			sites[0] = new Site(1,5, false); //8 pour essai
//		}
	}
}
