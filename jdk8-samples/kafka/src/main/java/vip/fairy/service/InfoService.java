package vip.fairy.service;

import vip.fairy.pojo.Info;
import java.util.List;

public interface InfoService {

  List<Info> findAll();

  int insert(Info info);

  int jdbcInsert(Info info);

}
