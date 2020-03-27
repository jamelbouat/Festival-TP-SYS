package TP_Part_1;

public class Guichet {
	
	private int nombre_billets;
	
	public Guichet(int nombre_billets) {

		this.nombre_billets = nombre_billets;
	}


	public void acheter_un_billet() {

		while(nombre_billets == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		nombre_billets--;
		
		// notigy() ou notifyAl()
;	}
}
