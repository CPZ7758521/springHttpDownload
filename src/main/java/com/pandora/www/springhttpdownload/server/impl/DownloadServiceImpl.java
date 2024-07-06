package com.pandora.www.springhttpdownload.server.impl;

import com.pandora.www.springhttpdownload.server.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class DownloadServiceImpl implements DownloadService {

    private static Logger LOG = LoggerFactory.getLogger(DownloadServiceImpl.class);

    @Override
    public void download(String urlStr, String fileName, String savePath) throws IOException {

        URL url = new URL(urlStr);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        //设置超时时间 为 3 秒
        conn.setConnectTimeout(3 * 1000);

        //防止屏蔽程序抓取而返回 403 错误
        conn.setRequestProperty("User-agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();

        byte[] getData = readInputStream(inputStream);

        //文件保存位置
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }

        File file = new File(saveDir + File.separator + fileName);

        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter fow = new OutputStreamWriter(fos);

        fos.write(getData);

        if (fos != null) {
            fos.close();
        }

        if (inputStream != null) {
            inputStream.close();
        }


        LOG.info(url + "download success");
    }

    @Override
    public ResponseEntity<Resource> directDownload(String urlStr, String fileName, String savePath) throws IOException {

        URL url = new URL(urlStr);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        //设置超时时间 为 3 秒
        conn.setConnectTimeout(3 * 1000);

        //防止屏蔽程序抓取而返回 403 错误
        conn.setRequestProperty("User-agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();

        //创建输入流资源对象
        InputStreamResource resource = new InputStreamResource(inputStream);

        //设置下载相应的头部信息
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + URLEncoder.encode(fileName + "UTF-8") + "");

        LOG.info(fileName + " download success!! \n" + "URL Is: \n" + url);

        //返回带有文件数据流的响应实体
        return ResponseEntity
                .ok()
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource)
                ;
    }

    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */

    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
}
