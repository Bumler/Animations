import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class MainApp extends JFrame {

	private JPanel contentPane;
	private AnimationPanel panel_1 = new AnimationPanel();
	private JTextField levelText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp frame = new MainApp();
					frame.setSize(380, 400);
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
	public MainApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		panel.setLayout(new GridLayout(10, 3, 0, 0));
		
		JButton btnNewButton = new JButton("Empeza");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_1.Start();
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Pausa");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.Stop();
			}
		});
		panel.add(btnNewButton_1);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		levelText = new JTextField();
		levelText.setText("Level:             1");
		panel.add(levelText);
		levelText.setColumns(10);
		
		panel_1 = new AnimationPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		JPanel panel_7 = new JPanel();
		contentPane.add(panel_7, BorderLayout.SOUTH);
		
		JSplitPane splitPane = new JSplitPane();
		panel_7.add(splitPane);
		
		JButton leftBtn = new JButton("<--");
		leftBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.moveLeft();
			}
		});
		splitPane.setLeftComponent(leftBtn);
		
		JButton rightBtn = new JButton("-->");
		rightBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.moveRight();
			}
		});
		splitPane.setRightComponent(rightBtn);
	}

	
}
