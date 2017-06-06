package com.example.tr.datacollection;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarInfo extends Fragment {

    private EditText ed_vin;//VIN码
    private EditText ed_chepaihao;//车牌号
    private Spinner spguobie;
    private Spinner spnianfen;
    private Spinner sppingpai;//品牌
    private Spinner spcarType;//车辆类型
    private Spinner sppeopelnum;//车内人数
    private Spinner sptszuoyong;//特殊作用
    private Spinner spjinjishiyong;//紧急作用
    private Spinner sp_xiansu;//限速
    private Spinner spXsfangxing;//行驶方向
    private Spinner spXingwei;//行驶方向
    private Spinner spJiechudian;//接触点
    private Spinner spshbuwei;//损坏部位
    private Spinner spshchengdu;//损坏程度


    public CarInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_car_info, container, false);
        try {
            init(view);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return view;
    }

    private void init(View view) throws ParseException {
        //初始化控件
        ed_vin = (EditText) view.findViewById(R.id.vin);
        ed_chepaihao=(EditText) view.findViewById(R.id.chepaihao);//车牌号

         spguobie= (Spinner) view.findViewById(R.id.sp_guobie);
         String[] strspguobie={"中国"};
         spguobie.setAdapter(new MyAdapter(strspguobie,view.getContext()).getAdaper());

//年份
         spnianfen=(Spinner) view.findViewById(R.id.sp_nianfen);
         Date d = new Date(System.currentTimeMillis());
         String ss="";
         SimpleDateFormat simpled = new SimpleDateFormat("yyyy");
         d = simpled.parse(simpled.format(d));
         for(int i=Integer.valueOf(simpled.format(d));i>1980;i--){
             ss+=i+",";
         }
         String[] strspnianfen =ss.split(",");
         spnianfen.setAdapter(new MyAdapter(strspnianfen,view.getContext()).getAdaper());

         sppingpai=(Spinner) view.findViewById(R.id.sp_pingpai);//品牌

         spcarType=(Spinner) view.findViewById(R.id.sp_leixing);//车辆类型

//车内人数
         sppeopelnum=(Spinner) view.findViewById(R.id.sp_cnrenshu);//车内人数
        ss ="";
         for(int i=0;i<=55;i++){
            ss+=i+",";
        }
        String[] strsppeopelnum=ss.split(",");
        sppeopelnum.setAdapter(new MyAdapter(strsppeopelnum,view.getContext()).getAdaper());
        
//特殊作用
         sptszuoyong=(Spinner) view.findViewById(R.id.sp_teshuzy);//特殊作用
        String[] strsptszuoyong={"无特殊作用","出租车","作为学校车辆使用","像其余公交一样使用","军用","警用","救护","消防车辆","非营运紧急救险车辆","应急响应车辆","未知"};
        sptszuoyong.setAdapter(new MyAdapter(strsptszuoyong,view.getContext()).getAdaper());
 //紧急作用       
         spjinjishiyong=(Spinner) view.findViewById(R.id.sp_jinjishiyong);//紧急作用
        String[] strspjinjishiyong={"未使用","非紧急情况，非交通运行状态","非紧急情况的交通运行状态","应急运行中，未使用应急预警设备","应急运行中，使用应急预警设备","未知"};
        spjinjishiyong.setAdapter(new MyAdapter(strspjinjishiyong,view.getContext()).getAdaper());

//限速
         sp_xiansu=(Spinner) view.findViewById(R.id.sp_xiansu);//限速
         String[] strsp_xiansu={"0-30","30-50","50-80","80-100","100-110","110-130","130-150","150-180","180-200","大于200"};
        sp_xiansu.setAdapter(new MyAdapter(strsp_xiansu,view.getContext()).getAdaper());

//行驶方向
         spXsfangxing=(Spinner) view.findViewById(R.id.sp_xsfangxiang);//行驶方向
        String[] strspXsfangxing = {"向北","向南","向东","向西","不在道路上","未知"};
        spXsfangxing.setAdapter(new MyAdapter(strspXsfangxing,view.getContext()).getAdaper());

//操作行为
         spXingwei=(Spinner) view.findViewById(R.id.sp_caozuo);//行驶方向
        String[] strspXingwei = {"几乎直行","连续转弯","超重/超车","右转","左转","掉头","驶离车道","进入车道","减速","停止的","运行时停车","其他","未知"};
        spXingwei.setAdapter(new MyAdapter(strspXingwei,view.getContext()).getAdaper());
 
//接触点        
         spJiechudian=(Spinner) view.findViewById(R.id.sp_jiechudian);//接触点
        String[] strspJiechudian = {"无碰撞","十二点时钟","车顶","车底","货物丢失","未知"};
        spJiechudian.setAdapter(new MyAdapter(strspJiechudian,view.getContext()).getAdaper());
        
//损坏部位        
         spshbuwei=(Spinner) view.findViewById(R.id.sp_shbuwei);//损坏部位
        String[] strspshbuwei = {"十二点时钟图（附录1）","车顶","车底","所有区域","无损坏","未知"};
        spshbuwei.setAdapter(new MyAdapter(strspshbuwei,view.getContext()).getAdaper());
        
//损坏程度        
         spshchengdu=(Spinner) view.findViewById(R.id.sp_shchengdu);//损坏程度
        String[] strspshchengdu = {"无损坏","轻微损坏","功能性损坏","失能性损坏","未知"};
        spshchengdu.setAdapter(new MyAdapter(strspshchengdu,view.getContext()).getAdaper());

    }

}
