package com.ejunhai.qutihuo.controller;

import com.ejunhai.qutihuo.common.base.BaseController;
import com.ejunhai.qutihuo.statistical.dto.MeasurementDto;
import com.ejunhai.qutihuo.statistical.dto.StandardReviseDto;
import com.ejunhai.qutihuo.statistical.service.MeasurementService;
import com.ejunhai.qutihuo.statistical.service.ProvinceStandardService;
import com.ejunhai.qutihuo.statistical.service.StatisticsService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("statistical")
public class StatisticalController extends BaseController {

	@Resource
	private StatisticsService statisticsService;

	@ResponseBody
	@RequestMapping("/calStatistics")
	public String calStatistics(String input,HttpServletRequest request, ModelMap modelMap) {
		System.out.println("=======");
		Map<String,Object> resultMap = new HashMap<>();
		JSONArray jsonMatrix = JSONArray.fromObject(input);
		JSONArray jsonArray = (JSONArray) jsonMatrix.get(0);
		int size = jsonArray.size();
		List<Double> array = new ArrayList<>();
		for(int i=0;i<size;i++){
			String j = (String)jsonArray.get(i);
			if (!"".equals(j)){
				array.add(new Double(j));
			}
		}
		double[] inputArray = new double[array.size()];
		for(int j=0;j<array.size();j++){
			inputArray[j]=array.get(j);
		}
		double average = statisticsService.calAverage(inputArray);
		System.out.println(average);
		resultMap.put("status",200);
		resultMap.put("average",average);
		return gson.toJson(resultMap);
	}

}
