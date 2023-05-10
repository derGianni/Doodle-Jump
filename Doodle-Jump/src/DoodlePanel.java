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
	private BufferedImage hintergrund;
	private BufferedImage spielfigur;
	private BufferedImage plattform_1;
	private ArrayList<DoodlePlattform> diePlattformen;
	private Spielfigur dieSpielfigur;
	private int punkte = 0;
	
	public DoodlePanel() {
		super();
		
		
		try {
			hintergrund = ImageIO.read(getClass().getResource("img/Hintergrund.png"));
			spielfigur = ImageIO.read(getClass().getResource("img/Figur_Rechts.png"));
			plattform_1 = ImageIO.read(getClass().getResource("img/Plattform_1.png"));
		} catch (IOException e) {
			System.out.println("Fehler beim Laden der Bilder!");
			e.printStackTrace();
		}
		
	}
	
	public void setzePlattformen(ArrayList<DoodlePlattform> pF)
	{
		diePlattformen = pF;
	}
	public void setzeSpielfigur(Spielfigur pDieSpielfigur) {
		dieSpielfigur = pDieSpielfigur;
	}
	public void setzePunkte(int pPunkte) {
		punkte = pPunkte;
	}
	
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		//g.drawImage(hintergrund, 0, 0, null);
		//g.drawImage(spielfigur, 828, 800, , null);
		g.drawImage(hintergrund, 0, 0, 531, 1062, getBackground(), null);
		
		try{
			for(int i = 0; i < diePlattformen.size(); i++) {
				DoodlePlattform diePlattform = diePlattformen.get(i);
				g.drawImage(plattform_1, diePlattform.gibX(), diePlattform.gibY(), 90,22,null, null);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		String sPunkte = String.valueOf(punkte);
		int länge  = sPunkte.length();
		char[] punkteZeichnen = new char[länge];
		for(int i = 0; i < sPunkte.length(); i++) {
			punkteZeichnen[i] = sPunkte.charAt(i);
		}
		g.setColor(Color.LIGHT_GRAY);
		Font font = new Font("Arial Black", Font.PLAIN, 25); // Hier wird die Schriftart "Arial" mit einer Größe von 20pt erstellt.
        g.setFont(font);
		g.drawChars(punkteZeichnen, 0, länge, 20, 30);
		
		
		g.drawImage(spielfigur, dieSpielfigur.gibX(), dieSpielfigur.gibY(), 66, 75, null, null);
		//g.drawImage(plattform_1, 0, 0, 125, 32, null, null);
	}

}
