package fr.sewatech.arquillian.ajax.backend;

import com.google.common.base.Function;
import fr.sewatech.arquillian.ajax.json.JSONable;

import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public class Talk implements JSONable {
    public String title;
    public String summary;
    public String speaker;
    static Function<Map<String, Object>, Talk> FROM_MIXIT = new Function<Map<String, Object>, Talk>() {
        @Override
        public Talk apply(Map<String, Object> item) {
            Talk talk = new Talk();
            talk.title = (String) item.get("title");
            talk.summary = (String) item.get("summary");
            Map speaker = ((List<Map>) item.get("speakers")).get(0);
            talk.speaker = speaker.get("firstname") + " " + speaker.get("lastname");
            return talk;
        }
    };
    static Function<Map<String, Object>, Talk> FROM_DEVOXX = new Function<Map<String, Object>, Talk>() {
        @Override
        public Talk apply(Map<String, Object> item) {
            Talk talk = new Talk();
            talk.speaker = (String) item.get("speaker");
            talk.title = (String) item.get("title");
            talk.summary = (String) item.get("summary");
            return talk;
        }
    };

    public String toJSON() {
        return format("{\"title\": \"%s\", \"summary\": \"%s\", \"speaker\": \"%s\"}", title, summary, speaker);
    }

    @Override
    public String toString() {
        return "User" + toJSON();
    }

}
