package ua.com.hedgehog.adspublisher.db.query;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ua.com.hedgehog.adspublisher.model.Ad;
import ua.com.hedgehog.adspublisher.model.Platform;
import ua.com.hedgehog.adspublisher.model.Status;
import ua.com.hedgehog.adspublisher.rest.exception.AdNotFoundException;

public class SelectAd {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SelectAd(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Ad find(int adId) {
        String sql = "select a.id, a.name, a.status, a.asset_url, p.id platform, c.id campId from ads a " +
                "join ads_platforms ap on a.id = ap.ads_id " +
                "join platforms p on ap.platform_id = p.id " +
                "join ads_campaigns ac on a.id = ac.ads_id " +
                "join campaigns c on c.id = ac.campaign_id " +
                "where a.id = :adId";
        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("adId", adId), rs -> {
            Ad ad = null;
            while (rs.next()) {
                if (ad == null) {
                    ad = new Ad();
                    ad.setId(adId);
                    ad.setName(rs.getString("name"));
                    ad.setStatus(Status.byId(rs.getInt("status")));
                    try {
                        ad.setAssetUrl(new URL(rs.getString("asset_url")));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
                ad.addPlatform(Platform.byId(rs.getInt("platform")));
                ad.addCampaign(rs.getInt("campId"));
            }
            if (ad == null) {
                throw new AdNotFoundException(adId);
            }
            return ad;
        });
    }
}
