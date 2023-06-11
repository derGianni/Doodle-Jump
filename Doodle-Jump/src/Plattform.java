import java.util.Random;

//alle zusammen
public class Plattform {
	//Variablen
	int posX = 0;
	int posY = 0;
	static Random ran = new Random();
	int posGruppe = 0;
	int geschwindigkeit = 1;
	Item dasItem;
	Monster dasMonster;
	
	//Konstruktor 
	public Plattform(int score) {
		//Koordinaten
		posX = ran.nextInt(383 - 1) + 1;
		posY = ran.nextInt(100 - 1) + 1;
		posY = posY * -1;
		
		//Items/Monster
		int item;
		if(score <= 200) {
			item = ran.nextInt(70 - 5) + 5;
		}
		else {
			item = ran.nextInt(50 - 1) + 1;
		}
			
		switch (item){
			case 1:
				dasMonster = new Monster();
			break;
			case 2:
				dasMonster = new Monster();
			break;
			case 3:
				dasMonster = new Monster();
			break;
			case 4:
				dasMonster = new Monster();
			break;
			case 5:
				dasItem = new DoodleItemSprungschuh();
			break;
			case 6:
				dasItem = new DoodleItemFeuerloescher();
			break;
		}
	}
	
	//Getter fuer die X-Koordinate
	public int gibX() {
		return posX;
	}
	
	//Getter fuer die Y-Koordinate
	public int gibY() {
		return posY;
	}
	
	//Getter fuer die Gruppen-Koordinate
	public int gibPosGruppe() {
		return posGruppe;
	}
	
	//Getter fuer das Item
	public Item gibItem() {
		return dasItem;
	}
	
	//Getter fuer das Monster
	public Monster gibMonster() {
		return dasMonster;
	}
	
	//Loesche das Item
	public void loescheItem() {
		dasItem = null;
	}
	
	//Loesche das Monster
	public void loescheMonster() {
		dasMonster = null;
	}
	
	//Bewege die Plattformen um einen Wert runter
	public void bewegeRunter(int pWert) {
		posY = posY + pWert;
		posGruppe = posGruppe + pWert;
	}
	

	//Ueberpruefe ob etwas die Plattform beruert
	public boolean pruefeBeruehrt(int pPosX, int pPosY, int pBreit, double pBewegungY) {
		
		if(pPosX > posX) {
			if(pPosX + 18 < posX + 90) {
			if((pPosY + 75) > posY) {
				if(pPosY + 75 < posY + (pBewegungY * -1) + 1) {
					return true;
				}}
			}
		}
		else if(pPosX + pBreit > posX && pPosX + pBreit < posX + 90) {
			if(pPosY + 75 > posY) {
				if(pPosY + 75 < posY + (pBewegungY * -1) + 1) {
					return true;
				}
			}
		}
		return false;
	}
}

	
	