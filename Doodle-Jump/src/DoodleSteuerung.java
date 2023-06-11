import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;


public class DoodleSteuerung {
	//Variablen
	private DoodleSteuerung dieDoodleSteuerung;
	private Spielfigur dieSpielfigur;
	private DoodlePanel dasDoodlePanel;
	private JPanel contentPane;
	private DoodleGUI dieDoodleGUI;
	private ArrayList<Plattform> diePlattformen = new ArrayList<Plattform>();
	private ArrayList<Item> dieItems = new ArrayList<Item>();
	private ArrayList<Schuss> dieSchuesse = new ArrayList<Schuss>();
	static Random ran = new Random();
	int punkte = 100;
	int abstand = 100;
	int schussTimer = 101;

	//Konstruktor mit Referenzen
	//Alle zusammen
	public DoodleSteuerung(Spielfigur pSpielfigur,  DoodleGUI pDieDoodleGUI) {
		dieSpielfigur = pSpielfigur;
		dieDoodleGUI = pDieDoodleGUI;
		dasDoodlePanel = new DoodlePanel(diePlattformen, dieSpielfigur, dieItems, dieSchuesse);
	}
	
	//Getter für die Referenz auf das DoodlePanel
	//Gian Luca
	public DoodlePanel getPanel(){
		return this.dasDoodlePanel;
	}
	
	//Getter für die Punkte
	//Lorenz
	public int getPunkte() {
		return punkte/100;
	}
	
	//Timer event
	//alle zusammen
	public void verarbeiteTimerEvent() {
		
		//Bewege die Spielfigur
		//Gian Luca
		dieSpielfigur.bewege(diePlattformen);
		
		//Bewegung des Spielfeldes nach unten
		//Gian Luca

		if(dieSpielfigur.gibY() < 350) {
			int ueberschuss = 350 - dieSpielfigur.gibY();
			bewegePlattformen(ueberschuss);
			dieSpielfigur.setzeY(350);
			pruefePunktestand(ueberschuss);
		}
		
		//Pruefung ob man verloren hat
		//Marcel
		pruefeVerloren();
		
		
		//Die bewegende Plattform wird bewegt
		//Lorenz + Gian Luca
		for(int i = 0; i < diePlattformen.size(); i++) {
			if(diePlattformen.get(i) instanceof DoodlePlattformBeweg) {
				DoodlePlattformBeweg diePlattform = (DoodlePlattformBeweg) diePlattformen.get(i);
				diePlattform.bewege();
			}
		}
		
		//Schuss funktion der Spielfigur
		//Lorenz
		if(dieSpielfigur.gibEffekt() == 3) {
			if(schussTimer > 10) {
				Schuss derSchuss = new Schuss(1, dieSpielfigur.gibX(), dieSpielfigur.gibY(), diePlattformen);
				dieSchuesse.add(derSchuss);
				schussTimer = 0;
			}
		}
		else if(dieSpielfigur.gibEffekt() == 4) {
			if(schussTimer > 10) {
				Schuss derSchuss = new Schuss(2, dieSpielfigur.gibX(), dieSpielfigur.gibY(), diePlattformen);
				dieSchuesse.add(derSchuss);
				schussTimer = 0;
			}
		}
		else if(dieSpielfigur.gibEffekt() == 5) {
			if(schussTimer > 10) {
				Schuss derSchuss = new Schuss(3, dieSpielfigur.gibX(), dieSpielfigur.gibY(), diePlattformen);
				dieSchuesse.add(derSchuss);
				schussTimer = 0;
			}
		}
		schussTimer++;
		
		//Bewegung der Schuesse
		//Lorenz
		for(int i = 0; i < dieSchuesse.size(); i++) {
			Schuss derSchuss = dieSchuesse.get(i);
			derSchuss.bewege();
			if(derSchuss.gibPosY() < 0 || derSchuss.gibPosX() < 0 || derSchuss.gibPosX() > 531) {
				dieSchuesse.remove(i);
			}
		}
		
		//Das Panel wird neu gezeichnet
		dasDoodlePanel.repaint();
		
	}
	
	//Errechne und setze Punkte
	//Lorenz
	public void pruefePunktestand(int ueberschuss) {
		punkte = punkte + ueberschuss;
		dasDoodlePanel.setzePunkte(punkte/100);
	}
	
	//Bewegt die Plattformen
	//Gian Luca
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
		
		//Errechne den Abstand
		if(punkte >= 10000 && punkte <= 16500) {
			abstand = punkte/100;
			System.out.println(abstand);
		}
		
		//Erzeuge neue Plattformen falls der abstand zu groß wird
		if(minPosGruppe >= abstand) {
			erzeugePlattformen();
		}
		loeschePlattformen();
	}
	
	//Prueft ob jemand verloren hat
	//Marcel
	public void pruefeVerloren() {
	    if(dieSpielfigur.gibY() > 685) {
	        dieDoodleGUI.verloren();
	      }
	}
	
	//Prueft ob die Plattformen sich gegenseitig ueberschneiden
	//Zusammen
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
	
	//Loescht die Plattformen
	//Marcel
	void loeschePlattformen() {
		for(int j = 0; j< diePlattformen.size();j++) {
			if(diePlattformen.get(j).gibY() >800){
				diePlattformen.remove(j);
			}
		}
	}
	
	//Erzeugt neue Plattformen
	//Gian Luca
	public void erzeugePlattformen() {
		ArrayList<Plattform> zPlattformen = new ArrayList<Plattform>();
		int anzahl = ran.nextInt(3 - 1) + 1;
		Plattform diePlattform;
		
			for(int i = 0; i < anzahl; i++) {
				do {
					int typ;
					if(punkte/100 < 70) {
						 typ = 3;
					}
					else if(punkte/100 < 160){
						 typ = ran.nextInt(4 - 2) + 2;
					}
					else {
						typ = ran.nextInt(4 - 1) + 1;
					}
					
					switch(typ) {
					case 1:
						diePlattform = new DoodlePlattformBrech(punkte/100);
					break;
					case 2:
						diePlattform = new DoodlePlattformBeweg(punkte/100);
					break;
					default:
						diePlattform = new Plattform(punkte/100);
					break;
					}
					 
				} while(pruefePlattformen(diePlattform));
				
				diePlattformen.add(diePlattform);
			}
		}
	
	//Giebt den Highscore zurueck
	//Gian Luca + Lorenz
	public String getHighscore() {
		int pPunkte = punkte/100;
		int highscore = 0;
		
		File file = new File("highscore");
        if (!file.exists()) {
            try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Fehler beim erstellen der Highscore-Datei");
	            return "Fehler beim schreiben des Highscors";
			}
        }
		
		try {
            BufferedReader reader = new BufferedReader(new FileReader("highscore"));
            String line = reader.readLine();
            try {
            	highscore = Integer.parseInt(line);
            }
            catch(NumberFormatException e) {
            	System.err.println("Ungültiger Wert in der Highscore-Datei: " + line);
            	highscore = 0;
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Highscore-Datei: " + e.getMessage());
        }
		if(pPunkte > highscore) {
			try {
	            BufferedWriter writer = new BufferedWriter(new FileWriter("highscore"));
	            writer.write(String.valueOf(pPunkte));
	            writer.close();
	            return String.valueOf(pPunkte);
	            
	        } catch (IOException e) {
	            System.err.println("Fehler beim Schreiben in die Datei: " + e.getMessage());
	            return "Fehler beim lesen des Highscors";
	        }
		}
		else {
			return String.valueOf(highscore);
		}
	}

	
}
