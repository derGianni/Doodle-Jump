import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class Spielfigur {
	//Variablen
	int posX = 203;
	int posY = 600;
	//0 = kein effekt, 1 = Feuerloescher, 2 = Sprungschuh, 3,4,5 = Schiessen
	int effekt= 0;
	int geschwindigkeitX = 0;
	int timer = 0;
	double geschwindigkeitY = 22;
	double beschleunigung = 0.3;
	DoodleGUI dieDoodleGUI;
	
	//Konstruktor mit Referenzen
	Spielfigur(DoodleGUI pDieDoodleGUI){
		dieDoodleGUI = pDieDoodleGUI;
	}
	
	//Getter fuer die X-Koordinate
	public int gibX() {
		return posX;
	}
		
	//Getter fuer die Y-Koordinate
	public int gibY() {
		return posY;
	}
	
	//Getter fuer die den Effekt
	public int gibEffekt() {
		return effekt;
	}
	
	//Setter fuer die Y-Koordinate
	public void setzeY(int pPosY) {
		posY = pPosY;
	}
	
	//Setter fuer die Bewegung
	public void setzeBewegungX(int pBewegungX) {
		// 0 = Keine 1 = Links 2 = Rechts
		geschwindigkeitX = pBewegungX;
	}
	
	//Setter fuer die Bewegung
	public void setzeBewegungY(int pBewegungY) {
		geschwindigkeitY = pBewegungY;
	}
	//Setter fuer den Effekt
	public void setzeEffekt(int pEffekt) {
		if(pEffekt >= 0 && pEffekt <= 5) {
			effekt = pEffekt;
		}
	}
	
	//Bewegung der Figur
	public void bewege(ArrayList<Plattform> pDiePlattformen) {
		//Lorenz
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
		
		//Beruerung mit einer Plattform
		//Gian Luca
		for(int i = 0; i < pDiePlattformen.size(); i++) {
			Plattform diePlattform = pDiePlattformen.get(i);
			if(diePlattform.pruefeBeruehrt(posX, posY, 66, geschwindigkeitY)) {
				geschwindigkeitY = 13;
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
		
		//Verhalten bei Effekten
		//Gian Luca
		if(effekt == 0) {
			beschleunigung = 0.3;
		}
		else if(effekt == 1) {
			if(timer < 200) {
				timer++;
				geschwindigkeitY = 13;
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
		
		//Bewegung in Y-Richtung
		//Lorenz
		posY = posY - (int)geschwindigkeitY;
		geschwindigkeitY = geschwindigkeitY - beschleunigung;
		
		if(geschwindigkeitY < -13) {
			geschwindigkeitY = -13;
		}
	}	
}


