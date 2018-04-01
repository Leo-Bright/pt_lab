package com.ejunhai.qutihuo.statistical.dao;

import com.ejunhai.qutihuo.statistical.model.CapabilityEvaluation;
import com.ejunhai.qutihuo.statistical.model.CapabilityValue;

import java.util.List;

public interface CapabilityValuesMapper {

    /**
     * 新增CE记录
     *
     * @param capabilityValue
     * @return
     */
    int insert(CapabilityValue capabilityValue);

    /**
     * 新增CE记录
     *
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 获取指定CEid下的能力验证记录
     *
     * @return 能力验证记录列表
     */
    List<CapabilityValue> getCapabilityValuesById(Integer ceId);
}
