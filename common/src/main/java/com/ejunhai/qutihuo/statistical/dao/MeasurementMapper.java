package com.ejunhai.qutihuo.statistical.dao;

import com.ejunhai.qutihuo.statistical.model.Measurement;
import com.ejunhai.qutihuo.statistical.model.ProvinceStandard;

import java.util.List;

public interface MeasurementMapper {
    /**
     * 获取各个省份的ProvinceStandard
     *
     * @return ProvinceStandard对象数组
     */
    List<Measurement> readProvinceMeasurements();

    /**
     * 获取所有包含的年份
     *
     * @return 年份数组
     */
    List<Integer> getDistinctYear();
}
