package com.ejunhai.qutihuo.statistical.service;

import com.ejunhai.qutihuo.statistical.model.CapabilityEvaluation;
import com.ejunhai.qutihuo.statistical.model.CapabilityValue;

import java.util.List;

public interface CapabilityValueService {
    /**
     * 新增CE记录
     *
     */
    int insert(CapabilityValue capabilityValue);

    /**
     * 获取指定CEid下的能力验证记录
     */
    List<com.ejunhai.qutihuo.statistical.model.CapabilityValue> getCapabilityValuesById(Integer ceId);
}
