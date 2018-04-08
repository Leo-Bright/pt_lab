package com.ejunhai.qutihuo.statistical.model;
/**
 * 能力验证记录信息表
 *
 * @author Leo
 * @date 2018-3-7 19:18:30
 */
public class CapabilityEvaluationData {
    /**
     * ce_id
     */
    private Integer ceId;

    /**
     * data_type
     */
    private Integer dataType;

    /**
     * sample_no
     */
    private String ptNo;

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
     * lab_no
     */
    private String labNo;

    /**
     * messure_1
     */
    private String messure1;

    /**
     * messure_2
     */
    private String messure2;

    /**
     * messure_3
     */
    private String messure3;

    /**
     * messure_4
     */
    private String messure4;

    /**
     * messure_5
     */
    private String messure5;



    public CapabilityEvaluationData(){

    }
    public CapabilityEvaluationData(Integer ceId, Integer dataType,String ptNo, String sampleName, String variable, String unit, String labNo, String messure1, String messure2, String messure3, String messure4,String messure5){
        this.ceId=ceId;
        this.dataType=dataType;
        this.ptNo=ptNo;
        this.sampleName=sampleName;
        this.variable=variable;
        this.unit=unit;
        this.labNo=labNo;
        this.messure1=messure1;
        this.messure2=messure2;
        this.messure3=messure3;
        this.messure4=messure4;
        this.messure5=messure5;
    }

    public Integer getCeId() {
        return ceId;
    }

    public void setCeId(Integer ceId) {
        this.ceId = ceId;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getPtNo() {
        return ptNo;
    }

    public void setPtNo(String ptNo) {
        this.ptNo = ptNo;
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

    public String getLabNo() {
        return labNo;
    }

    public void setLabNo(String labNo) {
        this.labNo = labNo;
    }

    public String getMessure1() {
        return messure1;
    }

    public void setMessure1(String messure1) {
        this.messure1 = messure1;
    }

    public String getMessure2() {
        return messure2;
    }

    public void setMessure2(String messure2) {
        this.messure2 = messure2;
    }

    public String getMessure3() {
        return messure3;
    }

    public void setMessure3(String messure3) {
        this.messure3 = messure3;
    }

    public String getMessure4() {
        return messure4;
    }

    public void setMessure4(String messure4) {
        this.messure4 = messure4;
    }

    public String getMessure5() {
        return messure5;
    }

    public void setMessure5(String messure5) {
        this.messure5 = messure5;
    }
}
