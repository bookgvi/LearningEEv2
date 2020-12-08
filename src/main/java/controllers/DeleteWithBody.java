package controllers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;

enum Types {
  string,
  number
}

@WebServlet (name = "test delete with body", urlPatterns = "/del")
public class DeleteWithBody extends HttpServlet {
  private JsonElement jsonElement;
  private String reqField = null;

  @Override
  protected void doDelete (HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/plain");
    PrintWriter out = resp.getWriter();
    InputStream inputStream = req.getInputStream();
    BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
    String[] stringFields_required = {"name", "second"};
    String[] integerFields_required = { "first" };
    HashMap<String, Object> request = new HashMap<>();

    JsonObject jsonObject = JsonParser.parseReader(buffer).getAsJsonObject();
    try {
      reqField = "data";
      jsonElement = jsonObject.get(reqField);
      JsonObject data = jsonElement.getAsJsonObject();
      request.putAll(parseBody_required(stringFields_required, data, Types.string));
      request.putAll(parseBody_required(integerFields_required, data, Types.number));
      System.out.printf("{ \"name\": \"%s\" } (%s) %n", request.get("name"), request.get("name").getClass().getName());
      System.out.printf("{ \"first\": %s } (%s) %n", request.get("first"), request.get("first").getClass().getName());
      System.out.printf("{ \"second\": \"%s\" } (%s) %n", request.get("second"), request.get("second").getClass().getName());
      out.write("OK");
    } catch (NullPointerException ex) {
      throw new NullPointerException("field '" + reqField + "' is required");
    }
  }

  private HashMap<String, Object> parseBody_required(String[] body, JsonObject root, Types type) throws NullPointerException {
    HashMap<String, Object> res = new HashMap<>();
    for (String field: body) {
      reqField = field;
      jsonElement = root.get(reqField);
      Object requestField = null;
      if (type == Types.string) requestField = jsonElement.getAsString();
      else if (type == Types.number) requestField = jsonElement.getAsInt();

      res.put(reqField, requestField);
    }
    return res;
  }
}
