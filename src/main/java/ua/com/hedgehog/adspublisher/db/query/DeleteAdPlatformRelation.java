package ua.com.hedgehog.adspublisher.db.query;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class DeleteAdPlatformRelation extends SqlUpdate {
    private static final String SQL_DELETE_RELATION = "delete from ads_platforms where ads_id = :id";

    public DeleteAdPlatformRelation(DataSource dataSource) {
        super(dataSource, SQL_DELETE_RELATION);
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
    }
}