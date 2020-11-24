package ErrorsHandlers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SQLException handler", urlPatterns = "/error-handler/sqlexception")
public class SQLExceptionHandler extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.printf("QQQ: %s%n", req.getContextPath());
    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
  }
}
