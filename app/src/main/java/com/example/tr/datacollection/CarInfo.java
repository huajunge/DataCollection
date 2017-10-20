package com.example.tr.datacollection;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.internal.http.multipart.FilePart;
import com.android.internal.http.multipart.Part;
import com.android.internal.http.multipart.StringPart;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.tr.datacollection.aboutCar.CarBrand;
import com.example.tr.datacollection.aboutCar.CarBrandActivity;
import com.example.tr.datacollection.util.MyMultipartRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarInfo extends Fragment implements View.OnClickListener{

    private static final String TAG = "CarInfo";

    private static final boolean DEBUG = true;

    private static final String BASIC_URL = "http://192.168.1.126:8080/lcx/servlet";
    private static final String IMG_URL = "/DriverHeadPhotoUpload";

    String fileNameCamera = "image_header_camera.png";   // 拍照得到的临时文件名
    String fileNameCrop = "image_header_crop.png";   //裁剪后得到的临时文件名
    //最终截取到的图片的uri
    private Uri finalUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), fileNameCrop));
    private int cropWidth = 500;    //截取框的宽度
    private int cropHeight = 500;   //截取框的高度

    public static final int FROM_CROP = 1;  //从相机或图库中返回
    private static final int CROP_PICTURE = 2;  //裁剪返回

    private static final int MESSAGE_SUCCESS = 1;
    private static final int MESSAGE_FAILED = 2;

    private ImageView img1Test;  //用户头像
    private ImageView addPhotos;  //用户头像
    private ProgressDialog progressDialog;
    private RequestQueue requestQueue;
    private ListView Imgs;
    private View headerView;
    ArrayList<String> arrayList = new ArrayList<String>();
    private LinearLayout header_ll;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dismissProgressDialog();
            if (msg.what == MESSAGE_SUCCESS) {
                Toast.makeText(getContext(), "上传成功", Toast.LENGTH_SHORT).show();
            }
            if (msg.what == MESSAGE_FAILED) {
                Toast.makeText(getContext(), "上传失败，请重试！", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private EditText ed_vin;//VIN码
    private EditText ed_chepaihao;//车牌号
    private Spinner spguobie;//国别
    private Spinner spnianfen;
    private TextView sppingpai;//品牌
    private TextView spcarType;//车辆类型
    private Spinner spLeixing;
    private Spinner sppeopelnum;//车内人数
    private Spinner sptszuoyong;//特殊作用

    private Spinner spXsfangxing;//行驶方向
    private Spinner spXingwei;//行驶方向
    private Spinner spJiechudian;//接触点
    private Spinner spshbuwei;//损坏部位
    private Spinner spshchengdu;//损坏程度

    private CarBrand parentCar; //品牌
    private CarBrand childCar;  //车型

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
         String[] strspguobie={"中国","外国"};
         spguobie.setAdapter(new MyAdapter2(strspguobie,view.getContext()).getAdaper());

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
         spnianfen.setAdapter(new MyAdapter2(strspnianfen,view.getContext()).getAdaper());

       //  sppingpai=(Spinner) view.findViewById(R.id.sp_pingpai);//品牌

       //  spcarType=(Spinner) view.findViewById(R.id.sp_leixing);//车辆类型

//车内人数
         sppeopelnum=(Spinner) view.findViewById(R.id.sp_cnrenshu);//车内人数
        ss ="";
         for(int i=0;i<=55;i++){
            ss+=i+",";
        }
        String[] strsppeopelnum=ss.split(",");
        sppeopelnum.setAdapter(new MyAdapter2(strsppeopelnum,view.getContext()).getAdaper());
        
//特殊作用
         sptszuoyong=(Spinner) view.findViewById(R.id.sp_teshuzy);//特殊作用
        String[] strsptszuoyong={"无","出租车","校车","公交","军用","警用","救护","消防车辆","其它"};
        sptszuoyong.setAdapter(new MyAdapter(strsptszuoyong,view.getContext()).getAdaper());
 //紧急作用       


//行驶方向
         spXsfangxing=(Spinner) view.findViewById(R.id.sp_xsfangxiang);//行驶方向
        String[] strspXsfangxing = {"向北","向南","向东","向西","未知"};
        spXsfangxing.setAdapter(new MyAdapter(strspXsfangxing,view.getContext()).getAdaper());

//操作行为
         spXingwei=(Spinner) view.findViewById(R.id.sp_caozuo);//行驶方向
        String[] strspXingwei = {"直行","变道","超车","右转","左转","掉头","减速","加速","其它"};
        spXingwei.setAdapter(new MyAdapter(strspXingwei,view.getContext()).getAdaper());
 
//接触点        
         spJiechudian=(Spinner) view.findViewById(R.id.sp_jiechudian);//接触点
        String[] strspJiechudian = {"无碰撞","左前角","前端左侧","前端右侧","右前角","右侧前部","右侧后部","右后角","后端右侧","后端左侧","左后角","左侧后部","左侧前部","车底","车顶"};
        spJiechudian.setAdapter(new MyAdapter(strspJiechudian,view.getContext()).getAdaper());
        
//损坏部位        
         spshbuwei=(Spinner) view.findViewById(R.id.sp_shbuwei);//损坏部位
        String[] strspshbuwei = {"无","左前角","前端左侧","前端右侧","右前角","右侧前部","右侧后部","右后角","后端右侧","后端左侧","左后角","左侧后部","左侧前部","车底","车顶"};
        spshbuwei.setAdapter(new MyAdapter(strspshbuwei,view.getContext()).getAdaper());
        
//损坏程度        
         spshchengdu=(Spinner) view.findViewById(R.id.sp_shchengdu);//损坏程度
        String[] strspshchengdu = {"无损坏","轻微损坏","功能性损坏","失能性损坏","未知"};
        spshchengdu.setAdapter(new MyAdapter(strspshchengdu,view.getContext()).getAdaper());
//车辆类型
        spLeixing = (Spinner) view.findViewById(R.id.sp_leixing);
        String[] strspLeixing = {"小客车","中客车","大客车","公交","校车","小货车","中货车","大货车","拖挂车","特种车辆","摩托车","非机动车","畜力车"};
        spLeixing.setAdapter(new MyAdapter(strspLeixing,view.getContext()).getAdaper());
    //车辆品牌
        sppingpai = (TextView) view.findViewById(R.id.sp_pingpai);
        spcarType = (TextView) view.findViewById(R.id.sp_xinghao);
        sppingpai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getContext(), CarBrandActivity.class).putExtra("fromActivity", 1), 1);
            }
        });
        spcarType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (parentCar != null && !sppingpai.getText().toString().trim().equals("")) {
                    //System.out.println("传过去的值：" + ",parentid：" + parentCar.getId() + ",carBrand：" + parentCar.getBrandName());
                    startActivityForResult(new Intent(getContext(), CarBrandActivity.class).putExtra("fromActivity", 2)
                            .putExtra("parentid", parentCar.getId()).putExtra("carBrand", parentCar.getBrandName()), 2);
                } else {
                    Toast.makeText(getContext(), "请先选择车辆品牌", Toast.LENGTH_SHORT).show();
                }
            }
        });addPhotos = (ImageView) view.findViewById(R.id.addphotos);
        addPhotos.setOnClickListener(this);
        img1Test = (ImageView) view.findViewById(R.id.img1);

        Imgs = (ListView) view.findViewById(R.id.imgs);
        headerView = LayoutInflater.from(view.getContext()).inflate(
                R.layout.item_home_header, null);

        header_ll = (LinearLayout) headerView.findViewById(R.id.header_ll);
