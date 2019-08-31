package ua.com.hedgehog.adspublisher.db.query;

import java.sql.Types;
import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertAdCampaignRelation extends SqlUpdate {
    private static final String SQL_INSERT_RELATION = "insert into ads_campaigns (ads_id, campaign_id) values (:ads_id, :campaign_id)";

    public InsertAdCampaignRelation(DataSource dataSource) {
        super(dataSource, SQL_INSERT_RELATION);
        super.declareParameter(new SqlParameter("ads_id", Types.INTEGER));
        super.declareParameter(new SqlParameter("campaign_id", Types.INTEGER));
    }
}