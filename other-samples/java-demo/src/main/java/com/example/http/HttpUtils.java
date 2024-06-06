package com.example.http;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpUtils {

  public static void main(String[] args) {
    downloadFile("https://archive.apache.org/dist/zookeeper/zookeeper-3.4.14/zookeeper-3.4.14.tar.gz", "zookeeper-3.4.14.tar.gzxxxxxxxxxxx");
    //  downloadFile("https://archive.apache.org/dist/zookeeper/zookeeper-3.4.14/apache-zookeeper-3.4.14.tar.gz","zookeeper-3.4.14.tar.gzxxxxxxxxxxx");
  }

  public static void downloadFile(String downloadUrl, String path) {
    System.out.println(path);
    InputStream inputStream = null;
    OutputStream outputStream = null;
    try {
      URL url = new URL(downloadUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      inputStream = new BufferedInputStream(connection.getInputStream());
      File file = new File(path);
      if (!file.exists()) {
        outputStream = new FileOutputStream(file);
        byte[] buffer = new byte[1024 * 1024 * 2];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
          outputStream.write(buffer, 0, len);
        }
        connection.disconnect();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(outputStream);
      IOUtils.closeQuietly(inputStream);
    }
  }

  public static void downloadSmallFile(String remoteFilePath, String localFilePath) {
    ReadableByteChannel rbc = null;
    FileOutputStream fos = null;
    try {
      URL website = new URL(remoteFilePath);
      rbc = Channels.newChannel(website.openStream());
      fos = new FileOutputStream(localFilePath);//本地要存储的文件地址 例如：test.txt
      fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (fos != null) {
        try {
          fos.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (rbc != null) {
        try {
          rbc.close();
        } catch (IOException e) {
          e.printStackTrace();
        }

      }
    }
  }

  static void demo() throws IOException {
    // 1、创建 CloseableHttpClient 同步请求对象
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    // 2、HttpGet(final String uri)：创建 http get 请求对象
    HttpGet httpGet = new HttpGet("https://archive.apache.org/dist/hadoop/common/hadoop-3.3.6/hadoop-3.3.6-aarch64.tar.gz");
    // 3、设置超时时间、请求时间、socket 时间都为 15 秒，允许重定向
    RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(15000).setConnectionRequestTimeout(15000).setSocketTimeout(15000)
        .setRedirectsEnabled(true).build();
    httpGet.setConfig(requestConfig);
    // 4、CloseableHttpResponse execute(final HttpUriRequest request)：执行请求
    // 如果连接不上服务器，则抛出:java.net.ConnectException: Connection refused: connect
    CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
    // 5、获取响应结果, 状态码 200 表示请求成功
    int statusCode = httpResponse.getStatusLine().getStatusCode();
    System.out.println("响应状态码：" + statusCode);
    if (statusCode == 200) {
      HttpEntity httpEntity = httpResponse.getEntity();
      InputStream inputStream = httpEntity.getContent();
      //使用bufferedInputStream 缓存流的方式来获取下载文件，不然大文件会出现内存溢出的情况
      File file = new File("hadoop-3.3.6-aarch64.tar.gz");
      if (!file.exists()) {
        OutputStream outputStream = new FileOutputStream(file);
        //这里也很关键每次读取的大小为5M 不一次性读取完
        byte[] buffer = new byte[1024 * 1024 * 5];// 2MB
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
          System.out.println("....." + len);
          outputStream.write(buffer, 0, len);
        }
      }
    }
  }
}
