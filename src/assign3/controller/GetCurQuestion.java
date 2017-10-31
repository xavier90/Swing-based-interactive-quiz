package assign3.controller;

import java.util.ArrayList;
import java.util.Collections;

import assign3.model.Question;
import assign3.model.QuestionBank;

public class GetCurQuestion {

	UpdateQuestion updateQuestion;
	GetNextQuestion getNextQuestion;
	String topic;
	int questionIndex;
	
	QuestionBank questionBank;
	ArrayList<Question> questionList; //load questions from database to the list
	ArrayList<Question> questionListForTopic = new ArrayList<Question>(); //choice questions with the specific topic from questionList
	
	
	public GetCurQuestion(String topic, int questionIndex) {
		this.topic = topic;
		this.questionIndex = questionIndex;
		questionListForTopic = getQuestionsForTopic();
		
		updateQuestion = new UpdateQuestion(questionListForTopic);
		getNextQuestion = new GetNextQuestion();
		updateQuestion.addObserver(getNextQuestion);
	
	}
	
	
	//load database and return questionsForTopic
	private ArrayList<Question> getQuestionsForTopic() {
		questionBank = new QuestionBank();
		questionBank.loadQuestion("src/assign3/questions.xml");
		questionList = questionBank.showQuestionBank();
		
		int i = 0;
		while(i < questionList.size()) {
			Question ques = questionList.get(i);
			if(ques.getTopic().equals(topic)) {
				questionListForTopic.add(ques);
			}
			i++;
		}
		return questionListForTopic;
	}
	
	
	public Question getQuestion() {
		return questionListForTopic.get(questionIndex);
	}
	
	public void changeQuestionIndex(int questionIndex) {
		updateQuestion.setQuestion(questionIndex);
	}
	
	//get the new question
	public Question getNextQuestion() {
		return getNextQuestion.getQuestion();
	}
	
	//shuffle questionListForTopic, so user can see question show up randomly
	public void shuffleQuestionListForTopic() {
		Collections.shuffle(questionListForTopic);
	}
}
