package ua.com.hedgehog.adspublisher.db.query;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class InsertCampaign extends SqlUpdate {
    private static final String SQL_INSERT_CAMPAIGN =
            "insert into campaigns (name, status, start_date, end_date) values (:name, :status, :start_date, :end_date);";

    public InsertCampaign(DataSource dataSource) {
        super(dataSource, SQL_INSERT_CAMPAIGN);
        super.declareParameter(new SqlParameter("name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("status", Types.INTEGER));
        super.declareParameter(new SqlParameter("start_date", Types.TIMESTAMP));
        super.declareParameter(new SqlParameter("end_date", Types.TIMESTAMP));
        super.setGeneratedKeysColumnNames("id");
        super.setReturnGeneratedKeys(true);
    }
}
