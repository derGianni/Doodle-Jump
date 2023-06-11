//Lorenz
public class DoodlePlattformBeweg extends Plattform{
	//Variablen
	int bewegung = 0;
	int richtung = 1;
	
	//Score wird an konstruktor uebergeben
	DoodlePlattformBeweg(int score){
		super(score);
	}
	
	//Bewegt die Plattform nach Rechts/Links
	void bewege(){
		if(richtung == 1 && bewegung <= 100) {
			posX = posX + 1;
			bewegung = bewegung +  1;
		}
		else if(richtung == 1) {
			richtung = -1;
			bewegung = bewegung -1;
			posX = posX -1;
		}
		else if(richtung == -1 && bewegung >= 0) {
			posX = posX -1;
			bewegung = bewegung -1;
		}
		else if(richtung == -1) {
			richtung = 1;
			bewegung = bewegung + 1;
			posX = posX + 1;
		}
	}
	
	

}
