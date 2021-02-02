package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import exception.ImportInputFileException;
import model.Entry;
import model.Frame;
import model.Game;
import service.impl.InputReaderImpl;
import service.impl.OutputGameImpl;
import service.impl.CalculatorImpl;

public class CalculatorImplTest {
	
	InputReader bowllingInputReader = new InputReaderImpl();
	OutputGame bowllingOutputGame = new OutputGameImpl();
	Calculator BowllingCalculator = new CalculatorImpl();
	private List<Entry> entries;
	private Map<String, Game> games ;

	@Before
	public void loadFile() {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		File file = new File(classLoader.getResource("CABOK01.txt").getFile());
		try {
			entries = bowllingInputReader.EntriesReader(file.toPath());
		} catch (ImportInputFileException e) {
			e.printStackTrace();
		}
		 games = BowllingCalculator.calculateInputs(entries);
	}

	@Test
	public void calculateInputsRegular()  {
		List<Frame> jeffFrames = games.get("Jeff").getFrames();
		int jeffScore = jeffFrames.get(jeffFrames.size()-1).getScore();
		List<Frame> johnFrames = games.get("John").getFrames();
		int johnScore = johnFrames.get(johnFrames.size()-1).getScore();
		assertEquals(jeffScore, 167);
		assertEquals(johnScore, 151);
	}

	@Test
	public void calculateInputsPerfect()  {
		List<Frame> levyFrames = games.get("Levy").getFrames();
		int levyScore = levyFrames.get(levyFrames.size()-1).getScore();
		assertEquals(levyScore, 300);
	}
	
	@Test
	public void calculateInputsZero()  {
		List<Frame> paulFrames = games.get("Paul").getFrames();
		int paulScore = paulFrames.get(paulFrames.size()-1).getScore();
		assertEquals(paulScore, 0);
	}
}
