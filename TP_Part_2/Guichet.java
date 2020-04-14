package TP_Part_2;

public class Guichet extends Thread{
	
	public int id_guichet;
	public Site site_du_guichet;
	
	public Guichet(int id_guichet, Site site_du_guichet) {
		this.id_guichet = id_guichet;
		this.site_du_guichet = site_du_guichet;
	}
	
	public int getIdGuichet() {
		return id_guichet;
	}

	public synchronized boolean acheter_un_billet() {
		
		if (this.site_du_guichet.getNbrBilletsDuSite() > 0) {
			this.site_du_guichet.setNbrBilletsDuSite(-1);
			return true;
			
		} else {
			return false;
		}
	}
}
