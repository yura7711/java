package com.geekbrains.gwt.common;

public class StatusDto {
    private Integer statusId;
    private String rusTitle;

    public StatusDto() {
    }

    public StatusDto(Integer statusId, String rusTitle) {
        this.statusId = statusId;
        this.rusTitle = rusTitle;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getRusTitle() {
        return rusTitle;
    }

    public void setRusTitle(String rusTitle) {
        this.rusTitle = rusTitle;
    }
}
