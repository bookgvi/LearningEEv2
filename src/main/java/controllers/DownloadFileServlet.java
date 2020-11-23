package controllers;

import defenitions.Defenitions;
import Services.JsonSerializer.ServiceDownload;

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


  @Inject
  ServiceDownload d;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ServletContext ctx = getServletConfig().getServletContext();
    String mimeType = ctx.getMimeType(Defenitions.defaultFileName) != null ? ctx.getMimeType(Defenitions.defaultFileName) : "text/plain";
    resp.setHeader("Content-Disposition", "attachment; filename=\"" + Defenitions.defaultFileName + "\"");
    resp.setContentType(mimeType);
    resp.addCookie(this.setCoockies());
    d.download(ctx, resp, Defenitions.BUF_SIZE, Defenitions.defaultFileName);
  }


  private Cookie setCoockies() {
    Cookie guardCoockie = new Cookie("guard", "fuck U");
    guardCoockie.setHttpOnly(true);
    guardCoockie.setMaxAge(-20);
    return guardCoockie;
  }
}
