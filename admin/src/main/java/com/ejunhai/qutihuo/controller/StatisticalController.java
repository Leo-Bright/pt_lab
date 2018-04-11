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
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("statistical")
public class StatisticalController extends BaseController {

	@Resource
	private StatisticsService statisticsService;

	@ResponseBody
	@RequestMapping("/calStatistics")
	public String calStatistics(String input,HttpServletRequest request, ModelMap modelMap) {
		double[][] matrix = JsonUtils.jsonString2double(input);
		Map<String,Object> result = statisticsService.calStatistics(matrix[0]);
		return gson.toJson(result);
	}

	@ResponseBody
	@RequestMapping("/calStatistics2")
	public String calStatistics2(String input,HttpServletRequest request, ModelMap modelMap) {
		double[][] matrix = JsonUtils.jsonString2double(input);
		int len = matrix.length;
		double[] array = new double[len];
		for(int i=0;i<len;i++){
			array[i] = matrix[i][0];
		}
		Map<String,Object> result = statisticsService.calStatistics(array);
		return gson.toJson(result);
	}

	@ResponseBody
	@RequestMapping("/calMethod")
	public String calMethod(String input,String method,HttpServletRequest request, ModelMap modelMap) {
		double[][] matrix = JsonUtils.jsonString2double(input);
		Map<String,Object> result = statisticsService.calMethod(matrix,method);
		return gson.toJson(result);
	}

	@ResponseBody
	@RequestMapping("/checkStability")
	public String checkStability(String input1,String input2,String method,String para,HttpServletRequest request, ModelMap modelMap) {
		double[][] matrix1 = JsonUtils.jsonString2double(input1);
		double[][] matrix2 = JsonUtils.jsonString2double(input2);
        double parameter = 0.0;
        if(null!=para&&!"".equals(para)){
            parameter = Double.valueOf(para);
        }
		Map<String,Object> result = statisticsService.checkStability(matrix1,matrix2,method,parameter);
		return gson.toJson(result);
	}

	@ResponseBody
	@RequestMapping("/checkStability2")
	public String checkStability2(String input,String para,HttpServletRequest request, ModelMap modelMap) {
		double[][] matrix = JsonUtils.jsonString2double(input);
        double parameter = 0.0;
        if(null!=para&&!"".equals(para)){
            parameter = Double.valueOf(para);
        }
        Map<String,Object> result = statisticsService.checkStability(matrix,matrix,"t2",parameter);

		return gson.toJson(result);
	}

	@ResponseBody
	@RequestMapping("/checkUniformity")
	public String checkUniformity(String input,String method,String para,String maxErr, HttpServletRequest request, ModelMap modelMap) {
		double[][] matrix = JsonUtils.jsonString2double(input);
        double stdORerror = 0.0;
        double maxError = 0.0;
		if(null!=para&&!"".equals(para)){
            stdORerror = Double.valueOf(para);
        }
		if(null!=maxErr &&!"".equals(maxErr)){
			maxError = Double.valueOf(maxErr);
		}
		Map<String,Object> result = statisticsService.checkUniformity(matrix,method,stdORerror,maxError);
		return gson.toJson(result);
	}

