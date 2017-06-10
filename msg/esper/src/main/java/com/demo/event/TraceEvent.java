package com.demo.event;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lg
 *         Date: 4/13/17
 *         Time: 3:07 PM
 */
public class TraceEvent {

    private Header header;
    private long id;
    private Map<String, String> tags;
    private String type;
    private String name;
    private String status;
    private long timestamp;
    private long timeMinutes;
    private String data;
    private long duration;

    public long getTimeMinutes() {
        return timeMinutes;
    }

    public void setTimeMinutes(long timeMinutes) {
        this.timeMinutes = timeMinutes;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public void addTag(String key, String value) {
        if (key == null || value == null) {
            return;
        }
        if (this.tags == null) {
            this.tags = new HashMap();
        }
        this.tags.put(key, value);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        this.timeMinutes = timestamp/1000/60*60000;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
