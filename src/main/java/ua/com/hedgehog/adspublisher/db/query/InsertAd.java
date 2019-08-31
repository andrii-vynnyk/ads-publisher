package ua.com.hedgehog.adspublisher.db.query;

import java.sql.Types;
import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertAd extends SqlUpdate {
    private static final String SQL_INSERT_AD = "insert into ads (name, status, asset_url) values (:name, :status, :asset_url)";

    public InsertAd(DataSource dataSource) {
        super(dataSource, SQL_INSERT_AD);
        super.declareParameter(new SqlParameter("name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("status", Types.INTEGER));
        super.declareParameter(new SqlParameter("asset_url", Types.VARCHAR));
        super.setGeneratedKeysColumnNames("id");
        super.setReturnGeneratedKeys(true);
    }
}
