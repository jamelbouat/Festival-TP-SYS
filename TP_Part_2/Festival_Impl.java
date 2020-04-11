//package TP_Part_2;
//
//import TP_Part_1.Festivalier;
//import TP_Part_1.Navette;
//import TP_Part_1.Site;
//
//public class Festival_Impl {
//	
//	public static final int nbr_de_sites = 7;
//	public static final int nbr_de_festivaliers = 100;
//	public static final int nbr_de_navettes = 10;
//	public static final int nbr_places_navette = 12;
//	public static final int nbr_billets_total = 70;
//	public static Site[] sites;
//	public static Festivalier[] festivaliers;
//	public static Navette[] navettes;
//	public static int nbr_billets_par_site;
//	
//	public static void main(String args[]) {
//		
//		sites = new Site[nbr_de_sites]; 
//		festivaliers = new Festivalier[nbr_de_festivaliers]; 
//		navettes = new Navette[nbr_de_navettes]; 
//		
//		creerUneListeDeSites();
//		creerUneListeDeFestivaliers();
//		creerUneListeDeNavettes();		
//	}
//
//	public static void creerUneListeDeSites() {
//		/*
//		 * is_entree : True (c'est le site d'entrée)
//		 * 0 : identifiant du site d'entrée
//		 */
//		sites[0] = new Site(0, true);
//		
//		/*
//		 * is_entree : false (c'est les sites oridinaires)
//		 * i : identifiant du site. Il y a (nbr_de_sites - 1) sites ordinaires
//		 */
//		
//		for(int i = 1; i < nbr_de_sites; i++) {
//			sites[i] = new Site(i, false);
//		}
//	}
//	
//	public static void creerUneListeDeFestivaliers() {
//		for(int i = 0; i < nbr_de_festivaliers; i++) {
//			int id_site_genere = generer_id_site_depart_festivalier();
//			
//			Site site_depart = sites[id_site_genere];
//			festivaliers[i] = new Festivalier(i, site_depart, sites[0]);
//		}
//	}
//	
//	public static void creerUneListeDeNavettes() {
//		Site site_depart_navette = sites[1];
//		
//		for(int i = 0; i < nbr_de_navettes; i++) {	
//			navettes[i] = new Navette(i, sites, site_depart_navette, sites[0], nbr_places_navette);
//		}
//	}
//	
//	// Génération d'un id site compris 0 et nbr_de_sites
//	public static int generer_id_site_depart_festivalier() {
//		return (int) (Math.random() * nbr_de_sites);
//	}	
//	
//}
