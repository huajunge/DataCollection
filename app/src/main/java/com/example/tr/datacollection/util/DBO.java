package com.example.tr.datacollection.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tr.datacollection.PeopelData3;
import com.example.tr.datacollection.model.AcccidentData;
import com.example.tr.datacollection.model.AccidenceCollectionData;
import com.example.tr.datacollection.model.CarData;
import com.example.tr.datacollection.model.EMdata;
import com.example.tr.datacollection.model.PelpelData;
import com.example.tr.datacollection.model.PeopelData2;
import com.example.tr.datacollection.model.SimpleDataTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by TR on 2017/2/17.
 */

public class DBO {
    SQLiteDatabase db;
    Context context;

    public DBO(Context context) {
        db = context.openOrCreateDatabase("mytest.db",MODE_PRIVATE,null);
        this.context = context;
    }


    public void insertToSimpleTest(SimpleDataTest simpleDataTest){
        db.execSQL("create table if not exists simpletest(_id integer primary key autoincrement," +
                "place text not null,yanzhongchengdu text not null,Lat double not null,Lng double not null," +
                "riqi long not null)");
        db.execSQL("insert into simpletest(place,yanzhongchengdu,Lat,Lng,riqi)values('" +
                simpleDataTest.getPlace()+"','"+simpleDataTest.getYanzhongchengdu()+"',"+simpleDataTest.getLat()+","+simpleDataTest.getLng()+","+simpleDataTest.getDate().getTime()+")");
    }

    public List<SimpleDataTest> getsimpleDataTest(){  //获取历史路线
        db.execSQL("create table if not exists simpletest(_id integer primary key autoincrement," +
                "place text not null,yanzhongchengdu text not null,Lat double not null,Lng double not null," +
                "riqi long not null)");
        List<SimpleDataTest> ps = new ArrayList<SimpleDataTest>();
        Cursor cursor=db.rawQuery("select * from simpletest",null);
        if(cursor!=null){
            while(cursor.moveToNext()){
                SimpleDataTest p = new SimpleDataTest(
                        cursor.getDouble(cursor.getColumnIndex("Lat")),
                        cursor.getDouble(cursor.getColumnIndex("Lng")),
                        cursor.getLong(cursor.getColumnIndex("riqi")),
                        cursor.getString(cursor.getColumnIndex("place")),
                        cursor.getString(cursor.getColumnIndex("yanzhongchengdu")));
                ps.add(p);
                Log.i("info",p.toString());
                Log.i("info","!!!!!!!!!!!!!!!!1");
            }
            cursor.close();
        }
        return ps;
    }

    public List<SimpleDataTest> getsimpleDataTestByDay(long time,long timeend){  //获取历史路线
        db.execSQL("create table if not exists simpletest(_id integer primary key autoincrement," +
                "place text not null,yanzhongchengdu text not null,Lat double not null,Lng double not null," +
                "riqi long not null)");
        List<SimpleDataTest> ps = new ArrayList<SimpleDataTest>();
        Cursor cursor=db.rawQuery("select * from simpletest where riqi>"+time+" and riqi<"+timeend,null);
        if(cursor!=null){
            while(cursor.moveToNext()){
                SimpleDataTest p = new SimpleDataTest(
                        cursor.getDouble(cursor.getColumnIndex("Lat")),
                        cursor.getDouble(cursor.getColumnIndex("Lng")),
                        cursor.getLong(cursor.getColumnIndex("riqi")),
                        cursor.getString(cursor.getColumnIndex("place")),
                        cursor.getString(cursor.getColumnIndex("yanzhongchengdu")));
                ps.add(p);
                Log.i("info",p.toString());
                Log.i("info","!!!!!!!!!!!!!!!!1");
            }
            cursor.close();
        }
        return ps;
    }
    public void clearTest(){
        db.execSQL("delete from simpletest where _id>0");
    }

    //插入用户
//    public void  insertToUser(User user){  //插入点
//        db.execSQL("create table if not exists user(_id integer primary key autoincrement," +
//                "name text not null,tel text not null,password text not null,brand text not null,type text not null,model text not null)");
//
//        db.execSQL("insert into places(name,tel,password,brand,type,model,)values('" +
//                user.getName()+"',"+user.getTel()+","+user.getPassword()+","+user.getBrand()+","+user.getType()+","+user.getModel()+")");
//    }
    
