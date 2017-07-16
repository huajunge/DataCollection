package com.example.tr.datacollection;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TR on 2017/5/30.
 */

public class MyAdapter {
    private List<String> datalist=new ArrayList<String>();
    private ArrayAdapter arrayAdapter;
    private Context context;
    public MyAdapter() {
    }
    public MyAdapter(String[] strings, Context context) {
        this.context = context;
        for(int i =0;i<strings.length;i++){
            datalist.add(strings[i]);
        }
        this.arrayAdapter=new ArrayAdapter<String>(context,R.layout.my_simple_spinner_self_item,datalist);
        this.arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
    public ArrayAdapter getAdaper(){
        return this.arrayAdapter;
    }
}
