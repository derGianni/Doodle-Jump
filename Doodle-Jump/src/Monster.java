import java.util.Random;

public class Monster {
	static Random ran = new Random();
	int typ = ran.nextInt(3);
	
	public int gibTyp() {
		return typ;
	}
	
	
}