    public void insertToCollection(AccidenceCollectionData accidenceCollectionData){
        db.execSQL("create table if not exists AccidenceCollectionData(_id integer primary key autoincrement," +
                "shigunumber text not null,place text not null,riqi long not null,accidencenumber text not null,environmentnumber text not null," +
                "carnumber text not null,peopelnumber text not null,isUpload interger not null)");
        db.execSQL("insert into AccidenceCollectionData(shigunumber,place,riqi,accidencenumber,environmentnumber,carnumber,peopelnumber,isUpload)values('" +
                accidenceCollectionData.getNumber()+"','"+accidenceCollectionData.getPlaceName()+"',"+accidenceCollectionData.getData().getTime()+",'"+accidenceCollectionData.getAccidenceNumber()
                +"','"+accidenceCollectionData.getEnvironmentNumber()+"','"+accidenceCollectionData.getCarNumber()+"','"+accidenceCollectionData.getPeopelNumber()+"',"+accidenceCollectionData.isUpload()+")");
    }
    public List<AccidenceCollectionData> getAccidenceCollectionData(long time,long timeend){  //获取历史路线
        db.execSQL("create table if not exists AccidenceCollectionData(_id integer primary key autoincrement," +
                "shigunumber text not null,place text not null,riqi long not null,accidencenumber text not null,environmentnumber text not null," +
                "carnumber text not null,peopelnumber text not null,isUpload interger not null)");
        List<AccidenceCollectionData> ps = new ArrayList<AccidenceCollectionData>();
        Cursor cursor=db.rawQuery("select * from AccidenceCollectionData where riqi>"+time+" and riqi<"+timeend,null);
        if(cursor!=null){
            while(cursor.moveToNext()){
                //String number, Date data, String placeName, boolean isUpload)
                //String number, String accidenceNumber, String environmentNumber, String carNumber, String peopelNumber, Date data, String placeName, boolean isUpload
                AccidenceCollectionData p = new AccidenceCollectionData(
                        cursor.getString(cursor.getColumnIndex("shigunumber")),
                        cursor.getString(cursor.getColumnIndex("accidencenumber")),
                        cursor.getString(cursor.getColumnIndex("environmentnumber")),
                        cursor.getString(cursor.getColumnIndex("carnumber")),
                        cursor.getString(cursor.getColumnIndex("peopelnumber")),
                        new Date(cursor.getLong(cursor.getColumnIndex("riqi"))),
                        cursor.getString(cursor.getColumnIndex("place")),
                        cursor.getInt(cursor.getColumnIndex("isUpload"))
                        );
                ps.add(p);
                Log.i("info",p.toString());
                Log.i("info","!!!!!!!!!!!!!!!!1");
            }
            cursor.close();
        }
        return ps;
    }
    public void updateAccidenceCollectionData(AccidenceCollectionData accidenceCollectionData ,String number,int isUpload){
        db.execSQL("update AccidenceCollectionData set isUpload ="+isUpload+" where shigunumber='"+number+"'");
    }
    public List<AccidenceCollectionData> getAccidenceCollectionData(int isUpload){  //获取数据
        db.execSQL("create table if not exists AccidenceCollectionData(_id integer primary key autoincrement," +
                "shigunumber text not null,place text not null,riqi long not null,accidencenumber text not null,environmentnumber text not null," +
                "carnumber text not null,peopelnumber text not null,isUpload interger not null)");
        List<AccidenceCollectionData> ps = new ArrayList<AccidenceCollectionData>();
        Cursor cursor=db.rawQuery("select * from AccidenceCollectionData where isUpload = "+isUpload,null);
        if(cursor!=null){
            while(cursor.moveToNext()){
                //String number, Date data, String placeName, boolean isUpload)
                //String number, String accidenceNumber, String environmentNumber, String carNumber, String peopelNumber, Date data, String placeName, boolean isUpload
                AccidenceCollectionData p = new AccidenceCollectionData(
                        cursor.getString(cursor.getColumnIndex("shigunumber")),
                        cursor.getString(cursor.getColumnIndex("accidencenumber")),
                        cursor.getString(cursor.getColumnIndex("environmentnumber")),
                        cursor.getString(cursor.getColumnIndex("carnumber")),
                        cursor.getString(cursor.getColumnIndex("peopelnumber")),
                        new Date(cursor.getLong(cursor.getColumnIndex("riqi"))),
                        cursor.getString(cursor.getColumnIndex("place")),
                        cursor.getInt(cursor.getColumnIndex("isUpload"))
                );
                ps.add(p);
                Log.i("info",p.toString());
                Log.i("info","!!!!!!!!!!!!!!!!1");
            }
            cursor.close();
        }
        return ps;
    }
    //清除AccidenceCollectionData
    public void deleteAccidenceCollectionData(){
        db.execSQL("delete from AccidenceCollectionData where _id>0");
    }
    //插入事故信息
    public void insertToAccidentData(AcccidentData accidentData){
        db.execSQL("create table if not exists AccidentData(_id integer primary key autoincrement," +
                "accidencnumber text not null,"+
                "riQI long," +
                "city text," +
                "xianQu text," +
                "shangQuan text," +
                "lat double not null,"+
                "lng double not null,"+
                "diMingBeiZhu text," +
                "shigu text," +
                "shiguType text," +
                "shigu2 text," +
                "shiguType2 text," +
                "yanZhongCd text," +
                "carnum text," +
                "driverNums text," +
                "feidriverNums text," +
                "ssNums text," +
                "dieNums text," +
                "gongjiao text," +
                "taoYi text," +
                "Weixianche text," +
                "Weixianbz text," +
                "fromWeixianbz text," +
                "youHaiWuZhi text)");
        db.execSQL("insert into AccidentData(" +
                "accidencnumber,"+
                "riQI," +
                "city," +
                "xianQu," +
                "shangQuan," +
                "lat," +
                "lng," +
                "diMingBeiZhu," +
                "shigu," +
                "shiguType," +
                "shigu2," +
                "shiguType2," +
                "yanZhongCd," +
                "carnum," +
                "driverNums," +
                "feidriverNums," +
                "ssNums," +
                "dieNums," +
                "gongjiao," +
                "taoYi," +
                "Weixianche," +
                "Weixianbz," +
                "fromWeixianbz," +
                "youHaiWuZhi)values('"+
              accidentData.getAccidencenumber()+"'," +
               accidentData.getRiQI().getTime()+",'" +
               accidentData.getCity()+"','"+
               accidentData.getXianQu()+"','"+
               accidentData.getShangQuan()+"',"+
               accidentData.getLnglat().latitude+","+
                accidentData.getLnglat().longitude+",'"+
               accidentData.getDiMingBeiZhu()+"','"+
               accidentData.getShigu()+"','"+
               accidentData.getShiguType()+"','"+
               accidentData.getShigu2()+"','"+
               accidentData.getShiguType2()+"','"+
               accidentData.getYanZhongCd()+"','"+
               accidentData.getCarnum()+"','"+
               accidentData.getDriverNums()+"','"+
               accidentData.getFeidriverNums()+"','"+
               accidentData.getSsNums()+"','"+
               accidentData.getDieNums()+"','"+
               accidentData.getGongjiao()+"','"+
               accidentData.getTaoYi()+"','"+
               accidentData.isWeixianche()+"','"+
               accidentData.getWeixianbz()+"','"+
               accidentData.getFromWeixianbz()+"','"+
               accidentData.getYouHaiWuZhi()+"')");
    }
    //更新
    public void updateAccidentData(AcccidentData accidentData,String number){
        db.execSQL("update AccidentData set accidencnumber = '"+accidentData.getAccidencenumber()+"'," +
                "riQI = "+accidentData.getRiQI().getTime()+"," +
                "city = '"+accidentData.getCity()+"'," +
                "xianQu = '"+accidentData.getXianQu()+"'," +
                "shangQuan = '"+accidentData.getShangQuan()+"'," +
                "lat = "+accidentData.getLnglat().latitude+"," +
                "lng = "+accidentData.getLnglat().longitude+"," +
                "diMingBeiZhu = '"+accidentData.getDiMingBeiZhu()+"'," +
                "shigu = '"+accidentData.getShigu()+"'," +
                "shiguType = '"+accidentData.getShiguType()+"'," +
                "shigu2 = '"+accidentData.getShigu2()+"'," +
                "shiguType2 = '"+accidentData.getShiguType2()+"'," +
                "yanZhongCd = '"+accidentData.getYanZhongCd()+"'," +
                "carnum = '"+accidentData.getCarnum()+"'," +
                "driverNums = '"+accidentData.getDriverNums()+"'," +
                "feidriverNums = '"+accidentData.getFeidriverNums()+"'," +
                "ssNums = '"+accidentData.getSsNums()+"'," +
                "dieNums = '"+accidentData.getDieNums()+"'," +
                "gongjiao = '"+accidentData.getGongjiao()+"'," +
                "taoYi = '"+accidentData.getTaoYi()+"'," +
                "Weixianche = '"+accidentData.isWeixianche()+"'," +
                "Weixianbz = '"+accidentData.getWeixianbz()+"'," +
                "fromWeixianbz = '"+accidentData.getFromWeixianbz()+"'," +
                "youHaiWuZhi = '"+accidentData.getYouHaiWuZhi()+"' where accidencnumber ='" +number+"'");
    }
    public List<AcccidentData> getAcccidentData(String bianhao){  //获取数据
        db.execSQL("create table if not exists AccidentData(_id integer primary key autoincrement," +
                "accidencnumber text not null,"+
                "riQI long," +
                "city text," +
                "xianQu text," +
                "shangQuan text," +
                "lat double not null,"+
                "lng double not null,"+
                "diMingBeiZhu text," +
                "shigu text," +
                "shiguType text," +
                "shigu2 text," +
                "shiguType2 text," +
                "yanZhongCd text," +
                "carnum text," +
                "driverNums text," +
                "feidriverNums text," +
                "ssNums text," +
                "dieNums text," +
                "gongjiao text," +
                "taoYi text," +
                "Weixianche text," +
                "Weixianbz text," +
                "fromWeixianbz text," +
                "youHaiWuZhi text)");
        List<AcccidentData> ps = new ArrayList<AcccidentData>();
        Cursor cursor=db.rawQuery("select * from AccidentData where accidencnumber ='"+bianhao+"'",null);
        if(cursor!=null){
            while(cursor.moveToNext()){
                //String number, Date data, String placeName, boolean isUpload)
                //String number, String accidenceNumber, String environmentNumber, String carNumber, String peopelNumber, Date data, String placeName, boolean isUpload
                AcccidentData p = new AcccidentData(
                        cursor.getString(cursor.getColumnIndex("accidencnumber")),
                        new java.util.Date(cursor.getLong(cursor.getColumnIndex("riQI"))),
                        cursor.getString(cursor.getColumnIndex("city")),
                        cursor.getString(cursor.getColumnIndex("xianQu")),
                        cursor.getString(cursor.getColumnIndex("shangQuan")),
                        cursor.getString(cursor.getColumnIndex("lat"))+","+cursor.getString(cursor.getColumnIndex("lng")),
                        cursor.getString(cursor.getColumnIndex("diMingBeiZhu")),
                        cursor.getString(cursor.getColumnIndex("shigu")),
                        cursor.getString(cursor.getColumnIndex("shiguType")),
                        cursor.getString(cursor.getColumnIndex("shigu2")),
                        cursor.getString(cursor.getColumnIndex("shiguType2")),
                        cursor.getString(cursor.getColumnIndex("yanZhongCd")),
                        cursor.getString(cursor.getColumnIndex("carnum")),
                        cursor.getString(cursor.getColumnIndex("driverNums")),
                        cursor.getString(cursor.getColumnIndex("feidriverNums")),
                        cursor.getString(cursor.getColumnIndex("ssNums")),
                        cursor.getString(cursor.getColumnIndex("dieNums")),
                        cursor.getString(cursor.getColumnIndex("gongjiao")),
                        cursor.getString(cursor.getColumnIndex("taoYi")),
                        cursor.getString(cursor.getColumnIndex("Weixianche")),
                        cursor.getString(cursor.getColumnIndex("Weixianbz")),
                        cursor.getString(cursor.getColumnIndex("fromWeixianbz")),
                        cursor.getString(cursor.getColumnIndex("youHaiWuZhi"))
                );
                ps.add(p);
                Log.i("info",p.toString());
                Log.i("info","!!!!!!!!!!!!!!!!1");
            }
            cursor.close();
        }
        return ps;
    }

