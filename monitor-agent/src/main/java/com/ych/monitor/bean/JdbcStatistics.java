package com.ych.monitor.bean;

/**
 * @Author yechenhao
 * @Date 25/04/2018
 */
public class JdbcStatistics extends Statistics {

    private String jdbcUrl;

    private String sql;

    public JdbcStatistics(Statistics statistics) {
        super(statistics);
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return "JdbcStatistics{" +
                "begin=" + begin +
                ", end=" + end +
                ", jdbcUrl='" + jdbcUrl + '\'' +
                ", userTime=" + userTime +
                ", sql='" + sql + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", errorType='" + errorType + '\'' +
                ", createTime=" + createTime +
                ", keyId='" + keyId + '\'' +
                ", ip='" + ip + '\'' +
                ", logType='" + logType + '\'' +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}
