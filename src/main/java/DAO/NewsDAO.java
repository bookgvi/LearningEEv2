package DAO;

import Services.JDBC.JDBCResource;

import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.sql.*;

@RequestScoped
public class NewsDAO {

  @Inject
  JDBCResource jdbcResource;

  public ResultSet getAll () {
    try (
        Connection con = this.jdbcResource.getDataSource().getConnection();
        Statement statement = con.createStatement();
    ) {
      try (ResultSet result = statement.executeQuery("SELECT * FROM news ORDER BY id");) {
        return result;
      }
    } catch (SQLException ex) {
      ex.getMessage();
    }
    return null;
  }

  public ResultSet getOne (Integer id) {
    String findById = "SELECT * FROM news WHERE id = ?";
    try (
        Connection con = this.jdbcResource.getDataSource().getConnection();
        PreparedStatement ps = con.prepareStatement(findById);
    ) {

      ps.setInt(1, id);
      try (ResultSet result = ps.executeQuery();) {
        return result;
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return null;
  }

}
