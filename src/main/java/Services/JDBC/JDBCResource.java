package Services.JDBC;

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

  public DataSource getDataSource() {
    try {
      InitialContext ctx = new InitialContext();
      return (DataSource) ctx.lookup(DB.DATA_SOURCE);
//      return con = ds.getConnection();
    } catch (NamingException  ex) {
      ex.getMessage();
    }
    return null;
  }

}
