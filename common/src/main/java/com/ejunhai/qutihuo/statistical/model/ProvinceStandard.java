package com.ejunhai.qutihuo.statistical.model;

/**
 * 地方标准信息表
 *
 * @author Leo
 * @date 2017-12-06 21:18:30
 */

public class ProvinceStandard implements Comparable{

    /**
     * ID
     */
    private String id;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 上年末累计
     */
    private Integer lastyear_cnt;

    /**
     * 昵按制、修订分-制定
     */
    private Integer bnd_zxd_zd;

    /**
     * 按制、修订分-修订
     */
    private Integer bnd_zxd_xd;

    /**
     * 按性质分-强制性
     */
    private Integer bnd_xz_qz;

    /**
     * 按性质分-推荐性
     */
    private Integer bnd_xz_tj;

    /**
     * 废止
     */
    private Integer bnd_fz;

    /**
     * 本年末累计-强制性
     */
    private Integer bnm_qz;

    /**
     * 本年末累计-推荐性
     */
    private Integer bnm_tj;

    /**
     * 省份
     */
    private String province;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getLastyear_cnt() {
        return lastyear_cnt;
    }

    public void setLastyear_cnt(Integer lastyear_cnt) {
        this.lastyear_cnt = lastyear_cnt;
    }

    public Integer getBnd_zxd_zd() {
        return bnd_zxd_zd;
    }

    public void setBnd_zxd_zd(Integer bnd_zxd_zd) {
        this.bnd_zxd_zd = bnd_zxd_zd;
    }

    public Integer getBnd_zxd_xd() {
        return bnd_zxd_xd;
    }

    public void setBnd_zxd_xd(Integer bnd_zxd_xd) {
        this.bnd_zxd_xd = bnd_zxd_xd;
    }

    public Integer getBnd_xz_qz() {
        return bnd_xz_qz;
    }

    public void setBnd_xz_qz(Integer bnd_xz_qz) {
        this.bnd_xz_qz = bnd_xz_qz;
    }

    public Integer getBnd_xz_tj() {
        return bnd_xz_tj;
    }

    public void setBnd_xz_tj(Integer bnd_xz_tj) {
        this.bnd_xz_tj = bnd_xz_tj;
    }

    public Integer getBnd_fz() {
        return bnd_fz;
    }

    public void setBnd_fz(Integer bnd_fz) {
        this.bnd_fz = bnd_fz;
    }

    public Integer getBnm_qz() {
        return bnm_qz;
    }

    public void setBnm_qz(Integer bnm_qz) {
        this.bnm_qz = bnm_qz;
    }

    public Integer getBnm_tj() {
        return bnm_tj;
    }

    public void setBnm_tj(Integer bnm_tj) {
        this.bnm_tj = bnm_tj;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public int compareTo(Object obj){
        if (!(obj instanceof ProvinceStandard))
            throw new RuntimeException("no ProvinceStandard");
        ProvinceStandard ps = (ProvinceStandard)obj;
        int result = this.getProvince().compareTo(ps.getProvince());
        return result==0?this.getYear().compareTo(ps.getYear()):result;
    }
}
