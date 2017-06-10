package com.demo.event;

/**
 * @author lg
 *         Date: 4/13/17
 *         Time: 3:08 PM
 */
public class Header {

    private String entry;
    private String appId;
    private String hostIp;
    private String hostName;
    private String id;
    private String msg;
    private String cluster;
    private String ezone;
    private String idc;
    private String requestId;
    private String addition;
    private int rpcLevel;

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getEzone() {
        return ezone;
    }

    public void setEzone(String ezone) {
        this.ezone = ezone;
    }

    public String getIdc() {
        return idc;
    }

    public void setIdc(String idc) {
        this.idc = idc;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public int getRpcLevel() {
        return rpcLevel;
    }

    public void setRpcLevel(int rpcLevel) {
        this.rpcLevel = rpcLevel;
    }
}
