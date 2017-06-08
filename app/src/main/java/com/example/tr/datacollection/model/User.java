package com.example.tr.datacollection.model;

/**
 * Created by TR on 2017/6/6.
 */

public class User {
    private String jingHao;
    private String password;
    private String xiaqu;

    public User() {
    }

    public User(String jingHao, String password, String xiaqu) {
        this.jingHao = jingHao;
        this.password = password;
        this.xiaqu = xiaqu;
    }

    public String getJingHao() {
        return jingHao;
    }

    public void setJingHao(String jingHao) {
        this.jingHao = jingHao;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getXiaqu() {
        return xiaqu;
    }

    public void setXiaqu(String xiaqu) {
        this.xiaqu = xiaqu;
    }
}
