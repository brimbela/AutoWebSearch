package autowebsearch.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static final String DateTimePattern = "dd-MM-yyyy HH:mm";

	public static String getCurrentDate(){
		return new SimpleDateFormat(DateTimePattern).format(new Date());
	}

	public static String convertDateToString(Date date){
		return new SimpleDateFormat(DateTimePattern).format(date);
	}

	public static Date convertStringToDate(String date) throws ParseException{
		if(date == null || date.isEmpty())
			return null;
		return new SimpleDateFormat(DateTimePattern).parse(date);
	}
}
