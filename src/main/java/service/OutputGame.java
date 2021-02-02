package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Frame;
import model.Game;

public interface OutputGame {

	public List<String> framesPrinter(Map<String, Game> game);

	public void CreateHeader();

	public StringBuilder createPinfallLine(Frame frame, int index);
	
	public void CreatePinfalls(Map<String, Game> games);
	
}