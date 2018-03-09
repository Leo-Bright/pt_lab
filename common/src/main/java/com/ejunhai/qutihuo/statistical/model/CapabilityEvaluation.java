package com.ejunhai.qutihuo.statistical.model;
/**
 * 能力验证表
 *
 * @author Leo
 * @date 2018-3-7 19:18:30
 */
public class CapabilityEvaluation {
    /**
     * ID
     */
    private Integer id;

    /**
     * sample_no
     */
    private String sampleNo;

    /**
     * sample_name
     */
    private String sampleName;

    /**
     * pt_no
     */
    private String ptNo;

    /**
     * variable
     */
    private String variable;

    /**
     * unit
     */
    private String unit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getPtNo() {
        return ptNo;
    }

    public void setPtNo(String ptNo) {
        this.ptNo = ptNo;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


}
