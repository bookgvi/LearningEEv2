package controllers;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener {
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext servletContext = sce.getServletContext();
    ServletRegistration.Dynamic sr = servletContext.addServlet("Greetings Servlet", GreetingsServlet.class);
    sr.addMapping("/hello");
    sr.setAsyncSupported(true);

    try {
      GetInputStreamServlet getInputStreamServlet = servletContext.createServlet(GetInputStreamServlet.class);
      ServletRegistration.Dynamic sr2 = servletContext.addServlet("InputStream handler", getInputStreamServlet);
      sr2.addMapping("/instream");
    } catch (ServletException ignored) {
    }
  }
}
