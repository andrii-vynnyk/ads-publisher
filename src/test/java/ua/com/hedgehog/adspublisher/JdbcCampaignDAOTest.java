package ua.com.hedgehog.adspublisher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.hedgehog.adspublisher.db.JdbcCampaignDAO;
import ua.com.hedgehog.adspublisher.db.util.SortCampaign;
import ua.com.hedgehog.adspublisher.db.util.SortDirection;
import ua.com.hedgehog.adspublisher.model.Ad;
import ua.com.hedgehog.adspublisher.model.Campaign;
import ua.com.hedgehog.adspublisher.model.Platform;
import ua.com.hedgehog.adspublisher.model.Status;
import ua.com.hedgehog.adspublisher.rest.exception.CampaignNotFoundException;
import ua.com.hedgehog.adspublisher.rest.model.CampaignInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcCampaignDAOTest {
    @Autowired
    private JdbcCampaignDAO campaignDao;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    public void findAll() {
        Integer page = null;
        Integer size = null;
        SortCampaign sortBy = null;
        SortDirection sortDir = null;
        String name = null;
        Status status = null;
        List<CampaignInfo> campaigns = campaignDao.findAll(page, size, sortBy, sortDir, name, status);
        assertThat(campaigns.size()).isEqualTo(2);
    }

    @Test
    public void find() throws MalformedURLException {
        final int campaignId = 1;
        final String campaignName = "Nice";
        final int adsSize = 2;
        final Status status = Status.ACTIVE;
        final LocalDateTime startDate = LocalDateTime.parse("2019-01-01 00:00:00", formatter);
        final LocalDateTime endDate = LocalDateTime.parse("2019-12-31 23:59:59", formatter);

        final int adsId1 = 1;
        final String adsName1 = "Shoes";
        final Status adsStatus1 = Status.ACTIVE;
        final URL assetUrl1 = new URL("https://www.nice.com/");
        final Platform platform1 = Platform.WEB;

        final int adsId2 = 2;
        final String adsName2 = "Accessories/Equipment";
        final Status adsStatus2 = Status.PAUSED;
        final URL assetUrl2 = new URL("https://www.nice.com/");
        final Platform platform2_android = Platform.ANDROID;
        final Platform platform2_ios = Platform.IOS;

        Campaign campaign = campaignDao.find(campaignId);
        assertThat(campaign.getId()).isEqualTo(campaignId);
        assertThat(campaign.getName()).isEqualTo(campaignName);
        assertThat(campaign.getStatus()).isEqualTo(status);
        assertThat(campaign.getStartDate()).isEqualTo(startDate);
        assertThat(campaign.getEndDate()).isEqualTo(endDate);
        assertThat(campaign.getAds().size()).isEqualTo(adsSize);

        for (Ad ad : campaign.getAds()) {
            if (ad.getId() == 1) {
                assertThat(ad.getId()).isEqualTo(adsId1);
                assertThat(ad.getName()).isEqualTo(adsName1);
                assertThat(ad.getStatus()).isEqualTo(adsStatus1);
                assertThat(ad.getAssetUrl()).isEqualTo(assetUrl1);
                assertThat(ad.getPlatforms().contains(platform1)).isTrue();
                assertThat(ad.getPlatforms().size()).isOne();
            } else {
                assertThat(ad.getId()).isEqualTo(adsId2);
                assertThat(ad.getName()).isEqualTo(adsName2);
                assertThat(ad.getStatus()).isEqualTo(adsStatus2);
                assertThat(ad.getAssetUrl()).isEqualTo(assetUrl2);
                assertThat(ad.getPlatforms().contains(platform2_android)).isTrue();
                assertThat(ad.getPlatforms().contains(platform2_ios)).isTrue();
                assertThat(ad.getPlatforms().size()).isEqualTo(2);
            }
        }
    }

    @Test
    public void crud() {
        final LocalDateTime startDate = LocalDateTime.parse("2019-01-01 00:00:00", formatter);
        final LocalDateTime endDate = LocalDateTime.parse("2019-12-31 23:59:59", formatter);
        final LocalDateTime newStartDate = LocalDateTime.parse("2019-03-01 00:00:00", formatter);
        final LocalDateTime newEndDate = LocalDateTime.parse("2019-10-31 23:59:59", formatter);

        Campaign insert_test = new Campaign();
        insert_test.setName("Green book");
        insert_test.setStatus(Status.ACTIVE);
        insert_test.setStartDate(startDate);
        insert_test.setEndDate(endDate);
        campaignDao.insert(insert_test);

        assertThat(insert_test.getId()).isGreaterThan(0);

        //check
        Campaign campaign = campaignDao.find(insert_test.getId());
        assertThat(campaign.getId()).isEqualTo(insert_test.getId());
        assertThat(campaign.getName()).isEqualTo(insert_test.getName());
        assertThat(campaign.getStatus()).isEqualTo(insert_test.getStatus());
        assertThat(campaign.getStartDate()).isEqualTo(insert_test.getStartDate());
        assertThat(campaign.getEndDate()).isEqualTo(insert_test.getEndDate());

        Campaign update_test = new Campaign();
        update_test.setName("Green books");
        update_test.setStatus(Status.PAUSED);
        update_test.setStartDate(newStartDate);
        update_test.setEndDate(newEndDate);
        update_test.setId(insert_test.getId());
        campaignDao.update(update_test);

        //check
        campaign = campaignDao.find(update_test.getId());
        assertThat(campaign.getId()).isEqualTo(update_test.getId());
        assertThat(campaign.getName()).isEqualTo(update_test.getName());
        assertThat(campaign.getStatus()).isEqualTo(update_test.getStatus());
        assertThat(campaign.getStartDate()).isEqualTo(update_test.getStartDate());
        assertThat(campaign.getEndDate()).isEqualTo(update_test.getEndDate());

        Campaign delete_test = new Campaign();
        delete_test.setId(insert_test.getId());
        campaignDao.delete(delete_test.getId());

        //check
        assertThatExceptionOfType(CampaignNotFoundException.class).isThrownBy(() -> campaignDao.find(delete_test.getId()));
    }

    @Test(expected = CampaignNotFoundException.class)
    public void notFoundException() {
        campaignDao.find(Integer.MAX_VALUE);
    }
}
