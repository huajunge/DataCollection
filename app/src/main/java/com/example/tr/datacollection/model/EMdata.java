package com.example.tr.datacollection.model;

/**
 * Created by TR on 2017/6/6.
 * 环境信息
 */

public class EMdata {
    private String emnumber;//编号
    private String Routeposition;//路面位置
    private String pengzhuangleixing;//碰撞类型
    private String liJiaoQuYu;//立交区域
    private String Jcktype;//交叉口类型
    private String Teshuposition;//特殊位置
    private String Tianqicondition;//天气状况
    private String zhaomingcondition;//照明状况
    private String luMianCondition;//路面状况
    private String luMianLev;//路面等级
    private String limitSpeed;//限速
    private String chedaowidth;//车道宽度
    private String luJianWidth;//路肩宽度
    private String bianYuanXianle;//边缘线类型
    private String zhongyanwidht;//中央带宽带
    private String zhongXinXian;//中心线类型
    private String chedaoxianbj;//车道线标记
    private String jiaotongkzlx;//交通控制类型
    private String zhuchedaoshu;//主车道数
    private String jiaochajiedaoshu;//交叉街道数
    private String WorkPlaceR;//与工作区相关
    private String Worktype;//工作区类型
    private String Havepeople;//是否存在工人
    private String xianChangZhifa;//现场执法


    public EMdata() {
    }

    public EMdata(String routeposition, String pengzhuangleixing, String liJiaoQuYu, String jcktype,
                  String teshuposition, String tianqicondition, String zhaomingcondition, String luMianCondition,
                  String luMianLev, String limitSpeed, String chedaowidth, String luJianWidth, String bianYuanXianle,
                  String zhongyanwidht, String zhongXinXian, String chedaoxianbj, String jiaotongkzlx, String zhuchedaoshu,
                  String jiaochajiedaoshu, String workPlaceR, String worktype, String havepeople, String xianChangZhifa) {
        Routeposition = routeposition;
        this.pengzhuangleixing = pengzhuangleixing;
        this.liJiaoQuYu = liJiaoQuYu;
        Jcktype = jcktype;
        Teshuposition = teshuposition;
        Tianqicondition = tianqicondition;
        this.zhaomingcondition = zhaomingcondition;
        this.luMianCondition = luMianCondition;
        this.luMianLev = luMianLev;
        this.limitSpeed = limitSpeed;
        this.chedaowidth = chedaowidth;
        this.luJianWidth = luJianWidth;
        this.bianYuanXianle = bianYuanXianle;
        this.zhongyanwidht = zhongyanwidht;
        this.zhongXinXian = zhongXinXian;
        this.chedaoxianbj = chedaoxianbj;
        this.jiaotongkzlx = jiaotongkzlx;
        this.zhuchedaoshu = zhuchedaoshu;
        this.jiaochajiedaoshu = jiaochajiedaoshu;
        WorkPlaceR = workPlaceR;
        Worktype = worktype;
        Havepeople = havepeople;
        this.xianChangZhifa = xianChangZhifa;
    }

    public String getEmnumber() {
        return emnumber;
    }

    public void setEmnumber(String emnumber) {
        this.emnumber = emnumber;
    }

    public EMdata(String emnumber, String routeposition, String pengzhuangleixing, String liJiaoQuYu, String jcktype,
                  String teshuposition, String tianqicondition, String zhaomingcondition, String luMianCondition,
                  String luMianLev, String limitSpeed, String chedaowidth, String luJianWidth, String bianYuanXianle,
                  String zhongyanwidht, String zhongXinXian, String chedaoxianbj, String jiaotongkzlx, String zhuchedaoshu,
                  String jiaochajiedaoshu, String workPlaceR, String worktype, String havepeople, String xianChangZhifa) {
        this.emnumber = emnumber;
        Routeposition = routeposition;
        this.pengzhuangleixing = pengzhuangleixing;
        this.liJiaoQuYu = liJiaoQuYu;
        Jcktype = jcktype;
        Teshuposition = teshuposition;
        Tianqicondition = tianqicondition;
        this.zhaomingcondition = zhaomingcondition;
        this.luMianCondition = luMianCondition;
        this.luMianLev = luMianLev;
        this.limitSpeed = limitSpeed;
        this.chedaowidth = chedaowidth;
        this.luJianWidth = luJianWidth;
        this.bianYuanXianle = bianYuanXianle;
        this.zhongyanwidht = zhongyanwidht;
        this.zhongXinXian = zhongXinXian;
        this.chedaoxianbj = chedaoxianbj;
        this.jiaotongkzlx = jiaotongkzlx;
        this.zhuchedaoshu = zhuchedaoshu;
        this.jiaochajiedaoshu = jiaochajiedaoshu;
        WorkPlaceR = workPlaceR;
        Worktype = worktype;
        Havepeople = havepeople;
        this.xianChangZhifa = xianChangZhifa;
    }
    public String getRouteposition() {
        return Routeposition;
    }

    public void setRouteposition(String routeposition) {
        Routeposition = routeposition;
    }

    public String getPengzhuangleixing() {
        return pengzhuangleixing;
    }

    public void setPengzhuangleixing(String pengzhuangleixing) {
        this.pengzhuangleixing = pengzhuangleixing;
    }

    public String getLiJiaoQuYu() {
        return liJiaoQuYu;
    }

