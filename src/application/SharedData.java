package application;
import java.util.Map;
import java.util.HashMap;
public class SharedData {
    private static SharedData instance;
    private Map<String, Object> data;

    private SharedData() {
        data = new HashMap<>();
    }

    public static SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }

    public Object getData(String key) {
        return data.get(key);
    }

    public void setData(String key, Object value) {
        data.put(key, value);
    }
}
