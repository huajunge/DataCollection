package com.example.tr.datacollection;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.internal.http.multipart.FilePart;
import com.android.internal.http.multipart.Part;
import com.android.internal.http.multipart.StringPart;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.tr.datacollection.util.MyMultipartRequest;
import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.example.tr.datacollection.R.id.videoView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccidentInfo extends Fragment implements View.OnClickListener, UniversalVideoView.VideoViewCallback {

    private static final String TAG = "AccidentInfo";

    private static final boolean DEBUG = true;

    private static final String BASIC_URL = "http://192.168.1.126:8080/lcx/servlet";
    private static final String IMG_URL = "/DriverHeadPhotoUpload";
    private static final int RESULT_VIDEO_CODE = 5;

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
    private RadioGroup Ryouhaiwuzhi;
    public boolean weiX;
    LinearLayout PZLX;
    LinearLayout PZLX2;
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
    private TextView Time;
    private TextView TimeHours;
    private ProgressDialog processDialog = null;
    public TextView CITY;
    private TextView COUNTRY;
    private TextView xiangzhen;
    private TextView jingweidu;
    private EditText dimingBeiZhu;
    private Spinner spyanzhongcd;

    private Spinner spgongjiao;
    private Spinner spzhaoshity;
    private Spinner spweixian;
    private Spinner spshigu;
    private Spinner spshiguType;

    private Spinner spshigu2;//事故2
    private Spinner spshiguType2;
    private Spinner spfromweixianbz;

    private EditText driverNums;
    private EditText ssrenshu;
    private EditText dieNums;
    private EditText feidriverNums;
    private Spinner carnum;
    private LinearLayout SHIGU2;
    private CheckBox CheckBoxShifu2;
    private CheckBox CheckBoxWeixina;//危险车
    private RadioGroup Weixianbz;
    private LinearLayout WXbiaozhi;
    private RadioButton YES;
    private RadioButton NO;
    private Spinner spCaraccident;
    private Spinner spCaraccident2;

    private LinearLayout DANGER;
    String[] strspShiguType_feipz = {"翻车", "坠落", "火灾", "爆炸", "淹没", "其它非碰撞"};
    String[] strspShiguType_feigudingc = {"机动车", "行人", "摩托车", "电动车", "自行车", "动物", "其它非固定物"};
    String[] strspShiguType_guding = {"防撞墩/桶", "护栏", "桥梁栏杆", "路缘石", "隔离墩", "信号灯杆", "路灯杆", "标志牌柱", "树木", "其它固定物"};
    private String[] xingqi = {"一", "二", "三", "四", "五", "六", "七"};
    private Calendar cal;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    //视频录制
    private File myRecAudioFile;
    private Button buttonStart;
    private File dir;
    private UniversalMediaController mediaController;
    private UniversalVideoView mVideoView;
    private RelativeLayout mVideoLayout;

    public AccidentInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accident_info, container, false);
        init(view);
        return view;
    }

    private void init(View view) {

        //时间
        //获取时间对象
        cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);

        Time = (TextView) view.findViewById(R.id.text_day);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 E");
        Date date = new Date(System.currentTimeMillis());
        Time.setText("" + simpleDateFormat.format(date));
        LinearLayout linearLayoutRiQi = (LinearLayout) view.findViewById(R.id.setriqi);
        linearLayoutRiQi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        // setTitle(i+"-"+(i1+1)+"-"+i2);
                        //  datePicker.getwe
                        // datePicker.get
                        cal.get(Calendar.DAY_OF_WEEK);
                        Calendar cal2 = new GregorianCalendar(i, i1, i2);
                        //cal2.gett
                        cal = cal2;
                        Time.setText(i + "年" + (i1 + 1) + "月" + i2 + "日" + "  星期" + xingqi[(cal2.get(Calendar.DAY_OF_WEEK) + 5) % 7]);

                    }
                }, year, cal.get(Calendar.MONTH), day).show();
            }
        });
        TimeHours = (TextView) view.findViewById(R.id.text_time);
        LinearLayout linearLayoutHours = (LinearLayout) view.findViewById(R.id.sethours);
        simpleDateFormat = new SimpleDateFormat("HH:mm");
        TimeHours.setText(simpleDateFormat.format(date));

        linearLayoutHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        TimeHours.setText(i + ":" + i1);
                    }
                }, hour, minute, true).show();
            }
        });
        //事故位置信息
        Intent intent = getActivity().getIntent();
        try {
            CITY = (TextView) view.findViewById(R.id.sp_city);

            CITY.setText(intent.getStringExtra("city").toString());
            COUNTRY = (TextView) view.findViewById(R.id.sp_country);
            COUNTRY.setText(intent.getStringExtra("country").toString());

            xiangzhen = (TextView) view.findViewById(R.id.sp_shangquan);
            xiangzhen.setText(intent.getStringExtra("xiangzhen").toString());

            dimingBeiZhu = (EditText) view.findViewById(R.id.dimingbz);
            dimingBeiZhu.setText(intent.getStringExtra("beizhu").toString());
            jingweidu = (TextView) view.findViewById(R.id.text_jinweidu);
            jingweidu.setText(intent.getDoubleExtra("lng", 0.0f) + "," + intent.getDoubleExtra("lat", 0.0f));
        } catch (Exception e) {
            Log.i("city", e.toString());
        }

        SHIGU2 = (LinearLayout) view.findViewById(R.id.shigu2);
        DANGER = (LinearLayout) view.findViewById(R.id.dangers);
        WXbiaozhi = (LinearLayout) view.findViewById(R.id.wxbiaowei);
        //
        YES = (RadioButton) view.findViewById(R.id.yes);
        NO = (RadioButton) view.findViewById(R.id.no);
        YES.setChecked(true);
        CheckBoxShifu2 = (CheckBox) view.findViewById(R.id.checkbox_shigu2);
        CheckBoxShifu2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    SHIGU2.setVisibility(LinearLayout.VISIBLE);
                } else {
                    SHIGU2.setVisibility(LinearLayout.GONE);
                }
            }
        });
        CheckBoxWeixina = (CheckBox) view.findViewById(R.id.checkbox_weixianche);
        CheckBoxWeixina.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    DANGER.setVisibility(LinearLayout.VISIBLE);
                    weiX = true;
                } else {
                    DANGER.setVisibility(LinearLayout.GONE);
                    weiX = false;
                }
            }
        });
        Weixianbz = (RadioGroup) view.findViewById(R.id.weixianbiaozhi);
        //Weixianbz.setId(R.id.yes);
        Weixianbz.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                Log.i("info", "" + i);
                switch (i) {
                    case R.id.yes:
                        WXbiaozhi.setVisibility(LinearLayout.VISIBLE);
                        break;
                    case R.id.no:
                        WXbiaozhi.setVisibility(LinearLayout.GONE);
                        break;
                }
            }
        });
