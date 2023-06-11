import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class DoodlePanel extends JPanel{
	//Variablen
	private BufferedImage hintergrund;
	private BufferedImage spielfigur;
	private BufferedImage spielfigurMitFeuerloescher;
	private BufferedImage spielfigurMitSprungschuhe;
	private BufferedImage spielfigurSchussLinks;
	private BufferedImage spielfigurSchussGerade;
	private BufferedImage spielfigurSchussRechts;
	private BufferedImage kugel;
	private BufferedImage feuerloescher;
	private BufferedImage sprungschuhe;
	private BufferedImage plattform_1;
	private BufferedImage plattform_2;
	private BufferedImage plattform_3;
	private BufferedImage gegner_1;
	private BufferedImage gegner_2;
	private BufferedImage gegner_3;
	private ArrayList<Plattform> diePlattformen;
	private ArrayList<Item> dieItems;
	private ArrayList<Schuss> dieSchuesse;
	private Spielfigur dieSpielfigur;
	private int punkte = 0;
	
	//Konstruktor
	public DoodlePanel(ArrayList<Plattform> pF, Spielfigur pDieSpielfigur, ArrayList<Item> pI, ArrayList<Schuss> pS) {
		//Referenzen werden gesetzt
		//Gian Luca
		diePlattformen = pF;
		dieSpielfigur = pDieSpielfigur;
		dieItems = pI;
		dieSchuesse = pS;
		
		//Bilder werden geladen
		//Marcel
		try {
			hintergrund = ImageIO.read(getClass().getResource("img/Hintergrund.png"));
			spielfigur = ImageIO.read(getClass().getResource("img/Figur_Rechts.png"));
			spielfigurMitFeuerloescher = ImageIO.read(getClass().getResource("img/SpielfigurMitFeuerloescher.png"));
			spielfigurMitSprungschuhe = ImageIO.read(getClass().getResource("img/SpielfigurMitSprungschuhe.png"));
			spielfigurSchussLinks = ImageIO.read(getClass().getResource("img/SpielfigurSchussLinks.png"));
			spielfigurSchussGerade = ImageIO.read(getClass().getResource("img/SpielfigurSchussGerade.png"));
			spielfigurSchussRechts = ImageIO.read(getClass().getResource("img/SpielfigurSchussRechts.png"));
			kugel = ImageIO.read(getClass().getResource("img/SchussKugel.png"));
			feuerloescher = ImageIO.read(getClass().getResource("img/Feuerloescher.png"));
			sprungschuhe = ImageIO.read(getClass().getResource("img/Sprungschuhe.png"));
			plattform_1 = ImageIO.read(getClass().getResource("img/Plattform_1.png"));
			plattform_2 = ImageIO.read(getClass().getResource("img/Plattform_2.png"));
			plattform_3 = ImageIO.read(getClass().getResource("img/Plattform_3.png"));
			gegner_1 = ImageIO.read(getClass().getResource("img/Gegner1.png"));
			gegner_2 = ImageIO.read(getClass().getResource("img/Gegner2.png"));
			gegner_3 = ImageIO.read(getClass().getResource("img/Gegner3.png"));
		
		} catch (IOException e) {
			System.out.println("Fehler beim Laden der Bilder!");
			e.printStackTrace();
		}
		
	}
	
	//Referenz auf die Punkte wird gesetzt
	//Lorenz
	public void setzePunkte(int pPunkte) {
		punkte = pPunkte;
	}
	
	//Alle das panel wird gezeichnet
	//Gian Luca
	@Override
	protected void paintComponent(Graphics g) {
		
		//Hintergrund
		g.drawImage(hintergrund, 0, 0, 531, 1062, getBackground(), null);
		
		//Plattformen + Items + Monster
		try{
			for(int i = 0; i < diePlattformen.size(); i++) {
				Plattform diePlattform = diePlattformen.get(i);
				if(diePlattform instanceof DoodlePlattformBrech) {
					g.drawImage(plattform_2, diePlattform.gibX(), diePlattform.gibY(), 90,22,null, null);
				}
				else if(diePlattform instanceof DoodlePlattformBeweg) {
					g.drawImage(plattform_3, diePlattform.gibX(), diePlattform.gibY(), 90,22,null, null);
				}
				else {
					g.drawImage(plattform_1, diePlattform.gibX(), diePlattform.gibY(), 90,22,null, null);
				}
				Item dasItem = diePlattform.gibItem();
				if (dasItem != null){
					if(dasItem instanceof DoodleItemFeuerloescher) {
						g.drawImage(feuerloescher, diePlattform.gibX() + 20, diePlattform.gibY() - 45, 40,50,null, null);
					}
					else if(dasItem instanceof DoodleItemSprungschuh) {
						g.drawImage(sprungschuhe, diePlattform.gibX() + 30, diePlattform.gibY() - 25, 30,30,null, null);
					}
				}
				Monster dasMonster = diePlattform.gibMonster();
				if(dasMonster != null) {
					if(dasMonster.gibTyp() == 0) {
						g.drawImage(gegner_1, diePlattform.gibX() + 10, diePlattform.gibY() - 42, 73,50,null, null);
					}
					else if(dasMonster.gibTyp() == 1) {
						g.drawImage(gegner_2, diePlattform.gibX() + 10, diePlattform.gibY() - 45, 73,50,null, null);
					}
					else if(dasMonster.gibTyp() == 2) {
						g.drawImage(gegner_3, diePlattform.gibX() + 6, diePlattform.gibY() - 42, 80,50,null, null);
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//Punkte
		String sPunkte = String.valueOf(punkte);
		int lange  = sPunkte.length();
		char[] punkteZeichnen = new char[lange];
		for(int i = 0; i < sPunkte.length(); i++) {
			punkteZeichnen[i] = sPunkte.charAt(i);
		}
		g.setColor(Color.LIGHT_GRAY);
		Font font = new Font("Arial Black", Font.PLAIN, 25);
        g.setFont(font);
		g.drawChars(punkteZeichnen, 0, lange, 20, 30);
		
		//Die Spielfigur
		if(dieSpielfigur.gibEffekt() == 0) {
			g.drawImage(spielfigur, dieSpielfigur.gibX(), dieSpielfigur.gibY(), 66, 75, null, null);			
		}
		else if(dieSpielfigur.gibEffekt() == 1) {
			g.drawImage(spielfigurMitFeuerloescher, dieSpielfigur.gibX() - 11, dieSpielfigur.gibY() - 6, 88, 85, null, null);
		}
		else if(dieSpielfigur.gibEffekt() == 2) {
			g.drawImage(spielfigurMitSprungschuhe, dieSpielfigur.gibX() - 4, dieSpielfigur.gibY(), 76, 83, null, null);
		}
		else if(dieSpielfigur.gibEffekt() == 3) {
			g.drawImage(spielfigurSchussLinks, dieSpielfigur.gibX(), dieSpielfigur.gibY(), 66, 75, null, null);
		}
		else if(dieSpielfigur.gibEffekt() == 4) {
			g.drawImage(spielfigurSchussGerade, dieSpielfigur.gibX(), dieSpielfigur.gibY(), 66, 75, null, null);
		}
		else if(dieSpielfigur.gibEffekt() == 5) {
			g.drawImage(spielfigurSchussRechts, dieSpielfigur.gibX(), dieSpielfigur.gibY(), 66, 75, null, null);
		}
		
		//Schuesse
		for(int i = 0; i < dieSchuesse.size(); i++) {
			Schuss derSchuss = dieSchuesse.get(i);
			g.drawImage(kugel, derSchuss.gibPosX(), derSchuss.gibPosY(),60, 60, null, null);
		}
	}

}
