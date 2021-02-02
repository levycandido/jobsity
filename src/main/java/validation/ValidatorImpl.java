package validation;

public class ValidatorImpl {

	public static boolean isValidInput(String input) {
		if (input.equals("F")) {
			return true;
		} else {
			try {
				double d = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return true;
	}

	public static boolean isValidScore(int score) {
		return score < 0 || score > 10;
	}

	public static boolean isValidThrowsCount(int inputs) {
		return inputs > 21;
	}
}
