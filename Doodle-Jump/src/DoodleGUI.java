import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;

public class DoodleGUI extends JFrame {

	private JPanel contentPane;
	private DoodlePanel dasDoodlePanel;
	private PlattformPanel dasPlattformPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoodleGUI frame = new DoodleGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DoodleGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		dasDoodlePanel = new DoodlePanel();
		contentPane.add(dasDoodlePanel, BorderLayout.CENTER);
	}

}
