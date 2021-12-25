package com.example.sqlandroid45demo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLHelper extends SQLiteOpenHelper {

    private static final String TAG = "Cannot invoke method length() on null object";
    private static final String DB_NAME = "OrderFoods.db";
    private static final String DB_TABLE = "Foods";
    private static final int DB_VERSION = 1;

    private static final String DB_FOODS_ID = "id";
    private static final String DB_FOODS_NAME = "name";
    private static final String DB_FOODS_NUMBER = "number";
    private static final String DB_FOODS_PRICE = "price";

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryCreateTable = "CREATE TABLE Foods(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "name Text," +
                "number INTEGER," +
                "price INTEGER)";

        db.execSQL(queryCreateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        if(i != i1){
            db.execSQL(" DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(db);
        }

    }

    public void addFoods(Foods foods){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DB_FOODS_NAME,foods.getName());
        contentValues.put(DB_FOODS_NUMBER,foods.getNumber());
        contentValues.put(DB_FOODS_PRICE,foods.getPrice());

        sqLiteDatabase.insert(DB_TABLE,null, contentValues);
    }

    public void onUpdateFoods(String id,Foods foods){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DB_FOODS_NAME,foods.getName());
        contentValues.put(DB_FOODS_NUMBER,foods.getNumber());
        contentValues.put(DB_FOODS_PRICE,foods.getPrice());

        sqLiteDatabase.update(DB_TABLE,contentValues,"id=?",new String[]{String.valueOf(id)});
    }

    public void onDeleteAll(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE,null,null);
    }

    public void onDeleteFoods(String id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE,"id=?",new String[]{String.valueOf(id)});
    }

    public List<Foods> getAllFoods(){
        List<Foods> foods = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Foods food;

        Cursor cursor =sqLiteDatabase.query(false,DB_TABLE,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()){
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DB_FOODS_ID));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DB_FOODS_NAME));
            @SuppressLint("Range") String price = cursor.getString(cursor.getColumnIndex(DB_FOODS_PRICE));
            @SuppressLint("Range") String number = cursor.getString(cursor.getColumnIndex(DB_FOODS_NUMBER));

            food = new Foods(id,name,number,price);

            foods.add(food);
        }

        return foods;
    }
}
