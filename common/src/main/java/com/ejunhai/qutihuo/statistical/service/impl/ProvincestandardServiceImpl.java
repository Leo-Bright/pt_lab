package com.ejunhai.qutihuo.statistical.service.impl;

import com.ejunhai.qutihuo.statistical.dao.ProvinceStandardMapper;
import com.ejunhai.qutihuo.statistical.dto.StandardReviseDto;
import com.ejunhai.qutihuo.statistical.model.ProvinceStandard;
import com.ejunhai.qutihuo.statistical.service.ProvinceStandardService;
import com.sun.xml.internal.ws.util.QNameMap;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;

/**
 * SystemUser Service 实现类
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:22:36
 * 
 */
@Service("provinceStandardService")
public class ProvincestandardServiceImpl implements ProvinceStandardService {

	@Resource
	private ProvinceStandardMapper provinceStandardMapper;

	@Override
	public StandardReviseDto acquireStandardRevise(){
		StandardReviseDto standardReviseDto = new StandardReviseDto();

		List<Integer> years = provinceStandardMapper.getDistinctYear();		//step timeLineData
		standardReviseDto.setTimeLineData(timeLineData(years));

		List<ProvinceStandard> nationalStatistics = provinceStandardMapper.readNationalStatistics();		//step SeriesData 1,2
		Map<Integer,ProvinceStandard> yearNationalStatistics = mapping4yearNation(nationalStatistics);
		Map<Integer,List<List<StandardReviseDto.SumarryObj>>> seriesData = seriesData(yearNationalStatistics,years);
		standardReviseDto.setSeriesOneData(seriesData.get(1));
		standardReviseDto.setSeriesTwoData(seriesData.get(2));

		List<ProvinceStandard> provinceStatistics = provinceStandardMapper.readProvinceStatistics();		//step SeriesData 3
		Map<Integer,Set<ProvinceStandard>> yearProvinceStatistics = mapping4yearProvince(provinceStatistics);
		List<List<Integer>> provinceStatisticsList = mapping4List(yearProvinceStatistics,years);
		standardReviseDto.setSeriesThreeData(provinceStatisticsList);

		standardReviseDto.setyAxisData(set2List(yAxisData(provinceStatistics)));			//step yAxisData

		standardReviseDto.setProviceYearData(provinceYearData(mapping4provinceYear(provinceStatistics)));			//step ProvinceYearData


		return standardReviseDto;
	}
	private Set<String> yAxisData(List<ProvinceStandard> provinceStandardList){
		TreeSet<String> yAxisData = new TreeSet<String>();
		for(ProvinceStandard provinceStandard:provinceStandardList){
			yAxisData.add(provinceStandard.getProvince());
		}
/*		yAxisData.add("安徽省");
		yAxisData.add("四川省");
		yAxisData.add("江苏省");
		yAxisData.add("山西省");
		yAxisData.add("广西壮族自治区");
		yAxisData.add("山东省");
		yAxisData.add("辽宁省");
		yAxisData.add("黑龙江省");
		yAxisData.add("吉林省");
		yAxisData.add("河南省");
		yAxisData.add("广东省");
		yAxisData.add("新疆维吾尔自治区");
		yAxisData.add("甘肃省");
		yAxisData.add("宁夏回族自治区");
		yAxisData.add("内蒙古自治区");
		yAxisData.add("河北省");
		yAxisData.add("天津市");
		yAxisData.add("北京市");
		yAxisData.add("湖南省");
		yAxisData.add("湖北省");
		yAxisData.add("贵州省");
		yAxisData.add("云南省");
		yAxisData.add("福建省");
		yAxisData.add("重庆市");
		yAxisData.add("海南省");
		yAxisData.add("上海市");
		yAxisData.add("陕西省");
		yAxisData.add("青海省");
		yAxisData.add("江西省");
		yAxisData.add("浙江省");
		yAxisData.add("西藏自治区");*/
		return yAxisData;
	}

	List<String> timeLineData(List<Integer> years){
		List<String> result = new ArrayList<String>();
		for(Integer year:years){
			result.add(String.valueOf(year)+"-01-01");
		}
		return result;
	}

