package TP_Part_2;

public class Guichet extends Thread {
	
	private int nbr_billets;
	
	public Guichet(int nbr_billets) {
		this.nbr_billets = nbr_billets;
	}
	
	public void acheter_un_billet() {

		if (nbr_billets == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		nbr_billets--;
		
		// notigy() ou notifyAl()
		
	}
}
