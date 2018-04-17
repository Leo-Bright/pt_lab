package com.ejunhai.qutihuo.statistical.service.impl;

import com.ejunhai.qutihuo.statistical.dao.CapabilityEvaluationMapper;
import com.ejunhai.qutihuo.statistical.dao.CapabilityValuesMapper;
import com.ejunhai.qutihuo.statistical.dto.CheckUncertainty;
import com.ejunhai.qutihuo.statistical.model.CapabilityEvaluation;
import com.ejunhai.qutihuo.statistical.model.CapabilityValue;
import com.ejunhai.qutihuo.statistical.service.CapabilityEvaluationService;
import com.ejunhai.qutihuo.statistical.service.CapabilityValueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String,Object> capacityEvaluate(String method,Integer id){
        Map<String,Object> resultMap = new HashMap<>();
        List<String> th = new ArrayList<>();
        List<CapabilityValue> capabilityValues = capabilityValuesMapper.getCapabilityValuesById(id);
        if(method.equals("checkuncertainty")){
            th.add("样品编号");
            th.add("样品名称");
            th.add("被测量");
            th.add("X_pt");
            th.add("Std_pt");
            th.add("评定结果");
            List<CheckUncertainty> result = new ArrayList<>();
            for(CapabilityValue value:capabilityValues){
                String str = null;
                if(Integer.valueOf(value.getxPt()) >= 0.3*Integer.valueOf(value.getStdPt())) str="该指定值的不确定度不可忽略，请使用z'值、Zeta值或En值";
                else str="该指定值的不确定度可忽略";
                result.add(new CheckUncertainty());//todo
            }
        }

        return resultMap;
    }

}
