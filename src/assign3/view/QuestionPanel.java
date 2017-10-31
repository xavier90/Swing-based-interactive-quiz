package assign3.view;

import javax.swing.JPanel;

import assign3.controller.GetCurQuestion;
import assign3.model.Question;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class QuestionPanel extends JPanel {

	String topic;
	private int questionIndex = 0;
	
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnReturn;
	private JButton btnNext;
	private JButton btnSubmit;
	
	GetCurQuestion getCurQuestion;//after pressing next button, this class will get a new question to be current question
	Question curQuestion;//current question, used for showing information of this question on panel
	private JLabel lblQuestion;
	private JRadioButton rdbtnChoiceA;
	private JRadioButton rdbtnChoiceB;
	private JRadioButton rdbtnChoiceC;
	private JRadioButton rdbtnChoiceD;
	
	private ShowChart showChart;
	private TimerPanel timerPanel;
	
	//the following two variable is used for return button
	private MainPanel mainPanel;
	private JPanel contentPane;
	
	//make sure user can only get points once for one question
	public static boolean submitOnce = true;
	/**
	 * Create the panel.
	 */
	public QuestionPanel(String topic, JPanel contentPane, MainPanel mainPanel) {
		this.topic = topic;
		this.contentPane = contentPane;
		this.mainPanel = mainPanel;
		
		getCurQuestion = new GetCurQuestion(topic, questionIndex);
		getCurQuestion.shuffleQuestionListForTopic();
		curQuestion = getCurQuestion.getQuestion();
		initComponents();
		initQuestion(curQuestion);
		createEvents();
	}


	//create and initialize the components
	private void initComponents() {
		setLayout(null);
		setBounds(0, 0, 800, 600);
		
		//add timerPanel
		timerPanel = new TimerPanel(this);
		add(timerPanel);

		//add panel to show current question
		panel = new JPanel();
		panel.setBounds(0, 150, 550, 430);
		panel.setBorder(new TitledBorder("Question"));
		add(panel);
		panel.setLayout(null);
		
		lblQuestion = new JLabel("<html>"+curQuestion.getQuestion()+"</html>");
		lblQuestion.setBounds(80, 50, 400, 100);
		panel.add(lblQuestion);
		
		rdbtnChoiceA = new JRadioButton(curQuestion.getChoiceA());
		buttonGroup.add(rdbtnChoiceA);
		rdbtnChoiceA.setBounds(100, 160, 300, 23);
		panel.add(rdbtnChoiceA);
		
		rdbtnChoiceB = new JRadioButton(curQuestion.getChoiceB());
		buttonGroup.add(rdbtnChoiceB);
		rdbtnChoiceB.setBounds(100, 200, 300, 23);
		panel.add(rdbtnChoiceB);
		
		rdbtnChoiceC = new JRadioButton(curQuestion.getChoiceC());
		buttonGroup.add(rdbtnChoiceC);
		rdbtnChoiceC.setBounds(100, 240, 300, 23);
		panel.add(rdbtnChoiceC);
		
		rdbtnChoiceD = new JRadioButton(curQuestion.getChoiceD());
		buttonGroup.add(rdbtnChoiceD);
		rdbtnChoiceD.setBounds(100, 280, 300, 23);
		panel.add(rdbtnChoiceD);
		
		
		btnReturn = new JButton("return");
		btnReturn.setBounds(150, 370, 100, 29);
		panel.add(btnReturn);
		
		btnNext = new JButton("next");
		btnNext.setBounds(280, 370, 100, 29);
		panel.add(btnNext);
		
		btnSubmit = new JButton("submit");
		btnSubmit.setBounds(410, 370, 100, 29);
		panel.add(btnSubmit);
		
		//add score chart
		showChart = new ShowChart();
		add(showChart);
		
	}
	
	//question will be initialized and updated by this method
	private void initQuestion(Question curQuestion) {
		lblQuestion.setText("<html>"+curQuestion.getQuestion()+"</html>");
		rdbtnChoiceA.setText(curQuestion.getChoiceA());
		rdbtnChoiceB.setText(curQuestion.getChoiceB());
		rdbtnChoiceC.setText(curQuestion.getChoiceC());
		rdbtnChoiceD.setText(curQuestion.getChoiceD());
	}
	
	//add attemptedNumber
	public void addAttemptedNumber() {
		showChart.addAttemptedNumber();
	}
	
	//put all actionListeners here
 	private void createEvents() {
		
 		btnReturn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(true);
				mainPanel.setContentPane(contentPane);
				
				//after press return button, stop the current timer
				timerPanel.stopTimer();;
			}
		});
		
		//still need to deal with the observer and observable
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//there are 10 question in total
				if(questionIndex < 9) {
					questionIndex++;
					getCurQuestion.changeQuestionIndex(questionIndex);
					curQuestion = getCurQuestion.getNextQuestion();
					initQuestion(curQuestion);	//update the new question
					buttonGroup.clearSelection();//clear the selection
					
					addAttemptedNumber();
					showChart.updateChart();//update chartPanel 
					showChart.updateUI();//show the new chartPanel
					submitOnce = true;
					
					//for each new question, reset timer and the face image
					timerPanel.stopTimer();
					timerPanel.resetTimer();
					
					//after pressing next button, show smile face again
					timerPanel.setSmileFace();
					//set submitCheck to be default value, so it will show cry face if user do not submit his/her answer
					timerPanel.setSubmitCheckF();
				}
		
			}
		});
		
		//if user input the right answer, points + 1
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(submitOnce) {
					int questionPoints = curQuestion.getPoints();
					String answer = curQuestion.getAnswer();
					
					if(answer.equals("A")) {
						if(rdbtnChoiceA.isSelected()) {
							showChart.updateData(topic, questionPoints);
						}
					} else if(answer.equals("B")) {
						if(rdbtnChoiceB.isSelected()) {
							showChart.updateData(topic, questionPoints);
						}
					} else if(answer.equals("C")) {
						if(rdbtnChoiceC.isSelected()) showChart.updateData(topic, questionPoints);
					} else if(answer.equals("D")) {
						if(rdbtnChoiceD.isSelected()) showChart.updateData(topic, questionPoints);
					}
					//after pressing submit button, clear selection of jRadioButton
					buttonGroup.clearSelection();
					submitOnce = false;
					//it will not show cryFace for this question any more
					timerPanel.setSubmitCheckT();
				}
				
			}
		});
		
	}

}
