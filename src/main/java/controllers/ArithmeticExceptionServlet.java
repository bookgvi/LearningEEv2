package controllers;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "Arithmetic exception source", urlPatterns = "/arithmex", asyncSupported = true)
public class ArithmeticExceptionServlet extends HttpServlet {
  @Resource
  ManagedExecutorService mes;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    final AsyncContext asyncContext = req.startAsync();
    asyncContext.addListener(new AsyncListenerClass());
    Integer result = null;
    try {
      result = mes.submit(new Callable<Integer>() {
        public Integer call() {
          asyncContext.complete();
          return 10 / 0;
        }
      }).get();
    } catch (InterruptedException | ExecutionException e) {
      Logger.getLogger("Exception").log(Level.WARNING, e.getMessage());
    }
    resp.getWriter().write(result.toString());
  }
}