	//均匀性检验结果
	@ResponseBody
	@RequestMapping("/checkUniformity2")
	public String checkUniformity2(String input,String pt, String maxErr,  HttpServletRequest request, ModelMap modelMap) {
		Map<String,Object> resultMap = new HashMap<>();
		double[][] matrix = JsonUtils.jsonString2double(input);
        double stdORerror = 0.0;
        double maxError = 0.0;
		if(null!=pt && !"".equals(pt)){
            stdORerror = Double.valueOf(pt);
        }
		if(null!=maxErr && !"".equals(maxErr)){
			maxError = Double.valueOf(maxErr);
		}
		//单因子方差分析
		Map<String,Object> singleFactory = statisticsService.checkUniformity(matrix,"danyinzi",stdORerror,maxError);
		double[][] singleFactoryMatrix = (double[][])singleFactory.get("result");
		double F = singleFactoryMatrix[1][5];
		double fa = singleFactoryMatrix[1][6];
		if(F<=fa){
			resultMap.put("singleFactory","通过");
		}else{
			resultMap.put("singleFactory","不通过");
		}

		//扩展方法
		Map<String,Object> extension = statisticsService.checkUniformity(matrix,"extension",stdORerror,maxError);
		double[] extensionMatrix = (double[])extension.get("result");
		double outer_variance = extensionMatrix[0];
		double sqrt_c = extensionMatrix[1];
		if(null!=pt && !"".equals(pt)){
			resultMap.put("extension","不可用");
		}else{
			if(outer_variance<=sqrt_c){
				resultMap.put("extension","通过");
			}else{
				resultMap.put("extension","不通过");
			}
		}

		//s_s≤0.3σ ̂准则
		Map<String,Object> lesslaw  = statisticsService.checkUniformity(matrix,"sszz",stdORerror,maxError);
		double[] lesslawMatrix = (double[])lesslaw.get("result");
		double outer_variance_lesslaw = lesslawMatrix[0];

		if(null!=pt && !"".equals(pt)){
			resultMap.put("sscc","不可用");
		}else{
			if(outer_variance_lesslaw <= 0.3 * stdORerror || outer_variance_lesslaw <= 0.1 * maxError){
				resultMap.put("sscc","通过");
			}else{
				resultMap.put("sscc","不通过");
			}
		}

		resultMap.put("status",200);
		return gson.toJson(resultMap);
	}

	@ResponseBody
	@RequestMapping("/confirm")
	public String confirm(String input,String method,String xcrm,String ucrm,HttpServletRequest request, ModelMap modelMap) {
		double[][] matrix = JsonUtils.jsonString2double(input);
		double para_xcrm = 0.0;
		double para_ucrm = 0.0;
		if(null!=xcrm&&!"".equals(xcrm)){
			para_xcrm = Double.valueOf(xcrm);
		}
		if(null!=ucrm&&!"".equals(ucrm)){
			para_ucrm = Double.valueOf(ucrm);
		}
		Map<String,Object> result = statisticsService.confirm(matrix,method,para_xcrm,para_ucrm);
		return gson.toJson(result);
	}

	@ResponseBody
	@RequestMapping("/ensureStdVar")
	public String ensureStdVar(String input,String method,HttpServletRequest request, ModelMap modelMap) {
		double[][] matrix = JsonUtils.jsonString2double(input);
		Map<String,Object> result = statisticsService.ensureStdVar(matrix,method);
		return gson.toJson(result);
	}

	@ResponseBody
	@RequestMapping("/computStdVar")
	public String computStdVar(String method,String c,String cfx,String zxx,String m,HttpServletRequest request, ModelMap modelMap) {
		double para_c = 0.0;
		double para_cfx = 0.0;
		double para_zxx = 0.0;
		double para_m = 0.0;
		if(null!=c&&!"".equals(c)){
			para_c = Double.valueOf(c);
		}
		if(null!=cfx&&!"".equals(cfx)){
			para_cfx = Double.valueOf(cfx);
		}
		if(null!=zxx&&!"".equals(zxx)){
			para_zxx = Double.valueOf(zxx);
		}
		if(null!=m&&!"".equals(m)){
			para_m = Double.valueOf(m);
		}
		Map<String,Object> result = statisticsService.computStdVar(method,para_c,para_cfx,para_zxx,para_m);
		return gson.toJson(result);
	}

	@ResponseBody
	@RequestMapping("/CapacityEvaluate")
	public String CapacityEvaluate(String input,String method,String xpt,String delta,String sigma,String uncertain,HttpServletRequest request, ModelMap modelMap) {
		double[][] matrix = JsonUtils.jsonString2double(input);
		double _xpt = 0.0;
		double _delta = 0.0;
		double _sigma = 0.0;
		double _uncertain = 0.0;
		if(null!=xpt&&!"".equals(xpt)){
			_xpt = Double.valueOf(xpt);
		}
		if(null!=delta&&!"".equals(delta)){
			_delta = Double.valueOf(delta);
		}
		if(null!=sigma&&!"".equals(sigma)){
			_sigma = Double.valueOf(sigma);
		}
		if(null!=uncertain&&!"".equals(uncertain)){
			_uncertain = Double.valueOf(uncertain);
		}
		Map<String,Object> result = statisticsService.CapacityEvaluate(matrix,method,_xpt,_delta,_sigma,_uncertain);
		return gson.toJson(result);
	}

}
