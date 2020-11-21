package ErrorsHandlers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.IOException;

@WebServlet(name = "NullPointerExceptions handler", urlPatterns = "/error-handler/nullpointer")
public class NullPointerExceptionsHandler extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//    resp.getWriter().write(req.getDispatcherType().toString());
  }
}
