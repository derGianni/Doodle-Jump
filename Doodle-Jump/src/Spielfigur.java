import java.awt.event.KeyEvent;
import java.util.Random;

public class Spielfigur {
	int posX = 203;
	int posY = 600;
	int geschwindigkeit = 5;
	int bewegungX = 0;
	int bewegungY = 100;		
		
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
	
	public void timer() {
		//Bewegungen in X-Richtung
		if (bewegungX == 1) {
			posX = posX + 10;
		}
		else if (bewegungX == 2) {
			posX = posX - 10;
		}
		
		//Bewegungen in Y-Richtung
		posY = posY - bewegungY;
		bewegungY = bewegungY - 10;
		
		
		
		
	}
	
	
		
		
}


