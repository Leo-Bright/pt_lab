package com.ejunhai.qutihuo.statistical.service.impl;

import com.ejunhai.qutihuo.statistical.dao.CapabilityEvaluationMapper;
import com.ejunhai.qutihuo.statistical.dao.CapabilityValuesMapper;
import com.ejunhai.qutihuo.statistical.model.CapabilityEvaluation;
import com.ejunhai.qutihuo.statistical.model.CapabilityValue;
import com.ejunhai.qutihuo.statistical.service.CapabilityEvaluationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("capabilityEvaluationService")
public class CapabilityEvaluationServiceIml implements CapabilityEvaluationService {

    @Resource
    private CapabilityEvaluationMapper capabilityEvaluationMapper;


    @Override
    public List<CapabilityEvaluation> getAll(){
        return capabilityEvaluationMapper.getAll();
    }

    @Override
    public CapabilityEvaluation getCapabilityEvaluationById(Integer id){
        return capabilityEvaluationMapper.getCapabilityEvaluationById(id);
    }

    @Override
    public int insert(CapabilityEvaluation capabilityEvaluation){
        return capabilityEvaluationMapper.insert(capabilityEvaluation);
    }

}
