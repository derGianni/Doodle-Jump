import java.util.Random;

public abstract class Plattform {
	int posX = 0;
	int posY = 0;
	int posGruppe = 0;
	public Plattform() {
		Random ran = new Random();
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
		posY = posY + 1;
		posGruppe++;
	}
	
	
}

	
	