package org.example.url;

public class ClickupUrl {

    private static final String BASE_URL = "https://api.clickup.com/api/v2";
    private static final String TEAM = "/team";
    private static final String SPACE = "/space";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getSpacesUrl(String teamId) {
        return TEAM + "/" + teamId + SPACE;
    }


}
