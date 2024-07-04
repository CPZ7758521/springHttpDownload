package com.pandora.www.springhttpdownload.server.impl;

import com.pandora.www.springhttpdownload.server.DownloadService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DownloadServiceImpl implements DownloadService {
    @Override
    public void download(String urlStr, String fileName, String savePath) {

    }

    @Override
    public ResponseEntity<Resource> directDownload(String urlStr, String fileName, String savePath) {
        return null;
    }
}
