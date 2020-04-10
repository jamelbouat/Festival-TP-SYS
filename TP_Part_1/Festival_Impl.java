package TP_Part_1;

public class Festival_Impl {
	
	public static final int nbr_de_sites = 7;
	public static final int nbr_de_festivaliers = 100;
	public static final int nbr_de_navettes = 10;
	public static final int nbr_places_navette = 12;
	public static final int nbr_billets_total = 70;
	public static Site[] sites;
	public static Festivalier[] festivaliers;
	public static Navette[] navettes;
	
	public static void main(String args[]) {
		
		sites = new Site[nbr_de_sites]; 
		festivaliers = new Festivalier[nbr_de_festivaliers]; 
		navettes = new Navette[nbr_de_navettes]; 
		
		creerUneListeDeSites();
		creerUneListeDeFestivaliers();
		creerUneListeDeNavettes();		
	}

	public static void creerUneListeDeSites() {
		/*
		 * is_entree : True (c'est le site d'entrée)
		 * nbr_de_sites : identifiant du site d'entrée,
		 * C'est le dernier site de l'ensemble du site
		 */
		sites[nbr_de_sites - 1] = new Site(nbr_de_sites, true);
		
		/*
		 * is_entree : false (c'est les sites oridinaires)
		 * i : identifiant du site. Il y a (nbr_de_sites - 1) sites ordinaires
		 */
		for(int i = 1; i < nbr_de_sites; i++) {
			sites[i - 1] = new Site(i, false);
		}
	}
	
	public static void creerUneListeDeFestivaliers() {
		for(int i = 0; i < nbr_de_festivaliers; i++) {
			int id_site_genere = generer_id_site_depart_festivalier();
			Site site_Arrivee_Potentiel_Festivalier = sites[id_site_genere];
			
			festivaliers[i] = new Festivalier(i, site_Arrivee_Potentiel_Festivalier, getSiteEntree());
			
			// Lancer le thread du festivalier
			festivaliers[i].start();
		}
	}
	
	public static void creerUneListeDeNavettes() {		
		for(int i = 0; i < nbr_de_navettes; i++) {	
			navettes[i] = new Navette(i, sites, getPremierSiteCircuit(), getSiteEntree(), nbr_places_navette);
			
			// Lancer le thread de la navette
			navettes[i].setDaemon(true);
			navettes[i].start();
		}
	}
	
	// Génération d'un id site compris 0 et nbr_de_sites
	public static int generer_id_site_depart_festivalier() {
		return (int) (Math.random() * nbr_de_sites);
	}	
	
	public static Site getSiteEntree() {
		return sites[nbr_de_sites - 1];
	}
	
	public static Site getPremierSiteCircuit() {
		return sites[0];
	}
	
}
