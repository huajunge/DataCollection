package com.example.tr.datacollection;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TR on 2017/5/30.
 */

public class MyAdapter2 {
    private List<String> datalist=new ArrayList<String>();
    private ArrayAdapter arrayAdapter;
    private Context context;
    private String[] strings;
    public MyAdapter2() {
    }
    public MyAdapter2(String[] strings, Context context) {
        this.context = context;
        this.strings = strings;
        for(int i =0;i<strings.length;i++){
            datalist.add(strings[i]);
        }
        this.arrayAdapter=new ArrayAdapter<String>(context,R.layout.my_simple_spinner_self_item,datalist);
        this.arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
    public ArrayAdapter getAdaper(){
        return this.arrayAdapter;
    }
    public String[] getStrings() {
        return strings;
    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }
}
