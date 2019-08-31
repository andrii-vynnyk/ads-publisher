package ua.com.hedgehog.adspublisher.db.util;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Strings;
import org.springframework.lang.Nullable;

public class SQLQueryBuilder {
    private StringBuilder query = new StringBuilder();
    private boolean hasCondition;
    private String orderBy;
    private String limit;
    private String offset;
    private String groupBy;
    private boolean finished;

    private final static Pattern patternWhere = Pattern.compile("((?!\\(.+)\\s+where\\s+(?!\n*\\){1})(?:.*))", Pattern.CASE_INSENSITIVE);

    public SQLQueryBuilder(final String query) {
        this.query.append(query);

    }

    public SQLQueryBuilder addCondition(final @Nullable String condition) {
        if (!Strings.isNullOrEmpty(condition)) {
            if (isHasCondition()) {
                query.append(" AND ").append(condition);
            } else {
                query.append(" WHERE ").append(condition);
                setHasCondition(true);
            }
        }
        return this;
    }

    public SQLQueryBuilder addORCondition(final @Nullable String condition) {
        if (!Strings.isNullOrEmpty(condition)) {
            if (isHasCondition()) {
                query.append(" OR ").append(condition);
            } else {
                query.append(" WHERE ").append(condition);
                setHasCondition(true);
            }
        }
        return this;
    }

    public void addString(final String string) {
        query.append(string);
    }

    public SQLQueryBuilder orderBy(final String fields) {
        return orderBy(fields, SortDirection.ASC);
    }

    public SQLQueryBuilder orderBy(final String fields, final SortDirection direction) {
        if (fields == null) {
            return this;
        }
        if (direction == null) {
            return orderBy(fields);
        }
        switch (direction) {
            case ASC:
                orderBy = " ORDER BY " + fields + " ASC ";
                break;
            case DESC:
                orderBy = " ORDER BY " + fields + " DESC ";
                break;
            default:
                break;
        }
        return this;
    }

    public SQLQueryBuilder limit(Integer limit) {
        if (limit != null && limit >= 0) {
            this.limit = " LIMIT " + limit;
        }
        return this;
    }

    public SQLQueryBuilder offset(Integer offset) {
        if (offset != null && offset >= 0) {
            this.offset = " OFFSET " + offset;
        }
        return this;
    }

    private void setOrderBy() {
        query.append(Strings.nullToEmpty(orderBy));
    }

    private void setGroupBy() {
        query.append(Strings.nullToEmpty(groupBy));
    }

    private void setLimit() {
        if (Objects.nonNull(limit)) {
            query.append(limit);
            query.append(Strings.nullToEmpty(offset));
        }
    }

    public StringBuilder getQuery() {
        if (finished) {
            return query;
        }
        setGroupBy();
        setOrderBy();
        setLimit();
        finished = true;
        return query;
    }


    private boolean isHasCondition() {
        if (!hasCondition) {
            hasCondition = checkWhereCondition();
        }
        return hasCondition;
    }

    private void setHasCondition(final boolean hasCondition) {
        this.hasCondition = hasCondition;
    }

    @Override
    public String toString() {
        return getQuery().toString();
    }

    private boolean checkWhereCondition() {
        final Matcher matcher = patternWhere.matcher(this.query.toString());
        return matcher.find();
    }

    public void groupBy(String groupBy) {
        this.groupBy = " GROUP BY " + groupBy;
    }
}
