package com.ejunhai.qutihuo.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejunhai.qutihuo.statistical.model.CapabilityEvaluation;
import com.ejunhai.qutihuo.statistical.service.CapabilityEvaluationService;
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

    @RequestMapping("insertOne2CE")
    public String insertOne2CE(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,String sampleNo,String sampleName,String ptNo,String variable,String unit) throws Exception {
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
}

