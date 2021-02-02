package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import model.Entry;
import model.Frame;
import model.Game;
import model.Roll;

public class Core {

	private int attempts[] = new int[21];
	private int currentRoll = 0;

	public void resetRoll() {
		Arrays.fill(attempts, 0);
		currentRoll = 0;
	}

	public List<Frame> creatFrames(List<Entry> entries) {

		int score = 0;
		int frameCount = 0;
		
		loadRolls(entries);

		List<Frame> listFrames = new ArrayList();

		for (int index = 0; index < 10; index++) {
			Frame frame = new Frame();

			if (index == 9) {
				score += sumOfBallsInFinalFrame(frameCount);
				setFrame(frame, new Roll(attempts[frameCount]), new Roll(attempts[frameCount + 1]),
						new Roll(attempts[frameCount + 2]), score);
			} else {
				if (isStrike(frameCount)) {
					score += 10 + strikeBonus(frameCount);
					setFrame(frame, new Roll(attempts[frameCount]), null, null, score);
					frameCount++;
				} else if (isSpare(frameCount)) {
					score += 10 + spareBonus(frameCount);
					setFrame(frame, new Roll(attempts[frameCount]), new Roll(attempts[frameCount + 1]), null, score);
					frameCount += 2;
				} else {
					score += sumOfBallsInFrame(frameCount);
					setFrame(frame, new Roll(attempts[frameCount]), new Roll(attempts[frameCount + 1]), null, score);
					frameCount += 2;
				}
			}
			listFrames.add(frame);
		}

		resetRoll();
		return listFrames;
	}

	private void loadRolls(List<Entry> entries) {
		for (int i = 0; i < entries.size(); i++) {
			attempts[i] = entries.get(i).getScore();
		}

	}

	private void setFrame(Frame frame, Roll firstAttempt, Roll secondAttempt, Roll thirdAttempt, int score) {
		frame.setFirstAttempt(firstAttempt);
		frame.setSecondAttempt(secondAttempt);
		frame.setThirdAttempt(thirdAttempt);
		frame.setScore(score);
	}

	private boolean isStrike(int frameIndex) {
		return attempts[frameIndex] == 10;
	}

	private int sumOfBallsInFrame(int frameIndex) {
		return attempts[frameIndex] + attempts[frameIndex + 1];
	}

	private int spareBonus(int frameIndex) {
		return attempts[frameIndex + 2];
	}

	private int strikeBonus(int frameIndex) {
		return attempts[frameIndex + 1] + attempts[frameIndex + 2];
	}

	private int sumOfBallsInFinalFrame(int frameIndex) {
		return attempts[frameIndex] + attempts[frameIndex + 1] + attempts[frameIndex + 2];
	}

	private boolean isSpare(int frameIndex) {
		return attempts[frameIndex] + attempts[frameIndex + 1] == 10;
	}
}