package com.ejunhai.qutihuo.statistical.service.impl;

import com.ejunhai.qutihuo.statistical.service.StatisticsService;
import com.ejunhai.qutihuo.statistical.utils.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService{

    @Resource
    Algorithm algorithm;

    @Override
    public Map<String,Object> calStatistics(double[] array){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("message","计算发生错误，请查看后台报错！");
        resultMap.put("average",algorithm.calAverage(array));
        resultMap.put("median",algorithm.calMedian(array));
        resultMap.put("variance",algorithm.calVariance(array));
        resultMap.put("standardVar",algorithm.calStandardVar(array));
        resultMap.put("quantile3",algorithm.calQuantile3(array));
        resultMap.put("quantile1",algorithm.calQuantile1(array));
        resultMap.put("iqr",algorithm.calIQR(array));
        resultMap.put("niqr",algorithm.calNIQR(array));
        resultMap.put("made",algorithm.calMADe(array));
        resultMap.put("status",200);
        return resultMap;
    }

    @Override
    public Map<String,Object> calMethod(double[][] matrix,String method){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("message","计算发生错误，请查看后台报错！");
        double[] array = matrix[0];
        if(11<array.length){
            resultMap.put("status",201);
            resultMap.put("message","实验室测量次数应不大于11次。");
            return resultMap;
        }
        if("algorithmA".equals(method)){
            resultMap.put("result",algorithm.methodA(array));
        }else if("algorithmS".equals(method)){
            resultMap.put("result",algorithm.methodS(matrix));
        }else if("QN".equals(method)){
            resultMap.put("result",algorithm.methodQn(array));
        }else{
            if(2>matrix.length){
                resultMap.put("status",201);
                resultMap.put("message","实验室个数应不小于2个。");
                return resultMap;
            }
            resultMap.put("result",algorithm.hampel(matrix));
        }
        resultMap.put("status",200);
        return resultMap;
    }

    @Override
    public Map<String,Object> checkStability(double[][] matrix1,double[][] matrix2,String method,double parameter){
        Map<String,Object> resultMap = new HashMap<>();
        HomogeneityandStabilityCheck stabilityCheck = new HomogeneityandStabilityCheck();
        resultMap.put("message","计算发生错误，请查看后台报错！");
        if("lessthan".equals(method)){
            resultMap.put("result",stabilityCheck.absolutlyLessLaw(matrix1,matrix2));
        }else if("t_check_ref".equals(method)){
            resultMap.put("result",stabilityCheck.tCheckMethod(matrix1[0],parameter,0.95));
        }else{
            resultMap.put("result",stabilityCheck.tCheckComparison(matrix2[0],matrix2[1]));
        }
        resultMap.put("status",200);
        return resultMap;
    }

    @Override
    public Map<String,Object> checkUniformity(double[][] matrix,String method,double stdORerror, double maxErr){
        Map<String,Object> resultMap = new HashMap<>();
        HomogeneityandStabilityCheck stabilityCheck = new HomogeneityandStabilityCheck();
        resultMap.put("message","计算发生错误，请查看后台报错！");
        if("danyinzi".equals(method)){
            resultMap.put("result",stabilityCheck.singleFactorVariableAnalysis(matrix));
        }else if("sszz".equals(method)){
            resultMap.put("result",stabilityCheck.ssLessLaw(matrix));
        }else{
            resultMap.put("result",stabilityCheck.extensionMethod(matrix,stdORerror));
        }
        resultMap.put("status",200);
        return resultMap;
    }

    @Override
    public Map<String,Object> confirm(double[][] matrix,String method,double xcrm,double ucrm){
        Map<String,Object> resultMap = new HashMap<>();
        Algorithm algorithm = new Algorithm();
        resultMap.put("message","计算发生错误，请查看后台报错！");
        if("refvalue".equals(method)){
            resultMap.put("result",algorithm.confirm(matrix[0],matrix[1],xcrm,ucrm));
        }else{
            resultMap.put("result",algorithm.confirm(matrix,method));
        }
        resultMap.put("status",200);
        return resultMap;
    }

    @Override
    public Map<String,Object> ensureStdVar(double[][] matrix,String method){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("message","计算发生错误，请查看后台报错！");
        double[] array = matrix[0];
        if("algorithmA".equals(method)){
            resultMap.put("result",algorithm.methodA(array));
        }else if("Qn".equals(method)){
            resultMap.put("result",algorithm.methodQn(array));
        }else{
            resultMap.put("result",algorithm.hampel(matrix));
        }
        resultMap.put("status",200);
        return resultMap;
    }

    @Override
    public Map<String,Object> computStdVar(String method,double c,double cfx,double zxx,double m){
        Map<String,Object> resultMap = new HashMap<>();
        ConfirmStdDeviation confirmStdDeviation = new ConfirmStdDeviation();
        resultMap.put("message","计算发生错误，请查看后台报错！");
        if("bymodel".equals(method)){
            resultMap.put("result",confirmStdDeviation.confirmedByGeneralModel(c));
        }else{
            resultMap.put("result",confirmStdDeviation.ConfirmedByMeasureMethod(cfx,zxx,m));
        }
        resultMap.put("status",200);
        return resultMap;
    }

    @Override
    public Map<String,Object> CapacityEvaluate(double[][] matrix,String method,double xpt,double delta,double sigma,double uncertain){
        Map<String,Object> resultMap = new HashMap<>();
        CapacityEvaluation capacityEvaluation = new CapacityEvaluation();
        resultMap.put("message","计算发生错误，请查看后台报错！");
        if("bais".equals(method)){
            resultMap.putAll(capacityEvaluation.bias(matrix[0],xpt,delta));
        }else if("zvalue".equals(method)){
            resultMap.putAll(capacityEvaluation.zvalue(matrix[0],xpt,sigma));
        }else if("z_value".equals(method)){
            resultMap.putAll(capacityEvaluation.z_value(matrix[0],xpt,sigma,uncertain));
        }else if("zetavalue".equals(method)){
            resultMap.putAll(capacityEvaluation.zetavalue(matrix[0],xpt,uncertain));
        }else {
            resultMap.putAll(capacityEvaluation.envalue(matrix[0],xpt,uncertain));
        }
        resultMap.put("status",200);
        return resultMap;
    }


}
