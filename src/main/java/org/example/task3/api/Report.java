package org.example.task3.api;

public class Report {
    private long uploadTime;
    private String fileName;
    private Status status;

    public void setUploadTime(long uploadTime) {
        this.uploadTime = uploadTime;
    }

    public long getUploadTime() {
        return uploadTime;
    }

    public String getFileName() {
        return fileName;
    }

    public Status getStatus() {
        return status;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
