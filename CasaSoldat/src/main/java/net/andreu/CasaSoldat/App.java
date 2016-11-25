package net.andreu.CasaSoldat;

import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

/**
 * Hello world!
 * 
 */
public class App extends GraphicsProgram {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7644181221282524736L;

	private static final int AMPLADA_PANTALLA = 1024;
	private static final int ALTURA_PANTALLA = 600;
	
	private Joc juga;

	public void init() {
		
		juga = new Joc(AMPLADA_PANTALLA, ALTURA_PANTALLA);

		setSize(AMPLADA_PANTALLA, ALTURA_PANTALLA);

	}

	public final void run() {

		clicaPerComencar();
		
		for (Soldat s : juga.getSoldats()) {
			add(s.getImatge());
		}
		while(true){
			juga.mou();
			pause(100);
		}
		
	}
	
	private void clicaPerComencar() {
		GLabel label = new GLabel("Clica per comenÃ§ar");
		double x = (getWidth() - label.getWidth()) / 2;
		double y = (getHeight() + label.getAscent()) / 2;
		add(label, x, y);
		waitForClick();
		remove(label);
	}
}
