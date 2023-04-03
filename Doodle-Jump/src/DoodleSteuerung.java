import java.util.ArrayList;
import java.util.Random;


public class DoodleSteuerung {
	private DoodleSteuerung dieDoodleSteuerung;
	private DoodlePlattform dieDoodlePlattform;
	private Spielfigur dieSpielfigur;
	private DoodlePanel dasDoodlePanel;
	private DoodleGUI dieDoodleGUI;
	private ArrayList<DoodlePlattform> diePlattformen = new ArrayList<DoodlePlattform>();
	
	public DoodleSteuerung() {
		dasDoodlePanel = new DoodlePanel();
		dieSpielfigur = new Spielfigur();
	}
	
	public DoodlePanel getPanel()
	{
		return this.dasDoodlePanel;
	}
	
	public void verarbeiteTimerEvent() {
		int minPosGruppe = 0;
		for(int i = 0; i<diePlattformen.size(); i++) {
			DoodlePlattform plattform = diePlattformen.get(i);
			plattform.bewegeRunter();
			
			if(plattform.gibPosGruppe() < minPosGruppe) {
				minPosGruppe = plattform.gibPosGruppe();
			}
			else if(minPosGruppe == 0) {
				minPosGruppe = plattform.gibPosGruppe();
			}
		}
		if(minPosGruppe > 99) {
			erzeugePlattformen();
		}
		
		System.out.println(diePlattformen.get(0).gibY());
		
		dasDoodlePanel.setzePlattformen(diePlattformen);
		dasDoodlePanel.setzeSpielfigur(dieSpielfigur);
		dasDoodlePanel.repaint();
	}
	
	public void erzeugePlattformen() {
		ArrayList<DoodlePlattform> zPlattformen = new ArrayList<DoodlePlattform>();
		
		Random ran = new Random();
		
			for(int i = 0; i < ran.nextInt(3 - 1 + 1) + 1; i++) {
				int typ = 0;
				
				if(typ == 0 || typ == 1 || typ == 2) {
					DoodlePlattform dieDoodlePlattform = new DoodlePlattform();
					int pY = dieDoodlePlattform.gibY();
					int pX = dieDoodlePlattform.gibY();
					
					for(int j = 0; j < zPlattformen.size(); j++) {
						int x = zPlattformen.get(j).gibX();
						int y = zPlattformen.get(j).gibY();
						if(!((pY > y + 32) && !(pY < y - 10))) {
							if(!((pX > x + 110) && !(pX < x - 20))) {
								erzeugePlattformen();
								return;
							}
						}
					}
					zPlattformen.add(dieDoodlePlattform);
					
				}
				else if(typ == 3) {
					
				}
				else if(typ == 4) {
					
				}
			}
			for(int i = 0; i < zPlattformen.size(); i++) {
				diePlattformen.add(zPlattformen.get(i));
			}
			//dasDoodlePanel.repaint();

				
	}

	
}
