package service.impl;

import static validation.ValidatorImpl.isValidThrowsCount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import core.Core;
import exception.BadImputException;
import model.Entry;
import model.Frame;
import model.Game;
import service.Calculator;

public class CalculatorImpl implements Calculator {

	public Map<String, Game> calculateInputs(List<Entry> entries) {

		Core core = new Core();
		String name = null;
		HashMap<String, Game> games = new HashMap();
		Map<String, List<Entry>> groupEntries = entries.stream().collect(Collectors.groupingBy(Entry::getName));
		for (Map.Entry<String, List<Entry>> entriesByName : groupEntries.entrySet()) {
			name = entriesByName.getKey();
			List<Entry> entryListNamed = entriesByName.getValue();
			if (isValidThrowsCount(entryListNamed.size())) {
				throw new BadImputException("ERROR! More than 10 throws to the user: " + name);
			}
			Game game = createGame(name, core.creatFrames(entryListNamed));
			games.put(name, game);
		}
		return games;
	}

	private Game createGame(String name, List<Frame> listFrames) {
		int score = scoreCalculator(listFrames);
		Game game = new Game();
		game.setFrame(listFrames);
		game.setScore(score);
		return game;
	}

	private int scoreCalculator(List<Frame> listFrames) {
		return listFrames.get(listFrames.size() - 1).getScore();
	}
}