    //插入环境信息
    public void insertToEMdata(EMdata eMdata){
        db.execSQL("create table if not exists EMdata(_id integer primary key autoincrement," +
                "emnumber text not null,"+
                "Routeposition text,"+
                "pengzhuangleixing text,"+
                "liJiaoQuYu text,"+
                "Jcktype text,"+
                "Teshuposition text,"+
                "Tianqicondition text,"+
                "zhaomingcondition text,"+
                "luMianCondition text,"+
                "luMianLev text,"+
                "limitSpeed text,"+
                "chedaowidth text,"+
                "luJianWidth text,"+
                "bianYuanXianle text,"+
                "zhongyanwidht text,"+
                "zhongXinXian text,"+
                "chedaoxianbj text,"+
                "jiaotongkzlx text,"+
                "zhuchedaoshu text,"+
                "jiaochajiedaoshu text,"+
                "WorkPlaceR text,"+
                "Worktype text,"+
                "havepeople text,"+
                "xianChangZhifa text"+
                ")");
        db.execSQL("insert into EMdata(" +
                "emnumber,"+
                "Routeposition," +
                "pengzhuangleixing," +
                "liJiaoQuYu," +
                "Jcktype," +
                "Teshuposition," +
                "Tianqicondition," +
                "zhaomingcondition," +
                "luMianCondition," +
                "luMianLev," +
                "limitSpeed," +
                "chedaowidth," +
                "luJianWidth," +
                "bianYuanXianle," +
                "zhongyanwidht," +
                "zhongXinXian," +
                "chedaoxianbj," +
                "jiaotongkzlx," +
                "zhuchedaoshu," +
                "jiaochajiedaoshu," +
                "WorkPlaceR," +
                "Worktype," +
                "havepeople," +
                "xianChangZhifa" +
                ")values('" +
                eMdata.getEmnumber()+"','"+
                eMdata.getRouteposition()+"','"+
                eMdata.getPengzhuangleixing()+"','"+
                eMdata.getLiJiaoQuYu()+"','"+
                eMdata.getJcktype()+"','"+
                eMdata.getTeshuposition()+"','"+
                eMdata.getTianqicondition()+"','"+
                eMdata.getZhaomingcondition()+"','"+
                eMdata.getLuMianCondition()+"','"+
                eMdata.getLuMianLev()+"','"+
                eMdata.getLimitSpeed()+"','"+
                eMdata.getChedaowidth()+"','"+
                eMdata.getLuJianWidth()+"','"+
                eMdata.getBianYuanXianle()+"','"+
                eMdata.getZhongyanwidht()+"','"+
                eMdata.getZhongXinXian()+"','"+
                eMdata.getChedaoxianbj()+"','"+
                eMdata.getJiaotongkzlx()+"','"+
                eMdata.getZhuchedaoshu()+"','"+
                eMdata.getJiaochajiedaoshu()+"','"+
                eMdata.getWorkPlaceR()+"','"+
                eMdata.getWorktype()+"','"+
                eMdata.getHavepeople()+"','"+
                eMdata.getXianChangZhifa()+"'"+
                ")");
    }

