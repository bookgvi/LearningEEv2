package controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetInputStreamServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    ServletInputStream inputStream = req.getInputStream();
    BufferedReader bufferedReader = req.getReader();
//    BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(inputStream));
    JsonObject json = JsonParser.parseReader(bufferedReader).getAsJsonObject();
//    JsonObject json1 = JsonParser.parseReader(bufferedReader1).getAsJsonObject();
    System.out.printf("json: %s%n", json);
//    System.out.printf("json1: %s%n", json1.get("data").getAsJsonObject());

  }
}
