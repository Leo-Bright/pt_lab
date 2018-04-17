package com.ejunhai.qutihuo.statistical.service;

import com.ejunhai.qutihuo.statistical.model.StabCheckData;

import java.util.List;

public interface StabCheckService {
    /**
     * 新增稳定性检验数据
     *
     */
    int insert(StabCheckData stabcheckdata);

    /**
     * 根据样品ID删除稳定性检验数据
     **/
    int deleteBySampleID (String sampleid);

    /**
     * 获取指定sampleid的稳定性检验数据
     */
    List<StabCheckData> getStabCheckDatabySampleID(String sampleid);

    /*
    * 保存所有的数据
    * */
    int saveStabCheckData(String[][] matrix);
}
