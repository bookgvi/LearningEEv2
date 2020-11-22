package controllers;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GreetingsServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    final AsyncContext asyncContext = req.startAsync();
    final PrintWriter write  = resp.getWriter();
    final String servletName = getServletConfig().getServletName();

    new Thread() {
      @Override
      public void run() {
        write.print(servletName);
        write.print("\nQQQ Greetings!!!");
        asyncContext.complete();
      }
    }.start();

    asyncContext.addListener(new AsyncListenerClass());

    write.write("Response from main thread...\n");
  }
}