    public List<EMdata> getEMdata(String number){
        db.execSQL("create table if not exists EMdata(_id integer primary key autoincrement," +
                "emnumber text not null,"+
                "Routeposition text,"+
                "pengzhuangleixing text,"+
                "liJiaoQuYu text,"+
                "Jcktype text,"+
                "Teshuposition text,"+
                "Tianqicondition text,"+
                "zhaomingcondition text,"+
                "luMianCondition text,"+
                "luMianLev text,"+
                "limitSpeed text,"+
                "chedaowidth text,"+
                "luJianWidth text,"+
                "bianYuanXianle text,"+
                "zhongyanwidht text,"+
                "zhongXinXian text,"+
                "chedaoxianbj text,"+
                "jiaotongkzlx text,"+
                "zhuchedaoshu text,"+
                "jiaochajiedaoshu text,"+
                "WorkPlaceR text,"+
                "Worktype text,"+
                "havepeople text,"+
                "xianChangZhifa text"+
                ")");
        List<EMdata> ps = new ArrayList<EMdata>();
        Cursor cursor=db.rawQuery("select * from EMdata where emnumber = '"+number+"'",null);
        if(cursor!=null){
            while(cursor.moveToNext()){
                //String number, Date data, String placeName, boolean isUpload)
                //String number, String accidenceNumber, String environmentNumber, String carNumber, String peopelNumber, Date data, String placeName, boolean isUpload
                EMdata p = new EMdata(
                        cursor.getString(cursor.getColumnIndex("emnumber")),
                        cursor.getString(cursor.getColumnIndex("Routeposition")),
                        cursor.getString(cursor.getColumnIndex("pengzhuangleixing")),
                        cursor.getString(cursor.getColumnIndex("liJiaoQuYu")),
                        cursor.getString(cursor.getColumnIndex("Jcktype")),
                        cursor.getString(cursor.getColumnIndex("Teshuposition")),
                        cursor.getString(cursor.getColumnIndex("Tianqicondition")),
                        cursor.getString(cursor.getColumnIndex("zhaomingcondition")),
                        cursor.getString(cursor.getColumnIndex("luMianCondition")),
                        cursor.getString(cursor.getColumnIndex("luMianLev")),
                        cursor.getString(cursor.getColumnIndex("limitSpeed")),
                        cursor.getString(cursor.getColumnIndex("chedaowidth")),
                        cursor.getString(cursor.getColumnIndex("luJianWidth")),
                        cursor.getString(cursor.getColumnIndex("bianYuanXianle")),
                        cursor.getString(cursor.getColumnIndex("zhongyanwidht")),
                        cursor.getString(cursor.getColumnIndex("zhongXinXian")),
                        cursor.getString(cursor.getColumnIndex("chedaoxianbj")),
                        cursor.getString(cursor.getColumnIndex("jiaotongkzlx")),
                        cursor.getString(cursor.getColumnIndex("zhuchedaoshu")),
                        cursor.getString(cursor.getColumnIndex("jiaochajiedaoshu")),
                        cursor.getString(cursor.getColumnIndex("WorkPlaceR")),
                        cursor.getString(cursor.getColumnIndex("Worktype")),
                        cursor.getString(cursor.getColumnIndex("havepeople")),
                        cursor.getString(cursor.getColumnIndex("xianChangZhifa"))
                );
                ps.add(p);
                Log.i("info",p.toString());
                Log.i("info","!!!!!!!!!!!!!!!!1");
            }
            cursor.close();
        }
        return ps;
    }

    //更新
    public void updateEMdata(EMdata eMdata,String number){
        db.execSQL("update EMdata set " +
                "emnumber='"+eMdata.getEmnumber()+"',"+
                "Routeposition='"+eMdata.getRouteposition()+"',"+
                "pengzhuangleixing='"+eMdata.getPengzhuangleixing()+"',"+
                "liJiaoQuYu='"+eMdata.getLiJiaoQuYu()+"',"+
                "Jcktype='"+eMdata.getJcktype()+"',"+
                "Teshuposition='"+eMdata.getTeshuposition()+"',"+
                "Tianqicondition='"+eMdata.getTianqicondition()+"',"+
                "zhaomingcondition='"+eMdata.getZhaomingcondition()+"',"+
                "luMianCondition='"+eMdata.getLuMianCondition()+"',"+
                "luMianLev='"+eMdata.getLuMianLev()+"',"+
                "limitSpeed='"+eMdata.getLimitSpeed()+"',"+
                "chedaowidth='"+eMdata.getChedaowidth()+"',"+
                "luJianWidth='"+eMdata.getLuJianWidth()+"',"+
                "bianYuanXianle='"+eMdata.getBianYuanXianle()+"',"+
                "zhongyanwidht='"+eMdata.getZhongyanwidht()+"',"+
                "zhongXinXian='"+eMdata.getZhongXinXian()+"',"+
                "chedaoxianbj='"+eMdata.getChedaoxianbj()+"',"+
                "jiaotongkzlx='"+eMdata.getJiaotongkzlx()+"',"+
                "zhuchedaoshu='"+eMdata.getZhuchedaoshu()+"',"+
                "jiaochajiedaoshu='"+eMdata.getJiaochajiedaoshu()+"',"+
                "WorkPlaceR='"+eMdata.getWorkPlaceR()+"',"+
                "Worktype='"+eMdata.getWorktype()+"',"+
                "havepeople='"+eMdata.getHavepeople()+"',"+
                "xianChangZhifa='"+eMdata.getXianChangZhifa()+"' "+
                "where emnumber ='" +number+"'");
    }

    //插入车辆
    public void insertToCarData(CarData carData){
        db.execSQL("create table if not exists CarData(_id integer primary key autoincrement," +
                "carnumber text,"+
                "xunhao integer,"+
                "vin text,"+
                "chepaihao text,"+
                "guobie text,"+
                "nianfen text,"+
                "pingpai text,"+
                "carType text,"+
                "Leixing text,"+
                "cheneinum text,"+
                "tesuzuoyong text,"+
                "xingshifangxing text,"+
                "Xingwei text,"+
                "Jiechudian text,"+
                "sunhuaibuwei text,"+
                "sunhuaschengdu text"+
                ")");
        db.execSQL("insert into CarData(" +
                "carnumber,"+
                "xunhao,"+
                "vin,"+
                "chepaihao,"+
                "guobie,"+
                "nianfen,"+
                "pingpai,"+
                "carType,"+
                "Leixing,"+
                "cheneinum,"+
                "tesuzuoyong,"+
                "xingshifangxing,"+
                "Xingwei,"+
                "Jiechudian,"+
                "sunhuaibuwei,"+
                "sunhuaschengdu"+
                ")values('" +
                carData.getCarnumber()+"',"+
                carData.getXunhao()+",'"+
                carData.getVin()+"','"+
                carData.getChepaihao()+"','"+
                carData.getGuobie()+"','"+
                carData.getNianfen()+"','"+
                carData.getPingpai()+"','"+
                carData.getCarType()+"','"+
                carData.getLeixing()+"','"+
                carData.getCheneinum()+"','"+
                carData.getTesuzuoyong()+"','"+
                carData.getXingshifangxing()+"','"+
                carData.getXingwei()+"','"+
                carData.getJiechudian()+"','"+
                carData.getSunhuaibuwei()+"','"+
                carData.getSunhuaschengdu()+"'"+
                ")");

    }

    public List<CarData> getCarData(String carnumber){
        db.execSQL("create table if not exists CarData(_id integer primary key autoincrement," +
                "carnumber,"+
                "xunhao integer,"+
                "vin text,"+
                "chepaihao text,"+
                "guobie text,"+
                "nianfen text,"+
                "pingpai text,"+
                "carType text,"+
                "Leixing text,"+
                "cheneinum text,"+
                "tesuzuoyong text,"+
                "xingshifangxing text,"+
                "Xingwei text,"+
                "Jiechudian text,"+
                "sunhuaibuwei text,"+
                "sunhuaschengdu text"+
                ")");
        List<CarData> ps = new ArrayList<CarData>();
        Cursor cursor=db.rawQuery("select * from CarData where carnumber = '"+carnumber+"'",null);
       // Cursor cursor=db.rawQuery("select * from CarData where _id > 0",null);
        if(cursor!=null){
            while(cursor.moveToNext()){
                //String number, Date data, String placeName, boolean isUpload)
                //String number, String accidenceNumber, String environmentNumber, String carNumber, String peopelNumber, Date data, String placeName, boolean isUpload
                CarData p = new CarData(
                        cursor.getString(cursor.getColumnIndex("carnumber")),
                       cursor.getInt(cursor.getColumnIndex("xunhao")),
                       cursor.getString(cursor.getColumnIndex("vin")),
                       cursor.getString(cursor.getColumnIndex("chepaihao")),
                       cursor.getString(cursor.getColumnIndex("guobie")),
                       cursor.getString(cursor.getColumnIndex("nianfen")),
                       cursor.getString(cursor.getColumnIndex("pingpai")),
                       cursor.getString(cursor.getColumnIndex("carType")),
                       cursor.getString(cursor.getColumnIndex("Leixing")),
                       cursor.getString(cursor.getColumnIndex("cheneinum")),
                       cursor.getString(cursor.getColumnIndex("tesuzuoyong")),
                       cursor.getString(cursor.getColumnIndex("xingshifangxing")),
                       cursor.getString(cursor.getColumnIndex("Xingwei")),
                       cursor.getString(cursor.getColumnIndex("Jiechudian")),
                       cursor.getString(cursor.getColumnIndex("sunhuaibuwei")),
                       cursor.getString(cursor.getColumnIndex("sunhuaschengdu"))
                );
                ps.add(p);
                Log.i("info",p.toString());
                Log.i("info","!!!!!!!!!!!!!!!!1");
            }
            cursor.close();
        }
        return ps;
    }

