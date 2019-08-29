package ua.com.hedgehog.adspublisher.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ua.com.hedgehog.adspublisher.db.JdbcCampaignDAO;

@RestController
public class AdsController {
    @Autowired
    private JdbcCampaignDAO campaignDao;
}
