package com.ejunhai.qutihuo.statistical.dto;

public class CheckUncertainty {

    private String sampleNo;

    private String sampleName;

    private String variable;

    private String xPt;

    private String stdPt;

    public CheckUncertainty(){

    }

    public CheckUncertainty(String sampleNo,String sampleName,String variable,String xPt,String stdPt){
        this.sampleNo=sampleNo;
        this.sampleName=sampleName;
        this.variable=variable;
        this.xPt=xPt;
        this.stdPt=stdPt;
        this.stdPt=stdPt;
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
}
