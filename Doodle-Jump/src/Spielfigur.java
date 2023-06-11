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
	double geschwindigkeitY = 22;
	double beschleunigung = 0.3;
	DoodleGUI dieDoodleGUI;
	 
	Spielfigur(DoodleGUI pDieDoodleGUI){
		dieDoodleGUI = pDieDoodleGUI;
	}
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
		if(pEffekt >= 0 && pEffekt <= 5) {
			effekt = pEffekt;
			System.out.println(effekt);
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
				geschwindigkeitY = 12;
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
				Monster dasMonster = diePlattform.gibMonster();
				if(dasMonster != null) {
					dieDoodleGUI.verloren(); 
				}
			}
		}
		
		if(effekt == 0) {
			beschleunigung = 0.3;
		}
		else if(effekt == 1) {
			if(timer < 200) {
				timer++;
				geschwindigkeitY = 12;
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
			}
		}
		
		
		posY = posY - (int)geschwindigkeitY;
		geschwindigkeitY = geschwindigkeitY - beschleunigung;
		
		if(geschwindigkeitY < -12) {
			geschwindigkeitY = -12;
		}
		
		
		
	}
	
	
		
		
}


