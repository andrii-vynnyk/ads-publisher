package ua.com.hedgehog.adspublisher.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.hedgehog.adspublisher.db.JdbcCampaignDAO;
import ua.com.hedgehog.adspublisher.model.Campaign;
import ua.com.hedgehog.adspublisher.service.CampaignInfo;
import ua.com.hedgehog.adspublisher.service.CampaignRequest;

import java.util.List;

@RestController
public class CampaignsController {
    @Autowired
    private JdbcCampaignDAO campaignDao;

    @GetMapping("/summaries")
    public List<CampaignInfo> summaries() {
        return campaignDao.findAll();
    }

    @GetMapping("/campaign/{campaignId}")
    public Campaign getCampaign(@PathVariable("campaignId") int id) {
        return campaignDao.find(id);
    }

    @PostMapping("/campaign")
    public Campaign insertCampaign(@RequestBody CampaignRequest request) {
        Campaign campaign = new Campaign();
        campaign.setName(request.getName());
        campaign.setStatus(request.getStatus());
        campaign.setStartDate(request.getStartDate());
        campaign.setEndDate(request.getEndDate());
        campaignDao.insert(campaign);
        return campaign;
    }
}
