package com.example.tr.datacollection.util;

/**
 * Created by TR on 2017/10/16.
 */

public class NoNumInString {

    public static String start(String string){
        if ( string == null || string .equals("")){
            return "";
        }
        String temp = string.replaceAll("\\d\\d ","");

        return temp;
    }

    public static void main(String[] args) {

        System.out.println(NoNumInString.start("01 的手法首发收发"));
    }
}
