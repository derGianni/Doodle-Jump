import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;


public class Spielfigur {
	
	int posX = 203;
	int posY = 600;
	//0 = kein effekt, 1 = Feuerloescher, 2 = Sprungschuh
	int effekt= 0;
	int geschwindigkeitX = 0;
	int timer = 0;
	double geschwindigkeitY = 20;
	double beschleunigung = 0.2;
	
	public int gibX() {
		return posX;
	}
		
	public int gibY() {
		return posY;
	}
	
	public int gibEffekt() {
		return effekt;
	}
	public void setzeY(int pPosY) {
		posY = pPosY;
	}
	
	public void setzeBewegungX(int pBewegungX) {
		// 0 = Keine 1 = Links 2 = Rechts
		geschwindigkeitX = pBewegungX;
	}
	
	public void setzeBewegungY(int pBewegungY) {
		geschwindigkeitY = pBewegungY;
	}
	
	public void setzeEffekt(int pEffekt) {
		if(pEffekt >= 0 && pEffekt <= 3) {
			effekt = pEffekt;
		}
	}
	
	public void bewege(ArrayList<Plattform> pDiePlattformen) {
		//Bewegungen in X-Richtung
		if (geschwindigkeitX == 1) {
			posX = posX + 5;
		}
		else if (geschwindigkeitX == 2) {
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
			Plattform diePlattform = pDiePlattformen.get(i);
			if(diePlattform.pruefeBeruehrt(posX, posY, 66, geschwindigkeitY)) {
				geschwindigkeitY = 10;
				if(diePlattform instanceof DoodlePlattformBrech) {
					pDiePlattformen.remove(i);
				}
				Item dasItem = diePlattform.gibItem();
				if(dasItem != null) {
					if(effekt == 0) {
						dasItem.setzeEffekt(this);
						diePlattform.loescheItem();
					}
				}
			}
		}
		
		if(effekt == 1) {
			if(timer < 200) {
				timer++;
				geschwindigkeitY = 10;
			}
			else {
				timer = 0;
				effekt = 0;
			}
		}
		else if(effekt == 2) {
			if(timer < 500) {
				timer++;
				beschleunigung = 0.1;
			}
			else {
				timer = 0;
				effekt = 0;
				beschleunigung = 0.2;
			}
		}
		
		
		posY = posY - (int)geschwindigkeitY;
		geschwindigkeitY = geschwindigkeitY - beschleunigung;
		
		if(geschwindigkeitY < -10) {
			geschwindigkeitY = -10;
		}
		
		
		
	}
	
	
		
		
}


