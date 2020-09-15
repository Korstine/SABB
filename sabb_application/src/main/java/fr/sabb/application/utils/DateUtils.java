package fr.sabb.application.utils;

import java.sql.Timestamp;

public final class DateUtils {
	
	final static long HALF_SEASON_TIME = 10368000000l;
	
	public static int calculateScore(Timestamp date) {
		long matchTime = date.getTime();
		long currentTime = new Timestamp(System.currentTimeMillis()).getTime();
		long dif = currentTime - matchTime;
		return (int) (HALF_SEASON_TIME/dif);
	}

}
