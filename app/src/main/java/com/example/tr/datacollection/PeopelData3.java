package com.example.tr.datacollection;

/**
 * Created by TR on 2017/6/15.
 */

public class PeopelData3 {
    private String number;
    private String SPname;
    private String SPxingbie;
    private String SPshenfentype;
    private String SPrenyuantype;

    private String SPsschengdu;
    private String  phonenum;

    private String SPsgfssxingwei;
    private String SPsgfsqxingwei;
    private String SPsgfsszhuangtai;
    private String SPaqsbsyzhuangtai;
    private String SPsgfssposition;

    private String SPcheliangxunhao;

    private String SPyinjiu;
    private String SPduPingLeiXing;
    private String SPceshizhuangtai;
    private String SPceshitype;
    private String SPceshiresult;

    public PeopelData3() {

    }

    public PeopelData3(String SPname, String SPxingbie, String SPshenfentype,
                        String SPrenyuantype, String SPsschengdu, String phonenum,
                        String SPsgfssxingwei, String SPsgfsqxingwei, String SPsgfsszhuangtai,
                        String SPaqsbsyzhuangtai, String SPsgfssposition, String SPcheliangxunhao,
                        String SPyinjiu, String SPduPingLeiXing, String SPceshizhuangtai,
                        String SPceshitype, String SPceshiresult) {
        this.SPname = SPname;
        this.SPxingbie = SPxingbie;
        this.SPshenfentype = SPshenfentype;
        this.SPrenyuantype = SPrenyuantype;
        this.SPsschengdu = SPsschengdu;
        this.phonenum = phonenum;
        this.SPsgfssxingwei = SPsgfssxingwei;
        this.SPsgfsqxingwei = SPsgfsqxingwei;
        this.SPsgfsszhuangtai = SPsgfsszhuangtai;
        this.SPaqsbsyzhuangtai = SPaqsbsyzhuangtai;
        this.SPsgfssposition = SPsgfssposition;
        this.SPcheliangxunhao = SPcheliangxunhao;
        this.SPyinjiu = SPyinjiu;
        this.SPduPingLeiXing = SPduPingLeiXing;
        this.SPceshizhuangtai = SPceshizhuangtai;
        this.SPceshitype = SPceshitype;
        this.SPceshiresult = SPceshiresult;
    }