    public void updateCarData(CarData carData ,String carnumber){
        db.execSQL("update CarData set " +
                "xunhao="+     carData.getXunhao()+","+
                "vin='"+  carData.getVin()+"',"+
                "chepaihao='"+   carData.getChepaihao()+"',"+
                "guobie='"+     carData.getGuobie()+"',"+
                "nianfen='"+  carData.getNianfen()+"',"+
                "pingpai='"+   carData.getPingpai()+"',"+
                "carType='"+   carData.getCarType()+"',"+
                "Leixing='"+     carData.getLeixing()+"',"+
                "cheneinum='"+   carData.getCheneinum()+"',"+
                "tesuzuoyong='"+  carData.getTesuzuoyong()+"',"+
                "xingshifangxing='"+    carData.getXingshifangxing()+"',"+
                "Xingwei='"+   carData.getXingwei()+"',"+
                "Jiechudian='"+    carData.getJiechudian()+"',"+
                "sunhuaibuwei='"+   carData.getSunhuaibuwei()+"',"+
                "sunhuaschengdu='"+  carData.getSunhuaschengdu()+"'"+
                " where carnumber = '"+
                carData.getCarnumber()+"'"
        );
    }
    public void updateCarData(CarData carData ,String carnumber,int i){
        db.execSQL("update CarData set " +
                "xunhao="+     carData.getXunhao()+","+
                "vin='"+  carData.getVin()+"',"+
                "chepaihao='"+   carData.getChepaihao()+"',"+
                "guobie='"+     carData.getGuobie()+"',"+
                "nianfen='"+  carData.getNianfen()+"',"+
                "pingpai='"+   carData.getPingpai()+"',"+
                "carType='"+   carData.getCarType()+"',"+
                "Leixing='"+     carData.getLeixing()+"',"+
                "cheneinum='"+   carData.getCheneinum()+"',"+
                "tesuzuoyong='"+  carData.getTesuzuoyong()+"',"+
                "xingshifangxing='"+    carData.getXingshifangxing()+"',"+
                "Xingwei='"+   carData.getXingwei()+"',"+
                "Jiechudian='"+    carData.getJiechudian()+"',"+
                "sunhuaibuwei='"+   carData.getSunhuaibuwei()+"',"+
                "sunhuaschengdu='"+  carData.getSunhuaschengdu()+"'"+
                " where carnumber = '"+
                carData.getCarnumber()+"'"+
                " and xunhao="+i
        );
    }


    public void insertToPeopel1(PelpelData pelpelData,String sid){
        db.execSQL("create table if not exists Peopel1(_id integer primary key autoincrement," +
                "number text,"+
                "name text,"+
                "xingbie text,"+
                "shenfentype text,"+
                "renyuantype text,"+
                "sschengdu text,"+
                "phonenum text,"+
                "cheliangxunhao text,"+
                "ysxitong text,"+
                "tuokuishiyong text,"+
                "aqqnzt text,"+
                "paochuzhuangta text,"+
                "sgfsszhuangtai text,"+
                "yinjiu text,"+
                "duPingLeiXing text,"+
                "ceshizhuangtai text,"+
                "ceshitype text,"+
                "ceshiresult text"+
                ")");
        db.execSQL("insert into Peopel1(" +
                "number,"+
                "name,"+
                "xingbie,"+
                "shenfentype,"+
                "renyuantype,"+
                "sschengdu,"+
                "phonenum,"+
                "cheliangxunhao,"+
                "ysxitong,"+
                "tuokuishiyong,"+
                "aqqnzt,"+
                "paochuzhuangta,"+
                "sgfsszhuangtai,"+
                "yinjiu,"+
                "duPingLeiXing,"+
                "ceshizhuangtai,"+
                "ceshitype,"+
                "ceshiresult"+
                ")values('" +
                sid+"','"+
                pelpelData.getName()+"','"+
                pelpelData.getXingbie()+"','"+
                pelpelData.getShenfentype()+"','"+
                pelpelData.getRenyuantype()+"','"+
                pelpelData.getSschengdu()+"','"+
                pelpelData.getPhonenum()+"','"+
                pelpelData.getCheliangxunhao()+"','"+
                pelpelData.getYsxitong()+"','"+
                pelpelData.getTuokuishiyong()+"','"+
                pelpelData.getAqqnzt()+"','"+
                pelpelData.getPaochuzhuangta()+"','"+
                pelpelData.getSgfsszhuangtai()+"','"+
                pelpelData.getYinjiu()+"','"+
                pelpelData.getDuPingLeiXing()+"','"+
                pelpelData.getCeshizhuangtai()+"','"+
                pelpelData.getCeshitype()+"','"+
                pelpelData.getCeshiresult()+"'"+
                ")");
    }

    public List<PelpelData> getPeopel1(String sid){
        db.execSQL("create table if not exists Peopel1(_id integer primary key autoincrement," +
                "number text,"+
                "name text,"+
                "xingbie text,"+
                "shenfentype text,"+
                "renyuantype text,"+
                "sschengdu text,"+
                "phonenum text,"+
                "cheliangxunhao text,"+
                "ysxitong text,"+
                "tuokuishiyong text,"+
                "aqqnzt text,"+
                "paochuzhuangta text,"+
                "sgfsszhuangtai text,"+
                "yinjiu text,"+
                "duPingLeiXing text,"+
                "ceshizhuangtai text,"+
                "ceshitype text,"+
                "ceshiresult text"+
                ")");
        List<PelpelData> ps = new ArrayList<PelpelData>();
        Cursor cursor=db.rawQuery("select * from Peopel1 where number = '"+sid+"'",null);
        // Cursor cursor=db.rawQuery("select * from CarData where _id > 0",null);
        if(cursor!=null){
            while(cursor.moveToNext()){
                //String number, Date data, String placeName, boolean isUpload)
                //String number, String accidenceNumber, String environmentNumber, String carNumber, String peopelNumber, Date data, String placeName, boolean isUpload
                PelpelData p = new PelpelData(
                                cursor.getString(cursor.getColumnIndex("number")),
                                cursor.getString(cursor.getColumnIndex("name")),
                                cursor.getString(cursor.getColumnIndex("xingbie")),
                                cursor.getString(cursor.getColumnIndex("shenfentype")),
                                cursor.getString(cursor.getColumnIndex("renyuantype")),
                                cursor.getString(cursor.getColumnIndex("sschengdu")),
                                cursor.getString(cursor.getColumnIndex("phonenum")),
                                cursor.getString(cursor.getColumnIndex("cheliangxunhao")),
                                cursor.getString(cursor.getColumnIndex("ysxitong")),
                                cursor.getString(cursor.getColumnIndex("tuokuishiyong")),
                                cursor.getString(cursor.getColumnIndex("aqqnzt")),
                                cursor.getString(cursor.getColumnIndex("paochuzhuangta")),
                                cursor.getString(cursor.getColumnIndex("sgfsszhuangtai")),
                                cursor.getString(cursor.getColumnIndex("yinjiu")),
                                cursor.getString(cursor.getColumnIndex("duPingLeiXing")),
                                cursor.getString(cursor.getColumnIndex("ceshizhuangtai")),
                                cursor.getString(cursor.getColumnIndex("ceshitype")),
                                cursor.getString(cursor.getColumnIndex("ceshiresult"))
                );
                ps.add(p);
                Log.i("info",p.toString());
                Log.i("info","!!!!!!!!!!!!!!!!1");
            }
            cursor.close();
        }
        return ps;
    }