//事故类型
        spshiguType = (Spinner) view.findViewById(R.id.sp_shijian_type);
        spshiguType.setAdapter(new MyAdapter(strspShiguType_feipz, view.getContext()).getAdaper());
        spshigu = (Spinner) view.findViewById(R.id.shijian);
        String[] strspshigu = {"非碰撞", "撞人、撞车等非固定物", "碰撞固定物"};
        spshigu.setAdapter(new MyAdapter(strspshigu, view.getContext()).getAdaper());

        spshigu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    switch (i) {
                        case 0:
                            spshiguType.setAdapter(new MyAdapter(strspShiguType_feipz, view.getContext()).getAdaper());
                            break;
                        case 1:
                            spshiguType.setAdapter(new MyAdapter(strspShiguType_feigudingc, view.getContext()).getAdaper());
                            break;
                        case 2:
                            spshiguType.setAdapter(new MyAdapter(strspShiguType_guding, view.getContext()).getAdaper());
                            break;
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//事故二级分类

        PZLX = (LinearLayout) view.findViewById(R.id.pzlx);
        spshiguType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spshigu.getSelectedItemPosition() == 1 && (i == 0)) {
                    //  LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.pzlx);
                    PZLX.setVisibility(LinearLayout.VISIBLE);
                } else {
                    PZLX.setVisibility(LinearLayout.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spCaraccident = (Spinner) view.findViewById(R.id.sp_caraccident);
        String[] strspCaraccident = {"追尾碰撞", "正面碰撞", "侧面碰撞", "直角碰撞", "刮擦", "其它"};
        spCaraccident.setAdapter(new MyAdapter(strspCaraccident, view.getContext()).getAdaper());

        spCaraccident2 = (Spinner) view.findViewById(R.id.sp_caraccident2);
        spCaraccident2.setAdapter(new MyAdapter(strspCaraccident, view.getContext()).getAdaper());
//事故二
        spshiguType2 = (Spinner) view.findViewById(R.id.sp_shijian_type2);
        spshiguType2.setAdapter(new MyAdapter(strspShiguType_feipz, view.getContext()).getAdaper());

        PZLX2 = (LinearLayout) view.findViewById(R.id.pzlx2);
        spshiguType2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spshigu2.getSelectedItemPosition() == 1 && (i == 0)) {
                    //  LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.pzlx);
                    PZLX2.setVisibility(LinearLayout.VISIBLE);
                } else {
                    PZLX2.setVisibility(LinearLayout.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spshigu2 = (Spinner) view.findViewById(R.id.shijian2);
        String[] strspshigu2 = {"非碰撞", "撞人、撞车等非固定物", "碰撞固定物"};
        spshigu2.setAdapter(new MyAdapter(strspshigu2, view.getContext()).getAdaper());
        spshigu2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    switch (i) {
                        case 0:
                            spshiguType2.setAdapter(new MyAdapter(strspShiguType_feipz, view.getContext()).getAdaper());
                            break;
                        case 1:
                            spshiguType2.setAdapter(new MyAdapter(strspShiguType_feigudingc, view.getContext()).getAdaper());
                            break;
                        case 2:
                            spshiguType2.setAdapter(new MyAdapter(strspShiguType_guding, view.getContext()).getAdaper());
                            break;
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//事故严重程度
        spyanzhongcd = (Spinner) view.findViewById(R.id.sp_shiguchengdu);
        String[] strspyanzhongcd = {"仅财损", "轻伤", "重伤", "死亡", "未知"};
        spyanzhongcd.setAdapter(new MyAdapter(strspyanzhongcd, view.getContext()).getAdaper());

//事故二级分类


        spgongjiao = (Spinner) view.findViewById(R.id.sp_busshuxing);
        String[] strspgongjiao = {"无", "出租车", "校车", "公交", "军用", "警用", "救护", "消防车辆", "其它"};
        spgongjiao.setAdapter(new MyAdapter(strspgongjiao, view.getContext()).getAdaper());

//事故严重程度
        carnum = (Spinner) view.findViewById(R.id.jidongcheshu);
        String[] strscarnum = {"1辆", "2辆", "大于等于3辆"};
        carnum.setAdapter(new MyAdapter2(strscarnum, view.getContext()).getAdaper());

        spzhaoshity = (Spinner) view.findViewById(R.id.sp_run);
        String[] strspzhaoshity = {"否", "是"};
        spzhaoshity.setAdapter(new MyAdapter2(strspzhaoshity, view.getContext()).getAdaper());
//危险标识
        spfromweixianbz = (Spinner) view.findViewById(R.id.sp_weixianbiaozhi);
        String[] strspfromweixianbz = {"无危险有害物识别码", "01 菱形或方形框中间的4位危险有害物识别码", "02 菱形底部的1位分类码"};
        spfromweixianbz.setAdapter(new MyAdapter2(strspfromweixianbz, view.getContext()).getAdaper());

        spweixian = (Spinner) view.findViewById(R.id.sp_dangers);
        String[] strspweixian = {"是", "否"};
        spweixian.setAdapter(new MyAdapter(strspweixian, view.getContext()).getAdaper());
        Ryouhaiwuzhi = (RadioGroup) view.findViewById(R.id.youhaiwuzhi);

        driverNums = (EditText) view.findViewById(R.id.renshu);
        ssrenshu = (EditText) view.findViewById(R.id.ssrenshu);
        dieNums = (EditText) view.findViewById(R.id.swrenshu);
        feidriverNums = (EditText) view.findViewById(R.id.outrenshu);

        addPhotos = (ImageView) view.findViewById(R.id.addphotos);
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

        buttonStart = (Button) view.findViewById(R.id.start);
        File defaultDir = Environment.getExternalStorageDirectory();
        String path = defaultDir.getAbsolutePath() + File.separator + "V" + File.separator;//创建文件夹存放视频
        dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recorder();
            }
        });
        mVideoLayout = (RelativeLayout) view.findViewById(R.id.video_layout);
        mediaController = (UniversalMediaController) view.findViewById(R.id.media_controller);
        mVideoView = (UniversalVideoView) view.findViewById(videoView);
        mVideoView.setMediaController(mediaController);
        mVideoView.setVideoViewCallback(this);

        setVideoAreaSize();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mVideoView != null)
            mVideoView.setFocusable(false);
    }

    private int cachedHeight;

    /**
     * 置视频区域大小
     */
    private void setVideoAreaSize() {
        mVideoLayout.post(new Runnable() {
            @Override
            public void run() {
                int width = mVideoLayout.getWidth();
                cachedHeight = (int) (width * 9f / 16f);
                //cachedHeight = (int) (width * 405f / 720f);
//                cachedHeight = (int) (width * 3f / 4f);
//                cachedHeight = (int) (width * 9f / 16f);
                ViewGroup.LayoutParams videoLayoutParams = mVideoLayout.getLayoutParams();
                videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                videoLayoutParams.height = cachedHeight;
                mVideoLayout.setLayoutParams(videoLayoutParams);
            }
        });
    }

    public void recorder() {
        Calendar calendar = Calendar.getInstance();
        String s = "" + calendar.get(Calendar.YEAR) + (calendar.get(Calendar.MONTH) + 1) + calendar.get(Calendar.DAY_OF_MONTH)
                + calendar.get(Calendar.HOUR_OF_DAY) + calendar.get(Calendar.MINUTE) + calendar.get(Calendar.SECOND);
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        File file = null;
        try {
            file = new File(Environment.getExternalStorageDirectory().getCanonicalFile() + "/myvideo" + s + ".mp4");
        } catch (IOException e) {
            e.printStackTrace();
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName() + ".provider", file));
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); // set the video image quality to high
        startActivityForResult(intent, RESULT_VIDEO_CODE);
    }


    public void dismissProcessing() {
        if (processDialog != null && processDialog.isShowing()) {
            processDialog.dismiss();
            processDialog = null;
        }
    }

    public void showProcessing(String hintText) {
        if (processDialog == null) {
            processDialog = new ProgressDialog(getContext());
        }
        processDialog.setMessage(hintText);
        processDialog.setCancelable(true);
        processDialog.show();
    }

    @Override
    public void onClick(View v) {
        if (DEBUG) {
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
                                        Toast.makeText(getActivity().getApplicationContext(), "没有存储卡，无法拍摄！", Toast.LENGTH_SHORT).show();
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
                    final View coupon_home_ad_item = LayoutInflater.from(getContext()).inflate(
                            R.layout.photoltem, null);
                    ImageView icon = (ImageView) coupon_home_ad_item
                            .findViewById(R.id.img);// 拿个这行的icon 就可以设置图片
                    icon.setImageBitmap(photo);
                    //   icon.setImageDrawable(getResources().getDrawable(R.drawable.addphoto));
                    header_ll.addView(coupon_home_ad_item);
                    ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.item,
                            R.id.textView1, arrayList);
                    Imgs.setAdapter(adapter);
                    Button cancel = (Button) coupon_home_ad_item.findViewById(R.id.cancel);
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            coupon_home_ad_item.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                    //上传头像
                    Log.i(TAG, "onActivityResult: finalUri.getPath() " + finalUri.getPath());
                    //     uploadImg(finalUri.getPath());
                    break;
                case RESULT_VIDEO_CODE:
                    if (data != null) {
                        if (data.getData() != null) {
                            mVideoView.setVideoURI(data.getData());
                            mVideoView.requestFocus();
                        }
                    }
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

    public String GetTime() {
        return Time.getText().toString();
    }

    public String GetTimeHours() {
        return TimeHours.getText().toString();
    }

    public String GetCITY() {
        return CITY.getText().toString();
    }

    public String GetCOUNTRY() {
        return COUNTRY.getText().toString();
    }

    public String Getxiangzhen() {
        return xiangzhen.getText().toString();
    }

    public String Getjingweidu() {
        return jingweidu.getText().toString();
    }

    public String GetdimingBeiZhu() {
        return dimingBeiZhu.getText().toString();
    }

    public String Getspyanzhongcd() {
        return spyanzhongcd.getSelectedItem().toString();
    }

    public String Getspgongjiao() {
        return spgongjiao.getSelectedItem().toString();
    }

    public String Getspzhaoshity() {
        return spzhaoshity.getSelectedItem().toString();
    }

    public String Getspweixian() {
        return spweixian.getSelectedItem().toString();
    }

    public String Getspshigu() {
        return spshigu.getSelectedItem().toString();
    }

    public String GetspshiguType() {
        return spshiguType.getSelectedItem().toString();
    }

    public String Getspshigu2() {
        return spshigu2.getSelectedItem().toString();
    }//事故2

    public String GetspshiguType2() {
        return spshiguType2.getSelectedItem().toString();
    }

    public String Getspfromweixianbz() {
        return spfromweixianbz.getSelectedItem().toString();
    }

    public String GetdriverNums() {
        return driverNums.getText().toString();
    }

    public String Getssrenshu() {
        return ssrenshu.getText().toString();
    }

    public String GetdieNums() {
        return dieNums.getText().toString();
    }

    public String Getcarnum() {
        return carnum.getSelectedItem().toString();
    }

    public String GetCheckBoxShifu2() {
        return CheckBoxShifu2.getText().toString();
    }

    public String GetCheckBoxWeixina() {
        return CheckBoxWeixina.getText().toString();
    }//危险车

    public String GetWeixianbz() {
        try {
            RadioButton radioButton = (RadioButton) getView().findViewById(Weixianbz.getCheckedRadioButtonId());
            return radioButton.getText().toString();
        } catch (Exception e) {
            return "无";
        }

    }

    public String GetWXbiaozhi() {
        //   RadioButton radioButton = getView().findViewById(WXbiaozhi.getCheckedRadioButtonId());
        return WXbiaozhi.getResources().toString();
    }

    public String GetYES() {
        return YES.getText().toString();
    }

    public String GetNO() {
        return NO.getText().toString();
    }

    public Calendar getCal() {
        return cal;
    }

    public void setCal(Calendar cal) {
        this.cal = cal;
    }

    public String GetFeidriverNums() {
        return feidriverNums.getText().toString();
    }

    public void setFeidriverNums(EditText feidriverNums) {
        this.feidriverNums = feidriverNums;
    }

    public String getRyouhaiwuzhi() {
        RadioButton radioButton;
        try {
            radioButton = (RadioButton) getView().findViewById(Ryouhaiwuzhi.getCheckedRadioButtonId());
            return radioButton.getText().toString();
        } catch (Exception e) {
            return "否";
        }
    }

    public void setRyouhaiwuzhi(RadioGroup ryouhaiwuzhi) {
        Ryouhaiwuzhi = ryouhaiwuzhi;
    }

    @Override
    public void onScaleChange(boolean isFullscreen) {

    }

    private int mSeekPosition;

    @Override
    public void onPause() {
        super.onPause();
        if (mVideoView != null && mVideoView.isPlaying()) {
            mSeekPosition = mVideoView.getCurrentPosition();
            Log.d(TAG, "onPause mSeekPosition=" + mSeekPosition);
            mVideoView.pause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.closePlayer();
            mVideoView = null;
        }
    }

    @Override
    public void onPause(MediaPlayer mediaPlayer) {

    }


    @Override
    public void onStart(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {

    }
}
