package TP_Part_1;

public class Guichet {
	
	public int id_site;
	public int nbr_billets_guichet;
	
	public Guichet(int id_site) {
		this.id_site = id_site;
	}
	
	/*
	 * Billets illimités, cette méthode retourne toujours true
	 */
	public boolean acheter_un_billet() {
		return true;
	}
}
