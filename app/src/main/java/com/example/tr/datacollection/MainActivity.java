package com.example.tr.datacollection;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.model.LatLng;
import com.example.tr.datacollection.model.AcccidentData;
import com.example.tr.datacollection.model.AccidenceCollectionData;
import com.example.tr.datacollection.model.CarData;
import com.example.tr.datacollection.model.EMdata;
import com.example.tr.datacollection.model.PelpelData;
import com.example.tr.datacollection.model.PeopelData2;
import com.example.tr.datacollection.util.DBO;
import com.example.tr.datacollection.util.MyProcessDialog;
import com.example.tr.datacollection.util.SendDataToServer;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.tr.datacollection.R.id.chepaihao;
import static com.example.tr.datacollection.R.id.vin;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView frg1;
    private ImageView frg2;
    private ImageView frg3;
    private ImageView frg4;
    private TextView tile;
    private String[] s_renyuantype = {"驾驶人", "乘坐人", "其他涉及人员"};
    private Context context;
    private AlertDialog dialog = null;
    ViewPager vp_main;
    FragmentPagerAdapter fAdapter;
    List<Fragment> mFrags;

    private boolean ISDEBUG = false;//是否调试模式

    private AcccidentData acccidentData = new AcccidentData();
    private EMdata eMdata = new EMdata();
    private List<CarData> carDatas = new ArrayList<CarData>();
    private PelpelData pelpelData1 = new PelpelData();
    private PeopelData2 peopelData2 = new PeopelData2();
    private PeopelData3 peopelData3 = new PeopelData3();

    private int carsOrder = 1;

    private String sgId = "";
    private boolean isSAVED = false;
    //上传线程
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SendDataToServer.SEND_SUCCESS:
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    break;
                case SendDataToServer.SEND_FAIL:
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void testUpload() {
        //String number, String accidenceNumber, String environmentNumber, String carNumber, String peopelNumber, Date data, String placeName, boolean isUpload
        DBO dao = new DBO(this);
        Log.i("TEST", "初始化成功");
        dao.insertToCollection(new AccidenceCollectionData("2017100601", "2017100601", "017100601", "2017100601", "2017100601", new java.sql.Date(System.currentTimeMillis()), "你猜", 0));
        dao.insertToCollection(new AccidenceCollectionData("2017100602", "2017100602", "017100601", "2017100601", "2017100601", new java.sql.Date(System.currentTimeMillis()), "你猜哇", 1));
        Log.i("TEST", "插入成功");
        List<AccidenceCollectionData> accidenceNumber = new ArrayList<AccidenceCollectionData>();

        accidenceNumber = dao.getAccidenceCollectionData(System.currentTimeMillis() - 24 * 60 * 60 * 1000, System.currentTimeMillis());
        Log.i("TEST", "查询成功");
        for (AccidenceCollectionData a : accidenceNumber) {
            Log.i("TEST", "输出");
            System.out.println(a.toString());
        }
        //  dao.clearAll();
    }

    private void init() {
        initId();
        tile = (TextView) findViewById(R.id.title);//标题

        frg1 = (ImageView) findViewById(R.id.frag_em);
        frg2 = (ImageView) findViewById(R.id.frag_car);
        frg3 = (ImageView) findViewById(R.id.frag_accident);
        frg4 = (ImageView) findViewById(R.id.frag_driver);
        frg1.setOnClickListener(this);
        frg2.setOnClickListener(this);
        frg3.setOnClickListener(this);
        frg4.setOnClickListener(this);
        vp_main = (ViewPager) findViewById(R.id.vpmain);
        mFrags = new ArrayList<>();
        mFrags.add(new AccidentInfo());
        mFrags.add(new EmInfo());
        mFrags.add(new CarInfo());

        mFrags.add(new DriverInfo());

        mFrags.get(0);
        selectFragment(0);
        fAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFrags.get(position);
            }

            @Override
            public int getCount() {
                return mFrags.size();
            }
        };

        vp_main.setAdapter(fAdapter);
        vp_main.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                vp_main.setCurrentItem(position);
                selectFragment(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initId() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddhhmmss");
        sgId = "SG" + sf.format(date);
    }

    public void selectFragment(int position) {
        switch (position) {
            case 1:
                frg1.setBackground(getResources().getDrawable(R.drawable.icon_em_on));
                frg2.setBackground(getResources().getDrawable(R.drawable.icon_car_off));
                frg3.setBackground(getResources().getDrawable(R.drawable.icon_accident_off));
                frg4.setBackground(getResources().getDrawable(R.drawable.icon_driver_off));
                tile.setText("环境信息");
//                AccidentInfo accidentInfo = (AccidentInfo) mFrags.get(0);
//                Toast.makeText(this,accidentInfo.GetCITY()+"",Toast.LENGTH_LONG).show();
                break;
            case 2:
                frg1.setBackground(getResources().getDrawable(R.drawable.icon_em));
                frg2.setBackground(getResources().getDrawable(R.drawable.icon_car_on));
                frg3.setBackground(getResources().getDrawable(R.drawable.icon_accident_off));
                frg4.setBackground(getResources().getDrawable(R.drawable.icon_driver_off));
                tile.setText("车辆信息");
                break;
            case 0:
                frg1.setBackground(getResources().getDrawable(R.drawable.icon_em));
                frg2.setBackground(getResources().getDrawable(R.drawable.icon_car_off));
                frg3.setBackground(getResources().getDrawable(R.drawable.icon_accident_on));
                frg4.setBackground(getResources().getDrawable(R.drawable.icon_driver_off));
                tile.setText("事故信息");
                break;
            case 3:
                frg1.setBackground(getResources().getDrawable(R.drawable.icon_em));
                frg2.setBackground(getResources().getDrawable(R.drawable.icon_car_off));
                frg3.setBackground(getResources().getDrawable(R.drawable.icon_accident_off));
                frg4.setBackground(getResources().getDrawable(R.drawable.icon_driver_on));
                tile.setText("人员信息");
                DriverInfo driverInfo = (DriverInfo) mFrags.get(3);
                driverInfo.refreshXunhao();
//                Spinner spinnerXunhao = (Spinner) findViewById(R.id.sp_cheliangxunhao);
//                String[] strXunhao = new String[carDatas.size()];
//                int i=0;
//                for(CarData c :carDatas){
//                    strXunhao[i++]=c.getXunhao()+" "+c.getChepaihao();
//                }
//             //   Intent intent = new Intent()
//                try{
//                    spinnerXunhao.setAdapter(new MyAdapter2(strXunhao,MainActivity.this).getAdaper());
//                }catch (Exception e){
//
//                }

                break;
        }
        vp_main.setCurrentItem(position);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.frag_em:
                selectFragment(1);
                break;
            case R.id.frag_car:
                selectFragment(2);
                break;
            case R.id.frag_accident:
                selectFragment(0);
                break;
            case R.id.frag_driver:
                selectFragment(3);
                break;

        }
    }

    public void addPeopel(final View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("新增人员类型");

        builder.setSingleChoiceItems(s_renyuantype, 0, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        Intent intent = new Intent(view.getContext(), PeopelInfo2.class);
                        startActivity(intent);

                        break;
                    case 1:
                        Intent intent2 = new Intent(view.getContext(), Peopelnfo1.class);
                        startActivity(intent2);

                        break;
                    case 2:
                        Intent intent3 = new Intent(view.getContext(), PeopelInfo3.class);
                        startActivity(intent3);

                        break;
                }
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    public void updateInfo(final View view) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view2 = inflater.inflate(R.layout.updata, null);
        //保存
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认保存？");
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

                Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_LONG).show();
