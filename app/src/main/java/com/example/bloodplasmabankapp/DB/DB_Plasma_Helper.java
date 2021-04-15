package com.example.bloodplasmabankapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.bloodplasmabankapp.Models.PlasmaDonorModel;

import java.util.ArrayList;

public class DB_Plasma_Helper extends SQLiteOpenHelper {
    final static String DBname = "NewPlasmaDB.db";
    final static int DBVersion = 1;
    public DB_Plasma_Helper(@Nullable Context context) {

        super(context, DBname, null , DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table plasma_donor"+
                        "(id integer primary key autoincrement," +
                        "phoneno text,"+
                        "name text ,"+
                        "bloodgroup text,"+
                        "emailaddress text,"+
                        "address text," +
                        "ailments text," +
                        "gender text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP table if exists plasma_donor");
        onCreate(db);
    }

    public boolean insertPlasmaDonor(String name,String phno,String bgp,String email,String address,String ailments,String gender){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values= new ContentValues();
        values.put("phoneno",phno);
        values.put("name",name);
        values.put("bloodgroup",bgp);
        values.put("emailaddress",email);
        values.put("address",address);
        values.put("ailments",ailments);
        values.put("gender",gender);
        long id = database.insert("plasma_donor",null,values);
        if(id<=0){
            return false;
        }
        else{
            return true;
        }
    }

    public ArrayList<PlasmaDonorModel> getPlasmaDonors(){
        ArrayList<PlasmaDonorModel> donors = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select name,address,bloodgroup from plasma_donor",null);
        if(cursor.moveToFirst()){
            do{
                PlasmaDonorModel model = new PlasmaDonorModel();
                model.setName(cursor.getString(0));
                model.setCity(cursor.getString(1));
                model.setPlasmagrp(cursor.getString(2));
                donors.add(model);
            }while(cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return donors;
    }
}