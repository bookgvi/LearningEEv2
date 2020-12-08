package controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "test delete with body", urlPatterns = "/del")
public class DeleteWithBody extends HttpServlet {
  @Override
  protected void doDelete (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/plain");
    PrintWriter out = resp.getWriter();
    InputStream inputStream = req.getInputStream();
    BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));

    JsonObject jsonObject = JsonParser.parseReader(buffer).getAsJsonObject();
    JsonObject data = jsonObject.get("data").getAsJsonObject();
    String name = data.get("name").getAsString();
    System.out.printf("{ \"name\": \"%s\" } %n", name);
    out.write("OK");
  }
}
