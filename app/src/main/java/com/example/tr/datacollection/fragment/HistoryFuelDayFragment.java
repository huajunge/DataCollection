package com.example.tr.datacollection.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tr.datacollection.MyAdapter2;
import com.example.tr.datacollection.R;
import com.example.tr.datacollection.model.SimpleDataTest;
import com.example.tr.datacollection.util.DBO;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tangpeng on 2017/2/28.
 */

public class HistoryFuelDayFragment extends Fragment implements Spinner.OnItemSelectedListener {

    private Spinner spinnerMonth;
    private Spinner spinnerYear;
    private LineChart lineChart;
    private List<String> basicDays;
    private List<String> days;
    private int nowYear=2017;
    private int nowMonth;
    private TextView shiJianShu;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        initBasicDays();

        View rootView = inflater.inflate(R.layout.fragment_history_fuel_day, null);
        spinnerMonth = (Spinner) rootView.findViewById(R.id.spinner_month);
        String[] strMonth ={"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
        spinnerMonth.setAdapter(new MyAdapter2(strMonth,getContext()).getAdaper());
        spinnerYear = (Spinner) rootView.findViewById(R.id.spinner_year);
        String ss="";
        Date d = new Date(System.currentTimeMillis());
        SimpleDateFormat simpled = new SimpleDateFormat("yyyy");
        try {
            d = simpled.parse(simpled.format(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for(int i=Integer.valueOf(simpled.format(d));i>2009;i--){
            ss+=i+",";
        }
        String[] strspnianfen =ss.split(",");
        spinnerYear.setAdapter(new MyAdapter2(strspnianfen,getContext()).getAdaper());

        spinnerMonth.setOnItemSelectedListener(this);
        spinnerYear.setOnItemSelectedListener(this);

        lineChart = (LineChart) rootView.findViewById(R.id.line_chart);
        shiJianShu = (TextView) rootView.findViewById(R.id.shinums);
        changeDays();   //计算选择的年月对应的天数
        initData();     //初始化chart
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lineChart.destroyDrawingCache();
    }

    private void initBasicDays() {
        if (basicDays == null)
            basicDays = new ArrayList<>();
        basicDays.clear();
        for (int i = 1; i <= 28; i++) {
            basicDays.add(i + "");
        }
    }

    private void changeDays() {
        String year = (String) spinnerYear.getSelectedItem();
        //year="2017";

        int yearInt = Integer.parseInt(year);
        //yearInt=2017;
        nowYear = yearInt;
        String month = (String) spinnerMonth.getSelectedItem();
        nowMonth = spinnerMonth.getSelectedItemPosition();
        boolean isRunNian = false;

        if ((yearInt % 4 == 0 && yearInt % 100 != 0) || yearInt % 400 == 0) {//是闰年
            isRunNian = true;
        }

        days = new ArrayList<>(basicDays);
        if (month.equals("二月")) {
            if (isRunNian) {
                days.add("29");
            }
        } else {
            days.add("29");
            days.add("30");
            switch (month) {
                case "一月":
                case "三月":
                case "五月":
                case "七月":
                case "八月":
                case "十月":
                case "十二月":
                    days.add("31");
                    break;
            }
        }
    }

    
    private void initData() {
//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.set(nowYear,nowMonth,1,0,0);
//        Log.i("cal",""+calendar1.get(java.util.Calendar.MONTH));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        try {
            date1 = simpleDateFormat.parse(""+nowYear+"-"+(nowMonth+1)+"-"+1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date date2 = new Date(date1.getTime());
        date2.setDate(date1.getDate()+days.size()-1);
        DBO dbo = new DBO(getContext());
        List<SimpleDataTest> simpleDataTests = dbo.getsimpleDataTestByDay(date1.getTime(),date2.getTime());
        List<SimpleDataTest> simpleDataTests2 = dbo.getsimpleDataTest();
//        Log.i("date1",""+"year:"+nowYear+"month:"+nowMonth);
//        Log.i("date1",""+date1.getMonth());
//        Log.i("date1",""+"d1"+date1.getTime());
//        Log.i("date1",""+"d2"+date2.getTime());
//        Log.i("date1",""+"d1"+simpleDateFormat.format(date1));
//        Log.i("date1",""+"d2"+simpleDateFormat.format(date2));
//        Log.i("date1",""+"ssssss---"+simpleDataTests.size());
//        Log.i("date1",""+"ssssss22---"+simpleDataTests2.size());
//        Log.i("date1",""+"ssssss22---"+simpleDataTests2.size());
        for(SimpleDataTest s:simpleDataTests2){
//            Log.i("date1",""+"ssssss22333---"+s.getDate().getTime());
//            Log.i("date1",""+"ssssss22333---"+simpleDateFormat.format(s.getDate()));
        }
        List<Entry> entryList = new ArrayList<>();
        for (int i = 0; i < days.size(); i++) {
            entryList.add(new Entry(i, 0));
        }
        for(SimpleDataTest ss:simpleDataTests){
            entryList.set(ss.getDate().getDate()-1,new Entry(ss.getDate().getDate()-1, entryList.get(ss.getDate().getDate()-1).getY()+1));
        }
        shiJianShu.setText("事件总数:"+simpleDataTests.size());
        //Collections.sort(entryList, new EntryXComparator());  x的坐标值必须是升序

        LineDataSet lineDataSet1 = new LineDataSet(entryList, "事故次数");    //对折线的文字说明
//        Format format = new DecimalFormat("00");
//        lineDataSet1.setFillFormatter((IFillFormatter) format);
        lineDataSet1.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet1.setColor(Color.GREEN);    //线段颜色
        lineDataSet1.setValueTextColor(Color.RED);   //顶点的颜色
        lineDataSet1.setCircleColor(Color.CYAN);
        lineDataSet1.setCircleColorHole(Color.WHITE);

        setLineChart();

        LineData lineData = new LineData(lineDataSet1);

        lineChart.clear();
        lineChart.animateY(2000);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

    //设置chart的坐标轴
    private void setLineChart() {
        XAxis xAxis = lineChart.getXAxis(); //x坐标轴
        xAxis.setGranularity(1f);    //设置最小的间隔单位
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if (value >= days.size())
                    value = days.size() - 1;
                return days.get((int) value);
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  //设置x坐标的位置

        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int) value + "";
            }
        });
        lineChart.getAxisLeft().setAxisMinimum(0f);
        lineChart.getAxisRight().setEnabled(false);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        changeDays();
        initData();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
