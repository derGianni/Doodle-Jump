import java.util.Random;

public abstract class Plattform {
	int posX = 0;
	int posY = 0;
	static Random ran = new Random();
	int posGruppe = 0;
	int geschwindigkeit = 1;
	public Plattform() {
			posX = ran.nextInt(383 - 1) + 1;
			posY = ran.nextInt(100 - 1) + 1;
			posY = posY * -1;
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

	
	