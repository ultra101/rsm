package org.example.task3.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

public class Handle implements Callable<Report> {
    private DownloadInfo downloadInfo;
    private UploadService upload;

    public Handle(DownloadInfo downloadInfo, UploadService upload) {
        this.downloadInfo = downloadInfo;
        this.upload = upload;
    }

    public Report call() throws URISyntaxException, MalformedURLException {
        String fileKey = downloadInfo.getFileKey();
        int size = downloadInfo.getSize();
        URL url = new URI(downloadInfo.getDownloadURL()).toURL();
        Report report = new Report();
        try {
            Instant start = Instant.now();
            upload.doUpload(fileKey, url.openStream() ,size);
            Instant end = Instant.now();
            report.setFileName(downloadInfo.getOriginalFileName());
            report.setUploadTime(Duration.between(start, end).toMillis());
            report.setStatus(Status.SUCCESS);
        } catch (IOException e) {
            report.setStatus(Status.FAILURE);
        }
        return report;
    }
}
