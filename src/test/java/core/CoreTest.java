package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import core.Core;
import model.Entry;
import model.Frame;

public class CoreTest {
	private Core core;
	private List<Entry> entries = new ArrayList();
	List<Frame> frames = new ArrayList();

	@BeforeEach
	public void setUp() {
		core = new Core();
		entries = new ArrayList();
	}

	private void rollMany(int rolls, int score) {
		for (int i = 0; i < rolls; i += 1) {
			entries.add(new Entry("Test", score));
		}
	}

	private void rollSpare() {
		entries.add(new Entry("Test", 5));
		entries.add(new Entry("Test", 5));
	}

	private void rollStrike() {
		entries.add(new Entry("Test", 10));
	}

	private void roll(int score) {
		entries.add(new Entry("Test", score));
	}

	@Test
	public void rollBall() {
		entries.add(new Entry("Test", 0));
	}

	@Test
	public void zeroScore() {
		rollMany(20, 0);
		frames = core.creatFrames(entries);
		int score = frames.get(frames.size() - 1).getScore();
		assertEquals(score, 0);
	}

	@Test
	public void allOnes() {
		rollMany(20, 1);
		frames = core.creatFrames(entries);
		int score = frames.get(frames.size() - 1).getScore();
		assertEquals(score, 20);
	}

	@Test
	public void oneSpare() {
		rollSpare();
		roll(3);
		rollMany(17, 0);
		frames = core.creatFrames(entries);
		int score = frames.get(frames.size() - 1).getScore();
		assertEquals(score, 16);
	}

	@Test
	public void oneStrike() {
		rollStrike();
		roll(3);
		roll(4);
		rollMany(16, 0);
		frames = core.creatFrames(entries);
		int score = frames.get(frames.size() - 1).getScore();
		assertEquals(score, 24);
	}

	@Test
	public void perfectScore() {
		rollMany(12, 10);
		frames = core.creatFrames(entries);
		int score = frames.get(frames.size() - 1).getScore();
		assertEquals(score, 300);
	}

	@Test
	public void testJeffGame() {
		roll(10);
		roll(7);
		roll(3);
		roll(9);
		roll(0);
		roll(10);
		roll(0);
		roll(8);
		roll(8);
		roll(2);
		roll(0);
		roll(6);
		roll(10);
		roll(10);
		roll(10);
		roll(8);
		roll(1);
		frames = core.creatFrames(entries);
		int score = frames.get(frames.size() - 1).getScore();
		assertEquals(score, 167);
	}
}
