package org.example.task3.api;

import java.util.List;

public interface DownloadInfo {
    int getSize();
    String getOriginalFileName();
    String getFileKey();
    String getDownloadURL();
    List<DownloadInfo> getDownloadInfos(long packageId);
}
