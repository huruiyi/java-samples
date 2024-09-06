package com.sinopharm.app.service.impl;

import com.sinopharm.app.entity.Renter;
import com.sinopharm.app.mapper.RenterMapper;
import com.sinopharm.app.service.RenterService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RenterServiceImpl implements RenterService {

  @Resource
  private RenterMapper renterMapper;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public boolean save(Renter renter) {
    int rs = renterMapper.insert(renter);
    return rs > 0;
  }
}
