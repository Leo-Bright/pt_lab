package com.ejunhai.qutihuo.statistical.dao;

import com.ejunhai.qutihuo.statistical.model.ProvinceStandard;
import java.util.List;

/**
 * 地方标准信息表
 *
 * @author Leo
 *
 * @date 2017-12-06 21:18:30
 *
 */

public interface ProvinceStandardMapper {

    /**
     * 获取全部ProvinceStandard
     *
     * @return ProvinceStandard对象数组
     */
    List<ProvinceStandard> read();

    /**
     * 获取全国统计的ProvinceStandard
     *
     * @return ProvinceStandard对象数组
     */
    List<ProvinceStandard> readNationalStatistics();

    /**
     * 获取各个省份的ProvinceStandard
     *
     * @return ProvinceStandard对象数组
     */
    List<ProvinceStandard> readProvinceStatistics();

    /**
     * 获取某一年的ProvinceStandard
     *
     * @return ProvinceStandard对象数组
     */
    List<ProvinceStandard> readByYear(Integer year);

    /**
     * 获取所有包含的年份
     *
     * @return 年份数组
     */
    List<Integer> getDistinctYear();

}
