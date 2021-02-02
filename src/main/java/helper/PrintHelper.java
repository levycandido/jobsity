package helper;

import model.Roll;

public class PrintHelper {

	public String firstToString(Roll firstAttemp) {
		if (isStrike(firstAttemp)) {
			return "\t\tX";
		} else {
			return "\t" + rollToString(firstAttemp);
		}
	}

	public String lastAttemptToString(Roll firstAttemp) {
		if (isStrike(firstAttemp)) {
			return "\tX";
		} else {
			return "\t" + rollToString(firstAttemp);
		}
	}

	public boolean isStrike(Roll firstAttempt) {
		return rollToInt(firstAttempt) == 10;
	}

	public boolean isSpare(Roll firstAttemp, Roll secondAttemp) {
		if (secondAttemp == null) {
			return false;
		} else if ((rollToInt(secondAttemp) == 10) || (rollToInt(firstAttemp) + rollToInt(secondAttemp) == 10)) {
			return true;
		}
		return false;
	}

	public String secondToString(Roll firstAttemp, Roll secondAttemp) {
		if (secondAttemp == null) {
			return "";
		} else if ((rollToInt(secondAttemp) == 10) || (rollToInt(firstAttemp) + rollToInt(secondAttemp) == 10)) {
			return "\t/";
		} else {
			return "\t" + rollToString(secondAttemp);
		}
	}

	public String thirdToString(Roll thirddAttemp) {
		if (thirddAttemp == null) {
			return "";
		} else if ((rollToInt(thirddAttemp) == 10)) {
			return "\tX";
		} else {
			return "\t" + rollToString(thirddAttemp);
		}
	}

	private int rollToInt(Roll roll) {
		if (roll == null) {
			return 0;
		} else {
			return roll.getCountPinsKnockedDown();
		}
	}

	private String rollToString(Roll roll) {
		if (roll == null) {
			return "0";
		} else {
			return String.valueOf(roll.getCountPinsKnockedDown());
		}
	}
}