	Map<Integer,List<List<StandardReviseDto.SumarryObj>>> seriesData(Map<Integer,ProvinceStandard> yearNationalStatistics, List<Integer> years){
		Map<Integer,List<List<StandardReviseDto.SumarryObj>>> result = new HashMap<Integer, List<List<StandardReviseDto.SumarryObj>>>();
		StandardReviseDto standardReviseDto = new StandardReviseDto();
		List<List<StandardReviseDto.SumarryObj>> one = new LinkedList<List<StandardReviseDto.SumarryObj>>();
		List<List<StandardReviseDto.SumarryObj>> two = new LinkedList<List<StandardReviseDto.SumarryObj>>();
		for(Integer year:years){
			List<StandardReviseDto.SumarryObj> yearDataOne = new LinkedList<StandardReviseDto.SumarryObj>();
			StandardReviseDto.SumarryObj zdObj = standardReviseDto.new SumarryObj();
			ProvinceStandard yearStatistics = yearNationalStatistics.get(year);
			zdObj.setName("制订");
			zdObj.setValue(yearStatistics.getBnd_zxd_zd());
			yearDataOne.add(zdObj);
			StandardReviseDto.SumarryObj xdObj = standardReviseDto.new SumarryObj();
			xdObj.setName("修订");
			xdObj.setValue(yearStatistics.getBnd_zxd_xd());
			yearDataOne.add(xdObj);

			List<StandardReviseDto.SumarryObj> yearDataTwo = new LinkedList<StandardReviseDto.SumarryObj>();
			StandardReviseDto.SumarryObj tjObj = standardReviseDto.new SumarryObj();
			tjObj.setName("推荐");
			tjObj.setValue(yearStatistics.getBnd_xz_tj());
			yearDataTwo.add(tjObj);
			StandardReviseDto.SumarryObj qzObj = standardReviseDto.new SumarryObj();
			qzObj.setName("强制");
			qzObj.setValue(yearStatistics.getBnd_xz_qz());
			yearDataTwo.add(qzObj);

			one.add(yearDataOne);
			two.add(yearDataTwo);
		}
		result.put(1,one);
		result.put(2,two);
		return result;
	}

	Map<String,List<Integer>> provinceYearData(Map<String,List<ProvinceStandard>> provinceYearMap){
		Map<String,List<Integer>> result = new HashMap<String, List<Integer>>();
		Set<Map.Entry<String,List<ProvinceStandard>>> entries = provinceYearMap.entrySet();
		Iterator<Map.Entry<String,List<ProvinceStandard>>> it = entries.iterator();
		while(it.hasNext()){
			Map.Entry<String,List<ProvinceStandard>> entry = it.next();
			result.put(entry.getKey(),mappingPS2Num(entry.getValue()));
		}
		return result;
	}

	List<Integer> mappingPS2Num(List<ProvinceStandard> provinceStandardsList){
		List<Integer> result = new ArrayList<Integer>();
		for(ProvinceStandard standard:provinceStandardsList){
			int xdNum = standard.getBnd_zxd_xd();
			int zdNum = standard.getBnd_zxd_zd();
			result.add(xdNum+zdNum);
		}
		return result;
	}

	Map<Integer,ProvinceStandard> mapping4yearNation(List<ProvinceStandard> standardList) {
		Map<Integer, ProvinceStandard> result = new HashMap<Integer, ProvinceStandard>();
		for (ProvinceStandard standard : standardList) {
			result.put(standard.getYear(), standard);
		}
		return result;

	}

	Map<Integer,Set<ProvinceStandard>> mapping4yearProvince(List<ProvinceStandard> standardList){
		Map<Integer,Set<ProvinceStandard>> result = new HashMap<Integer,Set<ProvinceStandard>>();
		for (ProvinceStandard standard:standardList) {
			Integer K = standard.getYear();
			Set<ProvinceStandard> V = result.get(K);
			if(null==V){
				V = new TreeSet<ProvinceStandard>();
				V.add(standard);
				result.put(K,V);

			}else{
				result.get(K).add(standard);
			}
		}
		return result;
	}

	Map<String,List<ProvinceStandard>> mapping4provinceYear(List<ProvinceStandard> standardList){
		Map<String,Set<ProvinceStandard>> tmp = new HashMap<String,Set<ProvinceStandard>>();
		Map<String,List<ProvinceStandard>> result = new HashMap<String,List<ProvinceStandard>>();
		for (ProvinceStandard standard:standardList) {
			String K = standard.getProvince();
			Set<ProvinceStandard> V = tmp.get(K);
			if(null==V){
				V = new TreeSet<ProvinceStandard>();
				V.add(standard);
				tmp.put(K,V);

			}else{
				tmp.get(K).add(standard);
			}
		}
		Set<Map.Entry<String,Set<ProvinceStandard>>> entries = tmp.entrySet();
		Iterator<Map.Entry<String,Set<ProvinceStandard>>> it = entries.iterator();
		while(it.hasNext()){
			Map.Entry<String,Set<ProvinceStandard>> entry = it.next();
			result.put(entry.getKey(),set2List(entry.getValue()));
		}
		return result;
	}

	List<List<Integer>> mapping4List(Map<Integer,Set<ProvinceStandard>> yearProvinceMap,List<Integer> years){
		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
		for(Integer year:years){
			List<Integer> list = new ArrayList<Integer>();
			Set<ProvinceStandard> provinceStandardSet = yearProvinceMap.get(year);
			Iterator<ProvinceStandard> iterator = provinceStandardSet.iterator();
			while(iterator.hasNext()){
				ProvinceStandard standard = iterator.next();
				int xdNum = standard.getBnd_zxd_xd();
				int zdNum = standard.getBnd_zxd_zd();
				list.add(xdNum+zdNum);
			}
			resultList.add(list);
		}
		return resultList;
	}

	<T> List<T> set2List(Set<T> set){
		List<T> resultList = new ArrayList<T>();
		Iterator<T> iterator = set.iterator();
		while(iterator.hasNext()){
			T ele = iterator.next();
			resultList.add(ele);
		}
		return resultList;
	}
}
