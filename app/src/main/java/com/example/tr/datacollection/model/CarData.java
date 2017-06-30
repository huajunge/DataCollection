package com.example.tr.datacollection.model;

/**
 * Created by TR on 2017/6/6.
 * 车辆信息
 */

public class CarData {
    private String vin;//VIN码
    private String chepaihao;//车牌号
    private String guobie;//国别
    private String nianfen;
    private String pingpai;//品牌
    private String carType;//车辆型号
    private String Leixing;//车辆类型
    private String cheneinum;//车内人数
    private String tesuzuoyong;//特殊作用

    private String xingshifangxing;//行驶方向
    private String Xingwei;//行驶方向
    private String Jiechudian;//接触点
    private String sunhuaibuwei;//损坏部位
    private String sunhuaschengdu;//损坏程度

    public CarData() {

    }

    public CarData(String vin, String chepaihao, String guobie, String nianfen, String pingpai,
                   String carType, String leixing, String cheneinum, String tesuzuoyong, String xingshifangxing,
                   String xingwei, String jiechudian, String sunhuaibuwei, String sunhuaschengdu) {
        this.vin = vin;
        this.chepaihao = chepaihao;
        this.guobie = guobie;
        this.nianfen = nianfen;
        this.pingpai = pingpai;
        this.carType = carType;
        Leixing = leixing;
        this.cheneinum = cheneinum;
        this.tesuzuoyong = tesuzuoyong;
        this.xingshifangxing = xingshifangxing;
        Xingwei = xingwei;
        Jiechudian = jiechudian;
        this.sunhuaibuwei = sunhuaibuwei;
        this.sunhuaschengdu = sunhuaschengdu;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getChepaihao() {
        return chepaihao;
    }

    public void setChepaihao(String chepaihao) {
        this.chepaihao = chepaihao;
    }

    public String getGuobie() {
        return guobie;
    }

    public void setGuobie(String guobie) {
        this.guobie = guobie;
    }

    public String getNianfen() {
        return nianfen;
    }

    public void setNianfen(String nianfen) {
        this.nianfen = nianfen;
    }

    public String getPingpai() {
        return pingpai;
    }

    public void setPingpai(String pingpai) {
        this.pingpai = pingpai;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getLeixing() {
        return Leixing;
    }

    public void setLeixing(String leixing) {
        Leixing = leixing;
    }

    public String getCheneinum() {
        return cheneinum;
    }

    public void setCheneinum(String cheneinum) {
        this.cheneinum = cheneinum;
    }

    public String getTesuzuoyong() {
        return tesuzuoyong;
    }

    public void setTesuzuoyong(String tesuzuoyong) {
        this.tesuzuoyong = tesuzuoyong;
    }

    public String getXingshifangxing() {
        return xingshifangxing;
    }

    public void setXingshifangxing(String xingshifangxing) {
        this.xingshifangxing = xingshifangxing;
    }

    public String getXingwei() {
        return Xingwei;
    }

    public void setXingwei(String xingwei) {
        Xingwei = xingwei;
    }

    public String getJiechudian() {
        return Jiechudian;
    }

    public void setJiechudian(String jiechudian) {
        Jiechudian = jiechudian;
    }

    public String getSunhuaibuwei() {
        return sunhuaibuwei;
    }

    public void setSunhuaibuwei(String sunhuaibuwei) {
        this.sunhuaibuwei = sunhuaibuwei;
    }

    public String getSunhuaschengdu() {
        return sunhuaschengdu;
    }

    public void setSunhuaschengdu(String sunhuaschengdu) {
        this.sunhuaschengdu = sunhuaschengdu;
    }
}
