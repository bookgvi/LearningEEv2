package Services.JsonSerializer;

import com.google.gson.Gson;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.HashMap;

@RequestScoped
public class SerializerDeserializer {

  public String jsonSerialize(HashMap map, boolean isError) {
    return serializer(map, isError);
  }

  public <T, V> String jsonSerialize(ArrayList<HashMap<T, V>> array, boolean isError) {
    return serializer(array, isError);
  }

  public String jsonSerialize(HashMap[] array, boolean isError) {
    return serializer(array, isError);
  }

  public String jsonSerialize(Object[] array, boolean isError) {
    return serializer(array, isError);
  }

  private String serializer(Object obj, boolean isError) {
    String dataType = isError ? "errors" : "data";
    HashMap<String, Object> dataMap = new HashMap<String, Object>();

    Gson gson = new Gson();
    dataMap.put(dataType, obj);
    return gson.toJson(dataMap);

  }
}
