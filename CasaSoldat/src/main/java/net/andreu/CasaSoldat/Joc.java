package net.andreu.CasaSoldat;

import java.util.ArrayList;
import java.util.List;

public class Joc {

	private int AMPLADA_PANTALLA;
	private int ALTURA_PANTALLA;

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

	public List<Soldat> getSoldats() {
		return soldats;
	}

	public void mou() {
		for (Soldat s : soldats) {
			s.mou();
		}
	}

	// para el joc
	public boolean gameOver() {
		return true;
	}
}
