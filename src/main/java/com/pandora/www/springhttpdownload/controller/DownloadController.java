package com.pandora.www.springhttpdownload.controller;

import com.pandora.www.springhttpdownload.server.DownloadService;
import com.pandora.www.springhttpdownload.server.impl.DownloadServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

@CrossOrigin
@RestController
@RequestMapping(value = "/api/download", method = RequestMethod.GET)
public class DownloadController {
    @Autowired
    DownloadService downloadService;

    String globalSavePath;
    String globalFileName;

    @RequestMapping("/test")
    public String test(@RequestParam(value = "url", defaultValue = "") String urlStr,
            @RequestParam(value = "fileName", defaultValue = "") String fileName,
            @RequestParam(value = "url", defaultValue = "") String savePath) {

        return urlStr + fileName + savePath + "\n" + LocalDate.now();
    }

    @RequestMapping("/start")
    public String download(@RequestParam(value = "url", defaultValue = "") String urlStr,
                       @RequestParam(value = "fileName", defaultValue = "") String fileName,
                       @RequestParam(value = "url", defaultValue = "") String savePath) {

        globalSavePath = savePath;
        globalFileName = fileName;
        DownloadServiceImpl downloadService = new DownloadServiceImpl();
        try {
            downloadService.download(urlStr, fileName, savePath);
            return fileName + "--下载成功！";
        } catch (IOException e) {
            e.printStackTrace();
            return fileName + "下载失败！";
        }
    }
}
