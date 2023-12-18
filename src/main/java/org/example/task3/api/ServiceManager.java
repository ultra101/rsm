package org.example.task3.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ServiceManager {
    private DownloadInfo downloadInfo;
    private UploadService upload;
    private final long MAX_FILE_SIZE = 100;
    private final List<String> notAllowed = List.of("cmd", "com", "dll", "dmg", "exe", "iso", "jar", "js");
    private final int numberOfThreads = 8;

    public ServiceManager(DownloadInfo downloadInfo, UploadService upload) {
        this.downloadInfo = downloadInfo;
        this.upload = upload;
    }
    public void process(long packageId) {
        validate(packageId);

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        List<Future<Report>> futures = new ArrayList<>();

        try {
            downloadInfo.getDownloadInfos(packageId).forEach(el -> {
                Handle handle = new Handle(downloadInfo, upload);
                futures.add(executor.submit(handle));
            });
            report(futures);
        } finally {
            executor.shutdown();
        }
    }

    private void validate(long packageId) {
        List<DownloadInfo> downloadInfoList = downloadInfo.getDownloadInfos(packageId);
        long totalSize = downloadInfoList.stream().map(DownloadInfo::getSize).reduce(0, Integer::sum);
        if (totalSize > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("Total file sized exceeded!" + totalSize);
        }

        List<String> fileNameList = downloadInfoList.stream().map(DownloadInfo::getOriginalFileName).toList();
        Set<String> uniqueNames = new HashSet<>(fileNameList);
        if (uniqueNames.size() < fileNameList.size()) {
            throw new IllegalArgumentException("File names not unique!");
        }

        List<String> fileExtList = fileNameList
                .stream()
                .filter(el -> el.contains("."))
                .map(el -> el.substring(el.lastIndexOf(".") + 1)).toList();

        boolean isAllowed = fileExtList.stream().anyMatch(notAllowed::contains);
        if (isAllowed) {
            throw new IllegalArgumentException("File list contains forbidden file extensions!");
        }
    }

    private void report(List<Future<Report>> futures) {
        List<Report> reportList = new ArrayList<>();

        futures.forEach(reportFuture -> {
            try {
                reportList.add(reportFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Report");
        reportList.forEach(report -> {
            System.out.println("\t file name :" + report.getFileName());
            System.out.println("\t upload time(ms) :" + report.getUploadTime());
            System.out.println("\t status :" + report.getStatus());
        });

        System.out.println("Total time : " + reportList.stream().map(Report::getUploadTime).reduce(0L, Long::sum));
        System.out.println("Success : " + reportList.stream().map(el -> el.getStatus().equals(Status.SUCCESS)).toList().size());
        System.out.println("Failures : " + reportList.stream().map(el -> el.getStatus().equals(Status.FAILURE)).toList().size());
    }
}
