package Services.Utils;

public class PathParser {
  public static String[] getUrlParams(String pathInfo) {
    return pathInfo.split("/");
  }

  public static String getFirstId(String pathInfo) {
    String[] pathParams = pathInfo.split("/");
    return pathParams.length == 2 ? pathParams[1] : null;
  }
}
