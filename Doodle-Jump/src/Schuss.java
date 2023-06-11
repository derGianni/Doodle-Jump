import java.util.ArrayList;

//Lorenz
public class Schuss {
	//Variablen
	int richtung;
	int posX;
	int posY;
	private ArrayList<Plattform> diePlattformen = new ArrayList<Plattform>();

	//Konstruktor mit Referenzen
	public Schuss(int pRichtung, int pPosX, int pPosY, ArrayList<Plattform> pDiePlattformen) {
		richtung = pRichtung;
		posX = pPosX + 10;
		posY = pPosY;
		diePlattformen = pDiePlattformen;
	}
	
	//Getter fuer die X-Koordinate
	public int gibPosX(){
		return posX;
	}
	
	//Getter fuer die Y-Koordinate
	public int gibPosY() {
		return posY;
	}
	
	//Bewege den Schuss
	public void bewege(){
		if(richtung == 1) {
			posX = posX - 10;
			posY = posY - 10;
		}
		else if(richtung == 2) {
			posY = posY - 10;
		}
		else if (richtung == 3) {
			posY = posY - 10;
			posX = posX + 10;
		}
		
		//Ueberpruefe ob ein Monster getroffen wurde
		for(int i = 0; i < diePlattformen.size(); i++) {
			Plattform diePlattform = diePlattformen.get(i);
			if(diePlattform.gibMonster() != null) {
				if(diePlattform.pruefeBeruehrt(posX, posY, 60, -10)) {
					diePlattform.loescheMonster();
				}
			}
		}
	}
}
