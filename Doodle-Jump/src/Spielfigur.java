import java.awt.event.KeyEvent;
import java.util.Random;

public class Spielfigur {
	int posX = 203;
	int posY = 600;
	int geschwindigkeit = 5;
		
			
		
	public int gibX() {
		return posX;
	}
		
	public int gibY() {
		return posY;
	}
	public void gehRechts(){
		posX = posX + 10;
		System.out.println("Rechts");
	}
	public void gehLinks(){
		posX = posX - 10;
		System.out.println("Links");
	}
	
		
		
}


