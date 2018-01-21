package com.ejunhai.qutihuo.statistical.service.impl;

import com.ejunhai.qutihuo.statistical.service.StatisticsService;
import com.ejunhai.qutihuo.statistical.utils.Algorithm;
import com.ejunhai.qutihuo.statistical.utils.Arith;
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
    public Map<String,Object> calMethod(double[] array,String method){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("message","计算发生错误，请查看后台报错！");
        if("algorithmA".equals(method)){
            resultMap.put("result",algorithm.methodA(array));
        }else if("algorithmS".equals(method)){
//            resultMap.put("result",algorithm.methodS(array));
        }else if("QN".equals(method)){
            resultMap.put("result",algorithm.methodA(array));
        }else{
            resultMap.put("result",algorithm.methodA(array));
        }

        resultMap.put("status",200);
        return resultMap;
    }


}
