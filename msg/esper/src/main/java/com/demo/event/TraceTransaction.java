package com.demo.event;

/**
 * @author lg
 *         Date: 4/13/17
 *         Time: 3:09 PM
 */
public abstract  class TraceTransaction {

    private long duration;

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
