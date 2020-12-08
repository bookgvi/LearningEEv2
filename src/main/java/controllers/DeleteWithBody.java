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

@WebServlet (name = "test delete with body", urlPatterns = "/del")
public class DeleteWithBody extends HttpServlet {
  @Override
  protected void doDelete (HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/plain");
    PrintWriter out = resp.getWriter();
    InputStream inputStream = req.getInputStream();
    BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));

    JsonElement jsonElement;
    String reqField = null;
    JsonObject jsonObject = JsonParser.parseReader(buffer).getAsJsonObject();
    try {
      reqField = "data";
      jsonElement = jsonObject.get(reqField);
      JsonObject data = jsonElement.getAsJsonObject();

      reqField = "name";
      jsonElement = data.get(reqField);
      String name = jsonElement.getAsString();

      System.out.printf("{ \"name\": \"%s\" } %n", name);
      out.write("OK");
    } catch (NullPointerException ex) {
      throw new NullPointerException(reqField + " is required");
    }
  }
}
