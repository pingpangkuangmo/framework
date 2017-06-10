package com.demo.event;

/**
 * @author lg
 *         Date: 4/13/17
 *         Time: 3:10 PM
 */
public class DALEvent extends TraceEvent{

    private String group;
    private String sql;
    private String clientApp;
    private String clientIp;
    private String errorType;
    private String errorCode;
    private long reqLen = 0;
    private long respLen = 0;
    private long wait = 0;
    private long exec = 0;
    private String table;
    private String operation;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getClientApp() {
        return clientApp;
    }

    public void setClientApp(String clientApp) {
        this.clientApp = clientApp;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public long getReqLen() {
        return reqLen;
    }

    public void setReqLen(long reqLen) {
        this.reqLen = reqLen;
    }

    public long getRespLen() {
        return respLen;
    }

    public void setRespLen(long respLen) {
        this.respLen = respLen;
    }

    public long getWait() {
        return wait;
    }

    public void setWait(long wait) {
        this.wait = wait;
    }

    public long getExec() {
        return exec;
    }

    public void setExec(long exec) {
        this.exec = exec;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
