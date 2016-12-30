package net.andreu.CasaSoldat;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

/**
 * Hello world!
 * 
 */
public class App extends GraphicsProgram implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7644181221282524736L;

	private static final int AMPLADA_PANTALLA = 1024;
	private static final int ALTURA_PANTALLA = 600;

	private Joc juga;

	public void init() {

		File f = new File("../../joc.txt");
		FileInputStream fitxer;
		
		try {
			if(f.exists()){
				fitxer = new FileInputStream(f);
				ObjectInputStream in = new ObjectInputStream(fitxer);
				juga = (Joc) in.readObject();
				in.close();
				fitxer.close();
				f.delete();
				juga.recuperaImatges();
				
			}else{
				juga = new Joc(AMPLADA_PANTALLA, ALTURA_PANTALLA);
			}

		} catch (Exception e) {
			
			e.printStackTrace();
		}

		setSize(AMPLADA_PANTALLA, ALTURA_PANTALLA);
		addKeyListeners();
		addMouseListeners();
	}

	public final void run() {

		GLabel marcador=new GLabel("");
		clicaPerComencar();

		while (juga.gameOver()==0) {
			juga.mou();
			
			for (Soldat s : juga.getSoldats()) {
				add(s.getImatge());
			}
			
			remove(marcador);
			marcador = juga.getMarcador();
			add(marcador);
			pause(180);
		}
		remove(marcador);
		marcador = juga.getMarcador();
		add(marcador);
		
		if(juga.gameOver()==2){
			File f = new File("../../joc.txt");
			FileOutputStream fitxer;

			try {
				fitxer = new FileOutputStream(f);
				ObjectOutputStream out = new ObjectOutputStream(fitxer);
				out.writeObject(juga);

				out.close();
				fitxer.close();
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}			
		}
		exit();
	}

	private void clicaPerComencar() {
		GLabel label = new GLabel("Clica per comenÃ§ar");
		double x = (getWidth() - label.getWidth()) / 2;
		double y = (getHeight() + label.getAscent()) / 2;
		add(label, x, y);
		waitForClick();
		remove(label);
	}

	// Event teclat
	public void keyPressed(KeyEvent e) {

		juga.evenTeclat(e.getKeyCode());
	}
	
	// Event ratoli
	@Override
	public void mouseClicked(MouseEvent e){
		
		for (Soldat s : juga.getSoldats()) {
			if(s.mHanClicat(e.getX(), e.getY())){
				juga.afegeixMorts();
			}
		}
	}
}
