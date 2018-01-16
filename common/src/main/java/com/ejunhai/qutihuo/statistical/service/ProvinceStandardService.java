package com.ejunhai.qutihuo.statistical.service;

import com.ejunhai.qutihuo.statistical.dto.MeasurementDto;
import com.ejunhai.qutihuo.statistical.dto.StandardReviseDto;

/**
 * 
 * SystemUser Service 接口
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:22:36
 * 
 */
public interface ProvinceStandardService {

	/**
	 * 计算页面StandardRevise的事物对象
	 *
	 * @return StandardReviseDto
	 */
	public StandardReviseDto acquireStandardRevise();

}
