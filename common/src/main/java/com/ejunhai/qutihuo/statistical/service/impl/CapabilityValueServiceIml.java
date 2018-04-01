package com.ejunhai.qutihuo.statistical.service.impl;

import com.ejunhai.qutihuo.statistical.dao.CapabilityEvaluationMapper;
import com.ejunhai.qutihuo.statistical.dao.CapabilityValuesMapper;
import com.ejunhai.qutihuo.statistical.model.CapabilityEvaluation;
import com.ejunhai.qutihuo.statistical.model.CapabilityValue;
import com.ejunhai.qutihuo.statistical.service.CapabilityEvaluationService;
import com.ejunhai.qutihuo.statistical.service.CapabilityValueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("capabilityValueService")
public class CapabilityValueServiceIml implements CapabilityValueService {

    @Resource
    private CapabilityValuesMapper capabilityValuesMapper;

    @Override
    public List<CapabilityValue> getCapabilityValuesById(Integer ceId){
        return capabilityValuesMapper.getCapabilityValuesById(ceId);
    }

    @Override
    public int insert(CapabilityValue capabilityValue){
        return capabilityValuesMapper.insert(capabilityValue);
    }

    @Override
    public int deleteById(Integer id){
        return capabilityValuesMapper.deleteById(id);
    }

}
