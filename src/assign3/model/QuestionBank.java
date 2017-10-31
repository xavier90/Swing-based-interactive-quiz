package assign3.model;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class QuestionBank {
	
	private ArrayList<Question> questionBank = new ArrayList<Question>();
	
	//load questions from database
	public void loadQuestion(String fileName) {
		try{
			File file = new File(fileName);
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			
			//30 is the number of questions
			for(int i = 0; i < 30; i++) {
				String topic = document.getElementsByTagName("topic_area").item(i).getTextContent();
				int points = Integer.parseInt(document.getElementsByTagName("points").item(i).getTextContent());
				String question = document.getElementsByTagName("question").item(i).getTextContent();
				String choiceA = document.getElementsByTagName("a").item(i).getTextContent();
				String choiceB = document.getElementsByTagName("b").item(i).getTextContent();
				String choiceC = document.getElementsByTagName("c").item(i).getTextContent();
				String choiceD = document.getElementsByTagName("d").item(i).getTextContent();
				String answer = document.getElementsByTagName("answer").item(i).getTextContent();
				//String pictureUrl = document.getElementsByTagName("pictureUrl").item(i).getTextContent();
			
				Question ques = new Question(topic, points, question, choiceA, choiceB, choiceC, choiceD, answer, null);
				questionBank.add(ques);
			}
			
			
		}catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Question> showQuestionBank() {
		return questionBank;
	}
	
}
