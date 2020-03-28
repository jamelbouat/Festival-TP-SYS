package TP_Part_1;

public class Festival_Impl {
	
	public static final int nbr_de_sites = 7;
	public static final int nbr_entrees = 1;
	public static final int nbr_de_festivaliers = 100;
	public static final int nbr_de_navettes = 10;
	public static final int nbr_places_navette = 12;
	public static final int id_site_entree = 0;
	public static final int id_site_depart_navettes = 1;
	
	public static void main(String args[]) {
		
		Site[] sites = new Site[nbr_de_sites]; 
		Festivalier[] festivaliers = new Festivalier[nbr_de_festivaliers]; 
		Navette[] navettes = new Navette[nbr_de_navettes]; 
		
		/*
		 * is_entree : True (c'est le(s) site(s) d'entr�e(s))
		 * i : identifiant du site
		 * il y a (nbr_entrees) entr�es au festival
		 */
		for(int i = 0; i < nbr_entrees; i++) {
			sites[i] = new Site(i, true);
		}
		
		/*
		 * is_entree : false (c'est le(s) site(s) oridinaire(s))
		 * i : identifiant du site
		 * il y a (nbr_de_sites - nbr_entrees) sites ordinaires
		 */
		for(int i = nbr_entrees; i < nbr_de_sites; i++) {
			sites[i] = new Site(i, false);
		}
		
		// D�signation d'un seul site d'entr�e
		Site site_entree = sites[id_site_entree];
		
		// D�signation du site de d�part des navettes
		Site site_depart_navette = sites[id_site_depart_navettes];
		
		// Cr�ation d'une liste de festivaliers
		for(int i = 0; i < nbr_de_festivaliers; i++) {
			// G�n�ration d'un id du site de d�part
			int id_site = generer_id_site_depart();
			Site site_depart = sites[id_site];
			festivaliers[i] = new Festivalier(i, site_depart, site_entree);
		}
		
		for(int i = 0; i < nbr_de_navettes; i++) {	
			navettes[i] = new Navette(i, sites, site_depart_navette, site_entree, nbr_places_navette);
		}
		
	}
	
	// G�n�ration d'un id site compris 0 et nbr_de_sites
	public static int generer_id_site_depart() {
		System.out.println((int) (Math.random() * nbr_de_sites));
		return (int) (Math.random() * nbr_de_sites);
	}
	
	
	
}
