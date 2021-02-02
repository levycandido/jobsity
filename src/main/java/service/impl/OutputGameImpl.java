package service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.IntStream;
import helper.PrintHelper;
import model.Frame;
import model.Game;
import model.Roll;
import service.OutputGame;

public class OutputGameImpl implements OutputGame {

	private List<String> lines = new ArrayList();
	PrintHelper printerHelper = new PrintHelper();

	public List<String> framesPrinter(Map<String, Game> game) {
		CreateHeader();
		CreatePinfalls(game);
		return lines;
	}

	public void CreateHeader() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Frame\t\t");
		IntStream.range(1, 11).forEach(l -> {
			stringBuilder.append(l);
			stringBuilder.append("\t\t");
		});
		lines.add(stringBuilder.toString());
	}

	public void CreatePinfalls(Map<String, Game> games) {

		for (Entry<String, Game> game : games.entrySet()) {
			StringBuilder stringBuilderScore = new StringBuilder();
			stringBuilderScore.append("Score\t\t");
			List<Frame> frames = game.getValue().getFrames();
			String nome = game.getKey();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(nome).append("\n").append("Pinfalls");
			for (int i = 0; i < frames.size(); i++) {
				Frame frame = frames.get(i);
				stringBuilder.append(createPinfallLine(frame, i));
				stringBuilderScore.append(frame.getScore()).append("\t\t");
			}
			lines.add(stringBuilder.toString());
			lines.add(stringBuilderScore.toString());
		}
	}

	public StringBuilder createPinfallLine(Frame frame, int index) {

		Roll firstAttempt = frame.getFirstAttempt();
		Roll secondAttempt = frame.getSecondAttempt();
		Roll thirdAttempt = frame.getThirdAttempt();
		StringBuilder stringBuilderPinfallLine = new StringBuilder();
		if (index == 9) {
			stringBuilderPinfallLine.append(printerHelper.lastAttemptToString(firstAttempt))
					.append(printerHelper.lastAttemptToString(secondAttempt))
					.append(printerHelper.lastAttemptToString(thirdAttempt));
		} else {
			if (printerHelper.isStrike(frame.getFirstAttempt())) {
				stringBuilderPinfallLine.append(printerHelper.firstToString(firstAttempt));
			} else if (printerHelper.isSpare(firstAttempt, secondAttempt)) {
				stringBuilderPinfallLine.append(printerHelper.firstToString(firstAttempt))
						.append(printerHelper.secondToString(firstAttempt, secondAttempt));
			} else {
				stringBuilderPinfallLine.append(printerHelper.firstToString(firstAttempt))
						.append(printerHelper.secondToString(firstAttempt, secondAttempt));
			}
		}
		return stringBuilderPinfallLine;
	}

}
