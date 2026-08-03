package com.example.ingestion.dto;

public class PaginationConfig {

    private String type;
    private int pageSize;
    private String limitParam;
    private String offsetParam;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getLimitParam() {
        return limitParam;
    }

    public void setLimitParam(String limitParam) {
        this.limitParam = limitParam;
    }

    public String getOffsetParam() {
        return offsetParam;
    }

    public void setOffsetParam(String offsetParam) {
        this.offsetParam = offsetParam;
    }
}