//                DBO dbo =new DBO(MainActivity.this);
//                dbo.insertToSimpleTest(new SimpleDataTest(acccidentData.getLnglat().latitude,
//                        acccidentData.getLnglat().longitude,date.getTime(),acccidentData.getCity()+acccidentData.getXianQu()+acccidentData.getShangQuan()+"  "+acccidentData.getDiMingBeiZhu(),acccidentData.getYanZhongCd()));
//
//                List<SimpleDataTest> simpleDataTests = dbo.getsimpleDataTest();
//                for(SimpleDataTest s:simpleDataTests){
//                    Log.i("simpleDataTests",s.toString()+"------------");
//                }
                uploadAll(view);
                dialog.dismiss();
                //上传数据，测试
                if (ISDEBUG) {
                    testUpload();
                }

                MyProcessDialog myProcessDialog = new MyProcessDialog(MainActivity.this);
                myProcessDialog.showProcessing("正在保存...", 2000);
                //JSONObject jsonObject = new JSONWriter(acccidentData);
                //  JSONObject jsonObject = new JSONObject((Map) acccidentData);
                //   jsonObject.
                //   JSONObject jsonObject = JSONObject.fromObject(acccidentData);
                //  new SendDataToServer(handler,"http://192.168.1.100:8080/DataCollectionServer/servlet/AccidentInfoServer").SendDataToServer(jsonObject.toString());
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    private void uploadAll(View view) {
        //获取环境信息
        AccidentInfo accidentInfo = (AccidentInfo) mFrags.get(0);

        final Date date = accidentInfo.getCal().getTime();
        //经纬度
        String[] latlngs = accidentInfo.Getjingweidu().split(",");
        LatLng latLng = new LatLng(Double.valueOf(latlngs[1]), Double.valueOf(latlngs[0]));

        acccidentData = new AcccidentData(sgId, date, accidentInfo.GetCITY(), accidentInfo.GetCOUNTRY(), accidentInfo.Getxiangzhen(), latLng,
                accidentInfo.GetdimingBeiZhu(), accidentInfo.Getspshigu(), accidentInfo.GetspshiguType(), accidentInfo.Getspshigu2(), accidentInfo.GetspshiguType2(),
                accidentInfo.Getspyanzhongcd(), accidentInfo.Getcarnum(), accidentInfo.GetdriverNums(), accidentInfo.GetFeidriverNums(), accidentInfo.Getssrenshu(),
                accidentInfo.GetdieNums(), accidentInfo.Getspgongjiao(), accidentInfo.Getspzhaoshity(), accidentInfo.weiX, accidentInfo.GetWeixianbz(),
                accidentInfo.Getspfromweixianbz(), accidentInfo.getRyouhaiwuzhi());
        Log.i("data", acccidentData.toString());
        //插入事故数据
        DBO dbo = new DBO(this);


//获取环境信息
        EmInfo emInfo = (EmInfo) mFrags.get(1);
        //获取车辆数据
        eMdata = new EMdata(
                sgId,
                emInfo.Routeposition(),//路面位置
                emInfo.pengzhuangleixing(),//碰撞类型
                emInfo.liJiaoQuYu(),//立交区域
                emInfo.Jcktype(),//交叉口类型
                emInfo.Teshuposition(),//特殊位置
                emInfo.Tianqicondition(),//天气状况
                emInfo.zhaomingcondition(),//照明状况
                emInfo.luMianCondition(),//路面状况
                emInfo.luMianLev(),//路面等级
                emInfo.limitSpeed(),//限速
                emInfo.chedaowidth(),//车道宽度
                emInfo.luJianWidth(),//路肩宽度
                emInfo.bianYuanXianle(),//边缘线类型
                emInfo.zhongyanwidht(),//中央带宽带
                emInfo.zhongXinXian(),//中心线类型
                emInfo.chedaoxianbj(),//车道线标记
                emInfo.jiaotongkzlx(),//交通控制类型
                emInfo.zhuchedaoshu(),//主车道数
                emInfo.jiaochajiedaoshu(),//交叉街道数
                emInfo.WorkPlaceR(),//与工作区相关
                emInfo.Worktype(),//工作区类型
                emInfo.Havepeople(),//是否存在工人
                emInfo.xianChangZhifa()//现场执法
        );
        Log.i("data", eMdata.toString());

        List<EMdata> eMdatas = new ArrayList<>();
        type = 1;
        addNewCar(view);
        //获取人员信息
        DriverInfo driverInfo = (DriverInfo) mFrags.get(3);
        List<PelpelData> pelpelDatas = driverInfo.getPeopel1();
        List<PeopelData2> peopelData2s = driverInfo.getPeopel2();
        List<PeopelData3> peopelData3s = driverInfo.getPeopel3();
        setCarData(carsOrder - 2);
        if (isSAVED) {
            dbo.updateAccidentData(acccidentData, sgId);
            dbo.updateEMdata(eMdata, sgId);
            for (CarData c : carDatas) {
                dbo.updateCarData(c, sgId, c.getXunhao());
            }
            for (PelpelData pelpelData : pelpelDatas) {
                pelpelData.setNumber(sgId);
                dbo.updatePeopel1(pelpelData);
            }

            for (PeopelData2 peopelData2 : peopelData2s) {
                peopelData2.setNumber(sgId);
                dbo.updatePeopel2(peopelData2);
            }

            for (PeopelData3 peopelData3 : peopelData3s) {
                peopelData3.setNumber(sgId);
                dbo.updatePeopel3(peopelData3);
            }

        } else {
            isSAVED = true;
            dbo.insertToCollection(new AccidenceCollectionData(sgId, sgId, sgId, sgId, sgId, new java.sql.Date(acccidentData.getRiQI().getTime()),
                    accidentInfo.GetCITY() + accidentInfo.GetCOUNTRY() + accidentInfo.Getxiangzhen() + accidentInfo.GetdimingBeiZhu(), 0));
            dbo.insertToEMdata(eMdata);
            dbo.insertToAccidentData(acccidentData);
            for (CarData c : carDatas) {
                dbo.insertToCarData(c);
            }
            for (PelpelData pelpelData : pelpelDatas) {
                dbo.insertToPeopel1(pelpelData, sgId);
            }
            for (PeopelData2 peopelData2 : peopelData2s) {
                dbo.insertToPeopel2(peopelData2, sgId);
            }

            for (PeopelData3 peopelData3 : peopelData3s) {
                dbo.insertToPeopel3(peopelData3, sgId);
            }
        }
    }

    public void addNewCar(View view) {
        //mFrags.set(2,new CarInfo());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确定保存？");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText editTextVin = (EditText) MainActivity.this.findViewById(vin);
                EditText editTextChepaiHao = (EditText) MainActivity.this.findViewById(chepaihao);
                AccidentInfo accidentInfo = (AccidentInfo) mFrags.get(0);
                // int cs = Integer.parseInt(accidentInfo.Getcarnum());
                String[] str = accidentInfo.Getcarnum().split("[^\\D]");
                Log.i("cars", "carsnum" + str[0]);
                Log.i("cars", str[0] + "  :  " + carsOrder);
                try {
                    BigInteger cs = trimToNumber(accidentInfo.Getcarnum());
                    if (carsOrder > cs.intValue()) {
                        Toast.makeText(MainActivity.this, "添加失败，已添加完成所有车辆信息！", Toast.LENGTH_LONG).show();
                        Log.i("cars", str[0] + "  :  " + carsOrder);
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_SHORT).show();

                setCarData(-1);//设置车辆
                BigInteger cs = trimToNumber(accidentInfo.Getcarnum());
                if (carsOrder == cs.intValue()) {
                    Toast.makeText(MainActivity.this, "已添加完成所有车辆信息！", Toast.LENGTH_LONG).show();
                    ++carsOrder;
                    return;
                }
                TextView textView = (TextView) findViewById(R.id.carorder);
                textView.setText("当前车辆序号:" + (++carsOrder));
                editTextChepaiHao.setText("");
                editTextVin.setText("");
                dialog.dismiss();
            }
        });

        dialog = builder.create();
        dialog.show();
        if (type == 1) {
            type = 0;
            dialog.dismiss();
        }
    }

    public void setCarData(int i) {

        try {
            CarInfo carInfo = (CarInfo) mFrags.get(2);
            CarData carData = new CarData(
                    sgId,
                    carInfo.xunhao(),
                    carInfo.vin(),//VIN码
                    carInfo.chepaihao(),//车牌号
                    carInfo.guobie(),//国别
                    carInfo.nianfen(),
                    carInfo.pingpai(),//品牌
                    carInfo.carType(),//车辆型号
                    carInfo.Leixing(),//车辆类型
                    carInfo.cheneinum(),//车内人数
                    carInfo.tesuzuoyong(),//特殊作用
                    carInfo.xingshifangxing(),//行驶方
                    carInfo.Xingwei(),//行驶方向
                    carInfo.Jiechudian(),//接触点
                    carInfo.sunhuaibuwei(),//损坏部位
                    carInfo.sunhuaschengdu()//损坏程度
            );
            carData.setXunhao(carsOrder);
            if (i < 0) {
                carDatas.add(carData);
            } else {
                carDatas.set(i, carData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BigInteger trimToNumber(String s) {
        int n = s.length();
        char[] a = new char[n];
        int len = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                a[len++] = ch;
            }
        }
        return new BigInteger(new String(a, 0, len));
    }

    public static void main(String[] args) {
        String str = "123、4567/885*54666+8874";
        BigInteger n = trimToNumber(str);
        System.out.println(n);
    }

    public List<CarData> getCarData() {
        return carDatas;
    }

    public void upLoad(View view) {
        uploadAll(view);
        Intent intent = new Intent(MainActivity.this, UploadData.class);
        startActivity(intent);
    }
}
