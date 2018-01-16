package com.ejunhai.qutihuo.statistical.dto;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class StandardReviseDto {

    private List<String> timeLineData;

    private List<String> yAxisData;

    private List<List<SumarryObj>> seriesOneData;

    private List<List<SumarryObj>> seriesTwoData;

    private List<List<Integer>> seriesThreeData;

    private Map<String,List<Integer>> proviceYearData;

    public Map<String, List<Integer>> getProviceYearData() {
        return proviceYearData;
    }

    public void setProviceYearData(Map<String, List<Integer>> proviceYearData) {
        this.proviceYearData = proviceYearData;
    }

    public List<String> getTimeLineData() {
        return timeLineData;
    }

    public void setTimeLineData(List<String> timeLineData) {
        this.timeLineData = timeLineData;
    }

    public List<String> getyAxisData() {
        return yAxisData;
    }

    public void setyAxisData(List<String> yAxisData) {
        this.yAxisData = yAxisData;
    }

    public List<List<SumarryObj>> getSeriesOneData() {
        return seriesOneData;
    }

    public void setSeriesOneData(List<List<SumarryObj>> seriesOneData) {
        this.seriesOneData = seriesOneData;
    }

    public List<List<SumarryObj>> getSeriesTwoData() {
        return seriesTwoData;
    }

    public void setSeriesTwoData(List<List<SumarryObj>> seriesTwoData) {
        this.seriesTwoData = seriesTwoData;
    }

    public List<List<Integer>> getSeriesThreeData() {
        return seriesThreeData;
    }

    public void setSeriesThreeData(List<List<Integer>> seriesThreeData) {
        this.seriesThreeData = seriesThreeData;
    }

    public class SumarryObj {
        private String name;
        private Integer value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }
}
