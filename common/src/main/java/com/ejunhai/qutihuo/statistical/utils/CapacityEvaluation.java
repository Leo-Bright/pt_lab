package com.ejunhai.qutihuo.statistical.utils;


import java.rmi.MarshalException;
import java.util.HashMap;
import java.util.Map;

public class CapacityEvaluation {

	//6.1 检查指定值不确定度
	public String checkSpecialValUncertainty(double xpt, double pt) {
		if (xpt > 0.3 * pt) return "该指定值的不确定度不可忽略，请使用z'值、Zeta值或En值";
		if (xpt < 0.3 * pt) return "该指定值的不确定度可忽略";
		return "";
	}

	//6.2 偏倚
	public Map<String,Object> bias(double[] array, double xpt, double delta) {
		Map<String,Object> resultMap = new HashMap<>();
		int size = array.length;
		double[] Di = new double[size];
		double[] Di100 = new double[size];
		String[] result = new String[size];
		for(int i=0;i<size;i++){
			double tmp = Arith.sub(array[i],xpt);
			Di[i]=tmp;
			Di100[i]=Arith.div(tmp,xpt);
			result[i]=(tmp<delta&&tmp>(-delta))?"可接受":"不可接受";
		}
		resultMap.put("Di",Di);
		resultMap.put("Di100",Di100);
		resultMap.put("biasResult",result);
		return resultMap;
	}

	//6.3 z值
	public Map<String,Object> zvalue(double[] array, double xpt, double sigma) {
		Map<String,Object> resultMap = new HashMap<>();
		int size = array.length;
		double[] Z = new double[size];
		String[] result = new String[size];
		for(int i=0;i<size;i++){
			double tmp = Arith.div(Arith.sub(array[i],xpt),sigma);
			Z[i]=tmp;
            double abs_tmp = Math.abs(tmp);
			if(abs_tmp<=2.0){
			    result[i] = "可接收";
            }else if(2.0<abs_tmp&&abs_tmp<3.0){
                result[i] = "警戒信号";
            }else{
                result[i] = "行动信号";
            }
		}
		resultMap.put("Z",Z);
		resultMap.put("result",result);
		return resultMap;
	}

	//6.4 z'值
	public Map<String,Object> z_value(double[] array, double xpt, double sigma,double uncertain) {
        Map<String,Object> resultMap = new HashMap<>();
        int size = array.length;
        double[] Z = new double[size];
        String[] result = new String[size];
        for(int i=0;i<size;i++){
            double tmp = Arith.div(Arith.sub(array[i],xpt),Math.sqrt(Arith.add(Arith.mul(sigma,sigma),Arith.mul(uncertain,uncertain))));
            Z[i]=tmp;
            double abs_tmp = Math.abs(tmp);
            if(abs_tmp<=2.0){
                result[i] = "可接收";
            }else if(2.0<abs_tmp&&abs_tmp<3.0){
                result[i] = "警戒信号";
            }else{
                result[i] = "行动信号";
            }
        }
        resultMap.put("Z",Z);
        resultMap.put("result",result);
        return resultMap;
	}

	//6.5 zeta值
	public Map<String,Object> zetavalue(double[] array, double xpt,double uncertain) {
        Map<String,Object> resultMap = new HashMap<>();
        int size = array.length;
        double[] Zeta = new double[size];
        String[] result = new String[size];
        for(int i=0;i<size;i++){
            double tmp = Arith.div(Arith.sub(array[i],xpt),Math.sqrt(Arith.add(Arith.mul(xpt,xpt),Arith.mul(uncertain,uncertain))));
            Zeta[i]=tmp;
            double abs_tmp = Math.abs(tmp);
            if(abs_tmp<=2.0){
                result[i] = "可接收";
            }else if(2.0<abs_tmp&&abs_tmp<3.0){
                result[i] = "警戒信号";
            }else{
                result[i] = "行动信号";
            }
        }
        resultMap.put("Zeta",Zeta);
        resultMap.put("result",result);
        return resultMap;
	}

	//6.6 En值
	public Map<String,Object> envalue(double[] array, double xpt,double uncertain) {
        Map<String,Object> resultMap = new HashMap<>();
        int size = array.length;
        double[] En = new double[size];
        String[] result = new String[size];
        for(int i=0;i<size;i++){
            double tmp = Arith.div(Arith.sub(array[i],xpt),Math.sqrt(Arith.add(Arith.mul(xpt,xpt),Arith.mul(uncertain,uncertain))));
            En[i]=tmp;
            double abs_tmp = Math.abs(tmp);
            if(abs_tmp<1.0){
                result[i] = "可接收";
            }else{
                result[i] = "警戒信号";
            }
        }
        resultMap.put("En",En);
        resultMap.put("result",result);
        return resultMap;
	}

}

