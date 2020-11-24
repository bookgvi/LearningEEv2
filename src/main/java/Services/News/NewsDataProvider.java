package Services.News;

import DAO.NewsDAO;
import Services.JsonSerializer.SerializerDeserializer;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Stateless
public class NewsDataProvider {
  @Inject
  NewsDAO newsDAO;
  @Inject
  SerializerDeserializer sd;

  public String findAll() throws SQLException {
    ResultSet news = newsDAO.getAll();
    Integer id = news.getInt("id");
    String lang = news.getString("lang");
    String title = news.getString("title");
    int dateStamp = news.getInt("date");

    SimpleDateFormat sdf = new SimpleDateFormat("YY-MM-dd");
    String date = sdf.format(new Date(dateStamp));

    HashMap<String, Object> responseMap = new HashMap<>();
    responseMap.put("id", id);
    responseMap.put("lang", lang);
    responseMap.put("title", title);
    responseMap.put("date", date);

    return sd.jsonSerialize(responseMap, false);
  }
}
