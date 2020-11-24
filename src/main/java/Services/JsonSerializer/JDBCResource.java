package Services.JsonSerializer;

import defenitions.DB;

import javax.enterprise.context.ApplicationScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ApplicationScoped
public class JDBCResource {
  public Connection createConnection() throws NamingException, SQLException {
    InitialContext ctx = new InitialContext();
    DataSource ds = (DataSource) ctx.lookup(DB.DATA_SOURCE);
    return ds.getConnection();
  }

  public void closeConnection(Connection conn) throws SQLException {
    conn.close();
  }
}
