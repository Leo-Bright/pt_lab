package com.ejunhai.qutihuo.statistical.dao;

import com.ejunhai.qutihuo.statistical.model.CapabilityEvaluation;
import com.ejunhai.qutihuo.statistical.model.CapabilityEvaluationData;

import java.util.List;

public interface CapabilityEvaluationDataMapper {

    /**
     * 保存一行能力验证数据
     *
     * @return 能力验证列表
     */
    int save(CapabilityEvaluationData capabilityEvaluationData);

    /**
     * 删除能力验证实验数据
     *
     * @return 能力验证列表
     */
    int delete(Integer ceId);

    /**
     * 获取相应ceid的能力验证实验数据
     *
     * @return 能力验证列表
     */
    List<CapabilityEvaluationData> get(Integer ceId);

}
