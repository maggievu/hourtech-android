// TODO: delete this file, not goint to use I guess - 26/02


package com.example.diego.searchmongodb;

public class SupportData {

    public String getDatabaseName() {
        return "hourtech";
    }

    public String getApiKey() {
        return "qqa6rAFdcOvgeTYXIRzlwvgXwm31LQpF";
    }

    public String getBaseUrl()
    {
        return "https://api.mlab.com/api/1/databases/"+getDatabaseName()+"/collections/";
    }

    public String apiKeyUrl()
    {
        return "?apiKey="+getApiKey();
    }

    public String collectionName()
    {
        return "hourTech";
    }

    public String buildContactsSaveURL()
    {
        return getBaseUrl()+collectionName()+apiKeyUrl();
    }

    public String buildContactsFetchURL()
    {
        return getBaseUrl()+collectionName()+apiKeyUrl();
    }

    public String createContact(MyContact contact) {
        return String
                .format("{\"username\": \"%s\", " + "\"name\": \"%s\", " + "\"email\": \"%s\"}",
                        contact.getUsername(), contact.getName(), contact.getEmail());
    }

}