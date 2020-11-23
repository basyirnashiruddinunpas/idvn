package org.masbas.idvn.helpers;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHelpers {
	
	public static ZonedDateTime now() {
		return ZonedDateTime.now();
	}
	
	public static String formatToString(String format, ZonedDateTime zdt) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss z");
		String formattedString = zdt.format(formatter);
		
		return formattedString;
	}
	
}
