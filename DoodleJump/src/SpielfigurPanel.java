import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SpielfigurPanel extends JPanel {
	private BufferedImage plattform_1;
	/**
	 * Create the panel.
	 */
	public SpielfigurPanel() {
		super();
		try {
			plattform_1 = ImageIO.read(getClass().getResource("img/Plattform_1.png"));
		} catch (IOException e) {
			System.out.println("Fehler beim Laden der Bilder!");
			e.printStackTrace();
		}
		erzeugeSpielfigur();


	}

}
