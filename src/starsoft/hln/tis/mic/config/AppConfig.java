package starsoft.hln.tis.mic.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class AppConfig {

    private static Map<String, String> settings = new HashMap<>();
    public static void initialize(InputStreamReader reader) throws IOException {
        Gson gson = new Gson();
        Type listType = new TypeToken<HashMap<String, String>>(){}.getType();

        settings = gson.fromJson(reader, listType);
    }

    public static String get(String key) {
        String value = settings.get(key);

        if (value != null) {
            return value;
        }

        throw new RuntimeException("key [" + key + "] is not found.");
    }

}
