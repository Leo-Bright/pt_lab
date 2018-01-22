package com.ejunhai.qutihuo.controller;

import com.ejunhai.qutihuo.common.base.BaseController;
import com.ejunhai.qutihuo.statistical.service.StatisticsService;
import com.ejunhai.qutihuo.statistical.utils.JsonUtils;
import net.sf.json.JSONArray;
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
		double[][] matrix = JsonUtils.fromJsonString(input);
		Map<String,Object> result = statisticsService.calStatistics(matrix[0]);
		return gson.toJson(result);
	}

	@ResponseBody
	@RequestMapping("/calMethod")
	public String calMethod(String input,String method,HttpServletRequest request, ModelMap modelMap) {
		double[][] matrix = JsonUtils.fromJsonString(input);
		Map<String,Object> result = statisticsService.calMethod(matrix,method);
		return gson.toJson(result);
	}

}
