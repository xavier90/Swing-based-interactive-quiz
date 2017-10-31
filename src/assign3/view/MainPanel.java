package assign3.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JFrame {

	private JPanel contentPane;
	private JButton btnStartQuiz;
	private JRadioButton rdbtnAstronomy;
	private JRadioButton rdbtnEconomic;
	private JRadioButton rdbtnHealth;

	private QuestionPanel questionPanel;
	static MainPanel frame;
	ButtonGroup buttonGroup;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainPanel();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainPanel() {

		
		initComponents();
		createEvents();
			
	}

	//create and initialize the components
	private void initComponents() {
		
		setTitle("Quiz");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//add Start Quiz button
		btnStartQuiz = new JButton("Start Quiz");
		btnStartQuiz.setBounds(511, 452, 184, 50);
		contentPane.add(btnStartQuiz);
		
		//add button for three topic
		rdbtnAstronomy = new JRadioButton("Astronomy");
		rdbtnAstronomy.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		rdbtnAstronomy.setBounds(320, 180, 141, 23);
		contentPane.add(rdbtnAstronomy);
		
		rdbtnEconomic = new JRadioButton("Economic");
		rdbtnEconomic.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		rdbtnEconomic.setBounds(320, 230, 141, 23);
		contentPane.add(rdbtnEconomic);
		
		rdbtnHealth = new JRadioButton("Health");
		rdbtnHealth.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		rdbtnHealth.setBounds(320, 280, 141, 23);
		contentPane.add(rdbtnHealth);
		
		//make sure the user can only choose one JRadioButton in total
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnAstronomy);
		buttonGroup.add(rdbtnEconomic);
		buttonGroup.add(rdbtnHealth);
		
		//welcome information
		JLabel lblNewLabel = new JLabel("Welcome to your quiz! Good luck!");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(240, 45, 506, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblAuthorYaojianWang = new JLabel("Author: Yaojian Wang");
		lblAuthorYaojianWang.setFont(new Font("SansSerif", Font.PLAIN, 8));
		lblAuthorYaojianWang.setBounds(708, 557, 86, 15);
		contentPane.add(lblAuthorYaojianWang);
	
		
	}
	
	private void createEvents() {
		btnStartQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnAstronomy.isSelected()) {
					questionPanel = new QuestionPanel("astronomy", contentPane, frame);
					setContentPane(questionPanel);
					contentPane.setVisible(false);
				} else if (rdbtnEconomic.isSelected()) {
					questionPanel = new QuestionPanel("economic", contentPane, frame);
					setContentPane(questionPanel);
					contentPane.setVisible(false);
				} else if (rdbtnHealth.isSelected()) {
					questionPanel = new QuestionPanel("health", contentPane, frame);
					setContentPane(questionPanel);
					contentPane.setVisible(false);
				}
				buttonGroup.clearSelection();
				//after pressing start quiz, user will see the first question, then attempted number plus 1
				questionPanel.addAttemptedNumber();
			}
		});
		
	}
}
