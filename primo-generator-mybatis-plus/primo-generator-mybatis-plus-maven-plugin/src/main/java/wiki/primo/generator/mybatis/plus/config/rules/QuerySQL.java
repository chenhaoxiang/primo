package wiki.primo.generator.mybatis.plus.config.rules;

/**
 * <p>
 * 表数据查询
 * </p>
 *
 * @author chenhx
 * @since 2020-04-25
 */
public enum QuerySQL {
    MYSQL("mysql", "show tables", "show table status",
            "show full fields from %s",
            "NAME", "COMMENT", "FIELD", "TYPE", "COMMENT", "KEY"),

    ORACLE("oracle", "SELECT * FROM USER_TABLES", "SELECT * FROM USER_TAB_COMMENTS",
            "SELECT A.COLUMN_NAME, CASE WHEN A.DATA_TYPE='NUMBER' THEN " +
                    "(CASE WHEN A.DATA_PRECISION IS NULL THEN A.DATA_TYPE " +
                    "WHEN NVL(A.DATA_SCALE, 0) > 0 THEN A.DATA_TYPE||'('||A.DATA_PRECISION||','||A.DATA_SCALE||')' " +
                    "ELSE A.DATA_TYPE||'('||A.DATA_PRECISION||')' END) " +
                    "ELSE A.DATA_TYPE END DATA_TYPE, B.COMMENTS,DECODE(C.POSITION, '1', 'PRI') KEY " +
                    "FROM USER_TAB_COLUMNS A INNER JOIN USER_COL_COMMENTS B ON A.TABLE_NAME = B.TABLE_NAME" +
                    " AND A.COLUMN_NAME = B.COLUMN_NAME LEFT JOIN USER_CONSTRAINTS D " +
                    "ON D.TABLE_NAME = A.TABLE_NAME AND D.CONSTRAINT_TYPE = 'P' " +
                    "LEFT JOIN USER_CONS_COLUMNS C ON C.CONSTRAINT_NAME = D.CONSTRAINT_NAME " +
                    "AND C.COLUMN_NAME=A.COLUMN_NAME WHERE A.TABLE_NAME = '%s' ",
            "TABLE_NAME", "COMMENTS", "COLUMN_NAME", "DATA_TYPE", "COMMENTS", "KEY");

    private final String dbType;
    private final String tablesSql;
    private final String tableCommentsSql;
    private final String tableFieldsSql;
    private final String tableName;
    private final String tableComment;
    private final String fieldName;
    private final String fieldType;
    private final String fieldComment;
    private final String fieldKey;


    QuerySQL(final String dbType, final String tablesSql, final String tableCommentsSql,
             final String tableFieldsSql, final String tableName, final String tableComment, final String fieldName,
             final String fieldType, final String fieldComment, final String fieldKey) {
        this.dbType = dbType;
        this.tablesSql = tablesSql;
        this.tableCommentsSql = tableCommentsSql;
        this.tableFieldsSql = tableFieldsSql;
        this.tableName = tableName;
        this.tableComment = tableComment;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.fieldComment = fieldComment;
        this.fieldKey = fieldKey;
    }

    public String getDbType() {
        return dbType;
    }

    public String getTablesSql() {
        return tablesSql;
    }

    public String getTableCommentsSql() {
        return tableCommentsSql;
    }

    public String getTableFieldsSql() {
        return tableFieldsSql;
    }

    public String getTableName() {
        return tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public String getFieldComment() {
        return fieldComment;
    }

    public String getFieldKey() {
        return fieldKey;
    }

}
