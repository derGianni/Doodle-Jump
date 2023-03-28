import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlattformPanel extends JPanel {
	private BufferedImage plattform_1;
	
	public PlattformPanel() {
		super();
		try {
			plattform_1 = ImageIO.read(getClass().getResource("img/Plattform_1.png"));
		} catch (IOException e) {
			System.out.println("Fehler beim Laden der Bilder!");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		g.drawImage(plattform_1, 0, 0, 128, 500, getBackground(), getFocusCycleRootAncestor());
	}
}
