package Services.Utils;

public class PathParser {
  public static String[] getUrlParams(String pathInfo) {
    if (pathInfo == null) return new String[0];
    String[] pathParams = pathInfo.split("/");
    return pathParams.length > 0 ? pathParams : new String[0];
  }

  public static String getFirstId(String pathInfo) {
    if (pathInfo == null) return null;
    String[] pathParams = pathInfo.split("/");
    return pathParams.length == 2 ? pathParams[1] : null;
  }
}
