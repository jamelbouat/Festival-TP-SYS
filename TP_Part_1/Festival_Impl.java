package TP_Part_1;

public class Festival_Impl {

	
	public static void main(String args[]) {
		
		Site[] sites = new Site[7]; 
		
		/*
		 * nombre_guichets : Un seul guichet
		 * nombre_navettes : 5 nombre de navettes simultan�e possible par site (� en discuter)
		 * is_entree : True (c'est le site entr�e)
		 */
		sites[0] = new Site(1,5, true,8); // 8 pour essai
		
		/*
		 * La cr�ation des 6 autres sites ordinaires(pas d'entr�e au festival)
		 */
		for(int i=1; i <7; i++) {
			sites[0] = new Site(1,5, false,8); //8 pour essai
		}
	}
}
