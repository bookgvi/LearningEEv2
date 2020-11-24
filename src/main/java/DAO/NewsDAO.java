package DAO;

import Services.JsonSerializer.JDBCResource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.naming.NamingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RequestScoped
public class NewsDAO {
  @Inject
  JDBCResource jdbcResource;

  public ResultSet getAll() throws SQLException, NamingException {
    Statement statement = jdbcResource.createConnection().createStatement();
    ResultSet result = statement.executeQuery("SELECT * FROM news");
    statement.close();
    return result;
  }
}
