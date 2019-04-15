
public class Player {
	private int score;
	private int heartCount;
	
	public Player() {
		this.heartCount = 10; 
		this.score = 0;
	}
	public int getHeartCount() {
		return this.heartCount;
	}
	public void setHeartCount(int newHeart) {
		if (newHeart >= 10) {
			heartCount = 10;
		}
		else {
			this.heartCount = newHeart;
		}
	}
	public int getScore() {
		return this.score;
	}
	public void setScore(int newScore) {
		this.score = newScore;
	}
}
