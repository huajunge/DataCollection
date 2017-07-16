package com.example.tr.datacollection.model;

import java.util.Date;

/**
 * Created by TR on 2017/7/1.
 */

public class SimpleDataTest {
    private double lng;
    private double lat;
    private java.util.Date date;
    private String place;
    private String yanzhongchengdu;

    public SimpleDataTest(double lng, double lat, Date date, String place, String yanzhongchengdu) {
        this.lng = lng;
        this.lat = lat;
        this.date = date;
        this.place = place;
        this.yanzhongchengdu = yanzhongchengdu;
    }
    public SimpleDataTest(double lng, double lat, long ltime, String place, String yanzhongchengdu) {
        this.lng = lng;
        this.lat = lat;
        this.date = new Date(ltime);
        this.place = place;
        this.yanzhongchengdu = yanzhongchengdu;
    }
    public SimpleDataTest() {
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getYanzhongchengdu() {
        return yanzhongchengdu;
    }

    public void setYanzhongchengdu(String yanzhongchengdu) {
        this.yanzhongchengdu = yanzhongchengdu;
    }

    @Override
    public String toString() {
        return "SimpleDataTest{" +
                "lng=" + lng +
                ", lat=" + lat +
                ", date=" + date +
                ", place='" + place + '\'' +
                ", yanzhongchengdu='" + yanzhongchengdu + '\'' +
                '}';
    }
}
