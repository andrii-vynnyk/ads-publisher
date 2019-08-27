package ua.com.hedgehog.adspublisher.db;

import ua.com.hedgehog.adspublisher.model.Campaign;
import ua.com.hedgehog.adspublisher.service.CampaignInfo;

import java.util.List;

public interface CampaignDAO {
    List<CampaignInfo> findAll();

    void insert(Campaign campaign);

    void update(Campaign campaign);

    void delete(int campaignId);

    Campaign find(int campaignId);

    void insertWithAds(Campaign campaign);
}
