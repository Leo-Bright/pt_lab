package com.ejunhai.qutihuo.statistical.service;

import com.ejunhai.qutihuo.statistical.dto.MeasurementDto;

public interface MeasurementService {
    /**
     * 计量标准数据
     *
     * @return MeasurementDto
     */
    MeasurementDto acquireMeasurement();
}
