package com.example.tr.datacollection.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tr.datacollection.model.SimpleDataTest;

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
    

    public void close(){
        db.close();
    }

}

