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
	public void setzeY(int pPosY) {
		posY = pPosY;
	}
	
	public void setBewege(int pBewegung) {
		// 0 = Keine 1 = Links 2 = Rechts
		bewegungX = pBewegung;
	}
	
	public void bewege(ArrayList<Plattform> pDiePlattformen) {
		//Bewegungen in X-Richtung
		if (bewegungX == 1) {
			posX = posX + 5;
		}
		else if (bewegungX == 2) {
			posX = posX - 5;
		}
		if (posX > 408) {
			posX = 409;
		}
		else if (posX < 1) {
			posX = 0;
		}
		
		//Bewegungen in Y-Richtung
		
		for(int i = 0; i < pDiePlattformen.size(); i++) {
			if(pDiePlattformen.get(i).pruefeBeruehrt(posX, posY, 66, bewegungY)) {
				bewegungY = 10;
				if(pDiePlattformen.get(i) instanceof DoodlePlattformBrech) {
					pDiePlattformen.remove(i);
				}
			}
		}
		
		posY = posY - (int)bewegungY;
		bewegungY = bewegungY - 0.2;
		
		if(bewegungY < -10) {
			bewegungY = -10;
		}
		
		
		
	}
	
	
		
		
}


