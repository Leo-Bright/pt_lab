package com.ejunhai.qutihuo.statistical.service;


import java.util.Map;

public interface StatisticsService {
    /**
     * 计量平均值
     *
     * @return array的平均值
     */
    Map<String,Object> calStatistics(double[] array);

    Map<String,Object> calMethod(double[][] matrix,String method);

    Map<String,Object> checkStability(double[][] matrix1,double[][] matrix2,double stddeviance,String method);

    Map<String,Object> checkUniformity(double[][] matrix,String method,double stdORerror);
}
