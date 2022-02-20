package rdguez.eduardo.upax.util;

public class RoundUtil {

  public static double roundAvoid(double value, int places) {
    double scale = Math.pow(10, places);
    return Math.round(value * scale) / scale;
  }

}
