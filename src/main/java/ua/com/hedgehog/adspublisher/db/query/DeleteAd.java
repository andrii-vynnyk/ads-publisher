package ua.com.hedgehog.adspublisher.db.query;

import java.sql.Types;
import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class DeleteAd extends SqlUpdate {
    private static final String SQL_DELETE_AD = "delete from ads where id = :id";

    public DeleteAd(DataSource dataSource) {
        super(dataSource, SQL_DELETE_AD);
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
    }
}
