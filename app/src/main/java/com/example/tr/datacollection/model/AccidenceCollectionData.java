package com.example.tr.datacollection.model;

import java.sql.Date;

/**
 * Created by TR on 2017/10/6.
 */

public class AccidenceCollectionData {
    private String number;//事故编号
    private String accidenceNumber;//事故信息
    private String environmentNumber;//环境信息
    private String carNumber;//车辆信息
    private String peopelNumber;//人员信息

    private Date   data;//时间
    private String placeName;//地名
    private int    isUpload;//是否上传

    public AccidenceCollectionData(String number, Date data, String placeName, int isUpload) {
        this.number = number;
        this.data = data;
        this.placeName = placeName;
        this.isUpload = isUpload;
    }

    public AccidenceCollectionData(String number, String accidenceNumber, String environmentNumber, String carNumber, String peopelNumber, Date data, String placeName, int isUpload) {
        this.number = number;
        this.accidenceNumber = accidenceNumber;
        this.environmentNumber = environmentNumber;
        this.carNumber = carNumber;
        this.peopelNumber = peopelNumber;
        this.data = data;
        this.placeName = placeName;
        this.isUpload = isUpload;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAccidenceNumber() {
        return accidenceNumber;
    }

    public void setAccidenceNumber(String accidenceNumber) {
        this.accidenceNumber = accidenceNumber;
    }

    public String getEnvironmentNumber() {
        return environmentNumber;
    }

    public void setEnvironmentNumber(String environmentNumber) {
        this.environmentNumber = environmentNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getPeopelNumber() {
        return peopelNumber;
    }

    public void setPeopelNumber(String peopelNumber) {
        this.peopelNumber = peopelNumber;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public int isUpload() {
        return isUpload;
    }

    public void setUpload(int upload) {
        isUpload = upload;
    }

    @Override
    public String toString() {
        return "AccidenceCollectionData{" +
                "number='" + number + '\'' +
                ", accidenceNumber='" + accidenceNumber + '\'' +
                ", environmentNumber='" + environmentNumber + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", peopelNumber='" + peopelNumber + '\'' +
                ", data=" + data +
                ", placeName='" + placeName + '\'' +
                ", isUpload=" + isUpload +
                '}';
    }
}
