package com.ejunhai.qutihuo.statistical.service.impl;

import com.ejunhai.qutihuo.statistical.service.StatisticsService;
import com.ejunhai.qutihuo.statistical.utils.Algorithm;
import com.ejunhai.qutihuo.statistical.utils.Arith;
import com.ejunhai.qutihuo.statistical.utils.HomogeneityandStabilityCheck;
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
    public Map<String,Object> checkStability(double[][] matrix1,double[][] matrix2,double stddeviance,String method){
        Map<String,Object> resultMap = new HashMap<>();
        HomogeneityandStabilityCheck stabilityCheck = new HomogeneityandStabilityCheck();
        resultMap.put("message","计算发生错误，请查看后台报错！");
        double[] array = matrix1[0];
        if(11<array.length){
            resultMap.put("status",201);
            resultMap.put("message","实验室测量次数应不大于11次。");
            return resultMap;
        }
        if("danyinzi1".equals(method)){
            resultMap.put("result",stabilityCheck.singleFactorVariableAnalysis(matrix1));
        }else if("sszz".equals(method)){
            resultMap.put("result",stabilityCheck.ssLessLaw(matrix1));
        }else if("extension".equals(method)){
//            resultMap.put("result",stabilityCheck.extensionMethod(matrix1));
        }else if("danyinzi2".equals(method)){
//            resultMap.put("result",stabilityCheck.absolutlyLessLaw(matrix1));
        }else if("t_check_ref".equals(method)){

        }else{

        }
        resultMap.put("status",200);
        return resultMap;
    }

    @Override
    public Map<String,Object> checkUniformity(double[][] matrix,String method,double stdORerror){
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


}
