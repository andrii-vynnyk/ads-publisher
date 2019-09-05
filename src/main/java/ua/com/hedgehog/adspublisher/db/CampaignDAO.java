package ua.com.hedgehog.adspublisher.db;

import java.util.List;

import ua.com.hedgehog.adspublisher.db.util.SortCampaign;
import ua.com.hedgehog.adspublisher.db.util.SortDirection;
import ua.com.hedgehog.adspublisher.model.Campaign;
import ua.com.hedgehog.adspublisher.model.Status;
import ua.com.hedgehog.adspublisher.rest.model.CampaignInfo;

public interface CampaignDAO extends EntityDAO<Campaign> {
    List<CampaignInfo> findAll(Integer page, Integer size, SortCampaign sortBy, SortDirection sortDir, String name, Status status);
}
