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
  }
}
