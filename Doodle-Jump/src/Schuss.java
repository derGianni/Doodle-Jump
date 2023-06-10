import java.util.ArrayList;

public class Schuss {
	int richtung;
	int posX;
	int posY;
	private ArrayList<Plattform> diePlattformen = new ArrayList<Plattform>();

	
	Schuss(int pRichtung, int pPosX, int pPosY, ArrayList<Plattform> pDiePlattformen) {
		richtung = pRichtung;
		posX = pPosX;
		posY = pPosY;
		diePlattformen = pDiePlattformen;
	}
	
	public int gibPosX(){
		return posX;
	}
	public int gibPosY() {
		return posY;
	}
	
	
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
		
		for(int i = 0; i < diePlattformen.size(); i++) {
			Plattform diePlattform = diePlattformen.get(i);
			if(diePlattform.gibItem() != null || diePlattform.gibMonster() != null) {
				if(diePlattform.pruefeBeruehrt(posX, posY, 10, -10)) {
					diePlattform.loescheItem();
					diePlattform.loescheGegner();
				}
				
			}
		}
	}
}
