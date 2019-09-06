package ua.com.hedgehog.adspublisher.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.com.hedgehog.adspublisher.db.query.DeleteCampaign;
import ua.com.hedgehog.adspublisher.db.query.InsertCampaign;
import ua.com.hedgehog.adspublisher.db.query.SelectCampaigns;
import ua.com.hedgehog.adspublisher.db.query.UpdateCampaign;
import ua.com.hedgehog.adspublisher.db.util.SortCampaign;
import ua.com.hedgehog.adspublisher.db.util.SortDirection;
import ua.com.hedgehog.adspublisher.model.Campaign;
import ua.com.hedgehog.adspublisher.model.CampaignInfo;
import ua.com.hedgehog.adspublisher.model.Status;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("campaignDao")
public class JdbcCampaignDAO implements CampaignDAO {
    private final static Logger log = LoggerFactory.getLogger(JdbcCampaignDAO.class);

    private SelectCampaigns select;
    private InsertCampaign insert;
    private UpdateCampaign update;
    private DeleteCampaign delete;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        select = new SelectCampaigns(new NamedParameterJdbcTemplate(dataSource));
        insert = new InsertCampaign(dataSource);
        update = new UpdateCampaign(dataSource);
        delete = new DeleteCampaign(dataSource);
    }

    @Override
    public List<CampaignInfo> findAll(Integer page, Integer size, SortCampaign sortBy, SortDirection sortDir, String name, Status status) {
        return select.findAllInfo(page, size, sortBy, sortDir, name, status);
    }

    @Override
    public void insert(Campaign campaign) {
        Map<String, Object> paramMap = getParamMap(campaign);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insert.updateByNamedParam(paramMap, keyHolder);
        campaign.setId(keyHolder.getKey().intValue());
        log.debug("New campaign inserted with id: " + campaign.getId());
    }

    @Override
    public void update(Campaign campaign) {
        Map<String, Object> paramMap = getParamMap(campaign);
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

    private Map<String, Object> getParamMap(Campaign campaign) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", campaign.getName());
        paramMap.put("status", campaign.getStatus().ordinal());
        paramMap.put("start_date", campaign.getStartDate());
        paramMap.put("end_date", campaign.getEndDate());
        return paramMap;
    }
}
