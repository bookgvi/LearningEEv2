package Services.Utils;

public class PathParser {
  public static Integer getFirstId(String pathInfo) {
    String[] pathParams = pathInfo.split("/");
    // TODO валидатор
    return pathParams.length == 2 ? Integer.parseInt(pathParams[1]) : null; // TODO - обработать остальные параметры
  }
}
