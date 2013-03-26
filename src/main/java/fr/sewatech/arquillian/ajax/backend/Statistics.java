package fr.sewatech.arquillian.ajax.backend;

import static java.lang.String.format;

public class Statistics {
    public static final String NAME = "statistics";

    private int searchCount = 0;
    private int distinctSearchCount = 0;
    private String previousSearch = "";

    public void increment(String currentSearch) {
        if (currentSearch == null || currentSearch.isEmpty()) {
            previousSearch = "";
            return;
        }

        searchCount++;
        if ( previousSearch.isEmpty()
                || !(currentSearch.startsWith(previousSearch) || previousSearch.startsWith(currentSearch)) ) {
            distinctSearchCount++;
        }
        previousSearch = currentSearch;
    }

    public int getSearchCount() {
        return searchCount;
    }

    public int getDistinctSearchCount() {
        return distinctSearchCount;
    }

    @Override
    public String toString() {
        return "Statistics" + format("{\"searchCount\": \"%s\", \"distinctSearchCount\": \"%s\"}", searchCount, distinctSearchCount);
    }
}
