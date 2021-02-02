package model;

import java.util.List;

public class Game {

	private List<Frame> frames;
	private int Score;

	public List<Frame> getFrames() {
		return frames;
	}

	public void setFrame(List<Frame> frames) {
		this.frames = frames;
	}

	public int getScore() {
		return Score;
	}

	public void setScore(int score) {
		Score = score;
	}
}
