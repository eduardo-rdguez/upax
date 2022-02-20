package rdguez.eduardo.upax.util;

import rdguez.eduardo.upax.constant.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

  static public LocalDate currentLocalDate() {
    return LocalDate.now();
  }

  static public Date stringToDate(String stringDate) {
    SimpleDateFormat formatter = new SimpleDateFormat(Constants.ENGLISH_DATE_FORMAT);
    Date date = null;
    try {
      date = formatter.parse(stringDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }

  static public LocalDate toLocalDate(Date date) {
    return date.toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDate();
  }

}
