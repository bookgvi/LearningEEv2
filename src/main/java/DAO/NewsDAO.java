package DAO;

import Services.JDBC.JDBCResource;

import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RequestScoped
public class NewsDAO {
  private Statement statement;

  @Inject
  JDBCResource jdbcResource;

  public ResultSet getAll() {
    ResultSet result = null;
    try {
      statement = jdbcResource.createConnection().createStatement();
      result = statement.executeQuery("SELECT * FROM news");
    } catch (SQLException ex) {
      ex.getMessage();
    }
    return result;
  }

  @PreDestroy
  private void closeStatement() {
    try {
      statement.close();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
}
