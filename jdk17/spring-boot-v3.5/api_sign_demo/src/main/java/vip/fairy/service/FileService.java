package vip.fairy.service;

import org.springframework.stereotype.Service;
import vip.fairy.model.FileInfo;

@Service
public class FileService {

  public boolean existsByMd5(String md5) {
    System.out.println("查询文件:" + md5);
    return true;
  }

  public void save(FileInfo fileInfo) {
    System.out.println("保存文件:" + fileInfo.getMd5());
  }
}
