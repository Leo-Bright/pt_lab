package com.ejunhai.qutihuo.statistical.service.impl;

import com.ejunhai.qutihuo.statistical.dao.MeasurementMapper;
import com.ejunhai.qutihuo.statistical.dto.MeasurementDto;
import com.ejunhai.qutihuo.statistical.model.Measurement;
import com.ejunhai.qutihuo.statistical.service.MeasurementService;
import com.ejunhai.qutihuo.statistical.service.StatisticsService;
import com.ejunhai.qutihuo.statistical.utils.Arith;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService{

    @Override
    public double calAverage(double[] array){
        return Arith.div(sum(array),array.length);
    }

    private double sum(double[] array){
        double result = 0.0;
        for(double num:array){
            result = Arith.add(result,num);
        }
        return result;
    }
}
