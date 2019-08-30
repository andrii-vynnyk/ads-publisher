package ua.com.hedgehog.adspublisher.db;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.hedgehog.adspublisher.db.query.*;
import ua.com.hedgehog.adspublisher.model.Ad;
import ua.com.hedgehog.adspublisher.model.Platform;

@Repository("adDao")
public class JdbcAdDAO implements AdDAO {
    private final static Logger log = LoggerFactory.getLogger(JdbcAdDAO.class);

    private SelectAd select;
    private InsertAd insert;
    private UpdateAd update;
    private DeleteAd delete;
    private InsertAdPlatformRelation insertAdPlatformRelation;
    private DeleteAdPlatformRelation deleteAdPlatformRelation;
    private InsertAdCampaignRelation insertAdCampaignRelation;
    private DeleteAdCampaignRelation deleteAdCampaignRelation;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        select = new SelectAd(new NamedParameterJdbcTemplate(dataSource));
        insert = new InsertAd(dataSource);
        update = new UpdateAd(dataSource);
        delete = new DeleteAd(dataSource);
        insertAdPlatformRelation = new InsertAdPlatformRelation(dataSource);
        deleteAdPlatformRelation = new DeleteAdPlatformRelation(dataSource);
        insertAdCampaignRelation = new InsertAdCampaignRelation(dataSource);
        deleteAdCampaignRelation = new DeleteAdCampaignRelation(dataSource);
    }

    @Override
    @Transactional
    public void insert(Ad ad) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", ad.getName());
        paramMap.put("status", ad.getStatus().ordinal());
        paramMap.put("asset_url", ad.getAssetUrl().toString());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insert.updateByNamedParam(paramMap, keyHolder);
        ad.setId(keyHolder.getKey().intValue());
        attachPlatforms(ad);
        attachCampaign(ad);
        log.debug("New ad inserted with id: " + ad.getId());
    }

    @Override
    @Transactional
    public void update(Ad ad) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", ad.getName());
        paramMap.put("status", ad.getStatus().ordinal());
        paramMap.put("asset_url", ad.getAssetUrl().toString());
        paramMap.put("id", ad.getId());
        update.updateByNamedParam(paramMap);
        Map<String, Object> delParamMap = new HashMap<>();
        delParamMap.put("id", ad.getId());
        deleteAdPlatformRelation.updateByNamedParam(delParamMap);
        attachPlatforms(ad);
        detachCampaign(ad);
        attachCampaign(ad);
        log.debug("Existing ad updated with id: " + ad.getId());
    }

    @Override
    public void delete(int adId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", adId);
        delete.updateByNamedParam(paramMap);
        log.info("Deleting ad with id: " + adId);
    }

    @Override
    public Ad find(int adId) {
        return select.find(adId);
    }

    private void attachPlatforms(Ad ad) {
        for (Platform platform : ad.getPlatforms()) {
            Map<String, Object> insertRelParams = new HashMap<>();
            insertRelParams.put("ads_id", ad.getId());
            insertRelParams.put("platform_id", platform.ordinal());
            insertAdPlatformRelation.updateByNamedParam(insertRelParams);
        }
    }

    private void attachCampaign(Ad ad) {
        for (Integer campaignId : ad.getCampaigns()) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("ads_id", ad.getId());
            paramMap.put("campaign_id", campaignId);
            insertAdCampaignRelation.updateByNamedParam(paramMap);
        }
    }

    private void detachCampaign(Ad ad) {
        for (Integer campaignId : ad.getCampaigns()) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("id", ad.getId());
            deleteAdCampaignRelation.updateByNamedParam(paramMap);
        }
    }
}
