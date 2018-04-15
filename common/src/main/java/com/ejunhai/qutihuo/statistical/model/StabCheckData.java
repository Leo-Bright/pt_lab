package com.ejunhai.qutihuo.statistical.model;

public class StabCheckData {
    //id
    private  String ID;
    //样品编号
    private String sampleID;
    //样品份样编号
    private String samplePartName;
    //被测量
    private String measurand;
    //测量时间
    private java.sql.Date checkDate;
    //计量单位
    private  String unit;
    //测量值
    private double value3;
    private double value2;
    private double value1;
    private double value6;
    private double value5;
    private double value4;
    private double value7;
    private double value8;
    private double value9;
    private double value10;

    public StabCheckData(String sampleid, String samplepartname, String measurand, java.sql.Date checkdate, String unit, double value1, double value2, double value3,double value4, double value5,double value6, double value7,double value8, double value9,double value10){
        this.ID = java.util.UUID.randomUUID().toString().replace("-","");
        this.sampleID = sampleid;
        this.samplePartName = samplepartname;
        this.measurand = measurand;
        this.checkDate = checkdate;
        this.unit = unit;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.value5 = value5;
        this.value6 = value6;
        this.value7 = value7;
        this.value8 = value8;
        this.value9 = value9;
        this.value10 = value10;
    }
}
