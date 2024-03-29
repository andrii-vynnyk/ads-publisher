package ua.com.hedgehog.adspublisher;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.hedgehog.adspublisher.db.JdbcCampaignDAO;
import ua.com.hedgehog.adspublisher.rest.CampaignsController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdsPublisherAppTests {
    @Autowired
    private JdbcCampaignDAO dao;
    @Autowired
    private CampaignsController controller;

    @Test
    public void contextLoads() {
        assertThat(dao).isNotNull();
        assertThat(controller).isNotNull();
    }
}
