package com.ejunhai.qutihuo.statistical.service;

import com.ejunhai.qutihuo.statistical.model.CapabilityEvaluation;
import com.ejunhai.qutihuo.statistical.model.CapabilityValue;

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

    /**
     * 获取所有能力验证信息
     */
    int saveAll(Integer id,Integer dataType, String[][] matrix);

    /**
     * 获取指定CEid下的能力验证记录
     */
    CapabilityEvaluation getCapabilityEvaluationById(Integer id);

    /**
     * 获取指定CEid/dataType下的能力验证实验数据
     */
    String[][] getCapabilityEvaluationData(Integer ceId,Integer dataType);
}
