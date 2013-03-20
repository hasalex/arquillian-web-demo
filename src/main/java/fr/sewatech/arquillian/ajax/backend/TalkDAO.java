package fr.sewatech.arquillian.ajax.backend;

import com.google.common.base.Predicate;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Strings.nullToEmpty;
import static com.google.common.collect.Collections2.filter;
import static fr.sewatech.arquillian.ajax.backend.DaoUtils.connectToMyBigAndExpensiveDatabase;
import static fr.sewatech.arquillian.ajax.backend.DaoUtils.loadFromFile;

public class TalkDAO {

    public static final TalkDAO INSTANCE = new TalkDAO();
    private final List<Talk> devoxxTalks;
    private final List<Talk> mixitTalks;

    private TalkDAO() {
        devoxxTalks = loadFromFile("devoxx-2013-talks.json", Talk.FROM_DEVOXX);
        Collections.shuffle(devoxxTalks);
        mixitTalks = loadFromFile("mixit-2013-talks.json", Talk.FROM_MIXIT);
        Collections.shuffle(mixitTalks);
    }

    public Collection<Talk> findAtDevoxx(String titleSearch, String speakerSearch) {
        return find(titleSearch, speakerSearch, devoxxTalks);
    }
    public Collection<Talk> findAtMixit(String titleSearch, String speakerSearch) {
        return find(titleSearch, speakerSearch, mixitTalks);
    }

    private Collection<Talk> find(String titleSearch, String speakerSearch, List<Talk> talks) {
        connectToMyBigAndExpensiveDatabase();
        return filter(talks, new FilterPredicate(titleSearch, speakerSearch));
    }

    private static class FilterPredicate implements Predicate<Talk> {
        private String titleSearch;
        private String speakerSearch;

        private FilterPredicate(String titleSearch, String speakerSearch) {
            this.titleSearch = nullToEmpty(titleSearch).trim().toLowerCase();
            this.speakerSearch = nullToEmpty(speakerSearch).trim().toLowerCase();
        }

        @Override
        public boolean apply(Talk talk) {
            return talk.title.toLowerCase().contains(titleSearch)
                    && talk.speaker.toLowerCase().contains(speakerSearch);
        }
    }
}
