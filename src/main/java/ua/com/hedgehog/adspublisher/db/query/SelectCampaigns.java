package ua.com.hedgehog.adspublisher.db.query;

import com.google.common.base.Strings;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ua.com.hedgehog.adspublisher.db.util.SQLQueryBuilder;
import ua.com.hedgehog.adspublisher.db.util.SortCampaign;
import ua.com.hedgehog.adspublisher.db.util.SortDirection;
import ua.com.hedgehog.adspublisher.model.Ad;
import ua.com.hedgehog.adspublisher.model.Campaign;
import ua.com.hedgehog.adspublisher.model.Platform;
import ua.com.hedgehog.adspublisher.model.Status;
import ua.com.hedgehog.adspublisher.rest.exception.CampaignNotFoundException;
import ua.com.hedgehog.adspublisher.service.CampaignInfo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SelectCampaigns {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SelectCampaigns(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<CampaignInfo> findAllInfo(Integer page, Integer size, SortCampaign sortBy, SortDirection sortDir, String nameFilter, Status statusFilter) {
        SQLQueryBuilder builder = new SQLQueryBuilder("select c.id, c.name, c.status, count(ac.ads_id) ads from campaigns c " +
                "left join ads_campaigns ac on c.id = ac.campaign_id");
        builder.groupBy("c.id");
        builder.limit(size);
        builder.offset(Objects.nonNull(page) && Objects.nonNull(size) ? page * size : null);
        builder.orderBy(sortBy == null ? null : sortBy.getQuery(), sortDir);
        builder.addCondition(Strings.isNullOrEmpty(nameFilter) ? null : " c.name='" + nameFilter + "'");
        builder.addCondition(statusFilter == null ? null : " c.status=" + statusFilter.ordinal());
        return namedParameterJdbcTemplate.query(builder.toString(), (rs, row) -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            Status status = Status.byId(rs.getInt("status"));
            int adsSize = rs.getInt("ads");
            return new CampaignInfo(id, name, status, adsSize);
        });
    }

    public Campaign find(int campaignId) {
        String sql = "select c.id, c.name, c.status, c.start_date, c.end_date, a.id ads_id, a.name ads_name, a.status ads_status, a.asset_url, p.id platform from campaigns c " +
                "left join ads_campaigns ac on c.id = ac.campaign_id " +
                "left join ads a on a.id = ac.ads_id " +
                "left join ads_platforms ap on a.id = ap.ads_id " +
                "left join platforms p on ap.platform_id = p.id " +
                "where c.id = :campaignId";
        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("campaignId", campaignId), rs -> {
            Campaign campaign = null;
            Map<Integer, Ad> ads = new HashMap<>();
            while (rs.next()) {
                if (campaign == null) {
                    campaign = new Campaign();
                    campaign.setId(rs.getInt("id"));
                    campaign.setName(rs.getString("name"));
                    campaign.setStatus(Status.byId(rs.getInt("status")));
                    campaign.setStartDate(rs.getTimestamp("start_date").toLocalDateTime());
                    campaign.setEndDate(rs.getTimestamp("end_date").toLocalDateTime());
                }
                int adId = rs.getInt("ads_id");
                if (adId > 0) {
                    Ad ad = ads.get(adId);
                    if (ad == null) {
                        ad = new Ad();
                        ad.setId(adId);
                        ad.setName(rs.getString("ads_name"));
                        ad.setStatus(Status.byId(rs.getInt("ads_status")));
                        try {
                            ad.setAssetUrl(new URL(rs.getString("asset_url")));
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        ads.put(ad.getId(), ad);
                    }
                    ad.addPlatform(Platform.byId(rs.getInt("platform")));
                    campaign.addAd(ad);
                }
            }
            if (campaign == null) {
                throw new CampaignNotFoundException(campaignId);
            }
            return campaign;
        });
    }
}
