package com.ejunhai.qutihuo.statistical.dao;
import com.ejunhai.qutihuo.statistical.model.HomoCheckData;

import java.util.List;

public interface HomoCheckMapper {
    /**
     * 新增均匀性检验数据
     *
     */
    int addHomoCheckData(HomoCheckData homocheckdata);

    /**
     * 根据id删除均匀性数据
     *
     */
    int deleteById(String id);

    /**
     * 根据样品ID删除均匀性检验数据
     **/
    int deleteBySampleID (String sampleid);

    /**
     * 获取指定sampleid的均匀性检验数据
     */
    List<HomoCheckData> getHomoCheckDatabySampleID(String sampleid);
}
