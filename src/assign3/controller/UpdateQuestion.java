package assign3.controller;

import java.util.ArrayList;
import java.util.Observable;

import assign3.model.Question;
import assign3.model.QuestionBank;

public class UpdateQuestion extends Observable{
	
	ArrayList<Question> questionListForTopic = new ArrayList<Question>();

	public UpdateQuestion(ArrayList<Question> questionListForTopic) {
		this.questionListForTopic = questionListForTopic;
	}
	
	
	
	//observe changes then send changes to observer(GetNextQuestion)
	public void setQuestion(int questionIndex) {
		Question newQuestion = questionListForTopic.get(questionIndex);
		
		setChanged();
		notifyObservers(newQuestion);
	}
	
	
}
