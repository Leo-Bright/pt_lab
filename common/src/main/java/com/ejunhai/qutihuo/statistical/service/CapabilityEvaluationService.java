package com.ejunhai.qutihuo.statistical.service;

import com.ejunhai.qutihuo.statistical.model.CapabilityEvaluation;

import java.util.List;

public interface CapabilityEvaluationService {
    /**
     * 新增CE记录
     *
     */
    int insert(CapabilityEvaluation capabilityEvaluation);

    /**
     * 获取所有能力验证信息
     */
    List<CapabilityEvaluation> getAll();
}
