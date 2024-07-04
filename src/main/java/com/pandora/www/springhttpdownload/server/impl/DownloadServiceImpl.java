package com.pandora.www.springhttpdownload.server.impl;

import com.pandora.www.springhttpdownload.server.DownloadService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DownloadServiceImpl implements DownloadService {
    @Override
    public void download(String urlStr, String fileName, String savePath) throws IOException {

    }

    @Override
    public ResponseEntity<Resource> directDownload(String urlStr, String fileName, String savePath) {
        return null;
    }
}
