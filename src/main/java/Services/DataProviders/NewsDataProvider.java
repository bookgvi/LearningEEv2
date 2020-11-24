package Services.DataProviders;

import DAO.NewsDAO;
import Services.JsonSerializer.SerializerDeserializer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@RequestScoped
public class NewsDataProvider {
  @Inject
  NewsDAO newsDAO;
  @Inject
  SerializerDeserializer sd;

  public String findAll() throws SQLException {
    ArrayList<HashMap<String, Object>> resultArr = new ArrayList<>();
    ResultSet news = newsDAO.getAll();

    while (news.next()) {
      resultArr.add(getNewsRecord(news));
    }
    return sd.jsonSerialize(resultArr, false);
  }


  private HashMap<String, Object> getNewsRecord(ResultSet news) throws SQLException {
    HashMap<String, Object> responseMap = new HashMap<>();
    int id = news.getInt("id");
    String lang = news.getString("lang");
    String title = news.getString("title");
    String date = new Date(news.getLong("date") * 1000).toString();

    responseMap.put("id", id);
    responseMap.put("lang", lang);
    responseMap.put("title", title);
    responseMap.put("date", date);
    return responseMap;
  }
}
