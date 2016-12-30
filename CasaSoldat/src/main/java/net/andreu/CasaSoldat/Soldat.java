package net.andreu.CasaSoldat;

import java.io.Serializable;
import java.util.Random;

import acm.graphics.GImage;

public class Soldat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2965721377552212627L;

	private transient GImage imatge;
	private boolean esGroc;
	

	private int posicioX;
	private int posicioY;
	private int midaFinestraX;
	@SuppressWarnings("unused")
	private int midaFinestraY;
	private int direccio;
	private int velocitat;
	private boolean escapat=false;
	private boolean mort=false;

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
		
		posicioX = (int)imatge.getLocation().getX();
		posicioY = (int)imatge.getLocation().getY();
		
		if (!mort && imatge.getLocation().getX() > midaFinestraX) {
			imatge.setLocation(0 - imatge.getBounds().getWidth(), posicioY);
			if(esGroc){
				escapat = true;
			}
		} else if (!mort && imatge.getLocation().getX() < 0 - imatge.getBounds().getWidth()) {
			imatge.setLocation(midaFinestraX, posicioY);
			if(esGroc){
				escapat = true;
			}
		}
	}
	
	public boolean sHaEscapat(){
		boolean esc = escapat;
		escapat=false;
		return esc;
	}
	
	public boolean mHanClicat(int x, int y){
		
		boolean clicat = false;
		
		if(esGroc&&(x > posicioX && x < posicioX + imatge.getWidth()) && (y > posicioY && y< posicioY + imatge.getHeight())){
			imatge.setLocation(-100,-100);
			velocitat = 0;
			mort=true;
			clicat=true;
		}
		return clicat;
	}
	
	public boolean isEsGroc() {
		return esGroc;
	}
}
