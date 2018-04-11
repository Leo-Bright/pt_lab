package com.ejunhai.qutihuo.statistical.service.impl;

import com.ejunhai.qutihuo.statistical.model.HomoCheckData;
import com.ejunhai.qutihuo.statistical.service.HomoCheckService;
import com.ejunhai.qutihuo.statistical.dao.HomoCheckMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;
@Service("homoCheckService")
public class HomoCheckServiceIml implements HomoCheckService {

    @Resource
    private HomoCheckMapper  homoCheckMapper;

    @Override
    public List<HomoCheckData> getHomoCheckDatabySampleID(String sampleID){
        return homoCheckMapper.getHomoCheckDatabySampleID(sampleID);
    }

    @Override
    public int deleteBySampleID(String sampleID){
        return homoCheckMapper.deleteBySampleID(sampleID);
    }

    @Override
    public int insert(HomoCheckData data) {
        return homoCheckMapper.addHomoCheckData(data);
    }

    @Override
    public int saveHomoCheckData(String[][] matrix){
        int len = matrix.length;
        for(int i = 0; i<len;i++){
            String[] array = matrix[i];
            if(array[0] == "") break; //是空值是结束
            HomoCheckData homoCheckData = new HomoCheckData(array[0],array[1], array[3],Date.valueOf(array[2]),array[4],Double.valueOf(array[5]),Double.valueOf(array[6]),0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00);
            homoCheckMapper.addHomoCheckData(homoCheckData);
        }
        return 0;
    }
}
