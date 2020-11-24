package DAO;

import Services.JDBC.JDBCResource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RequestScoped
public class NewsDAO {
  @Inject
  JDBCResource jdbcResource;

  public ResultSet getAll() {
    ResultSet result = null;
    try {
      Statement statement = jdbcResource.createConnection().createStatement();
      result = statement.executeQuery("SELECT * FROM news");
      statement.close();
    } catch (SQLException ex) {
      ex.getMessage();
    }
    return result;
  }
}
