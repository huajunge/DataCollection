package com.example.tr.datacollection;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class DriverInfo extends Fragment {

    private ListView list_info;
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>>datalist;
    public DriverInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_driver_info, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        list_info = (ListView) view.findViewById(R.id.list);
        simpleAdapter=new SimpleAdapter(view.getContext(),getData(),R.layout.tableitem2,new String[]{"name","sex","type"},
                new int[]{R.id.name,R.id.sex,R.id.type});
        list_info.setAdapter(simpleAdapter);
    }

    private List<Map<String,Object>> getData() {
        datalist =  new ArrayList<Map<String, Object>>();
        for(int i =0;i<5;i++){
            Map<String,Object>map=new HashMap<String,Object>();
            map.put("name","张三");
            map.put("sex","男");
            map.put("type","驾驶员");
            datalist.add(map);
        }
        return datalist;
    }

}
