package TP_Part_1;

public class Site {
	
	private int id_site;

	// Guichet du site
	public Guichet guichet;
	
	// l'arrêt a un état soit présence de navette(true) ou absence de navette(false);
	public boolean arret = false;
	
	// True : c'est une entrée, False : site ordinaire(pas une entrée)
	public boolean is_entree;
	
	/**
	 * Constructeur du site
	 * @param id_site : identifiant site
	 * @param is_entree : site d'entrée ou autre
	 */
	public Site(int id_site, boolean is_entree) {
		this.id_site = id_site;
		this.guichet = new Guichet(id_site);
		this.is_entree = is_entree;
	}

	/*
	 * Achat d'un billet par un client.
	 * synchronized est utilisé pour permettre l'achat 
	 * d'un seul billet à fois au niveau d'un guichet
	 */
	public synchronized void client_achete_billet() {
		guichet.acheter_un_billet();		
	}
	
	/*
	 * Faire monter des clients dans la navette s'il en reste des places libres
	 */
	public synchronized void monterClientsDansNavette(Navette navette) {
		
		/*
		 * Interdire la montée des clients vu que c'est l'entrée du festival,
		 * et appeler la méthode qui fait descencdre(vide) la navette
		 */
		if(is_entree) {
			navette.faireViderNavetteAuSiteEntree();
			
		} else {
			
			
			
		}
		
		
	}

	
	
	
//	// Le client accède à la navette 
//	public synchronized void client_accede_navette() {
//		// TODO Auto-generated method stub
//	}
	
	// Retourne l'identifiant du site
	public int retourner_id_site() {
		return this.id_site;		
	}

	

}
