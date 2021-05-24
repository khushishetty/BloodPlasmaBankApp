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
    final static int DBVersion = 3;
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
                        "gender text," +
                        "age text," +
                        "password text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP table if exists plasma_donor");
        onCreate(db);
    }

    public int insertPlasmaDonor(String name,String phno,String bgp,String email,String address,String ailments,String gender,String age, String pass){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values= new ContentValues();
        values.put("phoneno",phno);
        values.put("name",name);
        values.put("bloodgroup",bgp);
        values.put("emailaddress",email);
        values.put("address",address);
        values.put("ailments",ailments);
        values.put("gender",gender);
        values.put("age",age);
        values.put("password",pass);

        SQLiteDatabase d = this.getWritableDatabase();
        Cursor cursor = d.rawQuery("Select * from plasma_donor where phoneno= '" + phno + "'", null);

        if(!cursor.moveToFirst()){
            long id = database.insert("plasma_donor",null,values);
            if(id<=0){
                return 2;
            }
            else{
                return 1;
            }
        }
        else{
            return 3;
        }

    }

    public ArrayList<PlasmaDonorModel> getPlasmaDonors(){
        ArrayList<PlasmaDonorModel> donors = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select name,address,bloodgroup,phoneno,emailaddress,ailments,gender, password,age from plasma_donor",null);
        if(cursor.moveToFirst()){
            do{
                PlasmaDonorModel model = new PlasmaDonorModel();
                model.setName(cursor.getString(0));
                model.setCity(cursor.getString(1));
                model.setPlasmagrp(cursor.getString(2));
                model.setPhno(cursor.getString(3));
                model.setEmail(cursor.getString(4));
                model.setAilments(cursor.getString(5));
                model.setGender(cursor.getString(6));
                model.setPassword(cursor.getString(7));
                model.setAge(cursor.getString(8));
                donors.add(model);
            }while(cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return donors;
    }

    public Cursor getPlasmaDonorByPhone(String ph){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from plasma_donor where phoneno= '"+ph+"'",null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;

    }

    public int deletePlasmaDonor(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("plasma_donor","id = '"+id+"'",null);

    }


    public ArrayList<PlasmaDonorModel> getPlasmaDonorByCity(String city)
    {
        ArrayList<PlasmaDonorModel> donors = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select name,address,bloodgroup,phoneno,emailaddress,ailments,gender, age from plasma_donor where lower(address) LIKE '%"+ city+"%'",null);
        if(cursor.moveToFirst()){
            do{
                PlasmaDonorModel model = new PlasmaDonorModel();
                model.setName(cursor.getString(0));
                model.setCity(cursor.getString(1));
                model.setPlasmagrp(cursor.getString(2));
                model.setPhno(cursor.getString(3));
                model.setEmail(cursor.getString(4));
                model.setAilments(cursor.getString(5));
                model.setGender(cursor.getString(6));
                model.setAge(cursor.getString(7));
                donors.add(model);
            }while(cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return donors;
    }

    public ArrayList<PlasmaDonorModel> getPlasmaDonorByCity(String city, String grp)
    {
        ArrayList<PlasmaDonorModel> donors = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select name,address,bloodgroup,phoneno,emailaddress,ailments,gender,age from plasma_donor where bloodgroup = '"+grp+"' and lower(address) LIKE '%"+ city+"%'",null);
        if(cursor.moveToFirst()){
            do{
                PlasmaDonorModel model = new PlasmaDonorModel();
                model.setName(cursor.getString(0));
                model.setCity(cursor.getString(1));
                model.setPlasmagrp(cursor.getString(2));
                model.setPhno(cursor.getString(3));
                model.setEmail(cursor.getString(4));
                model.setAilments(cursor.getString(5));
                model.setGender(cursor.getString(6));
                model.setAge(cursor.getString(7));
                donors.add(model);
            }while(cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return donors;
    }

    public ArrayList<PlasmaDonorModel> getPlasmaDonorsGroupwise(String grp){
        ArrayList<PlasmaDonorModel> donors = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select name,address,bloodgroup,phoneno,emailaddress,ailments,gender,age from plasma_donor where bloodgroup = '"+grp+"'",null);
        if(cursor.moveToFirst()){
            do{
                PlasmaDonorModel model = new PlasmaDonorModel();
                model.setName(cursor.getString(0));
                model.setCity(cursor.getString(1));
                model.setPlasmagrp(cursor.getString(2));
                model.setPhno(cursor.getString(3));
                model.setEmail(cursor.getString(4));
                model.setAilments(cursor.getString(5));
                model.setGender(cursor.getString(6));
                model.setAge(cursor.getString(7));
                donors.add(model);
            }while(cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return donors;
    }

    public boolean updatePlasmaDonor(String name,String phno,String bgp,String email,String address,String ailments,String gender,String age,String pass, int id){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("phoneno",phno);
        values.put("name",name);
        values.put("bloodgroup",bgp);
        values.put("emailaddress",email);
        values.put("address",address);
        values.put("ailments",ailments);
        values.put("gender",gender);
        values.put("age",age);
        values.put("password", pass);

        SQLiteDatabase d= getWritableDatabase();
        Cursor cursor = d.rawQuery("Select * from plasma_donor where phoneno= '"+phno+"' and id != "+id,null);
        if(cursor.moveToFirst()){
            return false;
        }
        else{
            long row = database.update("plasma_donor",values, "id = "+id,null);
            if(row <=0){
                return false;
            }
            else{
                return true;
            }
        }

    }

    public int getPlasmaDonationStatusForLogin(String phno, String password){

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select phoneno  from plasma_donor where phoneno = '"+phno+"' ",null);
        if(cursor.moveToFirst()){
            cursor = database.rawQuery("select * from plasma_donor where phoneno = '"+phno+"' and password = '"+password+"' ",null);
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
}
