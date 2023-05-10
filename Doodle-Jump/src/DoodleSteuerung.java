import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class DoodleSteuerung {
	private DoodleSteuerung dieDoodleSteuerung;
	private DoodlePlattform dieDoodlePlattform;
	private Spielfigur dieSpielfigur;
	private DoodlePanel dasDoodlePanel;
	private JPanel contentPane;
	private ArrayList<DoodlePlattform> diePlattformen = new ArrayList<DoodlePlattform>();
	static Random ran = new Random();
	int punkte = 0;
	
	
	public DoodleSteuerung(Spielfigur pSpielfigur,  JPanel pContentPane) {
		dieSpielfigur = pSpielfigur;
		dasDoodlePanel = new DoodlePanel();
		contentPane = pContentPane;
		
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
			DoodlePlattform plattform = diePlattformen.get(i);
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
	}
	public void pruefeVerloren() {
	    if(dieSpielfigur.gibY() > 950) {
	        System.out.println("Verloren");
	        UIManager.put("OptionPane.yesButtonText", "Neustart");
	        UIManager.put("OptionPane.noButtonText", "Schliessen");
	      int wahl =  JOptionPane.showConfirmDialog(contentPane, "Verloren", "verloren", JOptionPane.YES_NO_OPTION);
	      if(wahl == JOptionPane.YES_OPTION) {
	      
	      }
	      else {
	    	System.exit(0);  
	      }
	      }
	    
	}
	
	
	public void erzeugePlattformen() {
		ArrayList<DoodlePlattform> zPlattformen = new ArrayList<DoodlePlattform>();
		
		int random = ran.nextInt(3 - 1) + 1;
		
			for(int i = 0; i < random; i++) {
				int typ = 0;
				
				if(typ == 0 || typ == 1 || typ == 2) {
					DoodlePlattform dieDoodlePlattform = new DoodlePlattform();
					int pY = dieDoodlePlattform.gibY();
					int pX = dieDoodlePlattform.gibY();
					
					for(int j = 0; j < zPlattformen.size(); j++) {
						int x = zPlattformen.get(j).gibX();
						int y = zPlattformen.get(j).gibY();
						if(!((pY > y + 32) && !(pY < y - 10))) {
							if(!((pX > x + 110) && !(pX < x - 20))) {
								erzeugePlattformen();
								return;
							}
						}
					}
					/*
					for(int j = 0; j < diePlattformen.size(); j++) {
						int x = diePlattformen.get(j).gibX();
						int y = diePlattformen.get(j).gibY();
						if(!((pY > y + 32) && !(pY < y - 10))) {
							System.out.println((pY > y + 32) + " " + pY + " " + y);
							if(!((pX > x + 110) && !(pX < x - 20))) {
								//----
								erzeugePlattformen();
								return;
							}
						}
					}
					*/
					

					zPlattformen.add(dieDoodlePlattform);
					
				}
				else if(typ == 3) {
					
				}
				else if(typ == 4) {
					
				}
			}
			for(int i = 0; i < zPlattformen.size(); i++) {
				diePlattformen.add(zPlattformen.get(i));
			}
			//dasDoodlePanel.repaint();

				
	}

	
}
