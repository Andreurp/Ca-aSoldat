package net.andreu.CasaSoldat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import acm.graphics.GLabel;

public class Joc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6003654590244656260L;
	
	private int AMPLADA_PANTALLA;
	private int ALTURA_PANTALLA;
	
	private GLabel marcador;
	private int morts = 0;
	private int escapats = 0;
	private transient int codiTeclat;

	List<Soldat> soldats = new ArrayList<Soldat>();

	public Joc(int AMPLADA_PANTALLA, int ALTURA_PANTALLA) {
		this.AMPLADA_PANTALLA = AMPLADA_PANTALLA;
		this.ALTURA_PANTALLA = ALTURA_PANTALLA;

		creaSoldats();
	}

	private void creaSoldats() {

		for(int i=0; i<5; i++){
			Soldat s = new Soldat(AMPLADA_PANTALLA, ALTURA_PANTALLA);
			soldats.add(s);
		}

	}
	
	public void recuperaImatges(){
		for (Soldat s : soldats){
			s.generaImatge();
		}
	}

	public List<Soldat> getSoldats() {
		return soldats;
	}

	public void mou() {
		for (Soldat s : soldats) {
			s.mou();
		}
	}

	// para el joc
	public int gameOver() {
		if( escapats > 20 || codiTeclat == 88){
			return 1;
		}else if(codiTeclat==83){
			return 2;
		}else{
			return 0;
		}
	}

	public void evenTeclat(int codi) {
		switch (codi) {
		//X
		case 88:
			codiTeclat = 88;
			break;
		//S
		case 83:
			codiTeclat = 83;
			break;
		}
		
	}
}
