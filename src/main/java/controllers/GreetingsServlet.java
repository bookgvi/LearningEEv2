package controllers;

import javafx.concurrent.Task;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GreetingsServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    final AsyncContext asyncContext = req.startAsync();
    final PrintWriter write  = resp.getWriter();
    final String servletName = getServletConfig().getServletName();

    AsyncListenerClass asyncListenerClass = asyncContext.createListener(AsyncListenerClass.class);
    asyncContext.addListener(asyncListenerClass);

    asyncContext.setTimeout(500);

    new Thread() {
      @Override
      public void run() {
        try {
          Thread.sleep(2500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        write.print(servletName);
        write.printf("%n1) %s%n", Thread.currentThread().getName());
        asyncContext.complete();
      }
    }.start();

    new Thread() {
      @Override
      public void run() {
        try {
          sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        write.print(servletName);
        write.printf("%n2) %s%n", Thread.currentThread().getName());
        Logger.getLogger(Thread.currentThread().getName()).log(Level.INFO, "Thread 2 finished...");
        asyncContext.complete();
      }
    }.start();

    write.write("\nResponse from main thread... " + Thread.currentThread().getName() + "\n");
  }
}
