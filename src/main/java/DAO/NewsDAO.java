package DAO;

import Services.JDBC.JDBCResource;

import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.sql.*;

@RequestScoped
public class NewsDAO {
  private ResultSet result = null;

  @Inject
  JDBCResource jdbcResource;

  public ResultSet getAll () {
    try (
        Connection con = this.jdbcResource.getDataSource().getConnection();
        Statement statement = con.createStatement();
    ) {
      result = statement.executeQuery("SELECT * FROM news ORDER BY id");
    } catch (SQLException ex) {
      ex.getMessage();
    }
    return result;
  }

  public ResultSet getOne (Integer id) {
    String findById = "SELECT * FROM news WHERE id = ?";
    try (
        Connection con = this.jdbcResource.getDataSource().getConnection();
        PreparedStatement ps = con.prepareStatement(findById);
    ) {

      ps.setInt(1, id);

      result = ps.executeQuery();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return result;
  }

}
