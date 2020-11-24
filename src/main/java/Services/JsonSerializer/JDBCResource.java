package Services.JsonSerializer;

import defenitions.DB;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ApplicationScoped
public class JDBCResource {
  private Connection con;

  public Connection createConnection() throws NamingException, SQLException {
    InitialContext ctx = new InitialContext();
    DataSource ds = (DataSource) ctx.lookup(DB.DATA_SOURCE);
    return con = ds.getConnection();
  }

  @PreDestroy
  private void closeConnection() throws SQLException {
    con.close();
  }
}
