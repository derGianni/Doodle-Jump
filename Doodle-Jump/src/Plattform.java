import java.util.Random;

public abstract class Plattform {
	protected int x;
	protected int y;
	
	public int gibX() {
		return x;
	}
	
	public int gibY() {
		return y;
	}
	
	public void bewegeRunter() {
		y = y - 1;
	}
	
	public void erzeugePlattform() {
		Random ran = new Random();
		for(int i = 0; i < ran.nextInt(5 - 1 + 1) + 1; i++) {
			int typ = 0; 
			if(typ == 0 || typ == 1 || typ == 2) {
				
			}
			else if(typ == 3) {
				
			}
			else if(typ == 4) {
				
			}
		}
		
		 
	}
}

	
	