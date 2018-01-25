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

    Map<String,Object> checkStability(double[][] matrix1,double[][] matrix2,String method,double parameter);

    Map<String,Object> checkUniformity(double[][] matrix,String method,double stdORerror);

    Map<String,Object> confirm(double[][] matrix,String method,double xcrm,double ucrm);

    Map<String,Object> ensureStdVar(double[][] matrix,String method);

    Map<String,Object> computStdVar(String method,double c,double cfx,double zxx,double m);

    Map<String,Object> CapacityEvaluate(double[][] matrix,String method,double xpt,double delta,double sigma,double uncertain);
}
