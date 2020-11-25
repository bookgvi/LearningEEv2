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
  private ResultSet result = null;

  @Inject
  JDBCResource jdbcResource;

  public ResultSet getAll() {
    try (Statement statement = this.jdbcResource.createConnection().createStatement()) {
      result = statement.executeQuery("SELECT * FROM news ORDER BY id");
    } catch (SQLException ex) {
      ex.getMessage();
    }
    return result;
  }

  public ResultSet getOne(Integer id) {

    String findById = "SELECT * FROM news WHERE id = ?";
    try (PreparedStatement ps = this.jdbcResource.createConnection().prepareStatement(findById)) {

      ps.setInt(1, id);

      result = ps.executeQuery();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return result;
  }

}
