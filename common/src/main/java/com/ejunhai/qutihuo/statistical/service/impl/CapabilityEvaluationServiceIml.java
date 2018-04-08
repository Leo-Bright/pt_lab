package com.ejunhai.qutihuo.statistical.service.impl;

import com.ejunhai.qutihuo.statistical.dao.CapabilityEvaluationMapper;
import com.ejunhai.qutihuo.statistical.dao.CapabilityEvaluationDataMapper;
import com.ejunhai.qutihuo.statistical.model.CapabilityEvaluation;
import com.ejunhai.qutihuo.statistical.model.CapabilityEvaluationData;
import com.ejunhai.qutihuo.statistical.service.CapabilityEvaluationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("capabilityEvaluationService")
public class CapabilityEvaluationServiceIml implements CapabilityEvaluationService {

    @Resource
    private CapabilityEvaluationMapper capabilityEvaluationMapper;

    @Resource
    private CapabilityEvaluationDataMapper capabilityEvaluationDataMapper;


    @Override
    public List<CapabilityEvaluation> getAll(){
        return capabilityEvaluationMapper.getAll();
    }

    @Override
    public CapabilityEvaluation getCapabilityEvaluationById(Integer id){
        return capabilityEvaluationMapper.getCapabilityEvaluationById(id);
    }

    @Override
    public String[][] getCapabilityEvaluationData(Integer ceId,Integer dataType){
        List<CapabilityEvaluationData> all = capabilityEvaluationDataMapper.get(ceId);
        List<CapabilityEvaluationData> needList = new ArrayList<>(16);
        for(CapabilityEvaluationData data:all){
            if(dataType.equals(data.getDataType())){
                needList.add(data);
            }
        }
        int size = needList.size();
        String[][]  matrix = new String[size][10];
        for(int i=0;i<size;i++){
            CapabilityEvaluationData ced = needList.get(i);
            matrix[i][0] = ced.getPtNo();
            matrix[i][1] = ced.getSampleName();
            matrix[i][2] = ced.getVariable();
            matrix[i][3] = ced.getUnit();
            matrix[i][4] = ced.getLabNo();
            matrix[i][5] = ced.getMessure1();
            matrix[i][6] = ced.getMessure2();
            matrix[i][7] = ced.getMessure3();
            matrix[i][8] = ced.getMessure4();
            matrix[i][9] = ced.getMessure5();
        }
        return matrix;
    }

    @Override
    public int insert(CapabilityEvaluation capabilityEvaluation){
        return capabilityEvaluationMapper.insert(capabilityEvaluation);
    }

    @Override
    public int saveAll(Integer id,Integer dataType, String[][] matrix){
        int len = matrix.length;
        for(int i=0;i<len;i++){
            String[] array = matrix[i];
            CapabilityEvaluationData capabilityEvaluationData = new CapabilityEvaluationData(id,dataType,array[0],array[1],array[2],array[3],array[4],array[5],array[6],array[7],array[8],array[9]);
            capabilityEvaluationDataMapper.save(capabilityEvaluationData);
        }
        return 0;
    }

}
