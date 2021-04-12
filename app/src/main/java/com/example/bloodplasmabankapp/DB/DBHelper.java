package com.example.bloodplasmabankapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bloodplasmabankapp.Models.BloodDonorModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    final static String DBNAME = "NewBloodPlasma.db";
    final static int DBVERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table donors "+
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
        db.execSQL("DROP table if exists donors");
        onCreate(db);
    }

    public boolean insertOrder(String name,String phno,String bgp,String email,String address,String ailments,String gender)
    {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("phoneno",phno);
        values.put("name",name);
        values.put("bloodgroup",bgp);
        values.put("emailaddress",email);
        values.put("address",address);
        values.put("ailments",ailments);
        values.put("gender",gender);
        long id = database.insert("donors",null,values);
        if (id<=0){
            return false;
        }
        else{
            return true;
        }

    }
    public ArrayList<BloodDonorModel> getBloodDonors(){
        ArrayList<BloodDonorModel> lst = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select name,bloodgroup,address,phoneno,emailaddress,ailments,gender from donors",null);
        if(cursor.moveToFirst()){
            while (cursor.moveToNext()){
                BloodDonorModel model = new BloodDonorModel();
                model.setName(cursor.getString(0));
                model.setBlood_group(cursor.getString(1));
                model.setCity(cursor.getString(2));
                model.setPhno(cursor.getString(3));
                model.setMail(cursor.getString(4));
                model.setAilments(cursor.getString(5));
                model.setAilments(cursor.getString(6));
                lst.add(model);
            }
        }
        cursor.close();
        database.close();
        return lst;
    }
}