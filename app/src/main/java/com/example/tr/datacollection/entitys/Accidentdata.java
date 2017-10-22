package com.example.tr.datacollection.entitys;


import android.util.Log;

import com.example.tr.datacollection.model.AcccidentData;
import com.example.tr.datacollection.util.NoNumInString;

public class Accidentdata {

    public Accidentdata() {

    }
    public Accidentdata(AcccidentData accidentData) {

            this.accidencnumber = accidentData.getAccidencenumber();
        try {
            this.riqi = "" + accidentData.getRiQI().getTime() + "";//日期
        }catch (Exception e) {
            Log.i("httptest","Exception:初始化错误"+e.getMessage());
        }
            this.city = NoNumInString.start(accidentData.getCity());//城市
            this.xianqu = NoNumInString.start(accidentData.getXianQu());//县区
            this.shangquan = NoNumInString.start(accidentData.getShangQuan());//商圈
            this.lng =accidentData.getLnglat().longitude;//经纬度
            this.lat = accidentData.getLnglat().latitude;//经纬度
            this.dimingbeizhu = NoNumInString.start(accidentData.getDiMingBeiZhu());//地名备注
            this.shigu = NoNumInString.start(accidentData.getShigu());//事故1
            this.shigutype = NoNumInString.start(accidentData.getShiguType());//事故1类型
            this.shigu2 = NoNumInString.start(accidentData.getShigu2());//事故2
            this.shigutype2 = NoNumInString.start(accidentData.getShiguType2());
            this.yanzhongcd = NoNumInString.start(accidentData.getYanZhongCd());//严重程度
            this.carnum = NoNumInString.start(accidentData.getCarnum());//车辆数
            this.drivernums = NoNumInString.start(accidentData.getDriverNums());//驾乘人数
            this.feidrivernums = NoNumInString.start(accidentData.getFeidriverNums());//非驾乘人数
            this.ssnums = NoNumInString.start(accidentData.getSsNums());//受伤人数
            this.dienums = NoNumInString.start(accidentData.getDieNums());//死亡人数
            this.gongjiao = NoNumInString.start(accidentData.getGongjiao());//公交用途
            this.taoyi = NoNumInString.start(accidentData.getTaoYi());//逃逸
            this.weixianche = NoNumInString.start(accidentData.isWeixianche());//危险车
            this.weixianbz = NoNumInString.start(accidentData.getWeixianbz());//危险标志
            this.fromweixianbz = NoNumInString.start(accidentData.getFromWeixianbz());//危险标志看出
            this.youhaiwuzhi = NoNumInString.start(accidentData.getYouHaiWuZhi());//有害物质
    }
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata._id
     *
     * @mbggenerated
     */


    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.lat
     *
     * @mbggenerated
     */
    private Double lat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.lng
     *
     * @mbggenerated
     */
    private Double lng;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.is_workday
     *
     * @mbggenerated
     */
    private Boolean isWorkday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.accidencnumber
     *
     * @mbggenerated
     */
    private String accidencnumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.riQI
     *
     * @mbggenerated
     */
    private String riqi;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.city
     *
     * @mbggenerated
     */
    private String city;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.xianQu
     *
     * @mbggenerated
     */
    private String xianqu;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.shangQuan
     *
     * @mbggenerated
     */
    private String shangquan;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.diMingBeiZhu
     *
     * @mbggenerated
     */
    private String dimingbeizhu;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.shigu
     *
     * @mbggenerated
     */
    private String shigu;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.shiguType
     *
     * @mbggenerated
     */
    private String shigutype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.shigu2
     *
     * @mbggenerated
     */
    private String shigu2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.shiguType2
     *
     * @mbggenerated
     */
    private String shigutype2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.yanZhongCd
     *
     * @mbggenerated
     */
    private String yanzhongcd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.carnum
     *
     * @mbggenerated
     */
    private String carnum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.driverNums
     *
     * @mbggenerated
     */
    private String drivernums;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.feidriverNums
     *
     * @mbggenerated
     */
    private String feidrivernums;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.ssNums
     *
     * @mbggenerated
     */
    private String ssnums;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.dieNums
     *
     * @mbggenerated
     */
    private String dienums;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.gongjiao
     *
     * @mbggenerated
     */
    private String gongjiao;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.taoYi
     *
     * @mbggenerated
     */
    private String taoyi;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.Weixianche
     *
     * @mbggenerated
     */
    private String weixianche;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.Weixianbz
     *
     * @mbggenerated
     */
    private String weixianbz;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.fromWeixianbz
     *
     * @mbggenerated
     */
    private String fromweixianbz;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.youHaiWuZhi
     *
     * @mbggenerated
     */
    private String youhaiwuzhi;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata._id
     *
     * @return the value of accidentdata._id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata._id
     *
     * @param id the value for accidentdata._id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.lat
     *
     * @return the value of accidentdata.lat
     *
     * @mbggenerated
     */
    public Double getLat() {
        return lat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.lat
     *
     * @param lat the value for accidentdata.lat
     *
     * @mbggenerated
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.lng
     *
     * @return the value of accidentdata.lng
     *
     * @mbggenerated
     */
    public Double getLng() {
        return lng;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.lng
     *
     * @param lng the value for accidentdata.lng
     *
     * @mbggenerated
     */
    public void setLng(Double lng) {
        this.lng = lng;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.is_workday
     *
     * @return the value of accidentdata.is_workday
     *
     * @mbggenerated
     */
    public Boolean getIsWorkday() {
        return isWorkday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.is_workday
     *
     * @param isWorkday the value for accidentdata.is_workday
     *
     * @mbggenerated
     */
    public void setIsWorkday(Boolean isWorkday) {
        this.isWorkday = isWorkday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.accidencnumber
     *
     * @return the value of accidentdata.accidencnumber
     *
     * @mbggenerated
     */
    public String getAccidencnumber() {
        return accidencnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.accidencnumber
     *
     * @param accidencnumber the value for accidentdata.accidencnumber
     *
     * @mbggenerated
     */
    public void setAccidencnumber(String accidencnumber) {
        this.accidencnumber = accidencnumber == null ? null : accidencnumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.riQI
     *
     * @return the value of accidentdata.riQI
     *
     * @mbggenerated
     */
    public String getRiqi() {
        return riqi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.riQI
     *
     * @param riqi the value for accidentdata.riQI
     *
     * @mbggenerated
     */
    public void setRiqi(String riqi) {
        this.riqi = riqi == null ? null : riqi.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.city
     *
     * @return the value of accidentdata.city
     *
     * @mbggenerated
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.city
     *
     * @param city the value for accidentdata.city
     *
     * @mbggenerated
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.xianQu
     *
     * @return the value of accidentdata.xianQu
     *
     * @mbggenerated
     */
    public String getXianqu() {
        return xianqu;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.xianQu
     *
     * @param xianqu the value for accidentdata.xianQu
     *
     * @mbggenerated
     */
    public void setXianqu(String xianqu) {
        this.xianqu = xianqu == null ? null : xianqu.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.shangQuan
     *
     * @return the value of accidentdata.shangQuan
     *
     * @mbggenerated
     */
    public String getShangquan() {
        return shangquan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.shangQuan
     *
     * @param shangquan the value for accidentdata.shangQuan
     *
     * @mbggenerated
     */
    public void setShangquan(String shangquan) {
        this.shangquan = shangquan == null ? null : shangquan.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.diMingBeiZhu
     *
     * @return the value of accidentdata.diMingBeiZhu
     *
     * @mbggenerated
     */
    public String getDimingbeizhu() {
        return dimingbeizhu;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.diMingBeiZhu
     *
     * @param dimingbeizhu the value for accidentdata.diMingBeiZhu
     *
     * @mbggenerated
     */
    public void setDimingbeizhu(String dimingbeizhu) {
        this.dimingbeizhu = dimingbeizhu == null ? null : dimingbeizhu.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.shigu
     *
     * @return the value of accidentdata.shigu
     *
     * @mbggenerated
     */
    public String getShigu() {
        return shigu;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.shigu
     *
     * @param shigu the value for accidentdata.shigu
     *
     * @mbggenerated
     */
    public void setShigu(String shigu) {
        this.shigu = shigu == null ? null : shigu.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.shiguType
     *
     * @return the value of accidentdata.shiguType
     *
     * @mbggenerated
     */
    public String getShigutype() {
        return shigutype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.shiguType
     *
     * @param shigutype the value for accidentdata.shiguType
     *
     * @mbggenerated
     */
    public void setShigutype(String shigutype) {
        this.shigutype = shigutype == null ? null : shigutype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.shigu2
     *
     * @return the value of accidentdata.shigu2
     *
     * @mbggenerated
     */
    public String getShigu2() {
        return shigu2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.shigu2
     *
     * @param shigu2 the value for accidentdata.shigu2
     *
     * @mbggenerated
     */
    public void setShigu2(String shigu2) {
        this.shigu2 = shigu2 == null ? null : shigu2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.shiguType2
     *
     * @return the value of accidentdata.shiguType2
     *
     * @mbggenerated
     */
    public String getShigutype2() {
        return shigutype2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.shiguType2
     *
     * @param shigutype2 the value for accidentdata.shiguType2
     *
     * @mbggenerated
     */
    public void setShigutype2(String shigutype2) {
        this.shigutype2 = shigutype2 == null ? null : shigutype2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.yanZhongCd
     *
     * @return the value of accidentdata.yanZhongCd
     *
     * @mbggenerated
     */
    public String getYanzhongcd() {
        return yanzhongcd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.yanZhongCd
     *
     * @param yanzhongcd the value for accidentdata.yanZhongCd
     *
     * @mbggenerated
     */
    public void setYanzhongcd(String yanzhongcd) {
        this.yanzhongcd = yanzhongcd == null ? null : yanzhongcd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.carnum
     *
     * @return the value of accidentdata.carnum
     *
     * @mbggenerated
     */
    public String getCarnum() {
        return carnum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.carnum
     *
     * @param carnum the value for accidentdata.carnum
     *
     * @mbggenerated
     */
    public void setCarnum(String carnum) {
        this.carnum = carnum == null ? null : carnum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.driverNums
     *
     * @return the value of accidentdata.driverNums
     *
     * @mbggenerated
     */
    public String getDrivernums() {
        return drivernums;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.driverNums
     *
     * @param drivernums the value for accidentdata.driverNums
     *
     * @mbggenerated
     */
    public void setDrivernums(String drivernums) {
        this.drivernums = drivernums == null ? null : drivernums.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.feidriverNums
     *
     * @return the value of accidentdata.feidriverNums
     *
     * @mbggenerated
     */
    public String getFeidrivernums() {
        return feidrivernums;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.feidriverNums
     *
     * @param feidrivernums the value for accidentdata.feidriverNums
     *
     * @mbggenerated
     */
    public void setFeidrivernums(String feidrivernums) {
        this.feidrivernums = feidrivernums == null ? null : feidrivernums.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.ssNums
     *
     * @return the value of accidentdata.ssNums
     *
     * @mbggenerated
     */
    public String getSsnums() {
        return ssnums;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.ssNums
     *
     * @param ssnums the value for accidentdata.ssNums
     *
     * @mbggenerated
     */
    public void setSsnums(String ssnums) {
        this.ssnums = ssnums == null ? null : ssnums.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.dieNums
     *
     * @return the value of accidentdata.dieNums
     *
     * @mbggenerated
     */
    public String getDienums() {
        return dienums;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.dieNums
     *
     * @param dienums the value for accidentdata.dieNums
     *
     * @mbggenerated
     */
    public void setDienums(String dienums) {
        this.dienums = dienums == null ? null : dienums.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.gongjiao
     *
     * @return the value of accidentdata.gongjiao
     *
     * @mbggenerated
     */
    public String getGongjiao() {
        return gongjiao;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.gongjiao
     *
     * @param gongjiao the value for accidentdata.gongjiao
     *
     * @mbggenerated
     */
    public void setGongjiao(String gongjiao) {
        this.gongjiao = gongjiao == null ? null : gongjiao.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.taoYi
     *
     * @return the value of accidentdata.taoYi
     *
     * @mbggenerated
     */
    public String getTaoyi() {
        return taoyi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.taoYi
     *
     * @param taoyi the value for accidentdata.taoYi
     *
     * @mbggenerated
     */
    public void setTaoyi(String taoyi) {
        this.taoyi = taoyi == null ? null : taoyi.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.Weixianche
     *
     * @return the value of accidentdata.Weixianche
     *
     * @mbggenerated
     */
    public String getWeixianche() {
        return weixianche;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.Weixianche
     *
     * @param weixianche the value for accidentdata.Weixianche
     *
     * @mbggenerated
     */
    public void setWeixianche(String weixianche) {
        this.weixianche = weixianche == null ? null : weixianche.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.Weixianbz
     *
     * @return the value of accidentdata.Weixianbz
     *
     * @mbggenerated
     */
    public String getWeixianbz() {
        return weixianbz;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.Weixianbz
     *
     * @param weixianbz the value for accidentdata.Weixianbz
     *
     * @mbggenerated
     */
    public void setWeixianbz(String weixianbz) {
        this.weixianbz = weixianbz == null ? null : weixianbz.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.fromWeixianbz
     *
     * @return the value of accidentdata.fromWeixianbz
     *
     * @mbggenerated
     */
    public String getFromweixianbz() {
        return fromweixianbz;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.fromWeixianbz
     *
     * @param fromweixianbz the value for accidentdata.fromWeixianbz
     *
     * @mbggenerated
     */
    public void setFromweixianbz(String fromweixianbz) {
        this.fromweixianbz = fromweixianbz == null ? null : fromweixianbz.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accidentdata.youHaiWuZhi
     *
     * @return the value of accidentdata.youHaiWuZhi
     *
     * @mbggenerated
     */
    public String getYouhaiwuzhi() {
        return youhaiwuzhi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accidentdata.youHaiWuZhi
     *
     * @param youhaiwuzhi the value for accidentdata.youHaiWuZhi
     *
     * @mbggenerated
     */
    public void setYouhaiwuzhi(String youhaiwuzhi) {
        this.youhaiwuzhi = youhaiwuzhi == null ? null : youhaiwuzhi.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table accidentdata
     *
     * @mbggenerated
     */


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table accidentdata
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Accidentdata other = (Accidentdata) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLat() == null ? other.getLat() == null : this.getLat().equals(other.getLat()))
            && (this.getLng() == null ? other.getLng() == null : this.getLng().equals(other.getLng()))
            && (this.getIsWorkday() == null ? other.getIsWorkday() == null : this.getIsWorkday().equals(other.getIsWorkday()))
            && (this.getAccidencnumber() == null ? other.getAccidencnumber() == null : this.getAccidencnumber().equals(other.getAccidencnumber()))
            && (this.getRiqi() == null ? other.getRiqi() == null : this.getRiqi().equals(other.getRiqi()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getXianqu() == null ? other.getXianqu() == null : this.getXianqu().equals(other.getXianqu()))
            && (this.getShangquan() == null ? other.getShangquan() == null : this.getShangquan().equals(other.getShangquan()))
            && (this.getDimingbeizhu() == null ? other.getDimingbeizhu() == null : this.getDimingbeizhu().equals(other.getDimingbeizhu()))
            && (this.getShigu() == null ? other.getShigu() == null : this.getShigu().equals(other.getShigu()))
            && (this.getShigutype() == null ? other.getShigutype() == null : this.getShigutype().equals(other.getShigutype()))
            && (this.getShigu2() == null ? other.getShigu2() == null : this.getShigu2().equals(other.getShigu2()))
            && (this.getShigutype2() == null ? other.getShigutype2() == null : this.getShigutype2().equals(other.getShigutype2()))
            && (this.getYanzhongcd() == null ? other.getYanzhongcd() == null : this.getYanzhongcd().equals(other.getYanzhongcd()))
            && (this.getCarnum() == null ? other.getCarnum() == null : this.getCarnum().equals(other.getCarnum()))
            && (this.getDrivernums() == null ? other.getDrivernums() == null : this.getDrivernums().equals(other.getDrivernums()))
            && (this.getFeidrivernums() == null ? other.getFeidrivernums() == null : this.getFeidrivernums().equals(other.getFeidrivernums()))
            && (this.getSsnums() == null ? other.getSsnums() == null : this.getSsnums().equals(other.getSsnums()))
            && (this.getDienums() == null ? other.getDienums() == null : this.getDienums().equals(other.getDienums()))
            && (this.getGongjiao() == null ? other.getGongjiao() == null : this.getGongjiao().equals(other.getGongjiao()))
            && (this.getTaoyi() == null ? other.getTaoyi() == null : this.getTaoyi().equals(other.getTaoyi()))
            && (this.getWeixianche() == null ? other.getWeixianche() == null : this.getWeixianche().equals(other.getWeixianche()))
            && (this.getWeixianbz() == null ? other.getWeixianbz() == null : this.getWeixianbz().equals(other.getWeixianbz()))
            && (this.getFromweixianbz() == null ? other.getFromweixianbz() == null : this.getFromweixianbz().equals(other.getFromweixianbz()))
            && (this.getYouhaiwuzhi() == null ? other.getYouhaiwuzhi() == null : this.getYouhaiwuzhi().equals(other.getYouhaiwuzhi()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table accidentdata
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLat() == null) ? 0 : getLat().hashCode());
        result = prime * result + ((getLng() == null) ? 0 : getLng().hashCode());
        result = prime * result + ((getIsWorkday() == null) ? 0 : getIsWorkday().hashCode());
        result = prime * result + ((getAccidencnumber() == null) ? 0 : getAccidencnumber().hashCode());
        result = prime * result + ((getRiqi() == null) ? 0 : getRiqi().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getXianqu() == null) ? 0 : getXianqu().hashCode());
        result = prime * result + ((getShangquan() == null) ? 0 : getShangquan().hashCode());
        result = prime * result + ((getDimingbeizhu() == null) ? 0 : getDimingbeizhu().hashCode());
        result = prime * result + ((getShigu() == null) ? 0 : getShigu().hashCode());
        result = prime * result + ((getShigutype() == null) ? 0 : getShigutype().hashCode());
        result = prime * result + ((getShigu2() == null) ? 0 : getShigu2().hashCode());
        result = prime * result + ((getShigutype2() == null) ? 0 : getShigutype2().hashCode());
        result = prime * result + ((getYanzhongcd() == null) ? 0 : getYanzhongcd().hashCode());
        result = prime * result + ((getCarnum() == null) ? 0 : getCarnum().hashCode());
        result = prime * result + ((getDrivernums() == null) ? 0 : getDrivernums().hashCode());
        result = prime * result + ((getFeidrivernums() == null) ? 0 : getFeidrivernums().hashCode());
        result = prime * result + ((getSsnums() == null) ? 0 : getSsnums().hashCode());
        result = prime * result + ((getDienums() == null) ? 0 : getDienums().hashCode());
        result = prime * result + ((getGongjiao() == null) ? 0 : getGongjiao().hashCode());
        result = prime * result + ((getTaoyi() == null) ? 0 : getTaoyi().hashCode());
        result = prime * result + ((getWeixianche() == null) ? 0 : getWeixianche().hashCode());
        result = prime * result + ((getWeixianbz() == null) ? 0 : getWeixianbz().hashCode());
        result = prime * result + ((getFromweixianbz() == null) ? 0 : getFromweixianbz().hashCode());
        result = prime * result + ((getYouhaiwuzhi() == null) ? 0 : getYouhaiwuzhi().hashCode());
        return result;
    }
}