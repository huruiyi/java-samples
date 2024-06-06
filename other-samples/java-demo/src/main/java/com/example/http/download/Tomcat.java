package com.example.http.download;

import com.example.http.HttpUtils;
import com.example.xml.ch.xpath.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Tomcat {

  public static void main(String[] args) throws IOException {
    List<String> versions = getUrl();
    for (String version : versions) {
      version = version.replace("v", "");
      List<String> urls = getStrings(version);

      String finalVersion = version;
      urls.forEach(url -> {
        String fileDirPath = System.getProperties().get("user.dir") + "\\" + "tomcat10" + "\\" + finalVersion + "\\";
        File file = new File(fileDirPath);
        try {
          Thread.sleep(1000);
          if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
          }
          new Thread(() -> HttpUtils.downloadFile(url, fileDirPath + getFileName(url, finalVersion))).start();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }

      });
    }
  }

  private static List<String> getStrings(String version) {
    List<String> urls = new ArrayList<>();
    urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-10/v{0}/src/apache-tomcat-{0}-src.tar.gz", version));
    urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-10/v{0}/src/apache-tomcat-{0}-src.zip", version));
    urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-10/v{0}/bin/apache-tomcat-{0}-deployer.tar.gz", version));
    urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-10/v{0}/bin/apache-tomcat-{0}-deployer.zip", version));
    urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-10/v{0}/bin/apache-tomcat-{0}-fulldocs.tar.gz", version));
    urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-10/v{0}/bin/apache-tomcat-{0}-windows-x64.zip", version));
    urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-10/v{0}/bin/apache-tomcat-{0}-windows-x86.zip", version));
    urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-10/v{0}/bin/apache-tomcat-{0}.exe", version));
    urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-10/v{0}/bin/apache-tomcat-{0}.tar.gz", version));
    urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-10/v{0}/bin/apache-tomcat-{0}.zip", version));
    urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-10/v{0}/bin/embed/apache-tomcat-{0}-embed.tar.gz", version));
    urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-10/v{0}/bin/embed/apache-tomcat-{0}-embed.zip", version));
    //urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/bin/extras/catalina-jmx-remote.jar", version));
    //urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/bin/extras/catalina-ws.jar", version));
    //urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/bin/extras/tomcat-juli-adapters.jar", version));
    //urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/bin/extras/tomcat-juli.jar", version));
    return urls;
  }

  static String getFileName(String url, String version) {
    String extrasStr = MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-10/v{0}/bin/extras/", version);
    String embedStr = MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-10/v{0}/bin/embed/", version);
    String binStr = MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-10/v{0}/bin/", version);
    String srcStr = MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-10/v{0}/src/", version);
    if (url.contains(extrasStr)) {
      return url.replace(extrasStr, "");
    } else if (url.contains(embedStr)) {
      return url.replace(embedStr, "");
    } else if (url.contains(binStr)) {
      return url.replace(binStr, "");
    } else if (url.contains(srcStr)) {
      return url.replace(srcStr, "");
    }
    return "";
  }


  static List<String> getUrl() throws IOException {
    List<String> versions = new ArrayList<>();
    String path = Test.class.getClassLoader().getResource("tomcat/v10.txt").getPath();
    File file = new File(path);

    BufferedReader br = new BufferedReader(new FileReader(file));

    String version;
    while ((version = br.readLine()) != null) {
      versions.add(version);
    }
    br.close();

    return versions;
  }

  private static void readFile1(File fin) throws IOException {
    FileInputStream fis = new FileInputStream(fin);

    //Construct BufferedReader from InputStreamReader
    BufferedReader br = new BufferedReader(new InputStreamReader(fis));

    String line = null;
    while ((line = br.readLine()) != null) {
      System.out.println(line);
    }
    br.close();
    fis.close();
  }
}
