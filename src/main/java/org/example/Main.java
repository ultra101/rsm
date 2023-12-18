package org.example;

import org.example.task1.FooBar;
import org.example.task3.api.DownloadInfo;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        FooBar fooBar = new FooBar();
        String test1 = "1,2,3,4,5,6,45";
        String test2 = "1,2,3,1,2,3,1,1,15,45,75,15,3,5";
        String test3 = "1,1,3,3,5,5,45,45";
        String test4 = "1.1";

        DownloadInfoImpl downloadInfo = new DownloadInfoImpl();
        List<DownloadInfo> fileList = downloadInfo.getDownloadInfos(100);
        fileList.forEach(file -> {
            file.getSize();
        });
    }
}