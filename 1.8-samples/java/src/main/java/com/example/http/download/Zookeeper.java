package com.example.http.download;

import com.example.http.HttpUtils;
import com.example.xml.ch.xpath.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Zookeeper {

  public static void main(String[] args) throws IOException {
    List<String> versions = getUrl();
    for (String version : versions) {
      List<String> urls = new ArrayList<>();
      urls.add(MessageFormat.format("https://archive.apache.org/dist/zookeeper/{0}/apache-{0}-bin.tar.gz", version));
      urls.add(MessageFormat.format("https://archive.apache.org/dist/zookeeper/{0}/apache-{0}.tar.gz", version));
      urls.add(MessageFormat.format("https://archive.apache.org/dist/zookeeper/{0}/{0}.tar.gz", version));

      urls.forEach(url -> {
        String fileDirPath = System.getProperties().get("user.dir") + "\\" + "zookeeper" + "\\" + version + "\\";
        File file = new File(fileDirPath);
        try {
          Thread.sleep(1000);
          if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
          }
          new Thread(() -> HttpUtils.downloadFile(url, fileDirPath + getFileName(url, version))).start();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }

      });
    }

  }

  static String getFileName(String url, String version) {
    String extrasStr = MessageFormat.format("https://archive.apache.org/dist/zookeeper/{0}/", version);
    return url.replace(extrasStr, "");
  }


  static List<String> getUrl() throws IOException {
    List<String> versions = new ArrayList<>();
    String path = Test.class.getClassLoader().getResource("zookeeper/version.txt").getPath();
    File file = new File(path);

    BufferedReader br = new BufferedReader(new FileReader(file));

    String version;
    while ((version = br.readLine()) != null) {
      versions.add(version);
    }
    br.close();

    return versions;
  }
}
