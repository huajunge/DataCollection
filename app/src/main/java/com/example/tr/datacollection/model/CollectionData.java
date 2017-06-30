package com.example.tr.datacollection.model;

import com.example.tr.datacollection.PeopelData3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TR on 2017/6/6.
 * 一个事故所有相关信息
 */

public class CollectionData {
    private AcccidentData acccidentData;
    private EMdata eMdata;
    private List<CarData> carData = new ArrayList<CarData>();
    private PelpelData pelpelData;
    private PeopelData2 pelpelData2;
    private PeopelData3 pelpelData3;

    public CollectionData() {

    }

    public PeopelData2 getPelpelData2() {
        return pelpelData2;
    }

    public CollectionData(AcccidentData acccidentData, EMdata eMdata, List<CarData> carData, PelpelData pelpelData, PeopelData2 pelpelData2, PeopelData3 pelpelData3) {
        this.acccidentData = acccidentData;
        this.eMdata = eMdata;
        this.carData = carData;
        this.pelpelData = pelpelData;
        this.pelpelData2 = pelpelData2;
        this.pelpelData3 = pelpelData3;
    }

    public void setPelpelData2(PeopelData2 pelpelData2) {
        this.pelpelData2 = pelpelData2;
    }

    public PeopelData3 getPelpelData3() {
        return pelpelData3;
    }

    public void setPelpelData3(PeopelData3 pelpelData3) {
        this.pelpelData3 = pelpelData3;
    }

    public CollectionData(AcccidentData acccidentData, EMdata eMdata, List<CarData> carData, PelpelData pelpelData) {
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

    public List<CarData> getCarData() {
        return carData;
    }

    public void setCarData(List<CarData> carData) {
        this.carData = carData;
    }

    public PelpelData getPelpelData() {
        return pelpelData;
    }

    public void setPelpelData(PelpelData pelpelData) {
        this.pelpelData = pelpelData;
    }
}
