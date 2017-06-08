package com.example.tr.datacollection.model;

/**
 * Created by TR on 2017/6/6.
 * 一个事故所有相关信息
 */

public class CollectionData {
    private AcccidentData acccidentData;
    private EMdata eMdata;
    private CarData carData;
    private PelpelData pelpelData;

    public CollectionData() {

    }

    public CollectionData(AcccidentData acccidentData, EMdata eMdata, CarData carData, PelpelData pelpelData) {
        this.acccidentData = acccidentData;
        this.eMdata = eMdata;
        this.carData = carData;
        this.pelpelData = pelpelData;
    }

    public AcccidentData getAcccidentData() {
        return acccidentData;
    }

    public void setAcccidentData(AcccidentData acccidentData) {
        this.acccidentData = acccidentData;
    }

    public EMdata geteMdata() {
        return eMdata;
    }

    public void seteMdata(EMdata eMdata) {
        this.eMdata = eMdata;
    }

    public CarData getCarData() {
        return carData;
    }

    public void setCarData(CarData carData) {
        this.carData = carData;
    }

    public PelpelData getPelpelData() {
        return pelpelData;
    }

    public void setPelpelData(PelpelData pelpelData) {
        this.pelpelData = pelpelData;
    }
}
