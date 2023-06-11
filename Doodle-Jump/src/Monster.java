//Marcel
import java.util.Random;
public class Monster {
	//Der typ wird zufaellig ermittelt
	static Random ran = new Random();
	int typ = ran.nextInt(3);
	
	//Getter fuer den typ
	public int gibTyp() {
		return typ;
	}
	
	
}
