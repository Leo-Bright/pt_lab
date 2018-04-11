package com.ejunhai.qutihuo.statistical.service;

import com.ejunhai.qutihuo.statistical.model.CapabilityValue;
import com.ejunhai.qutihuo.statistical.model.HomoCheckData;

import java.util.List;

public interface HomoCheckService {
    /**
     * 新增均匀性检验数据
     *
     */
    int insert(HomoCheckData homocheckdata);

    /**
    * 根据样品ID删除均匀性检验数据
    **/
    int deleteBySampleID (String sampleid);

    /**
     * 获取指定sampleid的均匀性检验数据
     */
    List<HomoCheckData> getHomoCheckDatabySampleID(String sampleid);

    /*
    * 保存所有的数据
    * */
    int saveHomoCheckData(String[][] matrix);
}
