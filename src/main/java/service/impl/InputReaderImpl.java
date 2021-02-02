package service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import exception.BadImputException;
import exception.ImportInputFileException;
import helper.InputHelper;
import model.Entry;
import service.InputReader;
import static validation.ValidatorImpl.*;

public class InputReaderImpl implements InputReader {

	public List<Entry> EntriesReader(Path path) {

		InputHelper inputHelper = new InputHelper();
		List<Entry> entriesGame = new ArrayList();
		Stream<String> linesStream;
		try {
			linesStream = Files.lines(path);
			linesStream.forEach(line -> {
				String[] lineParsed = line.split("\t");
				int intValue = 0;
				if (isValidInput(lineParsed[1])) {
					intValue = inputHelper.convertInput(lineParsed[1]);
				} else {
					throw new BadImputException(
							"ERROR! Incorrect input format " + lineParsed[1] + " to the user " + lineParsed[0]);
				}
				if (isValidScore(intValue)) {
					throw new BadImputException("ERROR! Incorrect input value " + intValue + " to the user "
							+ lineParsed[0] + ". Value out of range 0 - 10");
				}
				entriesGame.add(new Entry(lineParsed[0], intValue));
			});

		} catch (IOException e) {
			throw new ImportInputFileException("Error to import the input file path.");
		}
		return entriesGame;
	}
}
