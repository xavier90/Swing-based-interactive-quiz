package assign3.controller;

import java.util.Observable;
import java.util.Observer;

import assign3.model.Question;
import assign3.view.QuestionPanel;

public class GetNextQuestion implements Observer{

	private Question curQuestion;
	
	public GetNextQuestion() {

	}
	
	//get new question from observable
	public void update(Observable o, Object newQuestion) {
		curQuestion = (Question)newQuestion;
	}
	
	public Question getQuestion() {
		return curQuestion;
	}
	
}
