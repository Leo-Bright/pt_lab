package com.ejunhai.qutihuo.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejunhai.qutihuo.statistical.model.CapabilityEvaluation;
import com.ejunhai.qutihuo.statistical.model.CapabilityValue;
import com.ejunhai.qutihuo.statistical.service.CapabilityEvaluationService;
import com.ejunhai.qutihuo.statistical.service.CapabilityValueService;
import com.ejunhai.qutihuo.statistical.service.StatisticsService;
import com.ejunhai.qutihuo.statistical.utils.JsonUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejunhai.qutihuo.common.base.BaseController;
import com.ejunhai.qutihuo.common.base.Pagination;
import com.ejunhai.qutihuo.coupon.dto.CouponDto;
import com.ejunhai.qutihuo.coupon.dto.CouponSchemaDto;
import com.ejunhai.qutihuo.coupon.enums.Confusion;
import com.ejunhai.qutihuo.coupon.enums.CouponState;
import com.ejunhai.qutihuo.coupon.enums.ExchangeMode;
import com.ejunhai.qutihuo.coupon.model.Coupon;
import com.ejunhai.qutihuo.coupon.model.CouponSchema;
import com.ejunhai.qutihuo.coupon.service.CouponSchemaService;
import com.ejunhai.qutihuo.coupon.service.CouponService;
import com.ejunhai.qutihuo.coupon.utils.CouponUtil;
import com.ejunhai.qutihuo.errors.JunhaiAssert;
import com.ejunhai.qutihuo.utils.SessionManager;

@Controller
@RequestMapping("")
public class DataOpController extends BaseController {

    @Resource
    private CapabilityEvaluationService capabilityEvaluationService;

    @Resource
    private CapabilityValueService capabilityValueService;

    @RequestMapping("insertOne2CE")
    public String insertOne2CE(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,String sampleNo,String sampleName,String ptNo,String variable,String unit){
        CapabilityEvaluation capabilityEvaluation = new CapabilityEvaluation();
        capabilityEvaluation.setSampleNo(sampleNo);
        capabilityEvaluation.setSampleName(sampleName);
        capabilityEvaluation.setPtNo(ptNo);
        capabilityEvaluation.setVariable(variable);
        capabilityEvaluation.setUnit(unit);
        capabilityEvaluationService.insert(capabilityEvaluation);
        List<CapabilityEvaluation> capabilityEvaluationList = capabilityEvaluationService.getAll();
        modelMap.put("capabilityEvaluationList",capabilityEvaluationList);
        return "grid";
    }

    /*@RequestMapping("saveCapacityValues")
    public String saveCapacityValues(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,String input1,String input2,String xmethod,String smethod,
                                     String id,String c, String cfx,String zxx,String m,String std_spe,String xcrm,String ucrm,String x_spe,String u_spe){
        double[][] matrix1 = JsonUtils.fromJsonString(input1);
        double[][] matrix2 = JsonUtils.fromJsonString(input2);
        double xcrm_ = 0.0;
        double ucrm_ = 0.0;
        if(null!=xcrm&&!"".equals(xcrm)){
            xcrm_ = Double.valueOf(xcrm);
        }
        if(null!=ucrm&&!"".equals(ucrm)){
            ucrm_ = Double.valueOf(ucrm);
        }
        int len = matrix1.length;
        int col = matrix1[0].length;
        CapabilityValueService capabilityValue = new CapabilityValueService();
        if("spe_extensional".equals(xmethod)){
            capabilityValue.setxPt(x_spe);
            capabilityValue.setUncertainPt(u_spe);
        }else if ("spe_ref_sample".equals(xmethod)){
            statisticsService.confirm(matrix1,"refvalue",xcrm_,ucrm_);
        }

        modelMap.put("capabilityEvaluationList","");
        return "grid";
    }*//*@RequestMapping("saveCapacityValues")
    public String saveCapacityValues(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,String input1,String input2,String xmethod,String smethod,
                                     String id,String c, String cfx,String zxx,String m,String std_spe,String xcrm,String ucrm,String x_spe,String u_spe){
        double[][] matrix1 = JsonUtils.fromJsonString(input1);
        double[][] matrix2 = JsonUtils.fromJsonString(input2);
        double xcrm_ = 0.0;
        double ucrm_ = 0.0;
        if(null!=xcrm&&!"".equals(xcrm)){
            xcrm_ = Double.valueOf(xcrm);
        }
        if(null!=ucrm&&!"".equals(ucrm)){
            ucrm_ = Double.valueOf(ucrm);
        }
        int len = matrix1.length;
        int col = matrix1[0].length;
        CapabilityValueService capabilityValue = new CapabilityValueService();
        if("spe_extensional".equals(xmethod)){
            capabilityValue.setxPt(x_spe);
            capabilityValue.setUncertainPt(u_spe);
        }else if ("spe_ref_sample".equals(xmethod)){
            statisticsService.confirm(matrix1,"refvalue",xcrm_,ucrm_);
        }

        modelMap.put("capabilityEvaluationList","");
        return "grid";
    }*/

    @ResponseBody
    @RequestMapping("saveCapacityValues")
    public String saveCapacityValues(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,String input,
                                     String xmethod,String smethod, String id,String x, String u,String std,String sampleNo){
        Map<String,Object> result = new HashMap<>();
        String[][] matrix = JsonUtils.jsonString2string(input);
        String[] attribute = matrix[0];
        String sampleName = attribute[1];
        String variable = attribute[2];
        String unit = attribute[3];
        CapabilityValue capabilityValue = new CapabilityValue();
        capabilityValue.setCeId(Integer.valueOf(id));
        capabilityValue.setSampleNo(sampleNo);
        capabilityValue.setSampleName(sampleName);
        capabilityValue.setVariable(variable);
        capabilityValue.setUnit(unit);
        capabilityValue.setComputeMethod(xmethod);
        capabilityValue.setStandardMethod(smethod);
        capabilityValue.setxPt(x);
        capabilityValue.setStdPt(std);
        capabilityValue.setUncertainPt(u);
        capabilityValueService.insert(capabilityValue);
        result.put("status",200);
        return gson.toJson(result);
    }
}

