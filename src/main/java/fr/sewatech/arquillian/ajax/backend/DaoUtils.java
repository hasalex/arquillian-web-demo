package fr.sewatech.arquillian.ajax.backend;

import com.google.common.base.Function;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.transform;
import static java.lang.Thread.sleep;

public class DaoUtils {
    public static Map<String, Object>[] loadFromFile(String fileName) {
        try {
            InputStream stream = TalkDAO.class.getClassLoader().getResourceAsStream(fileName);// https://cfp.devoxx.com/rest/v1/events/8/devoxx-2013-talks.json
            Reader reader = new InputStreamReader(stream, "iso-8859-1");

            ObjectMapper mapper = new ObjectMapper();
            Map[] maps = mapper.readValue(reader, Map[].class);
            return maps;
        } catch (JsonParseException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public static <T> List<T> loadFromFile(String fileName, Function<Map<String, Object>, T> function) {
        return newArrayList(transform(newArrayList(loadFromFile(fileName)), function));
    }

    public static void connectToMyBigAndExpensiveDatabase() {
        try {
            sleep(300);
        } catch (InterruptedException e) {
        }
    }

}
