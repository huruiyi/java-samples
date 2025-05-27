package com.sinopharm.app;

import com.sinopharm.app.entity.Renter;
import com.sinopharm.app.mapper.RenterMapper;
import com.sinopharm.app.service.RenterService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest(classes = AppStart.class)
public class SysTest {

  @Resource
  private RenterService renterService;

  @Resource
  private RenterMapper renterMapper;

  @Test
  public void testQuery() {
    List<Renter> renterList = renterMapper.selectList(null);
    log.info(JSONArray.toJSONString(renterList));
  }

  @Test
  public void testInsert() {
    Renter renter = new Renter();
    renter.setRenterName("测试");
    renterService.save(renter);
  }
}
