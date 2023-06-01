package org.example.url;

public class ClickupUrl {

    private static final String BASE_URL = "https://api.clickup.com/api/v2";
    private static final String TEAM = "/team";
    private static final String SPACE = "/space";

    public static String getSpacesUrl(String teamId) {
        return BASE_URL + TEAM + "/" + teamId + SPACE;
    }


}
