package controllers;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NullpointerException", urlPatterns = "nullpointerex", asyncSupported = true)
public class NullPointerExceptionServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    final AsyncContext asyncContext = req.startAsync();
    asyncContext.addListener(new AsyncListenerClass());
    asyncContext.start(new Runnable() {
      @Override
      public void run() {
        asyncContext.complete();
      }
    });
    throw new NullPointerException();
  }
}
