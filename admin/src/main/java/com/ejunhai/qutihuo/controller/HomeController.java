package com.ejunhai.qutihuo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejunhai.qutihuo.statistical.dto.MeasurementDto;
import com.ejunhai.qutihuo.statistical.model.CapabilityEvaluation;
import com.ejunhai.qutihuo.statistical.model.CapabilityValue;
import com.ejunhai.qutihuo.statistical.service.CapabilityEvaluationService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejunhai.qutihuo.common.utils.PropertyConfigurer;
import com.ejunhai.qutihuo.errors.ErrorType;
import com.ejunhai.qutihuo.utils.FrontUtil;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.rs.PutPolicy;

@Controller
@RequestMapping("")
public class HomeController {

	@Resource
	private CapabilityEvaluationService capabilityEvaluationService;

	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
		return "index";
	}

	@RequestMapping("/pt")
	public String pt(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,String id) throws IOException {
		Integer ceId = Integer.valueOf(id);
		List<CapabilityValue> capabilityValueList = capabilityEvaluationService.getCapabilityValuesById(ceId);
		modelMap.put("capabilityValueList",capabilityValueList);
		return "pt";
	}

	@RequestMapping("/grid")
	public String grid(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
		List<CapabilityEvaluation> capabilityEvaluationList = capabilityEvaluationService.getAll();
		modelMap.put("capabilityEvaluationList",capabilityEvaluationList);
		return "grid";
	}

	@RequestMapping("/stab")
	public String stab(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
		return "stab";
	}

	@RequestMapping("/calstats")
	public String calstats(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
		return "calstats";
	}

	@RequestMapping("/hs")
	public String hs(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
		return "hs";
	}

	@RequestMapping("/confirm")
	public String confirm(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
		return "confirm";
	}

	@RequestMapping("/stddev")
	public String stddev(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
		return "stddev";
	}

	@RequestMapping("/ce")
	public String ce(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
		return "ce";
	}

	@RequestMapping("/graphic")
	public String graphic(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
		return "graphic";
	}

	@RequestMapping("getUptoken")
	@ResponseBody
	public String getUptoken(HttpServletRequest request, ModelMap modelMap) throws Exception {
		String bucketName = PropertyConfigurer.getContextProperty("qiniu.bucket.name");
		String accessKey = PropertyConfigurer.getContextProperty("qiniu.access.key");
		String secretKey = PropertyConfigurer.getContextProperty("qiniu.secret.key");

		Mac mac = new Mac(accessKey, secretKey);
		PutPolicy putPolicy = new PutPolicy(bucketName);
		return "{ \"uptoken\": \"" + putPolicy.token(mac) + "\" }";
	}

	@RequestMapping("/forbidden")
	public String forbidden(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 处理异步请求-重定向到统一异常处理
		if (FrontUtil.isAjax(request)) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter writer = response.getWriter();
			Integer errorCode = ErrorType.SYSTEM_FORBIDDEN.getValue();
			String errorMsg = ErrorType.SYSTEM_FORBIDDEN.getTitle();
			writer.write(FrontUtil.renderJson(errorCode, errorMsg, null));
			writer.flush();
			return null;
		}

		return "errors/error-403";
	}
}