    public void updatePeopel1(PelpelData pelpelData){
        db.execSQL("update Peopel1 set " +
                "name='"+pelpelData.getName()+"',"+
                "xingbie='"+pelpelData.getXingbie()+"',"+
                "shenfentype='"+pelpelData.getShenfentype()+"',"+
                "renyuantype='"+pelpelData.getRenyuantype()+"',"+
                "sschengdu='"+pelpelData.getSschengdu()+"',"+
                "phonenum='"+pelpelData.getPhonenum()+"',"+
                "cheliangxunhao='"+pelpelData.getCheliangxunhao()+"',"+
                "ysxitong='"+pelpelData.getYsxitong()+"',"+
                "tuokuishiyong='"+pelpelData.getTuokuishiyong()+"',"+
                "aqqnzt='"+pelpelData.getAqqnzt()+"',"+
                "paochuzhuangta='"+pelpelData.getPaochuzhuangta()+"',"+
                "sgfsszhuangtai='"+pelpelData.getSgfsszhuangtai()+"',"+
                "yinjiu='"+pelpelData.getYinjiu()+"',"+
                "duPingLeiXing='"+pelpelData.getDuPingLeiXing()+"',"+
                "ceshizhuangtai='"+pelpelData.getCeshizhuangtai()+"',"+
                "ceshitype='"+pelpelData.getCeshitype()+"',"+
                "ceshiresult='"+pelpelData.getCeshiresult()+"'"+
                " where number = '"+
                pelpelData.getNumber()+"' and name='"+pelpelData.getName()+"'"
        );
    }

    /*
    * 乘坐人
    *
    * */

    public void insertToPeopel2(PeopelData2 pelpelData2, String sid){
        db.execSQL("create table if not exists Peopel2(_id integer primary key autoincrement," +
                "number text,"+
                "SPname text,"+
                "SPxingbie text,"+
                "SPshenfentype text,"+
                "SPrenyuantype text,"+
                "SPsschengdu text,"+
                "phonenum text,"+
                "SPcheliangxunhao text,"+
                "SPysxitong text,"+
                "SPtuokuishiyong text,"+
                "SPaqqnzt text,"+
                "SPpaochuzhuangta text,"+
                "SPsgfsszhuangtai text,"+
                "SPyinjiu text,"+
                "SPduPingLeiXing text,"+
                "SPceshizhuangtai text,"+
                "SPceshitype text,"+
                "SPceshiresult text,"+
                "jiazhao text,"+
                "dengji text,"+
                "shangyejiazhao text,"+
                "qianzhu text,"+
                "guanxiaqu text,"+
                "chaosuxunwen text,"+
                "fenxinjiashi text,"+
                "sgfssxingwie text,"+
                "sgfsszhuangt text,"+
                "jtwfxingwei text,"+
                "jiazhaoxianzhi text"+
                ")");
        db.execSQL("insert into Peopel2(" +
                "number,"+
                "SPname,"+
                "SPxingbie,"+
                "SPshenfentype,"+
                "SPrenyuantype,"+
                "SPsschengdu,"+
                "phonenum,"+
                "SPcheliangxunhao,"+
                "SPysxitong,"+
                "SPtuokuishiyong,"+
                "SPaqqnzt,"+
                "SPpaochuzhuangta,"+
                "SPsgfsszhuangtai,"+
                "SPyinjiu,"+
                "SPduPingLeiXing,"+
                "SPceshizhuangtai,"+
                "SPceshitype,"+
                "SPceshiresult,"+
                "jiazhao,"+
                "dengji,"+
                "shangyejiazhao,"+
                "qianzhu,"+
                "guanxiaqu,"+
                "chaosuxunwen,"+
                "fenxinjiashi,"+
                "sgfssxingwie,"+
                "sgfsszhuangt,"+
                "jtwfxingwei,"+
                "jiazhaoxianzhi"+
                ")values('" +
                sid+"','"+
                pelpelData2.getSPname()+"','"+
                pelpelData2.getSPxingbie()+"','"+
                pelpelData2.getSPshenfentype()+"','"+
                pelpelData2.getSPrenyuantype()+"','"+
                pelpelData2.getSPsschengdu()+"','"+
                pelpelData2.getPhonenum()+"','"+
                pelpelData2.getSPcheliangxunhao()+"','"+
                pelpelData2.getSPysxitong()+"','"+
                pelpelData2.getSPtuokuishiyong()+"','"+
                pelpelData2.getSPaqqnzt()+"','"+
                pelpelData2.getSPpaochuzhuangta()+"','"+
                pelpelData2.getSPsgfsszhuangtai()+"','"+
                pelpelData2.getSPyinjiu()+"','"+
                pelpelData2.getSPduPingLeiXing()+"','"+
                pelpelData2.getSPceshizhuangtai()+"','"+
                pelpelData2.getSPceshitype()+"','"+
                pelpelData2.getSPceshiresult()+"','"+
                pelpelData2.getJiazhao()+"','"+
                pelpelData2.getDengji()+"','"+
                pelpelData2.getShangyejiazhao()+"','"+
                pelpelData2.getQianzhu()+"','"+
                pelpelData2.getGuanxiaqu()+"','"+
                pelpelData2.getChaosuxunwen()+"','"+
                pelpelData2.getFenxinjiashi()+"','"+
                pelpelData2.getSgfssxingwie()+"','"+
                pelpelData2.getSgfsszhuangt()+"','"+
                pelpelData2.getJtwfxingwei()+"','"+
                pelpelData2.getJiazhaoxianzhi()+"'"+
                ")");
    }

