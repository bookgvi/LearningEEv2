package Services.News;

import DAO.NewsDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.ResultSet;

@Stateless
public class NewsDataProvider {
  @Inject
  NewsDAO newsDAO;

  public ResultSet findAll() {
    return newsDAO.getAll();
  }
}
