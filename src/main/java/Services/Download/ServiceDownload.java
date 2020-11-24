package Services.Download;

import javax.enterprise.context.RequestScoped;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
public class ServiceDownload {
  public void download(ServletContext ctx, HttpServletResponse resp, int BUF_SIZE, String fileName) throws IOException {
    int length = 0;
    OutputStream out = resp.getOutputStream();
    byte[] buff = new byte[BUF_SIZE];
    InputStream in = ctx.getResourceAsStream("/" + fileName);
    Logger.getLogger("BUFFER").log(Level.INFO, String.valueOf(in));
    while ((in != null) && ((length = in.read(buff)) != -1)) {
      Logger.getLogger("BUFFER").log(Level.INFO, String.valueOf(length));
      out.write(buff, 0, length);
    }
    out.flush();
    out.close();
  }

}
