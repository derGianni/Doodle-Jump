import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;

public class DoodleGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private DoodlePanel dasDoodlePanel;
	private PlattformPanel dasPlattformPanel;
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
		//Das panel hat die Größe 473 x 750
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		
		
		dieDoodleSteuerung = new DoodleSteuerung();
		dieDoodleSteuerung.erzeugePlattformen();
		
		dasDoodlePanel = dieDoodleSteuerung.getPanel();
		contentPane.add(dasDoodlePanel, BorderLayout.CENTER);
		
		dieDoodleSteuerung.erzeugePlattformen();
		
		
		tim = new Timer(50, this);
		tim.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dieDoodleSteuerung.verarbeiteTimerEvent();
	}

}