    public List<PeopelData2> getPeopel2(String sid){
        db.execSQL("create table if not exists Peopel2(_id integer primary key autoincrement," +
                "number text,"+
                "SPname text,"+
                "SPxingbie text,"+
                "SPshenfentype text,"+
                "SPrenyuantype text,"+
                "SPsschengdu text,"+
                "phonenum text,"+
                "SPcheliangxunhao text,"+
                "SPysxitong text,"+
                "SPtuokuishiyong text,"+
                "SPaqqnzt text,"+
                "SPpaochuzhuangta text,"+
                "SPsgfsszhuangtai text,"+
                "SPyinjiu text,"+
                "SPduPingLeiXing text,"+
                "SPceshizhuangtai text,"+
                "SPceshitype text,"+
                "SPceshiresult text,"+
                "jiazhao text,"+
                "dengji text,"+
                "shangyejiazhao text,"+
                "qianzhu text,"+
                "guanxiaqu text,"+
                "chaosuxunwen text,"+
                "fenxinjiashi text,"+
                "sgfssxingwie text,"+
                "sgfsszhuangt text,"+
                "jtwfxingwei text,"+
                "jiazhaoxianzhi text"+
                ")");
        List<PeopelData2> ps = new ArrayList<PeopelData2>();
        Cursor cursor=db.rawQuery("select * from Peopel2 where number = '"+sid+"'",null);
        // Cursor cursor=db.rawQuery("select * from CarData where _id > 0",null);
        if(cursor!=null){
            while(cursor.moveToNext()){
                //String number, Date data, String placeName, boolean isUpload)
                //String number, String accidenceNumber, String environmentNumber, String carNumber, String peopelNumber, Date data, String placeName, boolean isUpload
                PeopelData2 p = new PeopelData2(
                        cursor.getString(cursor.getColumnIndex("number")),
                        cursor.getString(cursor.getColumnIndex("SPname")),
                        cursor.getString(cursor.getColumnIndex("SPxingbie")),
                        cursor.getString(cursor.getColumnIndex("SPshenfentype")),
                        cursor.getString(cursor.getColumnIndex("SPrenyuantype")),
                        cursor.getString(cursor.getColumnIndex("SPsschengdu")),
                        cursor.getString(cursor.getColumnIndex("phonenum")),
                        cursor.getString(cursor.getColumnIndex("SPcheliangxunhao")),
                        cursor.getString(cursor.getColumnIndex("SPysxitong")),
                        cursor.getString(cursor.getColumnIndex("SPtuokuishiyong")),
                        cursor.getString(cursor.getColumnIndex("SPaqqnzt")),
                        cursor.getString(cursor.getColumnIndex("SPpaochuzhuangta")),
                        cursor.getString(cursor.getColumnIndex("SPsgfsszhuangtai")),
                        cursor.getString(cursor.getColumnIndex("SPyinjiu")),
                        cursor.getString(cursor.getColumnIndex("SPduPingLeiXing")),
                        cursor.getString(cursor.getColumnIndex("SPceshizhuangtai")),
                        cursor.getString(cursor.getColumnIndex("SPceshitype")),
                        cursor.getString(cursor.getColumnIndex("SPceshiresult")),
                        cursor.getString(cursor.getColumnIndex("jiazhao")),
                        cursor.getString(cursor.getColumnIndex("dengji")),
                        cursor.getString(cursor.getColumnIndex("shangyejiazhao")),
                        cursor.getString(cursor.getColumnIndex("qianzhu")),
                        cursor.getString(cursor.getColumnIndex("guanxiaqu")),
                        cursor.getString(cursor.getColumnIndex("chaosuxunwen")),
                        cursor.getString(cursor.getColumnIndex("fenxinjiashi")),
                        cursor.getString(cursor.getColumnIndex("sgfssxingwie")),
                        cursor.getString(cursor.getColumnIndex("sgfsszhuangt")),
                        cursor.getString(cursor.getColumnIndex("jtwfxingwei")),
                        cursor.getString(cursor.getColumnIndex("jiazhaoxianzhi"))
                );
                ps.add(p);
                Log.i("info",p.toString());
                Log.i("info","!!!!!!!!!!!!!!!!1");
            }
            cursor.close();
        }
        return ps;
    }

    public void updatePeopel2(PeopelData2 pelpelData2){
        db.execSQL("update Peopel2 set " +
                "SPname='"+pelpelData2.getSPname()+"',"+
                "SPxingbie='"+pelpelData2.getSPxingbie()+"',"+
                "SPshenfentype='"+pelpelData2.getSPshenfentype()+"',"+
                "SPrenyuantype='"+pelpelData2.getSPrenyuantype()+"',"+
                "SPsschengdu='"+pelpelData2.getSPsschengdu()+"',"+
                "phonenum='"+pelpelData2.getPhonenum()+"',"+
                "SPcheliangxunhao='"+pelpelData2.getSPcheliangxunhao()+"',"+
                "SPysxitong='"+pelpelData2.getSPysxitong()+"',"+
                "SPtuokuishiyong='"+pelpelData2.getSPtuokuishiyong()+"',"+
                "SPaqqnzt='"+pelpelData2.getSPaqqnzt()+"',"+
                "SPpaochuzhuangta='"+pelpelData2.getSPpaochuzhuangta()+"',"+
                "SPsgfsszhuangtai='"+pelpelData2.getSPsgfsszhuangtai()+"',"+
                "SPyinjiu='"+pelpelData2.getSPyinjiu()+"',"+
                "SPduPingLeiXing='"+pelpelData2.getSPduPingLeiXing()+"',"+
                "SPceshizhuangtai='"+pelpelData2.getSPceshizhuangtai()+"',"+
                "SPceshitype='"+pelpelData2.getSPceshitype()+"',"+
                "SPceshiresult='"+pelpelData2.getSPceshiresult()+"',"+
                "jiazhao='"+pelpelData2.getJiazhao()+"',"+
                "dengji='"+pelpelData2.getDengji()+"',"+
                "shangyejiazhao='"+pelpelData2.getShangyejiazhao()+"',"+
                "qianzhu='"+pelpelData2.getQianzhu()+"',"+
                "guanxiaqu='"+pelpelData2.getGuanxiaqu()+"',"+
                "chaosuxunwen='"+pelpelData2.getChaosuxunwen()+"',"+
                "fenxinjiashi='"+pelpelData2.getFenxinjiashi()+"',"+
                "sgfssxingwie='"+pelpelData2.getSgfssxingwie()+"',"+
                "sgfsszhuangt='"+pelpelData2.getSgfsszhuangt()+"',"+
                "jtwfxingwei='"+pelpelData2.getJtwfxingwei()+"',"+
                "jiazhaoxianzhi='"+pelpelData2.getJiazhaoxianzhi()+"'"+
                " where number = '"+
                pelpelData2.getNumber()+"' and SPname='"+pelpelData2.getSPname()+"'"
        );
    }
/*
*  其它人
*
* */
public void insertToPeopel3(PeopelData3 pelpelData3, String sid){
    db.execSQL("create table if not exists Peopel3(_id integer primary key autoincrement," +
            "number text,"+
            "SPname text,"+
            "SPxingbie text,"+
            "SPshenfentype text,"+
            "SPrenyuantype text,"+
            "SPsschengdu text,"+
            "phonenum text,"+
            "SPsgfssxingwei text,"+
            "SPsgfsqxingwei text,"+
            "SPsgfsszhuangtai text,"+
            "SPaqsbsyzhuangtai text,"+
            "SPsgfssposition text,"+
            "SPcheliangxunhao text,"+
            "SPyinjiu text,"+
            "SPduPingLeiXing text,"+
            "SPceshizhuangtai text,"+
            "SPceshitype text,"+
            "SPceshiresult text"+
            ")");
    db.execSQL("insert into Peopel3(" +
            "number,"+
            "SPname,"+
            "SPxingbie,"+
            "SPshenfentype,"+
            "SPrenyuantype,"+
            "SPsschengdu,"+
            "phonenum,"+
            "SPsgfssxingwei,"+
            "SPsgfsqxingwei,"+
            "SPsgfsszhuangtai,"+
            "SPaqsbsyzhuangtai,"+
            "SPsgfssposition,"+
            "SPcheliangxunhao,"+
            "SPyinjiu,"+
            "SPduPingLeiXing,"+
            "SPceshizhuangtai,"+
            "SPceshitype,"+
            "SPceshiresult"+
            ")values('" +
            sid+"','"+
            pelpelData3.getSPname()+"','"+
            pelpelData3.getSPxingbie()+"','"+
            pelpelData3.getSPshenfentype()+"','"+
            pelpelData3.getSPrenyuantype()+"','"+
            pelpelData3.getSPsschengdu()+"','"+
            pelpelData3.getPhonenum()+"','"+
            pelpelData3.getSPsgfssxingwei()+"','"+
            pelpelData3.getSPsgfsqxingwei()+"','"+
            pelpelData3.getSPsgfsszhuangtai()+"','"+
            pelpelData3.getSPaqsbsyzhuangtai()+"','"+
            pelpelData3.getSPsgfssposition()+"','"+
            pelpelData3.getSPcheliangxunhao()+"','"+
            pelpelData3.getSPyinjiu()+"','"+
            pelpelData3.getSPduPingLeiXing()+"','"+
            pelpelData3.getSPceshizhuangtai()+"','"+
            pelpelData3.getSPceshitype()+"','"+
            pelpelData3.getSPceshiresult()+"'"+
            ")");
}

