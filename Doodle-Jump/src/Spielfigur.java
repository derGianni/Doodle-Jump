import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Spielfigur {
	int posX = 203;
	int posY = 600;
	int geschwindigkeit = 5;
	int bewegungX = 0;
	double bewegungY = 20;		
		
	public int gibX() {
		return posX;
	}
		
	public int gibY() {
		return posY;
	}
	
	public void setBewege(int pBewegung) {
		// 0 = Keine 1 = Links 2 = Rechts
		bewegungX = pBewegung;
	}
	
	public void timer(ArrayList<DoodlePlattform> pDiePlattformen) {
		//Bewegungen in X-Richtung
		if (bewegungX == 1) {
			posX = posX + 5;
		}
		else if (bewegungX == 2) {
			posX = posX - 5;
		}
		
		//Bewegungen in Y-Richtung
		
		for(int i = 0; i < pDiePlattformen.size(); i++) {
			pDiePlattformen.get(i).prüfeBerührt(posX, posY, 66);
		}
		
		posY = posY - (int)bewegungY;
		bewegungY = bewegungY - 0.2;
		
		if(bewegungY < -20) {
			bewegungY = -20;
		}
		
		
		
	}
	
	
		
		
}


