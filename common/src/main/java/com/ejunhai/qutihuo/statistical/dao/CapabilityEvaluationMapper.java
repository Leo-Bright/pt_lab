package com.ejunhai.qutihuo.statistical.dao;

import com.ejunhai.qutihuo.order.model.OrderLog;
import com.ejunhai.qutihuo.statistical.model.CapabilityEvaluation;
import com.ejunhai.qutihuo.statistical.model.CapabilityValue;

import java.util.List;

public interface CapabilityEvaluationMapper {

    /**
     * 新增CE记录
     *
     * @param capabilityEvaluation
     * @return
     */
    int insert(CapabilityEvaluation capabilityEvaluation);
    /**
     * 获取所有能力验证信息
     *
     * @return 能力验证列表
     */
    List<CapabilityEvaluation> getAll();

    /**
     * 根据id获取能力验证信息
     *
     * @return 能力验证记录
     */
    CapabilityEvaluation getCapabilityEvaluationById(Integer id);

}
