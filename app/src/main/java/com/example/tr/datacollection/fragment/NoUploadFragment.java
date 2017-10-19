package com.example.tr.datacollection.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tr.datacollection.MainActivity;
import com.example.tr.datacollection.PeopelData3;
import com.example.tr.datacollection.R;
import com.example.tr.datacollection.model.AcccidentData;
import com.example.tr.datacollection.model.AccidenceCollectionData;
import com.example.tr.datacollection.model.CarData;
import com.example.tr.datacollection.model.EMdata;
import com.example.tr.datacollection.model.PelpelData;
import com.example.tr.datacollection.model.PeopelData2;
import com.example.tr.datacollection.util.DBO;
import com.example.tr.datacollection.util.MyProcessDialog;
import com.example.tr.datacollection.util.NoNumInString;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoUploadFragment extends Fragment implements AdapterView.OnItemSelectedListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

    ListView listView;
    SimpleAdapter simpleAdapter;
    private List<Map<String,Object>>datalist;
    private List<AccidenceCollectionData> collectionDatas;
    private Button btnUpload;
    private List<Boolean> collectionBoolean = new ArrayList<>();
    private String TAG ="updataTest";
    private boolean isOK;
    private AlertDialog dialog = null;
    MyProcessDialog myProcessDialog;
 ///192.168.43.34
   final private String httpURL = "http://192.168.1.172:8080";
    final private String httpURL2 = "http://192.168.1.172:8081";
    public NoUploadFragment() {
        // Required empty public constructor
    }

    public ListView getListView(){

        return listView;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_no_upload, container, false);
        initView(view);
       // uploadAC();
        myProcessDialog = new MyProcessDialog(getContext());
        return view;
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.list);
        btnUpload = (Button) view.findViewById(R.id.btn_upload);
        datalist = new ArrayList<Map<String, Object>>();
        setListView();
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("确认上传？");
                //  builder.setView(view2);
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        int no = 0;
                        for (boolean b : collectionBoolean) {
                            if(b) {
                                uploadAC();
                                break;
                            }
                            no++;
                        }
                        dialog.dismiss();
                        if(no == collectionBoolean.size()){
                            setDialog("未选中数据！");
                        }


                    }
                });
                dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void setListView() {
        simpleAdapter = new SimpleAdapter(getContext(),getData(), R.layout.noupload_item,new String[]{"text_bianhao","text_location","text_time"},
                new int[]{R.id.text_bianhao,R.id.text_location,R.id.text_time});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemSelectedListener(this);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    public void setDialog(String text){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(text);
        //  builder.setView(view2);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
    }
    public void uploadAC(){
        myProcessDialog.showProcessing("正在上传...");
        RequestQueue mQueue = Volley.newRequestQueue(getContext());
        int i = 0;
        for(final AccidenceCollectionData collectionData : collectionDatas){
            if (!collectionBoolean.get(i++))
                continue;
                final int index = i-1;
            DBO dbo = new DBO(getContext());
            List<AcccidentData> acccidentDatas = new ArrayList<>();
            acccidentDatas = dbo.getAcccidentData(collectionData.getAccidenceNumber());
            if(acccidentDatas.size()>0) {
                AcccidentData accidentData = acccidentDatas.get(0);
                upLoadAccident(collectionData.getAccidenceNumber(),accidentData);
            }
            //环境信息
            List<EMdata> eMdatas = new ArrayList<>();
            eMdatas = dbo.getEMdata(collectionData.getEnvironmentNumber());
            if(eMdatas.size()>0) {
                EMdata eMdata = eMdatas.get(0);
                upLoadEnvironment(collectionData.getEnvironmentNumber(), eMdata);
            }
//环境信息
            List<CarData> carDatas = new ArrayList<>();
            carDatas = dbo.getCarData(collectionData.getCarNumber());
            if(carDatas.size()>0) {
                CarData carData = carDatas.get(0);
                for (CarData carData1 : carDatas) {
                    upLoadCarData(collectionData.getCarNumber(), carData1);
                }
            }
            //乘坐人
            List<PelpelData> pelpelDatas = new ArrayList<>();
            pelpelDatas = dbo.getPeopel1(collectionData.getPeopelNumber());
            for(PelpelData p : pelpelDatas){
                upLoadPeopel2(collectionData.getPeopelNumber(),p);
            }
            //驾驶人
            List<PeopelData2> peopelData2s = new ArrayList<>();
            peopelData2s = dbo.getPeopel2(collectionData.getPeopelNumber());
            for(PeopelData2 p : peopelData2s){
                upLoadPeopel1(collectionData.getPeopelNumber(),p);
            }

            //其它人

            List<PeopelData3> peopelData3s = new ArrayList<>();
            peopelData3s = dbo.getPeopel3(collectionData.getPeopelNumber());
            for (PeopelData3 peopelData3 : peopelData3s) {
                upLoadPeopel3(collectionData.getPeopelNumber(),peopelData3);
            }

                StringRequest stringRequest1 = new StringRequest(Request.Method.POST,httpURL+"/DataCollectionServer/servlet/CollectionData",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            Log.i(TAG,"OK");
                            DBO dbo = new DBO(getContext());
                            dbo.updateAccidenceCollectionData(collectionDatas.get(index),collectionDatas.get(index).getNumber(),1);
                            myProcessDialog.dismissProcessing(1000);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.i(TAG,"eee");
                    myProcessDialog.dismissProcessing();
                    setDialog("上传失败！请检查网络连接");
                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> map = new HashMap<String, String>();
//                    map.put("p1","hhh");
//                    map.put("p2","eee");eee
                    map.put("number",collectionData.getNumber());//事故编号
                    map.put("accidenceNumber",collectionData.getAccidenceNumber());//事故信息
                    map.put("environmentNumber",collectionData.getEnvironmentNumber());//环境信息
                    map.put("carNumber",collectionData.getCarNumber());//车辆信息
                    map.put("peopelNumber",collectionData.getPeopelNumber());//人员信息
                    map.put("data",collectionData.getData().getTime()+"");//时间
                    map.put("placeName",collectionData.getPlaceName());//地名
                    return map;
                }
            };
            mQueue.add(stringRequest1);
        }
        mQueue.start();
    }

    private void upLoadPeopel2(String peopelNumber, final PelpelData pelpelData) {
        RequestQueue mQueue = Volley.newRequestQueue(getContext());


        StringRequest stringRequest1 = new StringRequest(Request.Method.POST,"http://192.168.1.172:8081/upload/chenzuo",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.i(TAG,"OK");
                        Log.i(TAG,"ddd"+s);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i(TAG,"eee");
                myProcessDialog.dismissProcessing();
                setDialog("乘坐人信息上传失败！请检查网络或后台");
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String, String>();
                map.put("number", NoNumInString.start(pelpelData.getNumber()));
                map.put("name",NoNumInString.start(pelpelData.getName()));
                map.put("xingbie",NoNumInString.start(pelpelData.getXingbie()));
                map.put("shenfentype",NoNumInString.start(pelpelData.getShenfentype()));
                map.put("renyuantype",NoNumInString.start(pelpelData.getRenyuantype()));
                map.put("sschengdu",NoNumInString.start(pelpelData.getSschengdu()));
                map.put("phonenum",NoNumInString.start(pelpelData.getPhonenum()));
                map.put("cheliangxunhao",NoNumInString.start(pelpelData.getCheliangxunhao()));
                map.put("ysxitong",NoNumInString.start(pelpelData.getYsxitong()));
                map.put("tuokuishiyong",NoNumInString.start(pelpelData.getTuokuishiyong()));
                map.put("aqqnzt",NoNumInString.start(pelpelData.getAqqnzt()));
                map.put("paochuzhuangta",NoNumInString.start(pelpelData.getPaochuzhuangta()));
                map.put("sgfsszhuangtai",NoNumInString.start(pelpelData.getSgfsszhuangtai()));
                map.put("yinjiu",NoNumInString.start(pelpelData.getYinjiu()));
                map.put("duPingLeiXing",NoNumInString.start(pelpelData.getDuPingLeiXing()));
                map.put("ceshizhuangtai",NoNumInString.start(pelpelData.getCeshizhuangtai()));
                map.put("ceshitype",NoNumInString.start(pelpelData.getCeshitype()));
                map.put("ceshiresult",NoNumInString.start(pelpelData.getCeshiresult()));
                return map;
            }
        };
        mQueue.add(stringRequest1);
        mQueue.start();
    }

    private void upLoadPeopel3(String peopelNumber, final PeopelData3 pelpelData3) {
        RequestQueue mQueue = Volley.newRequestQueue(getContext());


        StringRequest stringRequest1 = new StringRequest(Request.Method.POST,"http://192.168.1.172:8081/upload/qitaren",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.i(TAG,"OK");
                        Log.i(TAG,"ddd"+s);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                myProcessDialog.dismissProcessing();
                setDialog("其它人信息上传失败！请检查网络或后台");
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String, String>();
                map.put("number",NoNumInString.start(pelpelData3.getNumber()));
                map.put("name",NoNumInString.start(pelpelData3.getSPname()));
                map.put("xingbie",NoNumInString.start(pelpelData3.getSPxingbie()));
                map.put("shenfentype",NoNumInString.start(pelpelData3.getSPshenfentype()));
                map.put("renyuantype",NoNumInString.start(pelpelData3.getSPrenyuantype()));
                map.put("sschengdu",NoNumInString.start(pelpelData3.getSPsschengdu()));
                map.put("phonenum",NoNumInString.start(pelpelData3.getPhonenum()));
                map.put("sgfssxingwei",NoNumInString.start(pelpelData3.getSPsgfssxingwei()));
                map.put("sgfsqxingwei",NoNumInString.start(pelpelData3.getSPsgfsqxingwei()));
                map.put("sgfsszhuangtai",NoNumInString.start(pelpelData3.getSPsgfsszhuangtai()));
                map.put("aqsbsyzhuangtai",NoNumInString.start(pelpelData3.getSPaqsbsyzhuangtai()));
                map.put("sgfsosition",NoNumInString.start(pelpelData3.getSPsgfssposition()));
                map.put("cheliangxunhao",NoNumInString.start(pelpelData3.getSPcheliangxunhao()));
                map.put("yinjiu",NoNumInString.start(pelpelData3.getSPyinjiu()));
                map.put("duPingLeiXing",NoNumInString.start(pelpelData3.getSPduPingLeiXing()));
                map.put("ceshizhuangtai",NoNumInString.start(pelpelData3.getSPceshizhuangtai()));
                map.put("ceshitype",NoNumInString.start(pelpelData3.getSPceshitype()));
                map.put("ceshiresult",NoNumInString.start(pelpelData3.getSPceshiresult()));
                return map;
            }
        };
        mQueue.add(stringRequest1);
        mQueue.start();
    }

    public void upLoadAccident(final String number,final AcccidentData accidentData){
        RequestQueue mQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,httpURL+"/DataCollectionServer/servlet/AccidentInfoServer",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.i(TAG,"OK");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i(TAG,"eee");
                myProcessDialog.dismissProcessing();
                setDialog("事故信息上传失败！请检查网络或后台");
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String, String>();
//                    map.put("p1","hhh");
//                    map.put("p2","eee");eee
                    map.put("accidencnumber",accidentData.getAccidencenumber()) ;
                    map.put("riQI", accidentData.getRiQI().getTime()+"");
                    map.put("city", accidentData.getCity());
                    map.put("xianQu", accidentData.getXianQu());
                    map.put("shangQuan", accidentData.getShangQuan());
                    map.put("lat", accidentData.getLnglat().latitude+"");
                    map.put("lng",  accidentData.getLnglat().longitude+"");
                    map.put("diMingBeiZhu", accidentData.getDiMingBeiZhu());
                    map.put("shigu", accidentData.getShigu());
                    map.put("shiguType", accidentData.getShiguType());
                    map.put("shigu2", accidentData.getShigu2());
                    map.put("shiguType2", accidentData.getShiguType2());
                    map.put("yanZhongCd", accidentData.getYanZhongCd());
                    map.put("carnum", accidentData.getCarnum());
                    map.put("driverNums", accidentData.getDriverNums());
                    map.put("feidriverNums", accidentData.getFeidriverNums());
                    map.put("ssNums", accidentData.getSsNums());
                    map.put("dieNums", accidentData.getDieNums());
                    map.put("gongjiao", accidentData.getGongjiao());
                    map.put("taoYi", accidentData.getTaoYi());
                    map.put("Weixianche", accidentData.isWeixianche());
                    map.put("Weixianbz", accidentData.getWeixianbz());
                    map.put("fromWeixianbz", accidentData.getFromWeixianbz());
                    map.put("youHaiWuZhi", accidentData.getYouHaiWuZhi());
                return map;
            }
        };
        mQueue.add(stringRequest);
        mQueue.start();
    }
   //上传环境信息
    public void upLoadEnvironment(final String number,final EMdata eMdata){
        RequestQueue mQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,httpURL+"/DataCollectionServer/servlet/EnvironmentDataServer",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.i(TAG,"OK");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i(TAG,"eee");
                myProcessDialog.dismissProcessing();
                setDialog("环境信息上传失败！请检查网络或后台");
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String, String>();
//                     map.put("p1","hhh");
//                     map.put("p2","eee");eee
                       map.put( "emnumber",  eMdata.getEmnumber());
                       map.put( "Routeposition",eMdata.getRouteposition());
                       map.put( "pengzhuangleixing",eMdata.getPengzhuangleixing());
                       map.put( "liJiaoQuYu",eMdata.getLiJiaoQuYu());
                       map.put( "Jcktype",eMdata.getJcktype());
                       map.put( "Teshuposition",eMdata.getTeshuposition());
                       map.put( "Tianqicondition",eMdata.getTianqicondition());
                       map.put( "zhaomingcondition",eMdata.getZhaomingcondition());
                       map.put( "luMianCondition",eMdata.getLuMianCondition());
                       map.put( "luMianLev",eMdata.getLuMianLev());
                       map.put( "limitSpeed",eMdata.getLimitSpeed());
                       map.put( "chedaowidth",eMdata.getChedaowidth());
                       map.put( "luJianWidth",eMdata.getLuJianWidth());
                       map.put( "bianYuanXianle",eMdata.getBianYuanXianle());
                       map.put( "zhongyanwidht",eMdata.getZhongyanwidht());
                       map.put( "zhongXinXian",eMdata.getZhongXinXian());
                       map.put( "chedaoxianbj",eMdata.getChedaoxianbj());
                       map.put( "jiaotongkzlx",eMdata.getJiaotongkzlx());
                       map.put( "zhuchedaoshu",eMdata.getZhuchedaoshu());
                       map.put( "jiaochajiedaoshu",eMdata.getJiaochajiedaoshu());
                       map.put( "WorkPlaceR",eMdata.getWorkPlaceR());
                       map.put( "Worktype",eMdata.getWorktype());
                       map.put( "havepeople",eMdata.getHavepeople());
                       map.put( "xianChangZhifa",eMdata.getXianChangZhifa());
                return map;
            }
        };
        mQueue.add(stringRequest);
        mQueue.start();
    }

    //上传车辆信息
    //上传环境信息
    public void upLoadCarData(final String number,final CarData carData){
        RequestQueue mQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,httpURL+"/DataCollectionServer/servlet/CarDataServer",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.i(TAG,"OK");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i(TAG,"eee");
                myProcessDialog.dismissProcessing();
                setDialog("环境信息上传失败！请检查网络或后台");
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String, String>();
//                     map.put("p1","hhh");
//                     "p2","eee");eee
                map.put("carnumber",NoNumInString.start(carData.getCarnumber()));
                map.put("xunhao",NoNumInString.start(carData.getXunhao()+""));
                map.put("vin",NoNumInString.start(carData.getVin()));
                map.put("chepaihao",NoNumInString.start(carData.getChepaihao()));
                map.put("guobie",NoNumInString.start(carData.getGuobie()));
                map.put("nianfen",NoNumInString.start(carData.getNianfen()));
                map.put("pingpai",NoNumInString.start(carData.getPingpai()));
                map.put("carType",NoNumInString.start(carData.getCarType()));
                map.put("Leixing",NoNumInString.start(carData.getLeixing()));
                map.put("cheneinum",NoNumInString.start(carData.getCheneinum()));
                map.put("tesuzuoyong",NoNumInString.start(carData.getTesuzuoyong()));
                map.put("xingshifangxing",NoNumInString.start(carData.getXingshifangxing()));
                map.put("Xingwei",NoNumInString.start(carData.getXingwei()));
                map.put("Jiechudian",NoNumInString.start(carData.getJiechudian()));
                map.put("sunhuaibuwei",NoNumInString.start(carData.getSunhuaibuwei()));
                map.put("sunhuaschengdu",NoNumInString.start(carData.getSunhuaschengdu()));
                return map;
            }
        };
        mQueue.add(stringRequest);
        mQueue.start();
    }
    private List<Map<String,Object>> getData() {
        DBO dbo = new DBO(getContext());
        collectionBoolean.clear();
        collectionBoolean = new ArrayList<>();
        datalist.clear();
        datalist = new ArrayList<>();
        collectionDatas = new ArrayList<AccidenceCollectionData>();
        collectionDatas = dbo.getAccidenceCollectionData(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-d hh:mm");
        for(int i=0;i<collectionDatas.size();i++){
            Map<String,Object>map=new HashMap<String,Object>();
            map.put("text_bianhao",collectionDatas.get(i).getNumber());
            map.put("text_location",collectionDatas.get(i).getPlaceName());
            map.put("text_time",simpleDateFormat.format(collectionDatas.get(i).getData()).toString());
            datalist.add(map);
            collectionBoolean.add(false);
        }
        return datalist;
    }

    public void reflash() {
        setListView();
    }

    public void upLoadPeopel1(final String number,final PeopelData2 pelpelData2){

        RequestQueue mQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,httpURL+"/DataCollectionServer/servlet/DriverServer",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.i(TAG,"OK");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i(TAG,"eee");
                myProcessDialog.dismissProcessing();
                setDialog("乘坐人信息上传失败！请检查网络或后台");
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String, String>();
//                    map.put("p1","hhh");
//                    map.put("p2","eee");eee
                        map.put("number",NoNumInString.start(pelpelData2.getNumber()));
                        map.put("SPname",NoNumInString.start(pelpelData2.getSPname()));
                        map.put("SPxingbie",NoNumInString.start(pelpelData2.getSPxingbie()));
                        map.put("SPshenfentype",NoNumInString.start(pelpelData2.getSPshenfentype()));
                        map.put("SPrenyuantype",NoNumInString.start(pelpelData2.getSPrenyuantype()));
                        map.put("SPsschengdu",NoNumInString.start(pelpelData2.getSPsschengdu()));
                        map.put("phonenum",NoNumInString.start(pelpelData2.getPhonenum()));
                        map.put("SPcheliangxunhao",NoNumInString.start(pelpelData2.getSPcheliangxunhao()));
                        map.put("SPysxitong",NoNumInString.start(pelpelData2.getSPysxitong()));
                        map.put("SPtuokuishiyong",NoNumInString.start(pelpelData2.getSPtuokuishiyong()));
                        map.put("SPaqqnzt",NoNumInString.start(pelpelData2.getSPaqqnzt()));
                        map.put("SPpaochuzhuangta",NoNumInString.start(pelpelData2.getSPpaochuzhuangta()));
                        map.put("SPsgfsszhuangtai",NoNumInString.start(pelpelData2.getSPsgfsszhuangtai()));
                        map.put("SPyinjiu",NoNumInString.start(pelpelData2.getSPyinjiu()));
                        map.put("SPduPingLeiXing",NoNumInString.start(pelpelData2.getSPduPingLeiXing()));
                        map.put("SPceshizhuangtai",NoNumInString.start(pelpelData2.getSPceshizhuangtai()));
                        map.put("SPceshitype",NoNumInString.start(pelpelData2.getSPceshitype()));
                        map.put("SPceshiresult",NoNumInString.start(pelpelData2.getSPceshiresult()));
                        map.put("jiazhao",NoNumInString.start(pelpelData2.getJiazhao()));
                        map.put("dengji",NoNumInString.start(pelpelData2.getDengji()));
                        map.put("shangyejiazhao",NoNumInString.start(pelpelData2.getShangyejiazhao()));
                        map.put("qianzhu",NoNumInString.start(pelpelData2.getQianzhu()));
                        map.put("guanxiaqu",NoNumInString.start(pelpelData2.getGuanxiaqu()));
                        map.put("chaosuxunwen",NoNumInString.start(pelpelData2.getChaosuxunwen()));
                        map.put("fenxinjiashi",NoNumInString.start(pelpelData2.getFenxinjiashi()));
                        map.put("sgfssxingwie",NoNumInString.start(pelpelData2.getSgfssxingwie()));
                        map.put("sgfsszhuangt",NoNumInString.start(pelpelData2.getSgfsszhuangt()));
                        map.put("jtwfxingwei",NoNumInString.start(pelpelData2.getJtwfxingwei()));
                        map.put("jiazhaoxianzhi",NoNumInString.start(pelpelData2.getJiazhaoxianzhi()));
                return map;
            }
        };
        mQueue.add(stringRequest);
        mQueue.start();
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.i("onItemLongClick","onItemSelected");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Log.i("onItemLongClick","onNothingSelected");
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        Log.i("onItemLongClick","onItemLongClick");
        //collectionsMap.get()
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.i("onItemLongClick","onItemClick");
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.btn_commit);
        collectionBoolean.set(i,!collectionBoolean.get(i));
        Log.i("onItemLongClick",""+collectionBoolean.get(i));
        if(checkBox.isChecked()){
            checkBox.setChecked(false);
        }else {
            checkBox.setChecked(true);
        }
    }
}
