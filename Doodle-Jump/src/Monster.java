import java.util.Random;

public class Monster {
	static Random ran = new Random();
	int typ = ran.nextInt(2);
	
	public int gibTyp() {
		return typ;
	}
	
	
}
