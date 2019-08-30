package ua.com.hedgehog.adspublisher.db.util;

public enum SortCampaign {
    NAME(" c.name "), STATUS(" c.status "), ADS(" ads ");
    private String query;

    SortCampaign(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
