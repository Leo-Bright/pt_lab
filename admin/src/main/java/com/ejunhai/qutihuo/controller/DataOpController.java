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
import com.ejunhai.qutihuo.statistical.service.HomoCheckService;
import com.ejunhai.qutihuo.statistical.service.StatisticsService;
import com.ejunhai.qutihuo.statistical.utils.JsonUtils;
import javafx.util.Pair;
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

    @Resource
    private StatisticsService statisticsService;

    @Resource
    private HomoCheckService homoCheckService;

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

    /*
    * 保存能力评定中计算指定值和标准差的数据
    *
    * */
    @RequestMapping("saveCEdata")
    public Map<String,Object> saveCEdata(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,String input1,String input2,String id){
        Map<String,Object> resultMap = new HashMap<>();
        String[][] matrix1 = JsonUtils.jsonString2string(input1);
        String[][] matrix2 = JsonUtils.jsonString2string(input2);
        int ceid = Integer.valueOf(id);
        capabilityEvaluationService.delete(ceid);
        capabilityEvaluationService.saveAll(ceid,1,matrix1);
        capabilityEvaluationService.saveAll(ceid,2,matrix2);
        resultMap.put("status",200);
        return resultMap;
    }

    /*
    * 保存均匀性和稳定性检验的数据
    * */
    @RequestMapping("saveHomoData")
    public Map<String,Object> saveHomoData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String input1){
        Map<String,Object> resultMap = new HashMap<>();
        String[][] maxtrix_homodata = JsonUtils.jsonString2string(input1);

        homoCheckService.saveHomoCheckData(maxtrix_homodata);
        resultMap.put("status",200);
        return resultMap;


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
    public String saveCapacityValues(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,String input1,String input2,String xmethod,
                                     String smethod, String id,String c,String cfx, String zxx,String m,String std_spe,String xcrm,String ucrm,String x_spe,String u_spe){
        Map<String,Object> resultMap = new HashMap<>();
        double[][] matrix1 = JsonUtils.jsonString2double(input1);
        double[][] matrix2 = JsonUtils.jsonString2double(input2);
        CapabilityEvaluation ce = capabilityEvaluationService.getCapabilityEvaluationById(Integer.valueOf(id));
        String x = null;
        String u = null;
        String std = null;
        String trueXmethod = null;
        String trueSmethod = null;
        if("stderr_extensional".equals(smethod)){
            trueSmethod = "由外部值确定";
        }else if("stderr_model".equals(smethod)){
            trueSmethod = "由一般模型确定";
        }else if("stderr_measurementmethod".equals(smethod)){
            trueSmethod = "由测量方法重复性和再现性确定";
        }else if("stderr_alg_a".equals(smethod)){
            trueSmethod = "由稳健方法确定-算法A";
        }else if("stderr_qn".equals(smethod)){
            trueSmethod = "由稳健方法确定-Qn方法";
        }else{
            trueSmethod = "由稳健方法确定-Q/Hampel方法";
        }
        capabilityValueService.deleteById(Integer.valueOf(id));
        if("spe_extensional".equals(xmethod)){
            trueXmethod = "由外部值确定";
            x = x_spe;
            u = u_spe;
            if("stderr_extensional".equals(smethod)){
                std = std_spe;
                CapabilityValue capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                capabilityValueService.insert(capabilityValue);
            }else if("stderr_model".equals(smethod)){
                Map<String,Object> computResult = statisticsService.computStdVar("bymodel",Double.valueOf(c),Double.valueOf(cfx),Double.valueOf(zxx),Double.valueOf(m));
                 std = computResult.get("result").toString();
                CapabilityValue capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                capabilityValueService.insert(capabilityValue);
            }else if("stderr_measurementmethod".equals(smethod)){
                Map<String,Object> computResult = statisticsService.computStdVar("measurement",Double.valueOf(c),Double.valueOf(cfx),Double.valueOf(zxx),Double.valueOf(m));
                std = computResult.get("result").toString();
                CapabilityValue capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                capabilityValueService.insert(capabilityValue);
            }else if("stderr_alg_a".equals(smethod)){
                int len = matrix1.length;
                double[][] tmpMatrix = new double[][]{};
                for(int i=0;i<len;i++){
                    tmpMatrix[0]=matrix1[i];
                    Map<String,Object> computResult = statisticsService.ensureStdVar(tmpMatrix,"algorithmA");
                    std = ((Pair<Double, Double>)computResult.get("result")).getValue().toString();
                    CapabilityValue capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                    capabilityValueService.insert(capabilityValue);
                }
            }else if("stderr_qn".equals(smethod)){
                int len = matrix1.length;
                double[][] tmpMatrix = new double[][]{};
                for(int i=0;i<len;i++){
                    tmpMatrix[0]=matrix1[i];
                    Map<String,Object> computResult = statisticsService.ensureStdVar(tmpMatrix,"Qn");
                    std = computResult.get("result").toString();
                    CapabilityValue capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                    capabilityValueService.insert(capabilityValue);
                }
            }else{
                Map<String,Object> computResult = statisticsService.ensureStdVar(matrix1,"hampel");
                std = computResult.get("result").toString();
                CapabilityValue capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                capabilityValueService.insert(capabilityValue);
            }
        }else if("spe_ref_sample".equals(xmethod)) {
            trueXmethod = "参照有证标准样品确定";
            double[][] tmpMatrix = new double[][]{};
            int len1 = matrix1.length;
            int len2 = matrix2.length;
            if(len1!=len2){
                resultMap.put("status",201);
                resultMap.put("message","两个矩阵长度不一致！");
                return gson.toJson(resultMap);
            }
            double xcrm_ = Double.valueOf(xcrm);
            double ucrm_ = Double.valueOf(ucrm);
            for(int i=0;i<len1;i++){
                tmpMatrix[0]=matrix1[0];
                tmpMatrix[1]=matrix2[0];
                Map<String,Object> result = statisticsService.confirm(tmpMatrix,"refvalue",xcrm_,ucrm_);
                Pair<Double,Double> resultPair = (Pair<Double,Double>)result.get("result");
                x = resultPair.getKey().toString();
                u = resultPair.getValue().toString();
                CapabilityValue capabilityValue = null;
                if("stderr_extensional".equals(smethod)){
                    std = std_spe;
                    capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                }else if("stderr_model".equals(smethod)){
                    Map<String,Object> computResult = statisticsService.computStdVar("bymodel",Double.valueOf(c),Double.valueOf(cfx),Double.valueOf(zxx),Double.valueOf(m));
                    std = computResult.get("result").toString();
                    capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                }else if("stderr_measurementmethod".equals(smethod)){
                    Map<String,Object> computResult = statisticsService.computStdVar("measurement",Double.valueOf(c),Double.valueOf(cfx),Double.valueOf(zxx),Double.valueOf(m));
                    std = computResult.get("result").toString();
                    capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                }else if("stderr_alg_a".equals(smethod)){
                    double[][] newMatrix = new double[][]{};
                    newMatrix[0]=matrix1[i];
                    Map<String,Object> computResult = statisticsService.ensureStdVar(newMatrix,"algorithmA");
                    std = ((Pair<Double, Double>)computResult.get("result")).getValue().toString();
                    capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                }else if("stderr_qn".equals(smethod)){
                    double[][] newMatrix = new double[][]{};
                    newMatrix[0]=matrix1[i];
                    Map<String,Object> computResult = statisticsService.ensureStdVar(newMatrix,"Qn");
                    std = computResult.get("result").toString();
                    capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                }else{
                    Map<String,Object> computResult = statisticsService.ensureStdVar(matrix1,"hampel");
                    std = computResult.get("result").toString();
                    capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                }
                capabilityValueService.insert(capabilityValue);
            }
        }else if("spe_alg_a".equals(xmethod)) {
            trueXmethod = "由稳健方法确定-算法A";
            double[][] tmpMatrix = new double[][]{};
            int len = matrix1.length;
            for(int i=0;i<len;i++){
                tmpMatrix[0]=matrix1[i];
                Map<String,Object> result = statisticsService.confirm(tmpMatrix,"algorithmA",0.0,0.0);
                x = ((Pair<Double, Double>)result.get("result")).getKey().toString();
                u = ((Pair<Double, Double>)result.get("result")).getValue().toString();
                CapabilityValue capabilityValue = null;
                if("stderr_extensional".equals(smethod)){
                    std = std_spe;
                    capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                }else if("stderr_model".equals(smethod)){
                    Map<String,Object> computResult = statisticsService.computStdVar("bymodel",Double.valueOf(c),Double.valueOf(cfx),Double.valueOf(zxx),Double.valueOf(m));
                    std = computResult.get("result").toString();
                    capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                }else if("stderr_measurementmethod".equals(smethod)){
                    Map<String,Object> computResult = statisticsService.computStdVar("measurement",Double.valueOf(c),Double.valueOf(cfx),Double.valueOf(zxx),Double.valueOf(m));
                    std = computResult.get("result").toString();
                    capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                }else if("stderr_alg_a".equals(smethod)){
                    double[][] newMatrix = new double[][]{};
                    newMatrix[0]=matrix1[i];
                    Map<String,Object> computResult = statisticsService.ensureStdVar(newMatrix,"algorithmA");
                    std = ((Pair<Double, Double>)computResult.get("result")).getValue().toString();
                    capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                }else if("stderr_qn".equals(smethod)){
                    double[][] newMatrix = new double[][]{};
                    newMatrix[0]=matrix1[i];
                    Map<String,Object> computResult = statisticsService.ensureStdVar(newMatrix,"Qn");
                    std = computResult.get("result").toString();
                    capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                }else{
                    Map<String,Object> computResult = statisticsService.ensureStdVar(matrix1,"hampel");
                    std = computResult.get("result").toString();
                    capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                }
                capabilityValueService.insert(capabilityValue);
            }
        }else {
            trueXmethod = "由稳健方法确定-Q/Hampel方法";
            Map<String, Object> result = statisticsService.confirm(matrix1, "hampel", 0.0, 0.0);
            x = ((Pair<Double, Double>) result.get("result")).getKey().toString();
            u = ((Pair<Double, Double>) result.get("result")).getValue().toString();
            if("stderr_extensional".equals(smethod)){
                std = std_spe;
                CapabilityValue capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                capabilityValueService.insert(capabilityValue);
            }else if("stderr_model".equals(smethod)){
                Map<String,Object> computResult = statisticsService.computStdVar("bymodel",Double.valueOf(c),Double.valueOf(cfx),Double.valueOf(zxx),Double.valueOf(m));
                std = computResult.get("result").toString();
                CapabilityValue capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                capabilityValueService.insert(capabilityValue);
            }else if("stderr_measurementmethod".equals(smethod)){
                Map<String,Object> computResult = statisticsService.computStdVar("measurement",Double.valueOf(c),Double.valueOf(cfx),Double.valueOf(zxx),Double.valueOf(m));
                std = computResult.get("result").toString();
                CapabilityValue capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                capabilityValueService.insert(capabilityValue);
            }else if("stderr_alg_a".equals(smethod)){
                int len = matrix1.length;
                double[][] tmpMatrix = new double[][]{};
                for(int i=0;i<len;i++){
                    tmpMatrix[0]=matrix1[i];
                    Map<String,Object> computResult = statisticsService.ensureStdVar(tmpMatrix,"algorithmA");
                    std = ((Pair<Double, Double>)computResult.get("result")).getValue().toString();
                    CapabilityValue capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                    capabilityValueService.insert(capabilityValue);
                }
            }else if("stderr_qn".equals(smethod)){
                int len = matrix1.length;
                double[][] tmpMatrix = new double[][]{};
                for(int i=0;i<len;i++){
                    tmpMatrix[0]=matrix1[i];
                    Map<String,Object> computResult = statisticsService.ensureStdVar(tmpMatrix,"Qn");
                    std = computResult.get("result").toString();
                    CapabilityValue capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                    capabilityValueService.insert(capabilityValue);
                }
            }else{
                Map<String,Object> computResult = statisticsService.ensureStdVar(matrix1,"hampel");
                std = computResult.get("result").toString();
                CapabilityValue capabilityValue = new CapabilityValue(Integer.valueOf(id),ce.getSampleNo(),ce.getSampleName(),ce.getVariable(),ce.getUnit(),trueXmethod,trueSmethod,x,std,u);
                capabilityValueService.insert(capabilityValue);
            }
        }
        resultMap.put("status",200);
        return gson.toJson(resultMap);
    }
}

