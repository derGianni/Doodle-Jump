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
	
	public void bewegeRunter() {
		posY = posY + geschwindigkeit;
		posGruppe = posGruppe + geschwindigkeit;
	}
	
	public boolean pruefeBeruehrt(int pPosX, int pPosY, int pBreit) {
		if(((pPosX > posX && pPosX < pPosX + 99)||(pPosX + pBreit > posX && pPosX + pBreit < pPosX + 99))&&(pPosY + 75 == posY)) {
			System.out.println("Ber�hrt");
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
}

	
	