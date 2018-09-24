package fr.sabb.application.utils;

import java.sql.Timestamp;
import java.util.Calendar;

import fr.sabb.application.data.object.Licensee;

public class SheetUtils {

	public static String toHeureFormat(Timestamp timestamp) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(timestamp);
		return cal.get(Calendar.HOUR_OF_DAY) + "h" + getDoubleDigitFormat(cal.get(Calendar.MINUTE));
	}
	
	public static String getDateToString(Timestamp timestamp) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(timestamp);
		return getDoubleDigitFormat(cal.get(Calendar.DAY_OF_MONTH)) + "/"
				+ getDoubleDigitFormat(cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
	}
	
	private static String getDoubleDigitFormat(int number) {
		return number > 9 ? "" + number : "0" + number;
	}
	
	public static String formatLicenseeString(Licensee licensee) {
		if (licensee == null) {
			return "";
		}
		return String.format("%s. %s", licensee.getFirstname().substring(0, 1), licensee.getName());
	}
	
	public static String formatFullLicenseeString(Licensee licensee) {
		if (licensee == null) {
			return "";
		}
		return String.format("%s %s", licensee.getFirstname(), licensee.getName());
	}
}
