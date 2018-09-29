package com.wgh.obj;

public class IpObjedct {
    private String start;
    private String end;
    private String address;

    public IpObjedct(String start, String end, String address) {
        this.start = start;
        this.end = end;
        this.address = address;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
