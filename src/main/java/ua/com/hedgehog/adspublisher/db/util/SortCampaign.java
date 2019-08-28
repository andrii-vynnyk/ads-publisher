package ua.com.hedgehog.adspublisher.db.util;

public enum SortCampaign {
    BY_NAME(" c.name "), BY_STATUS(" c.status "), BY_ADS_QTY(" ads ");
    private String query;

    SortCampaign(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
