package com.example.tr.datacollection.model;

import com.amap.api.maps.model.LatLng;

import java.util.Date;

/**
 * Created by TR on 2017/6/6.
 * 事故信息
 */

public class AcccidentData {

    private String accidencenumber;
    private Date   riQI = null;//日期
    private String city;//城市
    private String xianQu;//县区
    private String shangQuan;//商圈
    private LatLng lnglat = new LatLng(0.0,0.0);//经纬度
    private String diMingBeiZhu;//地名备注
    private String shigu;//事故1
    private String shiguType;//事故1类型
    private String shigu2;//事故2
    private String shiguType2;
    private String yanZhongCd;//严重程度
    private String carnum;//车辆数
    private String driverNums;//驾乘人数
    private String feidriverNums;//非驾乘人数
    private String ssNums;//受伤人数
    private String dieNums;//死亡人数
    private String gongjiao;//公交用途
    private String taoYi;//逃逸

    private String Weixianche="是";//危险车
    private String Weixianbz="有";//危险标志
    private String fromWeixianbz;//危险标志看出
    private String youHaiWuZhi="否";//有害物质

    public AcccidentData() {
    }

    public AcccidentData(Date riQI, String city, String xianQu, String shangQuan, LatLng lnglat,
                         String diMingBeiZhu, String shigu, String shiguType, String shigu2, String shiguType2,
                         String yanZhongCd, String carnum, String driverNums, String feidriverNums, String ssNums,
                         String dieNums, String gongjiao, String taoYi, boolean weixianche, String weixianbz,
                         String fromWeixianbz, String youHaiWuZhi) {

        this.riQI = riQI;
        this.city = city;
        this.xianQu = xianQu;
        this.shangQuan = shangQuan;
        this.lnglat = lnglat;
        this.diMingBeiZhu = diMingBeiZhu;
        this.shigu = shigu;
        this.shiguType = shiguType;
        this.shigu2 = shigu2;
        this.shiguType2 = shiguType2;
        this.yanZhongCd = yanZhongCd;
        this.carnum = carnum;
        this.driverNums = driverNums;
        this.feidriverNums = feidriverNums;
        this.ssNums = ssNums;
        this.dieNums = dieNums;
        this.gongjiao = gongjiao;
        this.taoYi = taoYi;
        if(weixianche){
            Weixianche = "否";
        }

        Weixianbz = weixianbz;
        this.fromWeixianbz = fromWeixianbz;
        this.youHaiWuZhi = youHaiWuZhi;
    }

    public AcccidentData(String number,Date riQI, String city, String xianQu, String shangQuan, LatLng lnglat,
                         String diMingBeiZhu, String shigu, String shiguType, String shigu2, String shiguType2,
                         String yanZhongCd, String carnum, String driverNums, String feidriverNums, String ssNums,
                         String dieNums, String gongjiao, String taoYi, boolean weixianche, String weixianbz,
                         String fromWeixianbz, String youHaiWuZhi) {
        this.accidencenumber = number;
        this.riQI = riQI;
        this.city = city;
        this.xianQu = xianQu;
        this.shangQuan = shangQuan;
        this.lnglat = lnglat;
        this.diMingBeiZhu = diMingBeiZhu;
        this.shigu = shigu;
        this.shiguType = shiguType;
        this.shigu2 = shigu2;
        this.shiguType2 = shiguType2;
        this.yanZhongCd = yanZhongCd;
        this.carnum = carnum;
        this.driverNums = driverNums;
        this.feidriverNums = feidriverNums;
        this.ssNums = ssNums;
        this.dieNums = dieNums;
        this.gongjiao = gongjiao;
        this.taoYi = taoYi;
        if(weixianche){
            Weixianche = "否";
        }

        Weixianbz = weixianbz;
        this.fromWeixianbz = fromWeixianbz;
        this.youHaiWuZhi = youHaiWuZhi;
    }

