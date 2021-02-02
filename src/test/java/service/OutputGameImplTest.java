package service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import exception.ImportInputFileException;
import model.Entry;
import model.Game;
import service.impl.CalculatorImpl;
import service.impl.InputReaderImpl;
import service.impl.OutputGameImpl;

public class OutputGameImplTest {

	InputReader bowllingInputReader = new InputReaderImpl();
	OutputGame bowllingOutputGame = new OutputGameImpl();
	Calculator BowllingCalculator = new CalculatorImpl();

	private File loadFile(String fileCABName) {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		return new File(classLoader.getResource(fileCABName).getFile());
	}

	@Test
	public void OutputGameRegular() throws IOException {
		List<Entry> entries = new ArrayList<>();
		File file = loadFile("CABOK01.txt");
		try {
			entries = bowllingInputReader.EntriesReader(file.toPath());
		} catch (ImportInputFileException e) {
			e.printStackTrace();
		}
		Map<String, Game> games = BowllingCalculator.calculateInputs(entries);
		List<String> lines = bowllingOutputGame.framesPrinter(games);
		assertEquals(lines.size(), 9);
	}
}
