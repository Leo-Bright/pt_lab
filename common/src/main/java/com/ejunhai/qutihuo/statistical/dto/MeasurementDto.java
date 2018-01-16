package com.ejunhai.qutihuo.statistical.dto;

import java.util.HashMap;
import java.util.List;

public class MeasurementDto {

    private List<String> timeLineData;

    private  List<List<MeasureObj>>  measurementStandard;

    private  List<List<MeasureObj>>  measurementAuthorized ;

    private  List<List<MeasureObj>>  newInstrument ;

    private  List<List<MeasureObj>>  maintainInstrument ;

    public List<String> getTimeLineData() {
        return timeLineData;
    }

    public void setTimeLineData(List<String> timeLineData) {
        this.timeLineData = timeLineData;
    }

    public List<List<MeasureObj>> getMeasurementStandard() {
        return measurementStandard;
    }

    public void setMeasurementStandard(List<List<MeasureObj>> measurementStandard) {
        this.measurementStandard = measurementStandard;
    }

    public List<List<MeasureObj>> getMeasurementAuthorized() {
        return measurementAuthorized;
    }

    public void setMeasurementAuthorized(List<List<MeasureObj>> measurementAuthorized) {
        this.measurementAuthorized = measurementAuthorized;
    }

    public List<List<MeasureObj>> getNewInstrument() {
        return newInstrument;
    }

    public void setNewInstrument(List<List<MeasureObj>> newInstrument) {
        this.newInstrument = newInstrument;
    }

    public List<List<MeasureObj>> getMaintainInstrument() {
        return maintainInstrument;
    }

    public void setMaintainInstrument(List<List<MeasureObj>> maintainInstrument) {
        this.maintainInstrument = maintainInstrument;
    }

    public class MeasureObj {
        private String name;
        private List<Integer> value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Integer> getValue() {
            return value;
        }

        public void setValue(List<Integer> value) {
            this.value = value;
        }
    }
}
