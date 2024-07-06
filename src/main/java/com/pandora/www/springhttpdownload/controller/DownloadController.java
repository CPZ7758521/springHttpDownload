package com.pandora.www.springhttpdownload.controller;

import com.pandora.www.springhttpdownload.server.DownloadService;
import com.pandora.www.springhttpdownload.server.impl.DownloadServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    public ResponseEntity<Resource> downloadFile() throws FileNotFoundException {

        //读取文件并创建输入流
        File file = new File(globalSavePath + globalFileName);

        FileInputStream inputStream = new FileInputStream(file);

        //创建输入流资源对象
        InputStreamResource resource = new InputStreamResource(inputStream);

        //设置下载相应的头部信息
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + globalFileName);


        //返回带有文件数据流的响应实体
        return ResponseEntity
                .ok()
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource)
                ;

    }


    public ResponseEntity<Resource> directDownload(@RequestParam(value = "url", defaultValue = "") String urlStr,
                                                   @RequestParam(value = "fileName", defaultValue = "") String fileName,
                                                   @RequestParam(value = "url", defaultValue = "") String savePath) throws IOException {
        globalSavePath = savePath;
        globalFileName = fileName;

        DownloadServiceImpl downloadService = new DownloadServiceImpl();

        return downloadService.directDownload(urlStr, fileName, savePath);
    }
}
