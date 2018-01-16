package com.ejunhai.qutihuo.controller;

import com.ejunhai.qutihuo.common.base.BaseController;
import com.ejunhai.qutihuo.statistical.dto.MeasurementDto;
import com.ejunhai.qutihuo.statistical.dto.StandardReviseDto;
import com.ejunhai.qutihuo.statistical.service.MeasurementService;
import com.ejunhai.qutihuo.statistical.service.ProvinceStandardService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("statistical")
public class StatisticalController extends BaseController {

	@Resource
	private ProvinceStandardService provinceStandardService;

	@Resource
	private MeasurementService measurementService;

	@RequestMapping("/standardRevise")
	public String standardRevise(HttpServletRequest request, ModelMap modelMap) {
		Map<String,Object> jsonMap = new HashMap<>();
		StandardReviseDto standardReviseDto = provinceStandardService.acquireStandardRevise();
		JSONObject dto = JSONObject.fromObject(standardReviseDto);
		modelMap.put("jsonData",dto);
		return "statistical/standardRevise";
	}

	@RequestMapping("/measurement")
	public String measurement(HttpServletRequest request, ModelMap modelMap) {
		Map<String,Object> jsonMap = new HashMap<>();
		MeasurementDto measurement = measurementService.acquireMeasurement();
		JSONObject dto = JSONObject.fromObject(measurement);
		modelMap.put("jsonData",dto);
		return "statistical/measurement";
	}
}
