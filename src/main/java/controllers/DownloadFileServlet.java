package controllers;

import service.ServiceDownload;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DownloadFile handler", urlPatterns = "/download1")
public class DownloadFileServlet extends HttpServlet {

  private final String defaultFile = "/test_file_for_download.txt";
  private final int BUF_SIZE = 1024;

  @Inject
  ServiceDownload d;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ServletContext ctx = getServletConfig().getServletContext();
    String mimeType = ctx.getMimeType(this.defaultFile) != null ? ctx.getMimeType(this.defaultFile) : "text/plain";
    resp.setHeader("Content-Disposition", "attachment; filename=\"" + defaultFile + "\"");
    resp.setContentType(mimeType);
    resp.addCookie(this.setCoockies());
    d.download(ctx, resp, BUF_SIZE, defaultFile);
  }


  private Cookie setCoockies() {
    Cookie guardCoockie = new Cookie("guard", "fuck U");
    guardCoockie.setHttpOnly(true);
    guardCoockie.setMaxAge(-20);
    return guardCoockie;
  }
}
