package TP_Part_2;

public class Festival_Impl {
	
	public static final int nbr_de_sites = 10;
	public static final int nbr_de_festivaliers = 15;
	public static final int nbr_de_navettes = 2;
	public static final int nbr_places_navette = 3;
	public static final int duree_deplacement_navette  = 200;
	public static final int duree_arret_navette = 200;
	
	// Minimum de billets par site
	public static final int min_billets_par_site = 1;
	// Maximum de billets par site
	public static final int max_billets_par_site = 2;
	
	// Minimum de guichets par site (un minimum 1 est exigé pour le bon fonctionnement du festival)
	public static final int min_guichets_par_site = 1;
	// Maximum de guichets par site2
	public static final int max_guichets_par_site = 3;
	
	public static Site[] sites;
	public static Festivalier[] festivaliers;
	public static Navette[] navettes;
	
	public static void main(String args[]) {
		/*
		 * Instantiation et création de 3 Listes de sites, festivaliers, et navattes
		 */
		sites = new Site[nbr_de_sites]; 
		festivaliers = new Festivalier[nbr_de_festivaliers]; 
		navettes = new Navette[nbr_de_navettes]; 
		
		creerUneListeDeSites();
		creerUneListeDeFestivaliers();
		creerUneListeDeNavettes();		
	}

	public static void creerUneListeDeSites() {
		int nombre_total_billets = 0;

		// Génération d'un nombre aléatoire de billets par site
		int nbr_billets_par_site = genererUnNombreAleatoire(min_billets_par_site, max_billets_par_site);
		nombre_total_billets += nbr_billets_par_site;
		
		// Génération d'un nombre aléatoire de guichets par site 
		int nbr_guichets_par_site = genererUnNombreAleatoire(min_guichets_par_site, max_guichets_par_site);
		
		/*
		 * is_entree : True (c'est le site d'entrée)
		 * nbr_de_sites : identifiant (id) du site d'entrée,
		 * C'est le dernier site de l'ensemble des sites	
		 */
		sites[nbr_de_sites - 1] = new Site(nbr_de_sites, true, nbr_billets_par_site, nbr_guichets_par_site);
		
		/*
		 * is_entree : false (c'est les sites ordinaires)
		 * Valeur de i : identifiant du site. Il y a (nbr_de_sites - 1) sites ordinaires
		 */
		for(int i = 1; i < nbr_de_sites; i++) {
			nbr_billets_par_site = genererUnNombreAleatoire(min_billets_par_site, max_billets_par_site);
			nbr_guichets_par_site = genererUnNombreAleatoire(min_guichets_par_site, max_guichets_par_site);
			
			nombre_total_billets += nbr_billets_par_site;
			sites[i - 1] = new Site(i, false, nbr_billets_par_site, nbr_guichets_par_site);
		}
		
		System.out.println("Nombre total de billets à vendre pour tout le festival est " + nombre_total_billets +"\n*********");
	}
	
	public static void creerUneListeDeFestivaliers() {
		for(int i = 0; i < nbr_de_festivaliers; i++) {
			
			// Génération d'un site aléatoire d'arrivée potentiel d'un festivalier
			int index_site_genere = genererIndexSiteDepartFestivalier();
			Site site_Arrivee_Potentiel_Festivalier = sites[index_site_genere];
			
			/*
			 * Temps de parcours entre deux sites du circuit pour un festivalier,
			 * Composée de duree_deplacement_navette + duree_arret_navette 
			 */
			int temps_parcours_interSitesPlusArret = duree_deplacement_navette + duree_arret_navette;
			
			Site site_entree = getSiteEntree();
			// Liste de festivaliers avec id's (identifiant) compris entre 1 et nbr_de_festivaliers
			festivaliers[i] = new Festivalier(i + 1, site_Arrivee_Potentiel_Festivalier, site_entree, temps_parcours_interSitesPlusArret);
			
			// Lancer le thread du festivalier
			festivaliers[i].start();
		}
	}
	
	public static void creerUneListeDeNavettes() {		
		for(int i = 0; i < nbr_de_navettes; i++) {	
			
			// Liste de navettes avec id's (identifiant) compris entre 1 et nbr_de_navettes
			navettes[i] = new Navette(i + 1, sites, nbr_places_navette, duree_deplacement_navette, duree_arret_navette);
			
			/*
			 *  Lancer le thread de la navette avec Daemon,
			 *  La navette ou les navettes s'arrêtent s'il n'y a plus de festivaliers(ou de billets à vendre)
			 */
			navettes[i].setDaemon(true);
			navettes[i].start();
		}
	}
	
	/**
	 * Génération d'un index site compris 0 et (nbr_de_sites-1),
	 * Avec l'index généré, un site départ est sélectionné dans la liste des sites
	 * @return index généré
	 */
	public static int genererIndexSiteDepartFestivalier() {
		return (int) (Math.random() * nbr_de_sites);
	}	
	
	/**
	 * Génération d'un nombre aléatoire compris entre les deux valeurs des paramètres
	 * @return le nombre généré
	 */
	public static int genererUnNombreAleatoire(int valeur_min, int valeur_max) {
		return ((int) (Math.random() * Math.abs(valeur_max - valeur_min + 1)) + valeur_min);
	}
	
	public static Site getSiteEntree() {
		return sites[nbr_de_sites - 1];
	}
	
}
