package com.event.collector.model;

public class Meters {

    MeterAttribute requestCount;
    MeterAttribute requestResponse4xx;
    MeterAttribute requestResponse5xx;
    MeterAttribute requestResponse1xx;
    MeterAttribute requestResponse2xx;
    MeterAttribute requestResponse3xx;

    public MeterAttribute getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(MeterAttribute requestCount) {
        this.requestCount = requestCount;
    }

    public MeterAttribute getRequestResponse4xx() {
        return requestResponse4xx;
    }

    public void setRequestResponse4xx(MeterAttribute requestResponse4xx) {
        this.requestResponse4xx = requestResponse4xx;
    }

    public MeterAttribute getRequestResponse5xx() {
        return requestResponse5xx;
    }

    public void setRequestResponse5xx(MeterAttribute requestResponse5xx) {
        this.requestResponse5xx = requestResponse5xx;
    }

    public MeterAttribute getRequestResponse1xx() {
        return requestResponse1xx;
    }

    public void setRequestResponse1xx(MeterAttribute requestResponse1xx) {
        this.requestResponse1xx = requestResponse1xx;
    }

    public MeterAttribute getRequestResponse2xx() {
        return requestResponse2xx;
    }

    public void setRequestResponse2xx(MeterAttribute requestResponse2xx) {
        this.requestResponse2xx = requestResponse2xx;
    }

    public MeterAttribute getRequestResponse3xx() {
        return requestResponse3xx;
    }

    public void setRequestResponse3xx(MeterAttribute requestResponse3xx) {
        this.requestResponse3xx = requestResponse3xx;
    }
}
