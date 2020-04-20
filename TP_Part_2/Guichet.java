package TP_Part_2;

public class Guichet {
	
	public int id_guichet;
	public Site site_du_guichet;
	
	public Guichet(int id_guichet, Site site_du_guichet) {
		this.id_guichet = id_guichet;
		this.site_du_guichet = site_du_guichet;
	}
	
	public int getIdGuichet() {
		return id_guichet;
	}

	/**
	 * Vérifie s'il y a des billets au niveau du site,
	 * et mis à jour le jour le nombre de billets
	 * synchronized est utilisé pour permettre l'achat d'un seul billet à la fois
	 * @return true si achat est effectué, sinon false
	 */
	public synchronized boolean acheterUnBillet() {
		
		if (this.site_du_guichet.getNbrBilletsDuSite() >= 1) {
			this.site_du_guichet.setNbrBilletsDuSite(-1);
			return true;
			
		} else {
			return false;
		}
	}
}
