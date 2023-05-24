import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class DoodleSteuerung {
	private DoodleSteuerung dieDoodleSteuerung;
	private Spielfigur dieSpielfigur;
	private DoodlePanel dasDoodlePanel;
	private JPanel contentPane;
	private DoodleGUI dieDoodleGUI;
	private ArrayList<Plattform> diePlattformen = new ArrayList<Plattform>();
	static Random ran = new Random();
	int punkte = 0;
	
	
	
	public DoodleSteuerung(Spielfigur pSpielfigur,  DoodleGUI pDieDoodleGUI) {
		dieSpielfigur = pSpielfigur;
		dasDoodlePanel = new DoodlePanel();
		dieDoodleGUI = pDieDoodleGUI;
		
	}
	
	public DoodlePanel getPanel()
	{
		return this.dasDoodlePanel;
	}
	
	public void verarbeiteTimerEvent() {
		
		dieSpielfigur.bewege(diePlattformen);
		
		if(dieSpielfigur.gibY() < 100) {
			int ueberschuss = 100 - dieSpielfigur.gibY();
			bewegePlattformen(ueberschuss);
			dieSpielfigur.setzeY(100);
			pruefePunktestand(ueberschuss);
		}
		pruefeVerloren();
		dasDoodlePanel.setzePlattformen(diePlattformen);
		dasDoodlePanel.setzeSpielfigur(dieSpielfigur);
		
		dasDoodlePanel.repaint();
	}
	
	public void pruefePunktestand(int ueberschuss) {
		punkte = punkte + ueberschuss;
		//System.out.println(punkte/100 + " " + ueberschuss); 
		dasDoodlePanel.setzePunkte(punkte/100);
	}
	
	public void bewegePlattformen(int pUeberschuss) {
		int minPosGruppe = 0;
		for(int i = 0; i<diePlattformen.size(); i++) {
			Plattform plattform = diePlattformen.get(i);
			plattform.bewegeRunter(pUeberschuss);
			
			if(plattform.gibPosGruppe() < minPosGruppe) {
				minPosGruppe = plattform.gibPosGruppe();
			}
			else if(minPosGruppe == 0) {
				minPosGruppe = plattform.gibPosGruppe();
			}
		}
		if(minPosGruppe > 99) {
			erzeugePlattformen();
		}
		loeschePlattformen();
	}
	

	
	public void pruefeVerloren() {
	    if(dieSpielfigur.gibY() > 755) {
	        System.out.println("Verloren");
	        dieDoodleGUI.verloren();
	      }
	    
	}
	
	boolean pruefePlattformen(int pX, int pY) {
		for(int j = 0; j < diePlattformen.size(); j++) {
			int x = diePlattformen.get(j).gibX();
			int y = diePlattformen.get(j).gibY();
			System.out.println("test");

			 if(!(pY > y + 32)) {
				 if(!(pY < y -32)) {
					 if(!(pX > x + 110)) {
						 if(!(pX < x -110)) {
							 return true;
						 }
					 }
				 }
			 }
		}
		return false;
	}
	void loeschePlattformen() {
		for(int j = 0; j< diePlattformen.size();j++) {
			
			if(diePlattformen.get(j).gibY() >800){
				diePlattformen.remove(j);
			
			}
	}
	}
	public void erzeugePlattformen() {
		ArrayList<Plattform> zPlattformen = new ArrayList<Plattform>();
		
		int anzahl = ran.nextInt(3 - 1) + 1;
		Plattform diePlattform;
		
			for(int i = 0; i < anzahl; i++) {
				
				
				do {
					int typ = ran.nextInt(4 - 1) + 1;
					switch(typ) {
					case 1:
						diePlattform = new DoodlePlattformBrech();
					break;
					case 2:
						diePlattform = new DoodlePlattformBrech2();
					break;
					default:
						diePlattform = new DoodlePlattform();
					break;
					}
					 
				} while(pruefePlattformen(diePlattform.gibX(), diePlattform.gibY()));
				
				diePlattformen.add(diePlattform);
				
			//dasDoodlePanel.repaint();

				
			}
		}

	
}
