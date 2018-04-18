package com.ejunhai.qutihuo.statistical.service.impl;

import com.ejunhai.qutihuo.statistical.dao.CapabilityEvaluationDataMapper;
import com.ejunhai.qutihuo.statistical.dao.CapabilityEvaluationMapper;
import com.ejunhai.qutihuo.statistical.dao.CapabilityValuesMapper;
import com.ejunhai.qutihuo.statistical.dto.CheckUncertainty;
import com.ejunhai.qutihuo.statistical.model.CapabilityEvaluation;
import com.ejunhai.qutihuo.statistical.model.CapabilityEvaluationData;
import com.ejunhai.qutihuo.statistical.model.CapabilityValue;
import com.ejunhai.qutihuo.statistical.service.CapabilityEvaluationService;
import com.ejunhai.qutihuo.statistical.service.CapabilityValueService;
import com.ejunhai.qutihuo.statistical.utils.CapacityEvaluation;
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

    @Resource
    private CapabilityEvaluationService capabilityEvaluationService;

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
        List<List<String>> result = new ArrayList<>();
        if(method.equals("checkuncertainty")){
            th.add("样品编号");
            th.add("样品名称");
            th.add("被测量");
            th.add("X_pt");
            th.add("Std_pt");
            th.add("评定结果");
            for(CapabilityValue value:capabilityValues){
                List<String> oneObject = new ArrayList<>();
                String str = null;
                if(Double.valueOf(value.getxPt()) >= 0.3*Double.valueOf(value.getStdPt())) str="该指定值的不确定度不可忽略，请使用z'值、Zeta值或En值";
                else str="该指定值的不确定度可忽略";
                oneObject.add(value.getSampleNo());
                oneObject.add(value.getSampleName());
                oneObject.add(value.getVariable());
                oneObject.add(value.getxPt());
                oneObject.add(value.getStdPt());
                oneObject.add(str);
                result.add(oneObject);
            }
        }else if("zvalue".equals(method)){
            CapacityEvaluation capacityEvaluation = new CapacityEvaluation();
            String[][] capabilityEvaluationDataMatrix = capabilityEvaluationService.getCapabilityEvaluationData(id,1);
            String[] capabilityEvaluationDataArray = capabilityEvaluationDataMatrix[0];
            int len = capabilityEvaluationDataArray.length;
            double[] array = new double[len-5];
            for(int i=5;i<len;i++){
                array[i-5] = Double.valueOf(capabilityEvaluationDataArray[i]);
            }
            th.add("样品编号");
            th.add("样品名称");
            th.add("被测量");
            th.add("Z值");
            th.add("评定结果");
            for(CapabilityValue value:capabilityValues){
                List<String> oneObject = new ArrayList<>();
                Map<String,Object> zvalue = capacityEvaluation.zvalue(array,Double.valueOf(value.getxPt()),Double.valueOf(value.getStdPt()));
                oneObject.add(value.getSampleNo());
                oneObject.add(value.getSampleName());
                oneObject.add(value.getVariable());
                double[] tmpD = (double[])zvalue.get("Z");
                oneObject.add(String.valueOf(tmpD[0]));
                String[] tmp = (String[])zvalue.get("result");
                oneObject.add(tmp[0]);
                result.add(oneObject);
            }
        }
        resultMap.put("th",th);
        resultMap.put("result",result);
        return resultMap;
    }

}
