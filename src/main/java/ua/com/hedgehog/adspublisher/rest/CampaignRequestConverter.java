package ua.com.hedgehog.adspublisher.rest;

import org.springframework.core.convert.converter.Converter;
import ua.com.hedgehog.adspublisher.model.Campaign;
import ua.com.hedgehog.adspublisher.model.CampaignRequest;

public class CampaignRequestConverter implements Converter<CampaignRequest, Campaign> {
    @Override
    public Campaign convert(CampaignRequest source) {
        Campaign campaign = new Campaign();
        campaign.setName(source.getName());
        campaign.setStatus(source.getStatus());
        campaign.setStartDate(source.getStartDate());
        campaign.setEndDate(source.getEndDate());
        return campaign;
    }
}