    public PeopelData3(String number,String SPname, String SPxingbie, String SPshenfentype,
                       String SPrenyuantype, String SPsschengdu, String phonenum,
                       String SPsgfssxingwei, String SPsgfsqxingwei, String SPsgfsszhuangtai,
                       String SPaqsbsyzhuangtai, String SPsgfssposition, String SPcheliangxunhao,
                       String SPyinjiu, String SPduPingLeiXing, String SPceshizhuangtai,
                       String SPceshitype, String SPceshiresult) {
        this.number = number;
        this.SPname = SPname;
        this.SPxingbie = SPxingbie;
        this.SPshenfentype = SPshenfentype;
        this.SPrenyuantype = SPrenyuantype;
        this.SPsschengdu = SPsschengdu;
        this.phonenum = phonenum;
        this.SPsgfssxingwei = SPsgfssxingwei;
        this.SPsgfsqxingwei = SPsgfsqxingwei;
        this.SPsgfsszhuangtai = SPsgfsszhuangtai;
        this.SPaqsbsyzhuangtai = SPaqsbsyzhuangtai;
        this.SPsgfssposition = SPsgfssposition;
        this.SPcheliangxunhao = SPcheliangxunhao;
        this.SPyinjiu = SPyinjiu;
        this.SPduPingLeiXing = SPduPingLeiXing;
        this.SPceshizhuangtai = SPceshizhuangtai;
        this.SPceshitype = SPceshitype;
        this.SPceshiresult = SPceshiresult;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSPname() {
        return SPname;
    }

    public void setSPname(String SPname) {
        this.SPname = SPname;
    }

    @Override
    public String toString() {
        return "PeopelData3{" +
                "number='" + number + '\'' +
                ", SPname='" + SPname + '\'' +
                ", SPxingbie='" + SPxingbie + '\'' +
                ", SPshenfentype='" + SPshenfentype + '\'' +
                ", SPrenyuantype='" + SPrenyuantype + '\'' +
                ", SPsschengdu='" + SPsschengdu + '\'' +
                ", phonenum='" + phonenum + '\'' +
                ", SPsgfssxingwei='" + SPsgfssxingwei + '\'' +
                ", SPsgfsqxingwei='" + SPsgfsqxingwei + '\'' +
                ", SPsgfsszhuangtai='" + SPsgfsszhuangtai + '\'' +
                ", SPaqsbsyzhuangtai='" + SPaqsbsyzhuangtai + '\'' +
                ", SPsgfssposition='" + SPsgfssposition + '\'' +
                ", SPcheliangxunhao='" + SPcheliangxunhao + '\'' +
                ", SPyinjiu='" + SPyinjiu + '\'' +
                ", SPduPingLeiXing='" + SPduPingLeiXing + '\'' +
                ", SPceshizhuangtai='" + SPceshizhuangtai + '\'' +
                ", SPceshitype='" + SPceshitype + '\'' +
                ", SPceshiresult='" + SPceshiresult + '\'' +
                '}';
    }

    public String getSPxingbie() {
        return SPxingbie;
    }

    public void setSPxingbie(String SPxingbie) {
        this.SPxingbie = SPxingbie;
    }

    public String getSPshenfentype() {
        return SPshenfentype;
    }

    public void setSPshenfentype(String SPshenfentype) {
        this.SPshenfentype = SPshenfentype;
    }

    public String getSPrenyuantype() {
        return SPrenyuantype;
    }

    public void setSPrenyuantype(String SPrenyuantype) {
        this.SPrenyuantype = SPrenyuantype;
    }

    public String getSPsschengdu() {
        return SPsschengdu;
    }

    public void setSPsschengdu(String SPsschengdu) {
        this.SPsschengdu = SPsschengdu;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getSPsgfssxingwei() {
        return SPsgfssxingwei;
    }

    public void setSPsgfssxingwei(String SPsgfssxingwei) {
        this.SPsgfssxingwei = SPsgfssxingwei;
    }

    public String getSPsgfsqxingwei() {
        return SPsgfsqxingwei;
    }

    public void setSPsgfsqxingwei(String SPsgfsqxingwei) {
        this.SPsgfsqxingwei = SPsgfsqxingwei;
    }

    public String getSPsgfsszhuangtai() {
        return SPsgfsszhuangtai;
    }

    public void setSPsgfsszhuangtai(String SPsgfsszhuangtai) {
        this.SPsgfsszhuangtai = SPsgfsszhuangtai;
    }

    public String getSPaqsbsyzhuangtai() {
        return SPaqsbsyzhuangtai;
    }

    public void setSPaqsbsyzhuangtai(String SPaqsbsyzhuangtai) {
        this.SPaqsbsyzhuangtai = SPaqsbsyzhuangtai;
    }

    public String getSPsgfssposition() {
        return SPsgfssposition;
    }

    public void setSPsgfssposition(String SPsgfssposition) {
        this.SPsgfssposition = SPsgfssposition;
    }

    public String getSPcheliangxunhao() {
        return SPcheliangxunhao;
    }

    public void setSPcheliangxunhao(String SPcheliangxunhao) {
        this.SPcheliangxunhao = SPcheliangxunhao;
    }

    public String getSPyinjiu() {
        return SPyinjiu;
    }

    public void setSPyinjiu(String SPyinjiu) {
        this.SPyinjiu = SPyinjiu;
    }

    public String getSPduPingLeiXing() {
        return SPduPingLeiXing;
    }

    public void setSPduPingLeiXing(String SPduPingLeiXing) {
        this.SPduPingLeiXing = SPduPingLeiXing;
    }

    public String getSPceshizhuangtai() {
        return SPceshizhuangtai;
    }

    public void setSPceshizhuangtai(String SPceshizhuangtai) {
        this.SPceshizhuangtai = SPceshizhuangtai;
    }

    public String getSPceshitype() {
        return SPceshitype;
    }

    public void setSPceshitype(String SPceshitype) {
        this.SPceshitype = SPceshitype;
    }

    public String getSPceshiresult() {
        return SPceshiresult;
    }

    public void setSPceshiresult(String SPceshiresult) {
        this.SPceshiresult = SPceshiresult;
    }

}
