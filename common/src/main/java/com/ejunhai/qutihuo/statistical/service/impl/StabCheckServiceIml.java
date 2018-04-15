package com.ejunhai.qutihuo.statistical.service.impl;

import com.ejunhai.qutihuo.statistical.model.StabCheckData;
import com.ejunhai.qutihuo.statistical.service.StabCheckService;
import com.ejunhai.qutihuo.statistical.dao.StabCheckMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;
@Service("stabCheckService")
public class StabCheckServiceIml implements StabCheckService {
    @Resource
    private StabCheckMapper  stabCheckMapper;

    @Override
    public List<StabCheckData> getStabCheckDatabySampleID(String sampleID){
        return stabCheckMapper.getStabCheckDatabySampleID(sampleID);
    }

    @Override
    public int deleteBySampleID(String sampleID){
        return stabCheckMapper.deleteBySampleID(sampleID);
    }

    @Override
    public int insert(StabCheckData data) {
        return stabCheckMapper.addStabCheckData(data);
    }

    @Override
    public int saveStabCheckData(String[][] matrix){
        int len = matrix.length;
        for(int i = 0; i<len;i++){
            String[] array = matrix[i];
            if(array[0] == "") break; //是空值是结束
            StabCheckData stabCheckData = new StabCheckData(array[1],array[0], array[3],Date.valueOf(array[2]),array[4],Double.valueOf(array[5]),Double.valueOf(array[6]),0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00);
            stabCheckMapper.addStabCheckData(stabCheckData);
        }
        return 0;
    }
}
