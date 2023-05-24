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
		
		for(int i = 0; i < diePlattformen.size(); i++) {
			if(diePlattformen.get(i) instanceof DoodlePlattformBeweg) {
				DoodlePlattformBeweg diePlattform = (DoodlePlattformBeweg) diePlattformen.get(i);
				diePlattform.bewege();
			}
		}
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
	
	boolean pruefePlattformen(Plattform diePlattform) {
		for(int j = 0; j < diePlattformen.size(); j++) {
			int x = diePlattformen.get(j).gibX();
			int y = diePlattformen.get(j).gibY();
			int pY = diePlattform.gibY();
			int pX = diePlattform.gibX();

			int breite = 110;
			int hoehe = 32;
			
			if(diePlattform instanceof DoodlePlattformBeweg) {
				breite = 210;
				hoehe = 132;
			}
			
			 if(!(pY > y + hoehe)) {
				 if(!(pY < y -hoehe)) {
					 if(!(pX > x + breite)) {
						 if(!(pX < x - breite)) {
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
						diePlattform = new DoodlePlattformBeweg();
					break;
					default:
						diePlattform = new DoodlePlattform();
					break;
					}
					 
				} while(pruefePlattformen(diePlattform));
				
				diePlattformen.add(diePlattform);
				
			//dasDoodlePanel.repaint();

				
			}
		}

	
}
