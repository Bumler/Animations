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

public class MainApp extends JFrame {

	private JPanel contentPane;
	private AnimationPanel panel_1 = new AnimationPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp frame = new MainApp();
					frame.setVisible(true);
					//frame.setSize(500, 800);
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
		
		JButton btnNewButton_1 = new JButton("Detene");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.Stop();
			}
		});
		panel.add(btnNewButton_1);
		
		JButton btnExplota = new JButton("Explota");
		btnExplota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.explode();
			}
		});
		panel.add(btnExplota);
		
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
