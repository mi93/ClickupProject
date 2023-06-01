package org.example.properties;

import java.util.ResourceBundle;

public class ClickupProperties {

    private static final String TOKEN = "clickup.token";
    private static final String TEAM_ID = "clickup.team.id";

    private static String getProperty(String property) {
        return ResourceBundle.getBundle("clickup").getString(property);
    }

    public static String getToken() {
        return getProperty(TOKEN);
    }

    public static String getTeamId() {
        return getProperty(TEAM_ID);
    }
}