    public List<PeopelData3> getPeopel3(String sid){
        db.execSQL("create table if not exists Peopel3(_id integer primary key autoincrement," +
                "number text,"+
                "SPname text,"+
                "SPxingbie text,"+
                "SPshenfentype text,"+
                "SPrenyuantype text,"+
                "SPsschengdu text,"+
                "phonenum text,"+
                "SPsgfssxingwei text,"+
                "SPsgfsqxingwei text,"+
                "SPsgfsszhuangtai text,"+
                "SPaqsbsyzhuangtai text,"+
                "SPsgfssposition text,"+
                "SPcheliangxunhao text,"+
                "SPyinjiu text,"+
                "SPduPingLeiXing text,"+
                "SPceshizhuangtai text,"+
                "SPceshitype text,"+
                "SPceshiresult text"+
                ")");
        List<PeopelData3> ps = new ArrayList<PeopelData3>();
        Cursor cursor=db.rawQuery("select * from Peopel3 where number = '"+sid+"'",null);
        // Cursor cursor=db.rawQuery("select * from CarData where _id > 0",null);
        if(cursor!=null){
            while(cursor.moveToNext()){
                //String number, Date data, String placeName, boolean isUpload)
                //String number, String accidenceNumber, String environmentNumber, String carNumber, String peopelNumber, Date data, String placeName, boolean isUpload
                PeopelData3 p = new PeopelData3(
                        cursor.getString(cursor.getColumnIndex("number")),
                        cursor.getString(cursor.getColumnIndex("SPname")),
                        cursor.getString(cursor.getColumnIndex("SPxingbie")),
                        cursor.getString(cursor.getColumnIndex("SPshenfentype")),
                        cursor.getString(cursor.getColumnIndex("SPrenyuantype")),
                        cursor.getString(cursor.getColumnIndex("SPsschengdu")),
                        cursor.getString(cursor.getColumnIndex("phonenum")),
                        cursor.getString(cursor.getColumnIndex("SPsgfssxingwei")),
                        cursor.getString(cursor.getColumnIndex("SPsgfsqxingwei")),
                        cursor.getString(cursor.getColumnIndex("SPsgfsszhuangtai")),
                        cursor.getString(cursor.getColumnIndex("SPaqsbsyzhuangtai")),
                        cursor.getString(cursor.getColumnIndex("SPsgfssposition")),
                        cursor.getString(cursor.getColumnIndex("SPcheliangxunhao")),
                        cursor.getString(cursor.getColumnIndex("SPyinjiu")),
                        cursor.getString(cursor.getColumnIndex("SPduPingLeiXing")),
                        cursor.getString(cursor.getColumnIndex("SPceshizhuangtai")),
                        cursor.getString(cursor.getColumnIndex("SPceshitype")),
                        cursor.getString(cursor.getColumnIndex("SPceshiresult"))
                );
                ps.add(p);
                Log.i("info",p.toString());
                Log.i("info","!!!!!!!!!!!!!!!!1");
            }
            cursor.close();
        }
        return ps;
    }

    public void updatePeopel3(PeopelData3 pelpelData3){
        db.execSQL("update Peopel3 set " +
                "SPname='"+pelpelData3.getSPname()+"',"+
                "SPxingbie='"+pelpelData3.getSPxingbie()+"',"+
                "SPshenfentype='"+pelpelData3.getSPshenfentype()+"',"+
                "SPrenyuantype='"+pelpelData3.getSPrenyuantype()+"',"+
                "SPsschengdu='"+pelpelData3.getSPsschengdu()+"',"+
                "phonenum='"+pelpelData3.getPhonenum()+"',"+
                "SPsgfssxingwei='"+pelpelData3.getSPsgfssxingwei()+"',"+
                "SPsgfsqxingwei='"+pelpelData3.getSPsgfsqxingwei()+"',"+
                "SPsgfsszhuangtai='"+pelpelData3.getSPsgfsszhuangtai()+"',"+
                "SPaqsbsyzhuangtai='"+pelpelData3.getSPaqsbsyzhuangtai()+"',"+
                "SPsgfssposition='"+pelpelData3.getSPsgfssposition()+"',"+
                "SPcheliangxunhao='"+pelpelData3.getSPcheliangxunhao()+"',"+
                "SPyinjiu='"+pelpelData3.getSPyinjiu()+"',"+
                "SPduPingLeiXing='"+pelpelData3.getSPduPingLeiXing()+"',"+
                "SPceshizhuangtai='"+pelpelData3.getSPceshizhuangtai()+"',"+
                "SPceshitype='"+pelpelData3.getSPceshitype()+"',"+
                "SPceshiresult='"+pelpelData3.getSPceshiresult()+"'"+
                " where number = '"+
                pelpelData3.getNumber()+"'and SPname='"+pelpelData3.getSPname()+"'"
        );
    }
    public void close(){
        db.close();
    }


    //清空
    public void deleteAccidentData(){
        db.execSQL("delete from AccidentData where _id>0");
    }
    public void deleteEMdata(){
        db.execSQL("delete from EMdata where _id>0");
    }
    public void deleteCarData(){
        db.execSQL("delete from CarData where _id>0");
    }
    public void deletePeopel1(){ db.execSQL("delete from Peopel1 where _id>0");}

    public void clearAll(){
        deleteAccidenceCollectionData();
        deleteAccidentData();
        deleteCarData();
        deleteEMdata();
    }
}

