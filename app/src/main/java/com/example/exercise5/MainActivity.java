package com.example.exercise5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.exercise5.Constant.*;
import static com.example.exercise5.DataBaseUtil.*;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private List<ComponentStaffList> displayCards = new ArrayList<>();
    ImageButton btnCreateTable;
    ImageButton btnInsertTable;
    ImageButton btnDisplay;
    ImageButton btnPage1;
    ImageButton btnPage2;

    //List Component
    ImageButton imageBtnDelete;
    TextView textStaffId;
    TextView textStaffName;
    TextView textStaffSalary;
    TextView textStaffDepart;

    //ListView Adapter
    StaffListAdapter adapter ;
    ListView listview ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("SQL", "Create1");
        dbHelper = new MyDatabaseHelper(this, "StaffTable.db", null, DB_VERSION);

        btnCreateTable = (ImageButton) findViewById(R.id.btn_create_table);
        btnInsertTable = (ImageButton) findViewById(R.id.btn_insert_table);
        btnDisplay = (ImageButton) findViewById(R.id.btn_display);
        btnPage1 = (ImageButton) findViewById(R.id.btn_page1);
        btnPage2 = (ImageButton) findViewById(R.id.btn_page2);

        adapter = new StaffListAdapter(MainActivity.this,
                R.layout.component_list_info, displayCards);
        listview = (ListView) findViewById(R.id.list_staff_info);
        displayCards = traverseDB(displayCards,dbHelper);       //遍历数据，做初始化

        listview.setAdapter(adapter);


        //跳转
        btnPage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //跳转
        btnPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        //测试创建数据库
        btnCreateTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SQL", "getWritableDatabase()");
                displayCards = traverseDB(displayCards,dbHelper);
                adapter.notifyDataSetChanged();
            }
        });

        //数据库装载数据
        btnInsertTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SQL", "InitDB");
                initDB();
                //将图片存入数据库
            }
        });

        //避免数据重复按钮
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SQL", "InitTable");
                String[] staffID = new String[]{"4"};
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete(" Staff", "id>?", staffID);
                adapter.notifyDataSetChanged();
            }
        });

    }

    //数据库初始化
    protected  void initDB(){
        ContentValues values = new ContentValues();
        values.put("image",convertToByte(R.drawable.dtb_1));
        values.put("name", "Hei");
        values.put("salary", 8000);
        values.put("depart", "Executive Force");
        insertRow(MainActivity.this,dbHelper,values);

        values.clear();
        values.put("image",convertToByte(R.drawable.dtb_2));
        values.put("name", "Yin");
        values.put("salary", 7000);
        values.put("depart", "Support Force");
        insertRow(MainActivity.this,dbHelper,values);

        values.clear();
        values.put("image",convertToByte(R.drawable.dtb_3));
        values.put("name", "Mao");
        values.put("salary", 1000);
        values.put("depart", "Scout Force");
        insertRow(MainActivity.this,dbHelper,values);

        values.clear();
        values.put("image",convertToByte(R.drawable.dtb_4));
        values.put("name", "Huang");
        values.put("salary", 8000);
        values.put("depart", "Tactical Guidance");
        insertRow(MainActivity.this,dbHelper,values);

        //删除测试数据

        values.clear();
        values.put("image",convertToByte(R.drawable.punish_1));
        values.put("name", "烬燃");
        values.put("salary", 10000);
        values.put("depart", "Punishing");
        insertRow(MainActivity.this,dbHelper,values);
    }
    //图片格式转换
    protected byte[] convertToByte(int id){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(id)).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

}

