package com.example.exercise5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import java.util.List;

public class DataBaseUtil {
    //删除特定行
    public static void  deleteSpecificRow(Context context,SQLiteOpenHelper dbHelper,
                                          String column, String value){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //ContentValues values = new ContentValues();
        String[] row = new String[]{value};
        db.delete(" Staff", column+ "=?", row);
        Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
    }

    //查询特定数据
    public static List<ComponentStaffList>  querySpecificRow(List<ComponentStaffList> displayCards,
                                                      SQLiteOpenHelper dbHelper,
                                                      String column, String value){
        //保险起见，clear一下
        displayCards.clear();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Staff", null,
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                //判断是否为所需的行
                if (cursor.getString(cursor.getColumnIndex(column)).equals(value)){
                    byte[] staffImage = cursor.getBlob(cursor.getColumnIndex("image"));
                    String staffId = cursor.getString(cursor.getColumnIndex("id"));
                    String staffName = cursor.getString(cursor.getColumnIndex("name"));
                    String staffSalary = cursor.getString(cursor.getColumnIndex("salary"));
                    String staffDepart = cursor.getString(cursor.getColumnIndex("depart"));
                    ComponentStaffList componentStaffList = new ComponentStaffList(staffImage,staffId, staffName,
                            staffSalary, staffDepart);
                    displayCards.add(componentStaffList);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return displayCards;
    }
    //遍历数据库
    public static List<ComponentStaffList>  traverseDB(List<ComponentStaffList> displayCards,
                                                       SQLiteOpenHelper dbHelper){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //cursor 光标
        Cursor cursor = db.query("Staff", null,
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                byte[] staffImage = cursor.getBlob(cursor.getColumnIndex("image"));
                Log.d("SQL",staffImage.toString());
                String staffId = cursor.getString(cursor.getColumnIndex("id"));
                String staffName = cursor.getString(cursor.getColumnIndex("name"));
                String staffSalary = cursor.getString(cursor.getColumnIndex("salary"));
                String staffDepart = cursor.getString(cursor.getColumnIndex("depart"));
                ComponentStaffList componentStaffList = new ComponentStaffList(staffImage,staffId, staffName,
                        staffSalary, staffDepart);
                displayCards.add(componentStaffList);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return displayCards;
    }

    //插入
    public static void insertRow(Context context,SQLiteOpenHelper dbHelper,  ContentValues values){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(" Staff", null, values);
    }
}
