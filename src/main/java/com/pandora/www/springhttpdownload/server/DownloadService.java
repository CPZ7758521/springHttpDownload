package com.pandora.www.springhttpdownload.server;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface DownloadService {
    public void download(String urlStr, String fileName, String savePath) throws IOException;

    public ResponseEntity<Resource> directDownload(String urlStr, String fileName, String savePath) throws IOException;
}
