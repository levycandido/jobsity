package core;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import exception.ImportInputFileException;
import model.Entry;
import model.Game;
import service.Calculator;
import service.InputReader;
import service.impl.CalculatorImpl;
import service.impl.InputReaderImpl;
import service.impl.OutputGameImpl;

public class BowllingApp {

	public static void main(String[] args) {

		InputReader bowllingInputReader = new InputReaderImpl();
		Calculator bowllingCalculatorImpl = new CalculatorImpl();
		Core core = new Core();
		List<Entry> entries;

		if (args.length == 0) {
			System.err.println("ERROR! Please informe the input file path");
			return;
		}

		Path path = Paths.get(args[0]);

		try {
			entries = bowllingInputReader.EntriesReader(path);
		} catch (ImportInputFileException e) {
			return;
		}

		Map<String, Game> controlGame = bowllingCalculatorImpl.calculateInputs(entries);

		List<String> lines = new OutputGameImpl().framesPrinter(controlGame);
		
		lines.forEach(System.out::println);

	}

}
