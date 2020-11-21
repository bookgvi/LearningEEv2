package controllers;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener {
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext servletContext = sce.getServletContext();
    ServletRegistration sr = servletContext.addServlet("Greetings Servlet", GreetingsServlet.class);
    sr.addMapping("/hello");
  }
}
