package service;

import java.nio.file.Path;
import java.util.List;

import exception.ImportInputFileException;
import model.Entry;

public interface InputReader {
	
	public List<Entry> EntriesReader(Path path) throws ImportInputFileException;
}
