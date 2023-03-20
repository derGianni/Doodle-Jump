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
			hintergrund = ImageIO.read(getClass().getResource("img/hintergrund.png"));
			spielfigur = ImageIO.read(getClass().getResource("img/spielfigur.png"));
		} catch (IOException e) {
			System.out.println("Fehler beim Laden der Bilder!");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(hintergrund, 0, 0, null);
		g.drawImage(spielfigur, 0, 700, 50, 50, null);
		
	}

}
