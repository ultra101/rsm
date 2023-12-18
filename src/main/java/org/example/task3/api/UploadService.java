package org.example.task3.api;

import java.io.InputStream;

public interface UploadService {
    void doUpload(String key, InputStream data, int size);
}
