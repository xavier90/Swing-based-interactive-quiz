package assign3.view;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.NumberFormat;



public class ShowChart extends JPanel {

	private HashMap dataHashMap = new HashMap();
	private static int ASTRONOMY_PT, ECONOMIC_PT, HEALTH_PT;
	private static int attemptedNumber = 0;
	private static int correctNumber = 0;
	private static int totalScore = 0;
	
	
	/**
	 * Create the panel.
	 */
	public ShowChart() {
		
		setLayout(null);
		setBounds(550, 150, 250, 450);
		
		updateData("null", 0);
		
	}

	
	//update data for bar chart
	public void updateData(String subjectName, int questionPoints) {
		if(subjectName.equals("astronomy")) {
			ASTRONOMY_PT += 1;
		} else if(subjectName.equals("economic")) {
			ECONOMIC_PT += 1;
		} else if(subjectName.equals("health")) {
			HEALTH_PT += 1;
		}
		
		totalScore += questionPoints;//update total score
		correctNumber = ASTRONOMY_PT+ECONOMIC_PT+HEALTH_PT;//update correct number
		
		dataHashMap.clear();
		dataHashMap.put("astronomy", ASTRONOMY_PT);
		dataHashMap.put("economic", ECONOMIC_PT);
		dataHashMap.put("health", HEALTH_PT);
		updateChart();
	}
	
	//update bar chart
	public void updateChart() {
		DefaultCategoryDataset dcd = new DefaultCategoryDataset();
		
		Set keys = dataHashMap.keySet();
		Iterator iterator = keys.iterator();
		
		while(iterator.hasNext()) {
			String subjectName = (String)iterator.next();
			int points = (int)dataHashMap.get(subjectName);
			dcd.setValue(points, "points", subjectName);
		}
		
		//create a bar chart
		JFreeChart jchart = ChartFactory.createBarChart("Report", "Subject Name", "Right Answer", dcd, PlotOrientation.VERTICAL, true, true, true);
		CategoryPlot plot = jchart.getCategoryPlot();
		plot.setRangeGridlinePaint(Color.BLACK);

		//create a chartPanel to contain bar chart
		ChartPanel chartPanel = new ChartPanel(jchart);
		chartPanel.setBounds(0, 50, 250, 380);
		chartPanel.setBorder(new TitledBorder("Score Chart"));
		
		//format number to be 2 digits
		NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumIntegerDigits(2);
        
        //create a jLabel to show user's statistical data
		JLabel lblInfo = new JLabel("  Attempted: " + nf.format(attemptedNumber) 
									+ "/Correct: "+ nf.format(correctNumber) + "/Total score: " + nf.format(totalScore));
		lblInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblInfo.setBounds(2, 6, 246, 45);
		lblInfo.setBorder(new TitledBorder(""));

		
		removeAll();
		add(chartPanel);
		add(lblInfo);

		validate();
	}
	
	//update attempted number
	public void addAttemptedNumber() {
		attemptedNumber++;
	}

}
