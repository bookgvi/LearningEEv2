package controllers;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "DownloadFile handler", urlPatterns = "/download1")
public class DownloadFileServlet extends HttpServlet {
  private final String defaultFile = "/test_file_for_download.txt";
  private final int BUF_SIZE = 1024;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ServletContext ctx = getServletConfig().getServletContext();
    String mimeType = ctx.getMimeType(this.defaultFile) != null ? ctx.getMimeType(this.defaultFile) : "text/plain";
    resp.setHeader("Content-Disposition", "attachment; filename=\"" + defaultFile + "\"");
    resp.setContentType(mimeType);
    download(ctx, resp);
  }

  private void download(ServletContext ctx, HttpServletResponse resp) throws IOException {
    int length = 0;
    OutputStream out = resp.getOutputStream();
    byte[] buff = new byte[BUF_SIZE];
    InputStream in = ctx.getResourceAsStream(defaultFile);
    Logger.getLogger("BUFFER").log(Level.INFO, String.valueOf(in));
    while ((in != null) && ((length = in.read(buff)) != -1)) {
      Logger.getLogger("BUFFER").log(Level.INFO, String.valueOf(length));
      out.write(buff, 0, length);
    }
    out.flush();
    out.close();
  }
}
