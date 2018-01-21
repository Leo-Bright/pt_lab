package com.ejunhai.qutihuo.statistical.service;


import java.util.Map;

public interface StatisticsService {
    /**
     * 计量平均值
     *
     * @return array的平均值
     */
    Map<String,Object> calStatistics(double[] array);
}
