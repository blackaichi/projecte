package com.projectefinal.blackaichi.projectefinal.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    //Declaracion global de la version de la base de datos
    public static final String DATABASE_NAME = "USER-PASS";

    //Declaracion del nombre de la tabla
    public static final String TABLE ="Usernames";

    //sentencia global de cracion de la base de datos
    public static final String KEY_user = "user";
    public static final String KEY_pass = "pass";
    private static final String TAG = "main activity";


    public DataBase(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String AddLogin = "CREATE TABLE " + TABLE + "(" + KEY_user + " TEXT, " + KEY_pass + " TEXT)";
        db.execSQL(AddLogin);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void createuserpass(String user, String pass, String reppass) {
        Log.v(TAG, "he afegit algu a la bd");
        SQLiteDatabase db = this.getWritableDatabase();
        Log.v(TAG, "he afegit algu a la bd");
        ContentValues values = new ContentValues();
        values.put(KEY_user, user);
        values.put(KEY_pass, pass);
        Log.v(TAG, "he afegit algu a la bd");
        db.insert(TABLE, null, values);
        db.close();
        Log.v(TAG, "he afegit algu a la bd");
    }

    public boolean checklogin(String user, String pass) {
        String selectQuery = "SELECT  * FROM " + TABLE + " WHERE user == '" + user + "' and pass == '" + pass + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() == 0) {
            cursor.close();
            db.close();
            return false;
        }
        cursor.close();
        db.close();
        return true;
    }
}
