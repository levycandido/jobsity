package service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import exception.BadImputException;
import exception.ImportInputFileException;
import model.Entry;
import service.impl.InputReaderImpl;
import service.InputReader;

public class InputReaderImplTest {

	InputReader bowllingInputReaderImpl = new InputReaderImpl();
	List<Entry> entries = new ArrayList<>();

	private File loadFile(String fileCABName) {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		return new File(classLoader.getResource(fileCABName).getFile());
	}

	@Test
	public void EntriesReaderOK() {
		File file = loadFile("CABOK01.txt");
		try {
			entries = bowllingInputReaderImpl.EntriesReader(file.toPath());
		} catch (ImportInputFileException e) {
			e.printStackTrace();
		}
		assertEquals(entries.size(), 68);
	}

	@Test
	public void EntriesReaderIncorrectInputScore() {
		File file = loadFile("CABNOK02.txt");
		Exception exception = assertThrows(BadImputException.class, () -> {
			bowllingInputReaderImpl.EntriesReader(file.toPath());
		});
	    String expectedMessage = "ERROR! Incorrect input format A to the user John";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void EntriesReaderIncorrectInputValue() {
		File file = loadFile("CABNOK02.txt");
		Exception exception = assertThrows(BadImputException.class, () -> {
			bowllingInputReaderImpl.EntriesReader(file.toPath());
		});
	    String expectedMessage = "ERROR! Incorrect input format A to the user John";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void EntriesReaderValueOutofRange() {
		File file = loadFile("CABNOK03.txt");
		Exception exception = assertThrows(BadImputException.class, () -> {
			bowllingInputReaderImpl.EntriesReader(file.toPath());
		});
	    String expectedMessage = "ERROR! Incorrect input value 11 to the user Jeff. Value out of range 0 - 10";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}
}
