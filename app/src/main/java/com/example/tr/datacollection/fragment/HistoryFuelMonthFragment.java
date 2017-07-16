package com.example.tr.datacollection.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tr.datacollection.MyAdapter;
import com.example.tr.datacollection.R;
import com.example.tr.datacollection.model.SimpleDataTest;
import com.example.tr.datacollection.util.DBO;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tangpeng on 2017/2/28.
 */

public class HistoryFuelMonthFragment extends Fragment implements Spinner.OnItemSelectedListener{

    private Spinner spinnerYear;
    private BarChart barChart;

    private String[] xAxisName = new String[]{"一月", "二月", "三月", "四月", "五月", "六月",
            "七月", "八月", "九月", "十月", "十一月", "十二月"};
    private int nowYear=2017;
    private TextView shiJianShu;
    public HistoryFuelMonthFragment() {

    }

    private void setXAxis() {
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  //设置x坐标的位置
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xAxisName[(int) value];
            }
        });
        barChart.getAxisLeft().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int)(value)+"";
            }
        });
        barChart.getAxisLeft().setAxisMinimum(0f); // start at zero
        barChart.getAxisRight().setEnabled(false);
    }

    private void initData() {
        List<BarEntry> barEntries = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        nowYear = Integer.parseInt(spinnerYear.getSelectedItem().toString());
        Date date1 = new Date();
        try {
            date1 = simpleDateFormat.parse(""+nowYear+"-"+1+"-"+1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date2 = new Date(date1.getTime());
        date2.setYear(date1.getYear()+1);
        DBO dbo = new DBO(getContext());
        List<SimpleDataTest> simpleDataTests = dbo.getsimpleDataTestByDay(date1.getTime(),date2.getTime());

        for (int i = 0; i < xAxisName.length; i++) {
            barEntries.add(new BarEntry(i, 0));
        }
        for(SimpleDataTest ss:simpleDataTests){
            barEntries.set(ss.getDate().getMonth(),new BarEntry(ss.getDate().getMonth(), barEntries.get(ss.getDate().getMonth()).getY()+1));
        }

        shiJianShu.setText("事件总数:"+simpleDataTests.size());
        BarDataSet dataSet = new BarDataSet(barEntries, "事故次数");
        dataSet.setColors(new int[]{R.color.springgreen, R.color.yellow, R.color.red, R.color.deepskyblue}, getActivity());
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.9f); //由x坐标值已经决定了两个bar之间的间隙为1，所以这里设置bar的宽度为0.9，则bar之间的宽度就为0.1了
        barChart.clear();
        barChart.animateY(2000);    //设置动画效果
        barChart.setData(data);
        barChart.setFitBars(true);  //使所有的bar都能完整显示出来
        barChart.invalidate();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history_fuel_month, null);
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
        shiJianShu = (TextView) rootView.findViewById(R.id.shinums);
        String[] strspnianfen =ss.split(",");
        spinnerYear.setAdapter(new MyAdapter(strspnianfen,getContext()).getAdaper());
        spinnerYear.setOnItemSelectedListener(this);
        barChart = (BarChart) rootView.findViewById(R.id.bar_chart);
        setXAxis();
        initData();
        return rootView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        initData();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
