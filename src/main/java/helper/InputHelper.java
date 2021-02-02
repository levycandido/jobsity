package helper;

public class InputHelper {
	
	public int convertInput(String input) {
		return input.equals("F") ? 0 : Integer.valueOf(input);
	}

}