    public void setLiJiaoQuYu(String liJiaoQuYu) {
        this.liJiaoQuYu = liJiaoQuYu;
    }

    public String getJcktype() {
        return Jcktype;
    }

    public void setJcktype(String jcktype) {
        Jcktype = jcktype;
    }

    public String getTeshuposition() {
        return Teshuposition;
    }

    public void setTeshuposition(String teshuposition) {
        Teshuposition = teshuposition;
    }

    public String getTianqicondition() {
        return Tianqicondition;
    }

    public void setTianqicondition(String tianqicondition) {
        Tianqicondition = tianqicondition;
    }

    public String getZhaomingcondition() {
        return zhaomingcondition;
    }

    public void setZhaomingcondition(String zhaomingcondition) {
        this.zhaomingcondition = zhaomingcondition;
    }

    public String getLuMianCondition() {
        return luMianCondition;
    }

    public void setLuMianCondition(String luMianCondition) {
        this.luMianCondition = luMianCondition;
    }

    public String getLuMianLev() {
        return luMianLev;
    }

    public void setLuMianLev(String luMianLev) {
        this.luMianLev = luMianLev;
    }

    public String getLimitSpeed() {
        return limitSpeed;
    }

    public void setLimitSpeed(String limitSpeed) {
        this.limitSpeed = limitSpeed;
    }

    public String getChedaowidth() {
        return chedaowidth;
    }

    public void setChedaowidth(String chedaowidth) {
        this.chedaowidth = chedaowidth;
    }

    public String getLuJianWidth() {
        return luJianWidth;
    }

    public void setLuJianWidth(String luJianWidth) {
        this.luJianWidth = luJianWidth;
    }

    public String getBianYuanXianle() {
        return bianYuanXianle;
    }

    public void setBianYuanXianle(String bianYuanXianle) {
        this.bianYuanXianle = bianYuanXianle;
    }

    public String getZhongyanwidht() {
        return zhongyanwidht;
    }

    public void setZhongyanwidht(String zhongyanwidht) {
        this.zhongyanwidht = zhongyanwidht;
    }

    public String getZhongXinXian() {
        return zhongXinXian;
    }

    public void setZhongXinXian(String zhongXinXian) {
        this.zhongXinXian = zhongXinXian;
    }

    public String getChedaoxianbj() {
        return chedaoxianbj;
    }

    public void setChedaoxianbj(String chedaoxianbj) {
        this.chedaoxianbj = chedaoxianbj;
    }

    public String getJiaotongkzlx() {
        return jiaotongkzlx;
    }

    public void setJiaotongkzlx(String jiaotongkzlx) {
        this.jiaotongkzlx = jiaotongkzlx;
    }

    public String getZhuchedaoshu() {
        return zhuchedaoshu;
    }

    public void setZhuchedaoshu(String zhuchedaoshu) {
        this.zhuchedaoshu = zhuchedaoshu;
    }

    public String getJiaochajiedaoshu() {
        return jiaochajiedaoshu;
    }

    public void setJiaochajiedaoshu(String jiaochajiedaoshu) {
        this.jiaochajiedaoshu = jiaochajiedaoshu;
    }

    public String getWorkPlaceR() {
        return WorkPlaceR;
    }

    public void setWorkPlaceR(String workPlaceR) {
        WorkPlaceR = workPlaceR;
    }

    public String getWorktype() {
        return Worktype;
    }

    public void setWorktype(String worktype) {
        Worktype = worktype;
    }

    public String getHavepeople() {
        return Havepeople;
    }

    public void setHavepeople(String havepeople) {
        Havepeople = havepeople;
    }

    public String getXianChangZhifa() {
        return xianChangZhifa;
    }

    public void setXianChangZhifa(String xianChangZhifa) {
        this.xianChangZhifa = xianChangZhifa;
    }

    @Override
    public String toString() {
        return "EMdata{" +
                "Routeposition='" + Routeposition + '\'' +
                ", pengzhuangleixing='" + pengzhuangleixing + '\'' +
                ", liJiaoQuYu='" + liJiaoQuYu + '\'' +
                ", Jcktype='" + Jcktype + '\'' +
                ", Teshuposition='" + Teshuposition + '\'' +
                ", Tianqicondition='" + Tianqicondition + '\'' +
                ", zhaomingcondition='" + zhaomingcondition + '\'' +
                ", luMianCondition='" + luMianCondition + '\'' +
                ", luMianLev='" + luMianLev + '\'' +
                ", limitSpeed='" + limitSpeed + '\'' +
                ", chedaowidth='" + chedaowidth + '\'' +
                ", luJianWidth='" + luJianWidth + '\'' +
                ", bianYuanXianle='" + bianYuanXianle + '\'' +
                ", zhongyanwidht='" + zhongyanwidht + '\'' +
                ", zhongXinXian='" + zhongXinXian + '\'' +
                ", chedaoxianbj='" + chedaoxianbj + '\'' +
                ", jiaotongkzlx='" + jiaotongkzlx + '\'' +
                ", zhuchedaoshu='" + zhuchedaoshu + '\'' +
                ", jiaochajiedaoshu='" + jiaochajiedaoshu + '\'' +
                ", WorkPlaceR='" + WorkPlaceR + '\'' +
                ", Worktype='" + Worktype + '\'' +
                ", Havepeople='" + Havepeople + '\'' +
                ", xianChangZhifa='" + xianChangZhifa + '\'' +
                '}';
    }
}
