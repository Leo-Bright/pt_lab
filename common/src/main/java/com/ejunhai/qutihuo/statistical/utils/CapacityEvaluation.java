package com.ejunhai.qutihuo.statistical.utils;


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

}

