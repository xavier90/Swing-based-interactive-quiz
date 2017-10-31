package assign3.view;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JLabel;
import javax.swing.JTable;

public class TimerPanel extends JPanel {

	private Timer timer;
	private JProgressBar progressBar;
	private QuestionPanel questionPanel;
	private JLabel lblFace;
	private ImageIcon cryFace;
	private ImageIcon smileFace;
	
	private boolean submitCheck;//if this variable is true, face image will not change into cry 
	private JTable table;
	/**
	 * Create the panel.
	 */
	public TimerPanel(QuestionPanel questionPanel) {
		setLayout(null);
		setBounds(0, 0, 800, 150);
		setBorder(new TitledBorder("Timer"));
		
		//add jProgressBar to show timer
		progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		progressBar.setStringPainted(true);
		progressBar.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		progressBar.setBounds(30, 25, 600, 100);
		add(progressBar);
		
		progressBar.setValue(100);
		

		//use this method to reset timer
		resetTimer();

		smileFace = new ImageIcon("src/assign3/smileFace.png");
		cryFace = new ImageIcon("src/assign3/cryFace.png");
		
		lblFace = new JLabel("");
		lblFace.setIcon(smileFace);
		lblFace.setBounds(650, 18, 120, 120);
		add(lblFace);
		
//		table = new JTable();
//		table.setBounds(126, 45, 100, 100);
//		add(table);
		
	}
	
	//this method is used for creating and reseting timer
	public void resetTimer() {

		timer = new Timer(100, new ActionListener() {
			int counter = 100;
			public void actionPerformed(ActionEvent e) {
				progressBar.setValue(--counter);
				if(counter < 0) {
					JOptionPane.showMessageDialog(null, "Time up!");
					timer.stop();
					
					//if user did not submit the answer, face will change into cry
					if(!submitCheck) lblFace.setIcon(cryFace);
					
					questionPanel.submitOnce = false;
				}
			}
		});
		
		timer.start();
	}
	
	public void setSmileFace() {
		lblFace.setIcon(smileFace);
	}
	public void stopTimer() {
		timer.stop();
	}
	
	//the following two methods are used for setting submitCheck, this variable will decide that whether we will show cryFace to user
	public void setSubmitCheckT() {
		submitCheck = true;
	}
	public void setSubmitCheckF() {
		submitCheck = false;
	}
}
