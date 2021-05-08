package com.example.bloodplasmabankapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.bloodplasmabankapp.Models.RequestBloodDonorModel;
import com.example.bloodplasmabankapp.Models.RequestPlasmaDonorModel;
import com.example.bloodplasmabankapp.RequestActivity;

import java.util.ArrayList;


public class Db_Helper_Requests extends SQLiteOpenHelper {

    final static String DBname = "RequestsDB.db";
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
                        "urgent text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP table if exists Request");
        onCreate(db);
    }

    public boolean insertRequest(String name,String phno,String bgp,String email,String address,String borp,String urgent) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("phoneno", phno);
        values.put("name", name);
        values.put("bloodgroup", bgp);
        values.put("emailaddress", email);
        values.put("address", address);
        values.put("b_or_p", borp);
        values.put("urgent", urgent);

        SQLiteDatabase d = this.getWritableDatabase();
        Cursor cursor = d.rawQuery("Select * from Request where phoneno= '" + phno + "' and b_or_p='"+ borp+"'", null);
        if (!cursor.moveToFirst()) {
            long id = database.insert("Request", null, values);
            if (id <= 0) {
                return false;
            } else {
                return true;
            }
        }
        else{
            return false;
        }

    }

    public ArrayList<RequestBloodDonorModel> getBloodRequests(){
        ArrayList<RequestBloodDonorModel> requests = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select name,address,bloodgroup,phoneno,emailaddress from Request where b_or_p = 'blood'",null);
        if(cursor.moveToFirst()){
            do{
                RequestBloodDonorModel model = new RequestBloodDonorModel();
                model.setName(cursor.getString(0));
                model.setAddress(cursor.getString(1));
                model.setBloodgrp(cursor.getString(2));
                model.setPhone(cursor.getString(3));
                model.setEmail(cursor.getString(4));
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
        Cursor cursor = database.rawQuery("select name,address,bloodgroup,phoneno,emailaddress from Request where b_or_p = 'plasma'",null);
        if(cursor.moveToFirst()){
            do{
                RequestPlasmaDonorModel model = new RequestPlasmaDonorModel();
                model.setName(cursor.getString(0));
                model.setAddress(cursor.getString(1));
                model.setBloodgrp(cursor.getString(2));
                model.setPhone(cursor.getString(3));
                model.setEmail(cursor.getString(4));
                requests.add(model);
            }while(cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return requests;
    }
}
