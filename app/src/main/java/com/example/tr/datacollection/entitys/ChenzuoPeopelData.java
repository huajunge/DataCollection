package com.example.tr.datacollection.entitys;

import com.example.tr.datacollection.model.PelpelData;

public class ChenzuoPeopelData {
    public ChenzuoPeopelData() {
    }
    public ChenzuoPeopelData(PelpelData pelpelData) {
        shigunumber = pelpelData.getNumber();
        name = pelpelData.getName();
        xingbie = pelpelData.getXingbie();
        shenfentype = pelpelData.getShenfentype();
        renyuantype = pelpelData.getRenyuantype();
        sschengdu = pelpelData.getSschengdu();
        phonenum = pelpelData.getPhonenum();
        cheliangxunhao = pelpelData.getCheliangxunhao();
        ysxitong = pelpelData.getYsxitong();
        tuokuishiyong = pelpelData.getTuokuishiyong();
        aqqnzt = pelpelData.getAqqnzt();
        paochuzhuangta = pelpelData.getPaochuzhuangta();
        sgfsszhuangtai = pelpelData.getSgfsszhuangtai();
        yinjiu = pelpelData.getYinjiu();
        duPingLeiXing = pelpelData.getDuPingLeiXing();
        ceshizhuangtai = pelpelData.getCeshizhuangtai();
        ceshitype = pelpelData.getCeshitype();
        ceshiresult = pelpelData.getCeshiresult();
    }
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chenzuo_peopel_data.id
     *
     * @mbggenerated
     */

    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chenzuo_peopel_data.aqqnzt
     *
     * @mbggenerated
     */
    private String aqqnzt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chenzuo_peopel_data.ceshiresult
     *
     * @mbggenerated
     */
    private String ceshiresult;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chenzuo_peopel_data.ceshitype
     *
     * @mbggenerated
     */
    private String ceshitype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chenzuo_peopel_data.ceshizhuangtai
     *
     * @mbggenerated
     */
    private String ceshizhuangtai;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chenzuo_peopel_data.cheliangxunhao
     *
     * @mbggenerated
     */
    private String cheliangxunhao;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chenzuo_peopel_data.du_ping_lei_xing
     *
     * @mbggenerated
     */
    private String duPingLeiXing;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chenzuo_peopel_data.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chenzuo_peopel_data.paochuzhuangta
     *
     * @mbggenerated
     */
    private String paochuzhuangta;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chenzuo_peopel_data.phonenum
     *
     * @mbggenerated
     */
    private String phonenum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chenzuo_peopel_data.renyuantype
     *
     * @mbggenerated
     */
    private String renyuantype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chenzuo_peopel_data.sgfsszhuangtai
     *
     * @mbggenerated
     */
    private String sgfsszhuangtai;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chenzuo_peopel_data.shenfentype
     *
     * @mbggenerated
     */
    private String shenfentype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chenzuo_peopel_data.sschengdu
     *
     * @mbggenerated
     */
    private String sschengdu;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chenzuo_peopel_data.tuokuishiyong
     *
     * @mbggenerated
     */
    private String tuokuishiyong;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chenzuo_peopel_data.xingbie
     *
     * @mbggenerated
     */
    private String xingbie;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chenzuo_peopel_data.yinjiu
     *
     * @mbggenerated
     */
    private String yinjiu;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chenzuo_peopel_data.ysxitong
     *
     * @mbggenerated
     */
    private String ysxitong;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chenzuo_peopel_data.shigunumber
     *
     * @mbggenerated
     */
    private String shigunumber;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chenzuo_peopel_data.id
     *
     * @return the value of chenzuo_peopel_data.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chenzuo_peopel_data.id
     *
     * @param id the value for chenzuo_peopel_data.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chenzuo_peopel_data.aqqnzt
     *
     * @return the value of chenzuo_peopel_data.aqqnzt
     *
     * @mbggenerated
     */
    public String getAqqnzt() {
        return aqqnzt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chenzuo_peopel_data.aqqnzt
     *
     * @param aqqnzt the value for chenzuo_peopel_data.aqqnzt
     *
     * @mbggenerated
     */
    public void setAqqnzt(String aqqnzt) {
        this.aqqnzt = aqqnzt == null ? null : aqqnzt.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chenzuo_peopel_data.ceshiresult
     *
     * @return the value of chenzuo_peopel_data.ceshiresult
     *
     * @mbggenerated
     */
    public String getCeshiresult() {
        return ceshiresult;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chenzuo_peopel_data.ceshiresult
     *
     * @param ceshiresult the value for chenzuo_peopel_data.ceshiresult
     *
     * @mbggenerated
     */
    public void setCeshiresult(String ceshiresult) {
        this.ceshiresult = ceshiresult == null ? null : ceshiresult.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chenzuo_peopel_data.ceshitype
     *
     * @return the value of chenzuo_peopel_data.ceshitype
     *
     * @mbggenerated
     */
    public String getCeshitype() {
        return ceshitype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chenzuo_peopel_data.ceshitype
     *
     * @param ceshitype the value for chenzuo_peopel_data.ceshitype
     *
     * @mbggenerated
     */
    public void setCeshitype(String ceshitype) {
        this.ceshitype = ceshitype == null ? null : ceshitype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chenzuo_peopel_data.ceshizhuangtai
     *
     * @return the value of chenzuo_peopel_data.ceshizhuangtai
     *
     * @mbggenerated
     */
    public String getCeshizhuangtai() {
        return ceshizhuangtai;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chenzuo_peopel_data.ceshizhuangtai
     *
     * @param ceshizhuangtai the value for chenzuo_peopel_data.ceshizhuangtai
     *
     * @mbggenerated
     */
    public void setCeshizhuangtai(String ceshizhuangtai) {
        this.ceshizhuangtai = ceshizhuangtai == null ? null : ceshizhuangtai.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chenzuo_peopel_data.cheliangxunhao
     *
     * @return the value of chenzuo_peopel_data.cheliangxunhao
     *
     * @mbggenerated
     */
    public String getCheliangxunhao() {
        return cheliangxunhao;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chenzuo_peopel_data.cheliangxunhao
     *
     * @param cheliangxunhao the value for chenzuo_peopel_data.cheliangxunhao
     *
     * @mbggenerated
     */
    public void setCheliangxunhao(String cheliangxunhao) {
        this.cheliangxunhao = cheliangxunhao == null ? null : cheliangxunhao.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chenzuo_peopel_data.du_ping_lei_xing
     *
     * @return the value of chenzuo_peopel_data.du_ping_lei_xing
     *
     * @mbggenerated
     */
    public String getDuPingLeiXing() {
        return duPingLeiXing;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chenzuo_peopel_data.du_ping_lei_xing
     *
     * @param duPingLeiXing the value for chenzuo_peopel_data.du_ping_lei_xing
     *
     * @mbggenerated
     */
    public void setDuPingLeiXing(String duPingLeiXing) {
        this.duPingLeiXing = duPingLeiXing == null ? null : duPingLeiXing.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chenzuo_peopel_data.name
     *
     * @return the value of chenzuo_peopel_data.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chenzuo_peopel_data.name
     *
     * @param name the value for chenzuo_peopel_data.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chenzuo_peopel_data.paochuzhuangta
     *
     * @return the value of chenzuo_peopel_data.paochuzhuangta
     *
     * @mbggenerated
     */
    public String getPaochuzhuangta() {
        return paochuzhuangta;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chenzuo_peopel_data.paochuzhuangta
     *
     * @param paochuzhuangta the value for chenzuo_peopel_data.paochuzhuangta
     *
     * @mbggenerated
     */
    public void setPaochuzhuangta(String paochuzhuangta) {
        this.paochuzhuangta = paochuzhuangta == null ? null : paochuzhuangta.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chenzuo_peopel_data.phonenum
     *
     * @return the value of chenzuo_peopel_data.phonenum
     *
     * @mbggenerated
     */
    public String getPhonenum() {
        return phonenum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chenzuo_peopel_data.phonenum
     *
     * @param phonenum the value for chenzuo_peopel_data.phonenum
     *
     * @mbggenerated
     */
    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chenzuo_peopel_data.renyuantype
     *
     * @return the value of chenzuo_peopel_data.renyuantype
     *
     * @mbggenerated
     */
    public String getRenyuantype() {
        return renyuantype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chenzuo_peopel_data.renyuantype
     *
     * @param renyuantype the value for chenzuo_peopel_data.renyuantype
     *
     * @mbggenerated
     */
    public void setRenyuantype(String renyuantype) {
        this.renyuantype = renyuantype == null ? null : renyuantype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chenzuo_peopel_data.sgfsszhuangtai
     *
     * @return the value of chenzuo_peopel_data.sgfsszhuangtai
     *
     * @mbggenerated
     */
    public String getSgfsszhuangtai() {
        return sgfsszhuangtai;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chenzuo_peopel_data.sgfsszhuangtai
     *
     * @param sgfsszhuangtai the value for chenzuo_peopel_data.sgfsszhuangtai
     *
     * @mbggenerated
     */
    public void setSgfsszhuangtai(String sgfsszhuangtai) {
        this.sgfsszhuangtai = sgfsszhuangtai == null ? null : sgfsszhuangtai.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chenzuo_peopel_data.shenfentype
     *
     * @return the value of chenzuo_peopel_data.shenfentype
     *
     * @mbggenerated
     */
    public String getShenfentype() {
        return shenfentype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chenzuo_peopel_data.shenfentype
     *
     * @param shenfentype the value for chenzuo_peopel_data.shenfentype
     *
     * @mbggenerated
     */
    public void setShenfentype(String shenfentype) {
        this.shenfentype = shenfentype == null ? null : shenfentype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chenzuo_peopel_data.sschengdu
     *
     * @return the value of chenzuo_peopel_data.sschengdu
     *
     * @mbggenerated
     */
    public String getSschengdu() {
        return sschengdu;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chenzuo_peopel_data.sschengdu
     *
     * @param sschengdu the value for chenzuo_peopel_data.sschengdu
     *
     * @mbggenerated
     */
    public void setSschengdu(String sschengdu) {
        this.sschengdu = sschengdu == null ? null : sschengdu.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chenzuo_peopel_data.tuokuishiyong
     *
     * @return the value of chenzuo_peopel_data.tuokuishiyong
     *
     * @mbggenerated
     */
    public String getTuokuishiyong() {
        return tuokuishiyong;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chenzuo_peopel_data.tuokuishiyong
     *
     * @param tuokuishiyong the value for chenzuo_peopel_data.tuokuishiyong
     *
     * @mbggenerated
     */
    public void setTuokuishiyong(String tuokuishiyong) {
        this.tuokuishiyong = tuokuishiyong == null ? null : tuokuishiyong.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chenzuo_peopel_data.xingbie
     *
     * @return the value of chenzuo_peopel_data.xingbie
     *
     * @mbggenerated
     */
    public String getXingbie() {
        return xingbie;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chenzuo_peopel_data.xingbie
     *
     * @param xingbie the value for chenzuo_peopel_data.xingbie
     *
     * @mbggenerated
     */
    public void setXingbie(String xingbie) {
        this.xingbie = xingbie == null ? null : xingbie.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chenzuo_peopel_data.yinjiu
     *
     * @return the value of chenzuo_peopel_data.yinjiu
     *
     * @mbggenerated
     */
    public String getYinjiu() {
        return yinjiu;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chenzuo_peopel_data.yinjiu
     *
     * @param yinjiu the value for chenzuo_peopel_data.yinjiu
     *
     * @mbggenerated
     */
    public void setYinjiu(String yinjiu) {
        this.yinjiu = yinjiu == null ? null : yinjiu.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chenzuo_peopel_data.ysxitong
     *
     * @return the value of chenzuo_peopel_data.ysxitong
     *
     * @mbggenerated
     */
    public String getYsxitong() {
        return ysxitong;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chenzuo_peopel_data.ysxitong
     *
     * @param ysxitong the value for chenzuo_peopel_data.ysxitong
     *
     * @mbggenerated
     */
    public void setYsxitong(String ysxitong) {
        this.ysxitong = ysxitong == null ? null : ysxitong.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chenzuo_peopel_data.shigunumber
     *
     * @return the value of chenzuo_peopel_data.shigunumber
     *
     * @mbggenerated
     */
    public String getShigunumber() {
        return shigunumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chenzuo_peopel_data.shigunumber
     *
     * @param shigunumber the value for chenzuo_peopel_data.shigunumber
     *
     * @mbggenerated
     */
    public void setShigunumber(String shigunumber) {
        this.shigunumber = shigunumber == null ? null : shigunumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chenzuo_peopel_data
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", aqqnzt=").append(aqqnzt);
        sb.append(", ceshiresult=").append(ceshiresult);
        sb.append(", ceshitype=").append(ceshitype);
        sb.append(", ceshizhuangtai=").append(ceshizhuangtai);
        sb.append(", cheliangxunhao=").append(cheliangxunhao);
        sb.append(", duPingLeiXing=").append(duPingLeiXing);
        sb.append(", name=").append(name);
        sb.append(", paochuzhuangta=").append(paochuzhuangta);
        sb.append(", phonenum=").append(phonenum);
        sb.append(", renyuantype=").append(renyuantype);
        sb.append(", sgfsszhuangtai=").append(sgfsszhuangtai);
        sb.append(", shenfentype=").append(shenfentype);
        sb.append(", sschengdu=").append(sschengdu);
        sb.append(", tuokuishiyong=").append(tuokuishiyong);
        sb.append(", xingbie=").append(xingbie);
        sb.append(", yinjiu=").append(yinjiu);
        sb.append(", ysxitong=").append(ysxitong);
        sb.append(", shigunumber=").append(shigunumber);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chenzuo_peopel_data
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
        ChenzuoPeopelData other = (ChenzuoPeopelData) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAqqnzt() == null ? other.getAqqnzt() == null : this.getAqqnzt().equals(other.getAqqnzt()))
            && (this.getCeshiresult() == null ? other.getCeshiresult() == null : this.getCeshiresult().equals(other.getCeshiresult()))
            && (this.getCeshitype() == null ? other.getCeshitype() == null : this.getCeshitype().equals(other.getCeshitype()))
            && (this.getCeshizhuangtai() == null ? other.getCeshizhuangtai() == null : this.getCeshizhuangtai().equals(other.getCeshizhuangtai()))
            && (this.getCheliangxunhao() == null ? other.getCheliangxunhao() == null : this.getCheliangxunhao().equals(other.getCheliangxunhao()))
            && (this.getDuPingLeiXing() == null ? other.getDuPingLeiXing() == null : this.getDuPingLeiXing().equals(other.getDuPingLeiXing()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPaochuzhuangta() == null ? other.getPaochuzhuangta() == null : this.getPaochuzhuangta().equals(other.getPaochuzhuangta()))
            && (this.getPhonenum() == null ? other.getPhonenum() == null : this.getPhonenum().equals(other.getPhonenum()))
            && (this.getRenyuantype() == null ? other.getRenyuantype() == null : this.getRenyuantype().equals(other.getRenyuantype()))
            && (this.getSgfsszhuangtai() == null ? other.getSgfsszhuangtai() == null : this.getSgfsszhuangtai().equals(other.getSgfsszhuangtai()))
            && (this.getShenfentype() == null ? other.getShenfentype() == null : this.getShenfentype().equals(other.getShenfentype()))
            && (this.getSschengdu() == null ? other.getSschengdu() == null : this.getSschengdu().equals(other.getSschengdu()))
            && (this.getTuokuishiyong() == null ? other.getTuokuishiyong() == null : this.getTuokuishiyong().equals(other.getTuokuishiyong()))
            && (this.getXingbie() == null ? other.getXingbie() == null : this.getXingbie().equals(other.getXingbie()))
            && (this.getYinjiu() == null ? other.getYinjiu() == null : this.getYinjiu().equals(other.getYinjiu()))
            && (this.getYsxitong() == null ? other.getYsxitong() == null : this.getYsxitong().equals(other.getYsxitong()))
            && (this.getShigunumber() == null ? other.getShigunumber() == null : this.getShigunumber().equals(other.getShigunumber()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chenzuo_peopel_data
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAqqnzt() == null) ? 0 : getAqqnzt().hashCode());
        result = prime * result + ((getCeshiresult() == null) ? 0 : getCeshiresult().hashCode());
        result = prime * result + ((getCeshitype() == null) ? 0 : getCeshitype().hashCode());
        result = prime * result + ((getCeshizhuangtai() == null) ? 0 : getCeshizhuangtai().hashCode());
        result = prime * result + ((getCheliangxunhao() == null) ? 0 : getCheliangxunhao().hashCode());
        result = prime * result + ((getDuPingLeiXing() == null) ? 0 : getDuPingLeiXing().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPaochuzhuangta() == null) ? 0 : getPaochuzhuangta().hashCode());
        result = prime * result + ((getPhonenum() == null) ? 0 : getPhonenum().hashCode());
        result = prime * result + ((getRenyuantype() == null) ? 0 : getRenyuantype().hashCode());
        result = prime * result + ((getSgfsszhuangtai() == null) ? 0 : getSgfsszhuangtai().hashCode());
        result = prime * result + ((getShenfentype() == null) ? 0 : getShenfentype().hashCode());
        result = prime * result + ((getSschengdu() == null) ? 0 : getSschengdu().hashCode());
        result = prime * result + ((getTuokuishiyong() == null) ? 0 : getTuokuishiyong().hashCode());
        result = prime * result + ((getXingbie() == null) ? 0 : getXingbie().hashCode());
        result = prime * result + ((getYinjiu() == null) ? 0 : getYinjiu().hashCode());
        result = prime * result + ((getYsxitong() == null) ? 0 : getYsxitong().hashCode());
        result = prime * result + ((getShigunumber() == null) ? 0 : getShigunumber().hashCode());
        return result;
    }
}