package com.startuptank.wbteam.constant;

public enum ProjectStatus {
    PROGRESS("Progress"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled"),
    OTHER("Other");

    private String status;

    ProjectStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

