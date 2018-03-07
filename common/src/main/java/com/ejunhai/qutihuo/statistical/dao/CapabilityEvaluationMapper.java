package com.ejunhai.qutihuo.statistical.dao;

import com.ejunhai.qutihuo.statistical.model.CapabilityEvaluation;

import java.util.List;

public interface CapabilityEvaluationMapper {
    /**
     * 获取所有能力验证信息
     *
     * @return 能力验证列表
     */
    List<CapabilityEvaluation> getAll();
}
