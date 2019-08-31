package ua.com.hedgehog.adspublisher.db.query;

import java.sql.Types;
import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UpdateCampaign extends SqlUpdate {
    private static final String SQL_UPDATE_CAMPAIGN = "update campaigns set name = :name, status = :status, start_date = :start_date, end_date = :end_date where id = :id";

    public UpdateCampaign(DataSource dataSource) {
        super(dataSource, SQL_UPDATE_CAMPAIGN);
        super.declareParameter(new SqlParameter("name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("status", Types.INTEGER));
        super.declareParameter(new SqlParameter("start_date", Types.TIMESTAMP));
        super.declareParameter(new SqlParameter("end_date", Types.TIMESTAMP));
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
    }
}
