package com.example.tr.datacollection.model;

/**
 * Created by TR on 2017/10/20.
 */

public class Test {
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
    private Double lat = 0.0;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.lng
     *
     * @mbggenerated
     */
    private Double lng = 0.0;

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
    private String accidencnumber = "";

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accidentdata.riQI
     *
     * @mbggenerated
     */
    private String riqi = "";

    private String noo;

    public String getNoo() {
        return noo;
    }

    public void setNoo(String noo) {
        this.noo = noo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Boolean getWorkday() {
        return isWorkday;
    }

    public void setWorkday(Boolean workday) {
        isWorkday = workday;
    }

    public String getAccidencnumber() {
        return accidencnumber;
    }

    public void setAccidencnumber(String accidencnumber) {
        this.accidencnumber = accidencnumber;
    }

    public String getRiqi() {
        return riqi;
    }

    public void setRiqi(String riqi) {
        this.riqi = riqi;
    }
}