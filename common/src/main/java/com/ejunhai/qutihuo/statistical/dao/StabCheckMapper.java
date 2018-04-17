package com.ejunhai.qutihuo.statistical.dao;
import com.ejunhai.qutihuo.statistical.model.HomoCheckData;
import com.ejunhai.qutihuo.statistical.model.StabCheckData;

import java.util.List;

public interface StabCheckMapper {
    /**
     * 新增稳定性检验数据
     *
     */
    int addStabCheckData(StabCheckData stabcheckdata);

    /**
     * 根据id删除稳定性数据
     *
     */
    int deleteById(String id);

    /**
     * 根据样品ID删除稳定性检验数据
     **/
    int deleteBySampleID (String sampleid);

    /**
     * 获取指定sampleid的稳定性检验数据
     */
    List<StabCheckData> getStabCheckDatabySampleID(String sampleid);
}
