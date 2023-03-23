import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DoodlePanel extends JPanel{
	private BufferedImage hintergrund;
	private BufferedImage spielfigur;
	
	public DoodlePanel() {
		super();
		try {
			hintergrund = ImageIO.read(getClass().getResource("img/Hintergrund.png"));
			spielfigur = ImageIO.read(getClass().getResource("img/Figur_Rechts.png"));
		} catch (IOException e) {
			System.out.println("Fehler beim Laden der Bilder!");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		//g.drawImage(hintergrund, 0, 0, null);
		//g.drawImage(spielfigur, 828, 800, , null);
		g.drawImage(hintergrund, 0, 0, 531, 1062, getBackground(), getFocusCycleRootAncestor());
	}

}
