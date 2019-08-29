package ua.com.hedgehog.adspublisher.db.query;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class InsertAdPlatformRelation extends SqlUpdate {
    private static final String SQL_INSERT_RELATION = "insert into ads_platforms (ads_id, platform_id) values (:ads_id, :platform_id)";

    public InsertAdPlatformRelation(DataSource dataSource) {
        super(dataSource, SQL_INSERT_RELATION);
        super.declareParameter(new SqlParameter("ads_id", Types.INTEGER));
        super.declareParameter(new SqlParameter("platform_id", Types.INTEGER));
    }
}
