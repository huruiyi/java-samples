package vip.fairy.model;

import lombok.Data;

@Data
public class FileInfo {

  private String fileName;
  private String md5;


  public FileInfo(String fileName, String md5) {
    this.fileName = fileName;
    this.md5 = md5;
  }
}
