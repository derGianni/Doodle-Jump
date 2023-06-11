import java.util.Random;

public class Plattform {
	int posX = 0;
	int posY = 0;
	static Random ran = new Random();
	int posGruppe = 0;
	int geschwindigkeit = 1;
	Item dasItem;
	Monster dasMonster;
	public Plattform(int score) {
			posX = ran.nextInt(383 - 1) + 1;
			posY = ran.nextInt(100 - 1) + 1;
			posY = posY * -1;
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
	
	public int gibX() {
		return posX;
	}
	
	public int gibY() {
		return posY;
	}
	
	public int gibPosGruppe() {
		return posGruppe;
	}
	
	public Item gibItem() {
		return dasItem;
	}
	
	public Monster gibMonster() {
		return dasMonster;
	}
	
	public void loescheItem() {
		dasItem = null;
	}
	
	public void loescheGegner() {
		dasMonster = null;
	}
	
	public void bewegeRunter(int pWert) {
		posY = posY + pWert;
		posGruppe = posGruppe + pWert;
	}
	

	
	public boolean pruefeBeruehrt(int pPosX, int pPosY, int pBreit, double pBewegungY) {
		
		if(pPosX > posX) {
			if(pPosX + 18 < posX + 90) {
			if((pPosY + 75) > posY) {
				if(pPosY + 75 < posY + (pBewegungY * -1)) {
					
					return true;
					
				}}
			}
		}
		else if(pPosX + pBreit > posX && pPosX + pBreit < posX + 90) {
			if(pPosY + 75 > posY) {
				if(pPosY + 75 < posY + (pBewegungY * -1)) {
					
					return true;
					
				}
			}
		}
			
		return false;
		
	}
	
	
}

	
	