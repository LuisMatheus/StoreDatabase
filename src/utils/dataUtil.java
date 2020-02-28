package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dataUtil {
	private static final SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        private static final SimpleDateFormat s1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	public static Date stringToDate(String data) {
		try {
			return s.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String dateToString(Date d) {
		return s.format(d);
	}
        
        public static Date systemdate(){
            try {
                return s1.parse(s1.format(new Date()));
            } catch (ParseException ex) {
                Logger.getLogger(dataUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
}