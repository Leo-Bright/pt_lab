package com.ejunhai.qutihuo.statistical.service;

import com.ejunhai.qutihuo.statistical.model.CapabilityEvaluation;
import com.ejunhai.qutihuo.statistical.model.CapabilityValue;

import java.util.List;
import java.util.Map;

public interface CapabilityValueService {
    /**
     * 新增CE记录
     *
     */
    int insert(CapabilityValue capabilityValue);

    /**
     * 根据id删除CE记录
     *
     */
    int deleteById(Integer id);

    /**
     * 获取指定CEid下的能力验证记录
     */
    List<com.ejunhai.qutihuo.statistical.model.CapabilityValue> getCapabilityValuesById(Integer ceId);

    Map<String,Object> capacityEvaluate(String method, Integer id);
}
