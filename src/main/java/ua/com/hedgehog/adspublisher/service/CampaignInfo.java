package ua.com.hedgehog.adspublisher.service;

import ua.com.hedgehog.adspublisher.model.Campaign;
import ua.com.hedgehog.adspublisher.model.Status;

public class CampaignInfo {
    private final int id;
    private final String name;
    private final Status status;
    private final int ads;

    public CampaignInfo(int id, String name, Status status, int adsSize) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.ads = adsSize;
    }

    public CampaignInfo(Campaign campaign) {
        id = campaign.getId();
        name = campaign.getName();
        status = campaign.getStatus();
        ads = campaign.getAds().size();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public int getAds() {
        return ads;
    }
}
