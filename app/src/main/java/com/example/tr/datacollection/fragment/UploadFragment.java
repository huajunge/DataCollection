package com.example.tr.datacollection.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.tr.datacollection.R;
import com.example.tr.datacollection.model.AccidenceCollectionData;
import com.example.tr.datacollection.util.DBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadFragment extends Fragment {
    ListView listView;
    SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> datalist;

    public UploadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upload, container, false);
        initView(view);
        return view;
    }
    public void reflash() {
        setListView();
    }
    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.list);

        datalist = new ArrayList<Map<String, Object>>();
        setListView();
    }

    public void setListView() {
        simpleAdapter = new SimpleAdapter(getContext(),getData(), R.layout.upload_item,new String[]{"text_bianhao","text_location","text_time"},
                new int[]{R.id.text_bianhao,R.id.text_location,R.id.text_time});
        listView.setAdapter(simpleAdapter);
    }

    private List<Map<String,Object>> getData() {
        DBO dbo = new DBO(getContext());
        datalist.clear();
        datalist = new ArrayList<>();
        List<AccidenceCollectionData> accidenceCollectionDatas = new ArrayList<AccidenceCollectionData>();
        accidenceCollectionDatas = dbo.getAccidenceCollectionData(1);
        for(int i=0;i<accidenceCollectionDatas.size();i++){
            Map<String,Object>map=new HashMap<String,Object>();
            map.put("text_bianhao",accidenceCollectionDatas.get(i).getNumber());
            map.put("text_location",accidenceCollectionDatas.get(i).getPlaceName());
            map.put("text_time",accidenceCollectionDatas.get(i).getData().toString());
            datalist.add(map);
        }
        return datalist;
    }

}
