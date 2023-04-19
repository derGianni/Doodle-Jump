import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;

public class DoodleGUI extends JFrame implements ActionListener,  KeyListener {

	private JPanel contentPane;
	private DoodlePanel dasDoodlePanel;
	Spielfigur dieSpielfigur;
	DoodleSteuerung dieDoodleSteuerung;
	private Timer tim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoodleGUI frame = new DoodleGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		DoodleGUI frame = new DoodleGUI();
		frame.setVisible(true);
	
	}

	/**
	 * Create the frame.
	 */
	public DoodleGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 800);
		//Das panel hat die Größe

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		dieSpielfigur = new Spielfigur();
		
		dieDoodleSteuerung = new DoodleSteuerung(dieSpielfigur);
		dieDoodleSteuerung.erzeugePlattformen();
		
		dasDoodlePanel = dieDoodleSteuerung.getPanel();
		contentPane.add(dasDoodlePanel, BorderLayout.CENTER);
		
		dieDoodleSteuerung.erzeugePlattformen();
		
		tim = new Timer(50, this);
		tim.start();
		this.addKeyListener(this);
		
		
	
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dieDoodleSteuerung.verarbeiteTimerEvent();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		
		case 39: dieSpielfigur.setBewege(1);
		break;
		case 37: dieSpielfigur.setBewege(2);
		break;
		}
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		dieSpielfigur.setBewege(0);
		
	}

}
