import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class PlattformPanel extends JPanel {
	private BufferedImage plattform_1;
	public DoodlePlattform[] diePlattformen = {null};
	
	public PlattformPanel() {
		super();
		try {
			plattform_1 = ImageIO.read(getClass().getResource("img/Plattform_1.png"));
		} catch (IOException e) {
			System.out.println("Fehler beim Laden der Bilder!");
			e.printStackTrace();
		}
		erzeugePlattformen();
	}
	
	public void erzeugePlattformen() {
		Random ran = new Random();
			for(int i = 0; i < ran.nextInt(5 - 1 + 1) + 1; i++) {
				int typ = 0;
				
				if(typ == 0 || typ == 1 || typ == 2) {
					DoodlePlattform dieDoodlePlattform = new DoodlePlattform();
					diePlattformen[0] = dieDoodlePlattform;
				}
				else if(typ == 3) {
					
				}
				else if(typ == 4) {
					
				}
			}
			
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		for(int i = 0; i < diePlattformen.length; i++) {
			DoodlePlattform diePlattform = diePlattformen[i];
			g.drawImage(plattform_1, diePlattform.gibX(), diePlattform.gibY(), 128,500,getBackground(), null);
		}
		g.drawImage(plattform_1, 0, 0, 128, 500, getBackground(), getFocusCycleRootAncestor());
	}
}
