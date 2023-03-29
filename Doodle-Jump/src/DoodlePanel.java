import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class DoodlePanel extends JPanel{
	private BufferedImage hintergrund;
	private BufferedImage spielfigur;
	private BufferedImage plattform_1;
	private ArrayList<DoodlePlattform> diePlattformen = new ArrayList<DoodlePlattform>();
	
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
		erzeugePlattformen();
	}
	
	public void erzeugePlattformen() {
		ArrayList<DoodlePlattform> zPlattformen = new ArrayList<DoodlePlattform>();
		Random ran = new Random();
		
			for(int i = 0; i < ran.nextInt(3 - 1 + 1) + 1; i++) {
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
			repaint();

				
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		//g.drawImage(hintergrund, 0, 0, null);
		//g.drawImage(spielfigur, 828, 800, , null);
		g.drawImage(hintergrund, 0, 0, 531, 1062, getBackground(), null);
		for(int i = 0; i < diePlattformen.size(); i++) {
			DoodlePlattform diePlattform = diePlattformen.get(i);
			g.drawImage(plattform_1, diePlattform.gibX(), diePlattform.gibY(), 90,22,null, null);
		}
		//g.drawImage(plattform_1, 0, 0, 125, 32, null, null);
	}

}
