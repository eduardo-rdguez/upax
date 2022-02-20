package rdguez.eduardo.upax.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

  static public LocalDate currentLocalDate() {
    return LocalDate.now();
  }

  static public Date currentDate() {
    return new Date();
  }

  static public LocalDate toLocalDate(Date date) {
    return date.toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDate();
  }

}
