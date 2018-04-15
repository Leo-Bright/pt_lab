package com.ejunhai.qutihuo.statistical.model;

import com.sun.org.apache.bcel.internal.generic.RETURN;

/*均匀性检验的数据信息
* created by wugang on 2018-4-8
*
* */
public class HomoCheckData {
    //id
    private  String id;
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

    public HomoCheckData(){
        this.id = java.util.UUID.randomUUID().toString().replace("-","");
    }

    public HomoCheckData(String sampleid, String samplepartname, String measurand, java.sql.Date checkdate, String unit, double value1, double value2, double value3,double value4, double value5,double value6, double value7,double value8, double value9,double value10){
        this.id = java.util.UUID.randomUUID().toString().replace("-","");
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

    public String getID(){
        return id;
    }
    public String getMeasurand(){
        return measurand;
    }
    public java.sql.Date getCheckDate(){return checkDate;}
    public String getSampleID(){return sampleID;}
    public String getSamplePartName(){return samplePartName;}
    public Double getValue1(){return value1;}
    public Double getValue2(){return value2;}
    public Double getValue3(){return value3;}
    public Double getValue4(){return value4;}
    public Double getValue5(){return value5;}
    public Double getValue6(){return value6;}
    public Double getValue7(){return value7;}
    public Double getValue8(){return value8;}
    public Double getValue9(){return value9;}
    public Double getValue10(){return value10;}
    public void setId(String id){this.id = id;}
    public void setSampleID(String sampleID){this.sampleID = sampleID;}
    public void setSamplePartName(String samplePartName){this.samplePartName = samplePartName;}
    public void setMeasurand(String measurand){this.measurand = measurand;}
    public void setUnit(String unit){this.unit = unit;}
    public void setCheckDate(java.sql.Date checkDate){this.checkDate = checkDate;}
    public void setValue1(Double value1){this.value1 = value1;}
    public void setValue2(Double value2){this.value2 = value2;}
    public void setValue3(Double value3){this.value3 = value3;}
    public void setValue4(Double value4){this.value4 = value4;}
    public void setValue5(Double value5){this.value5 = value5;}
    public void setValue6(Double value6){this.value6 = value6;}
    public void setValue7(Double value7){this.value7 = value7;}
    public void setValue8(Double value8){this.value8 = value8;}
    public void setValue9(Double value9){this.value9 = value9;}
    public void setValue10(Double value10){this.value10 = value10;}
}
