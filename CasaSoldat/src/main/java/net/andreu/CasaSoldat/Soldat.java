package net.andreu.CasaSoldat;

import java.util.Random;

import acm.graphics.GImage;

public class Soldat {

	private GImage imatge;
	private boolean esGroc;
	private int posicioX;
	private int posicioY;
	private int midaFinestraX;
	private int midaFinestraY;
	private int direccio;
	private int velocitat;

	private Random rand = new Random();

	public Soldat(int midaFinestraX, int midaFinestraY) {
		super();
		this.midaFinestraX = midaFinestraX;
		this.midaFinestraY = midaFinestraY;
		posicioX = rand.nextInt(midaFinestraX - 100);
		posicioY = rand.nextInt(midaFinestraY - 100);
		velocitat = rand.nextInt(11) + 5;

		if (rand.nextInt(10) < 7) {
			esGroc = true;
		} else {
			esGroc = false;
		}

		if (rand.nextBoolean() == true) {
			direccio = 1;
		} else {
			direccio = -1;
		}
		generaImatge();
	}

	public void generaImatge() {
		String img = "";

		if (esGroc == true) {
			if (direccio > 0) {
				img = "cavallerGD.png";
			} else {
				img = "cavallerGE.png";
			}
		} else {
			if (direccio > 0) {
				img = "cavallerVD.png";
			} else {
				img = "cavallerVE.png";
			}
		}
		
		imatge = new GImage(img, posicioX, posicioY);
	}

	public GImage getImatge() {
		return imatge;
	}

	public void mou() {
		
		imatge.move(direccio * velocitat, 0);

	}

}
