package ua.com.hedgehog.adspublisher.db.query;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class UpdateAd extends SqlUpdate {
    private static final String SQL_UPDATE_AD = "update ads set name = :name, status = :status, asset_url = :asset_url where id = :id";

    public UpdateAd(DataSource dataSource) {
        super(dataSource, SQL_UPDATE_AD);
        super.declareParameter(new SqlParameter("name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("status", Types.INTEGER));
        super.declareParameter(new SqlParameter("asset_url", Types.VARCHAR));
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
    }
}
