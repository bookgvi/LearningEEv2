package DAO;

import Services.JDBC.JDBCResource;

import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RequestScoped
public class NewsDAO {
  private Statement statement = null;
  private PreparedStatement ps = null;
  private ResultSet result = null;

  @Inject
  JDBCResource jdbcResource;

  public ResultSet getAll() {
    try {
      statement = jdbcResource.createConnection().createStatement();
      result = statement.executeQuery("SELECT * FROM news ORDER BY id");
    } catch (SQLException ex) {
      ex.getMessage();
    }
    return result;
  }

  public ResultSet getOne(Integer id) {

    String findById = "SELECT * FROM news WHERE id = ?";
    try {
      ps = jdbcResource.createConnection().prepareStatement(findById);
      ps.setInt(1, id);

      result = ps.executeQuery();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return result;
  }

  @PreDestroy
  private void closeStatement() {
    try {
      if (statement != null) statement.close();
      if (ps != null) ps.close();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
}
