package com.example.bloodplasmabankapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.bloodplasmabankapp.Models.RequestBloodDonorModel;
import com.example.bloodplasmabankapp.Models.RequestPlasmaDonorModel;
import com.example.bloodplasmabankapp.RequestActivity;

import java.util.ArrayList;


public class Db_Helper_Requests extends SQLiteOpenHelper {

    final static String DBname = "Requests3DB.db";
    final static int DBVersion = 1;

    public Db_Helper_Requests(@Nullable Context context) {
        super(context, DBname, null , DBVersion);;
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table Request"+
                        "(id integer primary key autoincrement," +
                        "phoneno text,"+
                        "name text ,"+
                        "bloodgroup text,"+
                        "emailaddress text,"+
                        "address text," +
                        "b_or_p text," +
                        "urgent text," +
                        "password text," +
                        "time text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP table if exists Request");
        onCreate(db);
    }

    public int insertRequest(String name,String phno,String bgp,String email,String address,String borp,String urgent,String pass, String time) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("phoneno", phno);
        values.put("name", name);
        values.put("bloodgroup", bgp);
        values.put("emailaddress", email);
        values.put("address", address);
        values.put("b_or_p", borp);
        values.put("urgent", urgent);
        values.put("password",pass);
        values.put("time",time);
        SQLiteDatabase d = this.getWritableDatabase();
        Cursor cursor = d.rawQuery("Select * from Request where phoneno= '" + phno + "' and b_or_p='"+ borp+"'", null);
        if (!cursor.moveToFirst()) {
            long id = database.insert("Request", null, values);
            if (id <= 0) {
                return 2;
            } else {
                return 1;
            }
        }
        else{
            return 3;
        }

    }

    public ArrayList<RequestBloodDonorModel> getBloodRequests(){
        ArrayList<RequestBloodDonorModel> requests = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select name,address,bloodgroup,phoneno,emailaddress,urgent, time from Request where b_or_p Like 'blood' and urgent like 'Yes'",null);
        if(cursor.moveToFirst()){
            do{
                RequestBloodDonorModel model = new RequestBloodDonorModel();
                model.setName(cursor.getString(0));
                model.setAddress(cursor.getString(1));
                model.setBloodgrp(cursor.getString(2));
                model.setPhone(cursor.getString(3));
                model.setEmail(cursor.getString(4));
                model.setUrgent(cursor.getString(5));
                model.setTime(cursor.getString(6));
                requests.add(model);
            }while(cursor.moveToNext());
        }
        cursor = database.rawQuery("select name,address,bloodgroup,phoneno,emailaddress,urgent, time from Request where b_or_p Like 'blood' and urgent LIKE 'no'",null);
        if(cursor.moveToFirst()){
            do{
                RequestBloodDonorModel model = new RequestBloodDonorModel();
                model.setName(cursor.getString(0));
                model.setAddress(cursor.getString(1));
                model.setBloodgrp(cursor.getString(2));
                model.setPhone(cursor.getString(3));
                model.setEmail(cursor.getString(4));
                model.setUrgent(cursor.getString(5));
                model.setTime(cursor.getString(6));
                requests.add(model);
            }while(cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return requests;
    }

    public ArrayList<RequestPlasmaDonorModel> getPlasmaRequests(){
        ArrayList<RequestPlasmaDonorModel> requests = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select name,address,bloodgroup,phoneno,emailaddress, urgent, time from Request where b_or_p LIKE 'plasma' and urgent like 'yes'",null);
        if(cursor.moveToFirst()){
            do{
                RequestPlasmaDonorModel model = new RequestPlasmaDonorModel();
                model.setName(cursor.getString(0));
                model.setAddress(cursor.getString(1));
                model.setBloodgrp(cursor.getString(2));
                model.setPhone(cursor.getString(3));
                model.setEmail(cursor.getString(4));
                model.setUrgent(cursor.getString(5));
                model.setTime(cursor.getString(6));
                requests.add(model);
            }while(cursor.moveToNext());
        }
        cursor = database.rawQuery("select name,address,bloodgroup,phoneno,emailaddress, urgent, time from Request where b_or_p LIKE 'plasma' and urgent like 'no'",null);
        if(cursor.moveToFirst()){
            do{
                RequestPlasmaDonorModel model = new RequestPlasmaDonorModel();
                model.setName(cursor.getString(0));
                model.setAddress(cursor.getString(1));
                model.setBloodgrp(cursor.getString(2));
                model.setPhone(cursor.getString(3));
                model.setEmail(cursor.getString(4));
                model.setUrgent(cursor.getString(5));
                model.setTime(cursor.getString(6));
                requests.add(model);
            }while(cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return requests;
    }

    public int getBloodRequestStatusForLogin(String phno, String password, String choice){

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select phoneno  from Request where phoneno = '"+phno+"' and b_or_p like '"+choice+"'",null);
        if(cursor.moveToFirst()){
            cursor = database.rawQuery("select * from Request where phoneno = '"+phno+"' and password = '"+password+"'  and b_or_p like '"+choice+"'",null);
            if(cursor.moveToFirst())
            {
                return 1;
            }
            else{
                return 2;
            }


        }
        else{
            return 3;
        }

    }

    public Cursor getRequestsByPhoneAndType(String ph, String type){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from Request where phoneno= '"+ph+"' and b_or_p like '"+type+"'",null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;

    }
    public boolean updateRequests(String name,String phno,String bgp,String email,String address,String borp,String urgent,String pass, int id){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("phoneno", phno);
        values.put("name", name);
        values.put("bloodgroup", bgp);
        values.put("emailaddress", email);
        values.put("address", address);
        values.put("b_or_p", borp);
        values.put("urgent", urgent);
        values.put("password",pass);

        SQLiteDatabase d= getWritableDatabase();
        Cursor cursor = d.rawQuery("Select * from Request where phoneno= '"+phno+"' and b_or_p like '"+borp+"' and id != "+id,null);
        if(cursor.moveToFirst()){
            return false;
        }
        else{
            long row = database.update("Request",values, "id = "+id,null);
            if(row <=0){
                return false;
            }
            else{
                return true;
            }
        }

    }

    public int deleteRequest(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        return  database.delete("Request","id = "+id,null);
    }
}
