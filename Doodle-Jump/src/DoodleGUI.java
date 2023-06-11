import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

public class DoodleGUI extends JFrame implements ActionListener, KeyListener {

	private JPanel contentPane;
	private DoodlePanel dasDoodlePanel;
	Spielfigur dieSpielfigur;
	DoodleSteuerung dieDoodleSteuerung;
	DoodleGUI dieDoodleGUI;
	private Timer tim;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*
		 * EventQueue.invokeLater(new Runnable() { public void run() { try { DoodleGUI
		 * frame = new DoodleGUI(); frame.setVisible(true); } catch (Exception e) {
		 * e.printStackTrace(); } } });
		 */
		//Die Doodle GUI wird erstellt und sichtbar gemacht
		DoodleGUI dieDoodleGUI = new DoodleGUI();
		dieDoodleGUI.setVisible(true);


	}


	/**
	 * Create the frame.
	 */
	//alle zusammen
	public DoodleGUI() {
		//Setze groeße des Fensters
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 800);
		
		//Die content Pane wird erstellt
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//Die Spielfigur wird erstellt
		dieSpielfigur = new Spielfigur(this);

		//Die Steuerung wird erstellt und bekommt die Referenz auf die Spielfigur
		dieDoodleSteuerung = new DoodleSteuerung(dieSpielfigur, this);
		
		//Das panel wird aus der Steuerung geholt
		dasDoodlePanel = dieDoodleSteuerung.getPanel();

		//Der Start button wird erstellt und angezeigt
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(203, 400, 80, 40);
		btnStart.setFocusable(false);
		contentPane.add(btnStart, null);
		
		//Der action listener fuer den start button
		btnStart.addActionListener(new ActionListener() {
			//Wenn start gedrueckt wird, wird der timer gestartet und der button unsichtbar gemacht
			public void actionPerformed(ActionEvent e) {
				tim.start();
				btnStart.setVisible(false);
			}
		});
		
		//Das doodle panel wird der content pane zugewiesen
		contentPane.add(dasDoodlePanel);

		//Es werden einmal Plattformen erzeugt
		dieDoodleSteuerung.erzeugePlattformen();
		
		//Der timer wird gestartet
		tim = new Timer(10, this);
		this.addKeyListener(this);

	}
	
	//Wenn verloren wird ein Dialog angezeigt auf dem das Fenster geschlossen oder neugestartet werden kann
	//Lorenz
	public void verloren() {
		UIManager.put("OptionPane.yesButtonText", "Neustart");
		UIManager.put("OptionPane.noButtonText", "Schliessen");
		int wahl = JOptionPane.showConfirmDialog(contentPane, "Leider verloren \nErreichte Punkte: " +  dieDoodleSteuerung.getPunkte() + " \nHighscore: "+ dieDoodleSteuerung.getHighscore(), "Verloren", JOptionPane.YES_NO_OPTION);
		if (wahl == JOptionPane.YES_OPTION) {

			tim.stop();
			dispose();
			dieDoodleGUI = new DoodleGUI();
			dieDoodleGUI.setVisible(true);

		} else {
			System.exit(0);
		}
	}

	//Action listener fuer den Timer
	//alle zusammen
	@Override
	public void actionPerformed(ActionEvent e) {
		dieDoodleSteuerung.verarbeiteTimerEvent();
	}
	
	//Key listener
	//Marcel
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {

		//Bewegung der Figur
		case 39:
			dieSpielfigur.setzeBewegungX(1);
			break;
		case 37:
			dieSpielfigur.setzeBewegungX(2);
			break;
			
		//Pause/Weiter
		case 27:
			if (tim.isRunning()) {
				tim.stop();
			} else {
				tim.start();
			}
			break;
			
		//Schießen
		case 87:
			if(dieSpielfigur.gibEffekt() == 0) {
				dieSpielfigur.setzeEffekt(4);
			}
			break;
		case 65:
			if(dieSpielfigur.gibEffekt() == 0) {
				dieSpielfigur.setzeEffekt(3);
			}
			break;
		case 68:
			if(dieSpielfigur.gibEffekt() == 0) {
				dieSpielfigur.setzeEffekt(5);
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//Bewegung stoppen
		if(e.getKeyCode() == 39 || e.getKeyCode() == 37) {
			dieSpielfigur.setzeBewegungX(0);
		}
		//Schießen stoppen
		else {
			if(dieSpielfigur.gibEffekt() == 3 || dieSpielfigur.gibEffekt() == 4 || dieSpielfigur.gibEffekt() == 5) {
				dieSpielfigur.setzeEffekt(0);
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {		
	}

}
