package controllers;

import Services.JsonSerializer.ServiceDownload;
import defenitions.Defenitions;

import javax.activation.MimeType;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/adownload1", asyncSupported = true)
public class AsyncDownloadServlet extends HttpServlet {
  @Resource
  ManagedExecutorService mes;


  @Override
  protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
    final AsyncContext asyncContext = req.startAsync();

    mes.submit(new Runnable() {
      @Override
      public void run() {
        try {
          InputStream inputStream = getServletContext().getResourceAsStream("/" + Defenitions.defaultFileName);
          String mimeType = getServletContext().getMimeType(Defenitions.defaultFileName);
          resp.setContentType(mimeType != null ? mimeType : "text/plain");
          resp.setHeader("Content-Disposition", "attachment; filename=\"" + Defenitions.defaultFileName + "\"");
          download(inputStream, resp, Defenitions.BUF_SIZE);
          asyncContext.complete();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }
  private void download(InputStream in, ServletResponse resp, int BUF_SIZE) throws IOException {
    int length = 0;
    OutputStream out = resp.getOutputStream();
    byte[] buff = new byte[BUF_SIZE];
    Logger.getLogger("BUFFER").log(Level.INFO, String.valueOf(in));
    while ((in != null) && ((length = in.read(buff)) != -1)) {
      Logger.getLogger("BUFFER").log(Level.INFO, String.valueOf(length));
      out.write(buff, 0, length);
    }
    out.flush();
    out.close();
  }

}
