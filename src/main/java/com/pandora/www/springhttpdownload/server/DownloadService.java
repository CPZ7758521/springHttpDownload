package com.pandora.www.springhttpdownload.server;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface DownloadService {
    public void download(String urlStr, String fileName, String savePath);

    public ResponseEntity<Resource> directDownload(String urlStr, String fileName, String savePath);
}
