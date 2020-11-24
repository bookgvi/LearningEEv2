package Services.Utils;

public class ParamsValidator {
  public static Integer id(Object param) {
    try {
      return Integer.parseInt(param.toString());
    } catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException ex) {
      return null;
    }
  }
}
