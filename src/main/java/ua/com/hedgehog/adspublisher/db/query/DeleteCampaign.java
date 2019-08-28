package ua.com.hedgehog.adspublisher.db.query;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class DeleteCampaign extends SqlUpdate {
    private static final String SQL_DELETE_CAMPAIGN =
            "delete from campaigns where id = :id";

    public DeleteCampaign(DataSource dataSource) {
        super(dataSource, SQL_DELETE_CAMPAIGN);
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
    }
}