    public AcccidentData(String accidencenumber,Date riQI, String city, String xianQu, String shangQuan, String lnglat,
                         String diMingBeiZhu, String shigu, String shiguType, String shigu2, String shiguType2,
                         String yanZhongCd, String carnum, String driverNums, String feidriverNums, String ssNums,
                         String dieNums, String gongjiao, String taoYi, String weixianche, String weixianbz,
                         String fromWeixianbz, String youHaiWuZhi) {
        this.accidencenumber = accidencenumber;
        this.riQI = riQI;
        this.city = city;
        this.xianQu = xianQu;
        this.shangQuan = shangQuan;
        String[] latlngs = lnglat.split(",");
        this.lnglat = new LatLng(Double.valueOf(latlngs[0]),Double.valueOf(latlngs[1]));

        this.diMingBeiZhu = diMingBeiZhu;
        this.shigu = shigu;
        this.shiguType = shiguType;
        this.shigu2 = shigu2;
        this.shiguType2 = shiguType2;
        this.yanZhongCd = yanZhongCd;
        this.carnum = carnum;
        this.driverNums = driverNums;
        this.feidriverNums = feidriverNums;
        this.ssNums = ssNums;
        this.dieNums = dieNums;
        this.gongjiao = gongjiao;
        this.taoYi = taoYi;

        this.Weixianche = weixianche;

        Weixianbz = weixianbz;
        this.fromWeixianbz = fromWeixianbz;
        this.youHaiWuZhi = youHaiWuZhi;
    }

    public String getAccidencenumber() {
        return accidencenumber;
    }

    public void setAccidencenumber(String accidencenumber) {
        this.accidencenumber = accidencenumber;
    }

    public Date getRiQI() {
        return riQI;
    }

    public void setRiQI(Date riQI) {
        this.riQI = riQI;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getXianQu() {
        return xianQu;
    }

    public void setXianQu(String xianQu) {
        this.xianQu = xianQu;
    }

    public String getShangQuan() {
        return shangQuan;
    }

    public void setShangQuan(String shangQuan) {
        this.shangQuan = shangQuan;
    }

    public LatLng getLnglat() {
        return lnglat;
    }

    public String getLnglatstr() {
        return lnglat.latitude+","+lnglat.longitude;
    }
    public void setLnglat(LatLng lnglat) {
        this.lnglat = lnglat;
    }

    public String getDiMingBeiZhu() {
        return diMingBeiZhu;
    }

    public void setDiMingBeiZhu(String diMingBeiZhu) {
        this.diMingBeiZhu = diMingBeiZhu;
    }

    public String getShigu() {
        return shigu;
    }

    public void setShigu(String shigu) {
        this.shigu = shigu;
    }

    public String getShiguType() {
        return shiguType;
    }

    public void setShiguType(String shiguType) {
        this.shiguType = shiguType;
    }

    public String getShigu2() {
        return shigu2;
    }

    public void setShigu2(String shigu2) {
        this.shigu2 = shigu2;
    }

    public String getShiguType2() {
        return shiguType2;
    }

    public void setShiguType2(String shiguType2) {
        this.shiguType2 = shiguType2;
    }

    public String getYanZhongCd() {
        return yanZhongCd;
    }

    public void setYanZhongCd(String yanZhongCd) {
        this.yanZhongCd = yanZhongCd;
    }

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }

    public String getDriverNums() {
        return driverNums;
    }

    public void setDriverNums(String driverNums) {
        this.driverNums = driverNums;
    }

    public String getFeidriverNums() {
        return feidriverNums;
    }

    public void setFeidriverNums(String feidriverNums) {
        this.feidriverNums = feidriverNums;
    }

    public String getSsNums() {
        return ssNums;
    }

    public void setSsNums(String ssNums) {
        this.ssNums = ssNums;
    }

    public String getDieNums() {
        return dieNums;
    }

    public void setDieNums(String dieNums) {
        this.dieNums = dieNums;
    }

    public String getGongjiao() {
        return gongjiao;
    }

    public void setGongjiao(String gongjiao) {
        this.gongjiao = gongjiao;
    }

    public String getTaoYi() {
        return taoYi;
    }

    public void setTaoYi(String taoYi) {
        this.taoYi = taoYi;
    }

    public String isWeixianche() {
        return Weixianche;
    }

    public void setWeixianche(String weixianche) {
        Weixianche = weixianche;
    }

    public String getWeixianbz() {
        return Weixianbz;
    }

    public void setWeixianbz(String weixianbz) {
        Weixianbz = weixianbz;
    }

    public String getFromWeixianbz() {
        return fromWeixianbz;
    }

    public void setFromWeixianbz(String fromWeixianbz) {
        this.fromWeixianbz = fromWeixianbz;
    }

    public String getYouHaiWuZhi() {
        return youHaiWuZhi;
    }

    public void setYouHaiWuZhi(String youHaiWuZhi) {
        this.youHaiWuZhi = youHaiWuZhi;
    }


}
