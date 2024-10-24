package vip.fairy.services;

import javax.annotation.Resource;

public class AppStatisticsImpl implements AppStatistics {

    @Resource
    private SingerService singerService;

    @Override
    public int getTotalSingerCount() {
        return singerService.findAll().size();
    }
}
