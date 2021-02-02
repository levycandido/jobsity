package service;

import java.util.List;
import java.util.Map;
import model.Entry;
import model.Game;

public interface Calculator {

	Map<String, Game> calculateInputs(List<Entry> entries);

}
