import java.util.Random;

public abstract class Plattform {
	Random ran = new Random();
	int posX = ran.nextInt(100 - 1) + 1;
	int posY = ran.nextInt(100 - 1) + 1;
	
	public int gibX() {
		return posX;
	}
	
	public int gibY() {
		return posY;
	}
	
	public void bewegeRunter() {
		posY = posY - 1;
	}
	
	
}

	
	