package assign3.model;

public class Question {
	private String question;
	private String choiceA;
	private String choiceB;
	private String choiceC;
	private String choiceD;
	private String answer;
	private String topic;
	private int points;
	private String pictureUrl;
	
	public Question(String topic, int points, String question, String choiceA, String choiceB, String choiceC, String choiceD, 
			String answer, String pictureUrl) {
		this.topic = topic;
		this.points = points;
		this.question = question;
		this.choiceA = choiceA;
		this.choiceB = choiceB;
		this.choiceC = choiceC;
		this.choiceD = choiceD;
		this.answer = answer;
		this.pictureUrl = pictureUrl;
	}
	
	public Question() {
		this(null,0,null,null,null,null,null,null,null);
	}
	public String getQuestion() {
		return question;
	}
	
	public String getChoiceA() {
		return choiceA;
	}
	
	public String getChoiceB() {
		return choiceB;
	}
	
	public String getChoiceC() {
		return choiceC;
	}
	
	public String getChoiceD() {
		return choiceD;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public String getTopic() {
		return topic;
	}
	public int getPoints() {
		return points;
	}
}