//        for (int i = 0; i < 10; i++) {
//            View coupon_home_ad_item = LayoutInflater.from(getContext()).inflate(
//                    R.layout.photoltem, null);
//            ImageView icon = (ImageView) coupon_home_ad_item
//                    .findViewById(R.id.img);// 拿个这行的icon 就可以设置图片
//            icon.setImageDrawable(getResources().getDrawable(R.drawable.addphoto));
//            header_ll.addView(coupon_home_ad_item);
//
//        }
        Imgs.addHeaderView(headerView);

        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.item,
                R.id.textView1, arrayList);
        Imgs.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        if ( DEBUG) {
            switch (v.getId()) {
                case R.id.addphotos:
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setItems(new String[]{"拍照", "相册"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0:
                                    if (hasSdCard()) {//是否有存储卡
                                        Uri imgUri = null;
                                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);    //调用相机
                                        imgUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), fileNameCamera));
                                        //指定照片保存路径，image_header_camera.png是一个临时文件,数据最终保存到imgUri中
                                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
                                        startActivityForResult(intent, FROM_CROP);
                                    } else {
                                        Toast.makeText(getActivity().getApplicationContext(), "没有存储卡！", Toast.LENGTH_SHORT).show();
                                    }
                                    break;
                                case 1://相册选取
                                    Intent libraryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                                    libraryIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                                    startActivityForResult(libraryIntent, FROM_CROP);
                            }
                        }
                    });
                    builder.setNegativeButton("取消", null);
                    builder.create().show();
                    break;
            }
        } else {
            Toast.makeText(v.getContext(), "请登录！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            if (requestCode == 1) {//选择汽车品牌
                parentCar = (CarBrand) data.getExtras().getSerializable("selectedCarBrand");
                sppingpai.setText(parentCar.getBrandName());
                //System.out.println("选取的parentCar："+parentCar);
            }
        }
        if (resultCode == 2) {
            if (requestCode == 2) {//选择汽车类型
                childCar = (CarBrand) data.getExtras().getSerializable("selectedCarBrand");
                spcarType.setText(childCar.getBrandName());
                //System.out.println("选取的childCar："+childCar);
            }
        }
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case FROM_CROP:
                    Uri uri = null;
                    if (data != null) {//得到数据的uri(相册)
                        uri = data.getData();
                    } else {//得到临时保存的文件(照相)的uri
                        uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), fileNameCamera));
                    }
                    //开始截图
                    cropImg(uri, cropWidth, cropHeight, CROP_PICTURE);
                    break;
                case CROP_PICTURE://系统截图返回
                    Bitmap photo = null;
                    photo = decodeUriAsBitmap(finalUri);    //通过 之前生成的文件的uri来获得返回的照片，而不是data对象
                    Log.i(TAG, "onActivityResult: " + finalUri);
                    //显示截图结果
                    img1Test.setImageBitmap(photo);
                    View coupon_home_ad_item = LayoutInflater.from(getContext()).inflate(
                            R.layout.photoltem, null);
                    ImageView icon = (ImageView) coupon_home_ad_item
                            .findViewById(R.id.img);// 拿个这行的icon 就可以设置图片
                    icon.setImageBitmap(photo);
                    //   icon.setImageDrawable(getResources().getDrawable(R.drawable.addphoto));
                    header_ll.addView(coupon_home_ad_item);
                    ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.item,
                            R.id.textView1, arrayList);
                    Imgs.setAdapter(adapter);
                    //上传头像
                    Log.i(TAG, "onActivityResult: finalUri.getPath() " + finalUri.getPath());
                    //     uploadImg(finalUri.getPath());
                    break;
            }
        }
    }

    //调用系统截图
    private void cropImg(Uri uri, int i, int i1, int cropPicture) {
        //系统自带截图
        Intent intent = new Intent("com.android.camera.action.CROP");
        //设置截取的图片信息
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        //裁剪框的比例 1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", cropWidth);
        intent.putExtra("outputY", cropHeight);
        //图片格式
        intent.putExtra("outputFormat", "png");
        //取消人脸识别
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);  //如果为true，图片会包含在data中返回，当数据过大时（一般是1M）就会崩溃，
        // 所以这里不通过data返回，而是通过下面的uri来获取该照片
        intent.putExtra(MediaStore.EXTRA_OUTPUT, finalUri);

        startActivityForResult(intent, CROP_PICTURE);
    }

    private Bitmap decodeUriAsBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));
            //进行图片的压缩截取
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "decodeUriAsBitmap: FileNotFoundException ", e);
            return null;
        }
        return bitmap;
    }
    private void uploadImg(String filePath) {
        List<Part> partList = new ArrayList<>();
        //partList.add(new StringPart("driPhone", "15520452757"));
        partList.add(new StringPart("driId", 2 + ""));
        showProgressDialog();
        try {
            partList.add(new FilePart("filebody", new File(filePath)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "uploadImg: ", e);
        }
        MyMultipartRequest myMultipartRequest = new MyMultipartRequest(Request.Method.POST, BASIC_URL + IMG_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    int msg = jsonObject.getInt("msg");
                    if (msg == 1) {//上传成功
                        handler.sendEmptyMessage(MESSAGE_SUCCESS);
                    } else {//上传失败
                        handler.sendEmptyMessage(0);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "onRespe:JSONException ", e);
                }
                Log.i(TAG, "onRespe: jsonObject " + s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, "onErrorResponse: ", volleyError);
                handler.sendEmptyMessage(MESSAGE_FAILED);
            }
        }, partList.toArray(new Part[partList.size()]));
        requestQueue.add(myMultipartRequest);
    }
    private boolean hasSdCard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            return true;
        return false;
    }

    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("上传头像...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    private void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
    //选择品牌
    public void choseCarBrand(View v) {

    }

    //选择车型
    public void choseCarType(View v) {

    }

    public String carnumber(){
        return null;
    }
    public int   xunhao(){
        return 0;
    }
    public String vin(){
        return ed_vin.getText().toString();
    }//VIN码
    public String chepaihao(){
        return ed_chepaihao.getText().toString();
    }//车牌号
    public String guobie(){
        return spguobie.getSelectedItem().toString();
    }//国别
    public String nianfen(){
        return spnianfen.getSelectedItem().toString();
    }
    public String pingpai(){
        return sppingpai.getText().toString();
    }//品牌
    public String carType(){
        return spcarType.getText().toString();
    }//车辆型号
    public String Leixing(){
        return spLeixing.getSelectedItem().toString();
    }//车辆类型
    public String cheneinum(){
        return sppeopelnum.getSelectedItem().toString();
    }//车内人数
    public String tesuzuoyong(){
        return sptszuoyong.getSelectedItem().toString();
    }//特殊作用
    public String xingshifangxing(){
        return spXsfangxing.getSelectedItem().toString();
    }//行驶方向
    public String Xingwei(){
        return spXingwei.getSelectedItem().toString();
    }//行驶方向
    public String Jiechudian(){
        return spJiechudian.getSelectedItem().toString();
    }//接触点
    public String sunhuaibuwei(){
        return spshbuwei.getSelectedItem().toString();
    }//损坏部位
    public String sunhuaschengdu(){
        return spshchengdu.getSelectedItem().toString();
    }//损坏程度
}
