package ua.com.hedgehog.adspublisher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.hedgehog.adspublisher.db.JdbcAdDAO;
import ua.com.hedgehog.adspublisher.model.Ad;
import ua.com.hedgehog.adspublisher.model.Platform;
import ua.com.hedgehog.adspublisher.model.Status;
import ua.com.hedgehog.adspublisher.rest.exception.AdNotFoundException;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcAdDAOTest {
    @Autowired
    private JdbcAdDAO adDao;

    @Test
    public void find_1() throws MalformedURLException {
        final int adsId = 1;
        final String adsName = "Shoes";
        final Status adsStatus = Status.ACTIVE;
        final URL assetUrl = new URL("https://www.nice.com/");
        final Platform platform = Platform.WEB;

        final Ad ad = adDao.find(adsId);

        assertThat(ad.getId()).isEqualTo(adsId);
        assertThat(ad.getName()).isEqualTo(adsName);
        assertThat(ad.getStatus()).isEqualTo(adsStatus);
        assertThat(ad.getAssetUrl()).isEqualTo(assetUrl);
        assertThat(ad.getPlatforms().contains(platform)).isTrue();
        assertThat(ad.getPlatforms().size()).isOne();
        assertThat(ad.getCampaigns().size()).isEqualTo(2);
    }

    @Test
    public void find_2() throws MalformedURLException {
        final int adsId = 2;
        final String adsName = "Accessories/Equipment";
        final Status adsStatus = Status.PAUSED;
        final URL assetUrl = new URL("https://www.nice.com/");
        final Platform platform_android = Platform.ANDROID;
        final Platform platform_ios = Platform.IOS;

        final Ad ad = adDao.find(adsId);

        assertThat(ad.getId()).isEqualTo(adsId);
        assertThat(ad.getName()).isEqualTo(adsName);
        assertThat(ad.getStatus()).isEqualTo(adsStatus);
        assertThat(ad.getAssetUrl()).isEqualTo(assetUrl);
        assertThat(ad.getPlatforms().contains(platform_android)).isTrue();
        assertThat(ad.getPlatforms().contains(platform_ios)).isTrue();
        assertThat(ad.getPlatforms().size()).isEqualTo(2);
        assertThat(ad.getCampaigns().size()).isOne();
    }

    @Test
    public void crud() throws MalformedURLException {
        Ad insert_test = new Ad();
        insert_test.setName("Bikes");
        insert_test.setStatus(Status.PLANNED);
        insert_test.setAssetUrl(new URL("http://bike.com"));
        insert_test.addPlatform(Platform.ANDROID);
        insert_test.addPlatform(Platform.IOS);
        insert_test.addPlatform(Platform.WEB);
        insert_test.addCampaign(1);
        adDao.insert(insert_test);

        //check
        Ad ad = adDao.find(insert_test.getId());
        assertThat(insert_test.getId()).isGreaterThan(0);
        assertThat(ad).isNotNull();

        Ad update_test = new Ad();
        update_test.setName("Bikes");
        update_test.setStatus(Status.ACTIVE);
        update_test.setAssetUrl(new URL("http://bike.org"));
        update_test.addPlatform(Platform.ANDROID);
        update_test.addCampaign(1);
        update_test.addCampaign(2);
        update_test.setId(insert_test.getId());
        adDao.update(update_test);

        //check
        ad = adDao.find(update_test.getId());
        assertThat(ad.getId()).isEqualTo(update_test.getId());
        assertThat(ad.getName()).isEqualTo(update_test.getName());
        assertThat(ad.getStatus()).isEqualTo(update_test.getStatus());
        assertThat(ad.getAssetUrl()).isEqualTo(update_test.getAssetUrl());
        assertThat(ad.getPlatforms().contains(Platform.ANDROID)).isTrue();
        assertThat(ad.getPlatforms().size()).isOne();
        assertThat(ad.getCampaigns().size()).isEqualTo(2);

        Ad delete_test = new Ad();
        delete_test.setId(insert_test.getId());
        adDao.delete(delete_test.getId());

        //check
        assertThatExceptionOfType(AdNotFoundException.class).isThrownBy(() -> adDao.find(insert_test.getId()));
    }

    @Test(expected = AdNotFoundException.class)
    public void notFoundException() {
        adDao.find(Integer.MAX_VALUE);
    }
}
