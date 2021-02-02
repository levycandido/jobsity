package model;

public class Frame {

	private Roll firstAttempt;
	private Roll secondAttempt;
	private Roll thirdAttempt;
	private int score;

	public Frame(Roll firstAttempt, Roll secondtAttempt, Roll thirdAttempt, int score) {
		super();
		this.firstAttempt = firstAttempt;
		this.secondAttempt = secondtAttempt;
		this.thirdAttempt = thirdAttempt;
		this.score = score;
	}

	public Frame() {

	}

	public Roll getFirstAttempt() {
		return firstAttempt;
	}

	public void setFirstAttempt(Roll firstAttempt) {
		this.firstAttempt = firstAttempt;
	}

	public Roll getSecondAttempt() {
		return secondAttempt;
	}

	public void setSecondAttempt(Roll secondAttempt) {
		this.secondAttempt = secondAttempt;
	}

	public Roll getThirdAttempt() {
		return thirdAttempt;
	}

	public void setThirdAttempt(Roll thirdAttempt) {
		this.thirdAttempt = thirdAttempt;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
