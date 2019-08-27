package ua.com.hedgehog.adspublisher.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.com.hedgehog.adspublisher.db.query.DeleteCampaign;
import ua.com.hedgehog.adspublisher.db.query.InsertCampaign;
import ua.com.hedgehog.adspublisher.db.query.SelectCampaigns;
import ua.com.hedgehog.adspublisher.db.query.UpdateCampaign;
import ua.com.hedgehog.adspublisher.model.Campaign;
import ua.com.hedgehog.adspublisher.service.CampaignInfo;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository("campaignDao")
public class JdbcCampaignDAO implements CampaignDAO {
    private DataSource dataSource;
    private SelectCampaigns select;
    private InsertCampaign insert;
    private UpdateCampaign update;
    private DeleteCampaign delete;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        final NamedParameterJdbcTemplate jJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        select = new SelectCampaigns(jJdbcTemplate);
        insert = new InsertCampaign(dataSource);
        update = new UpdateCampaign(dataSource);
        delete = new DeleteCampaign(dataSource);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public List<CampaignInfo> findAll() {
        return select.findAllInfo();
    }

    @Override
    public void insert(Campaign campaign) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", campaign.getName());
        paramMap.put("status", campaign.getStatus().ordinal());
        paramMap.put("start_date", campaign.getStartDate());
        paramMap.put("end_date", campaign.getEndDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insert.updateByNamedParam(paramMap, keyHolder);
        campaign.setId(keyHolder.getKey().intValue());
        log.debug("New campaign inserted with id: " + campaign.getId());
    }

    @Override
    public void update(Campaign campaign) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", campaign.getName());
        paramMap.put("status", campaign.getStatus().ordinal());
        paramMap.put("start_date", campaign.getStartDate());
        paramMap.put("end_date", campaign.getEndDate());
        paramMap.put("id", campaign.getId());
        update.updateByNamedParam(paramMap);
        log.debug("Existing campaign updated with id: " + campaign.getId());
    }

    @Override
    public void delete(int campaignId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", campaignId);
        delete.updateByNamedParam(paramMap);
        log.info("Deleting campaign with id: " + campaignId);
    }

    @Override
    public Campaign find(int campaignId) {
        return select.find(campaignId);
    }

    @Override
    public void insertWithAds(Campaign campaign) {

    }
}
