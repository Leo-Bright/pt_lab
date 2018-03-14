package com.ejunhai.qutihuo.statistical.model;
/**
 * 能力验证记录信息表
 *
 * @author Leo
 * @date 2018-3-7 19:18:30
 */
public class CapabilityValue {
    /**
     * ce_id
     */
    private Integer ceId;

    /**
     * sample_no
     */
    private String sampleNo;

    /**
     * sample_name
     */
    private String sampleName;

    /**
     * variable
     */
    private String variable;

    /**
     * unit
     */
    private String unit;

    /**
     * compute_method
     */
    private String computeMethod;

    /**
     * standard_method
     */
    private String standardMethod;

    /**
     * x_pt
     */
    private String xPt;

    /**
     * std_pt
     */
    private String stdPt;

    /**
     * uncertain_pt
     */
    private String uncertainPt;

    public Integer getCeId() {
        return ceId;
    }

    public void setCeId(Integer ceId) {
        this.ceId = ceId;
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

    public String getComputeMethod() {
        return computeMethod;
    }

    public void setComputeMethod(String computeMethod) {
        this.computeMethod = computeMethod;
    }

    public String getStandardMethod() {
        return standardMethod;
    }

    public void setStandardMethod(String standardMethod) {
        this.standardMethod = standardMethod;
    }

    public String getxPt() {
        return xPt;
    }

    public void setxPt(String xPt) {
        this.xPt = xPt;
    }

    public String getStdPt() {
        return stdPt;
    }

    public void setStdPt(String stdPt) {
        this.stdPt = stdPt;
    }

    public String getUncertainPt() {
        return uncertainPt;
    }

    public void setUncertainPt(String uncertainPt) {
        this.uncertainPt = uncertainPt;
    }
}
