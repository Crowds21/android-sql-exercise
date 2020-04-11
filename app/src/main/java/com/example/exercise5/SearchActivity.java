package com.example.exercise5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.exercise5.Constant.DB_VERSION;
import static com.example.exercise5.DataBaseUtil.*;

public class SearchActivity extends AppCompatActivity {
    ImageButton btnPage1;
    ImageButton btnPage2;
    EditText editTextSearch;
    Button btnSearch;
    private MyDatabaseHelper dbHelper;
    private List<ComponentStaffList> displayCards = new ArrayList<>();
    StaffListAdapter adapter;
    ListView listview;
    Spinner keySpinner;
    String keyword;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        dbHelper = new MyDatabaseHelper(this, "StaffTable.db", null, DB_VERSION);

        btnPage1 = (ImageButton) findViewById(R.id.btn_page_search_1);
        btnPage2 = (ImageButton) findViewById(R.id.btn_page_search_2);
        editTextSearch = (EditText) findViewById(R.id.edit_search);
        btnSearch = (Button) findViewById(R.id.btn_search);
        keySpinner = (Spinner) findViewById(R.id.spinner_key);


        btnPage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SearchActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SearchActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        //下拉选单
        keySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                String[] bdkey = getResources().getStringArray(R.array.db_key);
                keyword = bdkey[pos];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        //查找
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCards.clear();
                Log.d("SQL", "Search click");
                String value = editTextSearch.getText().toString().trim();
                Log.d("SQL", "Thread");

                adapter = new StaffListAdapter(SearchActivity.this,
                        R.layout.component_list_info, displayCards);
                listview = (ListView) findViewById(R.id.list_search_staff_info);
                displayCards = querySpecificRow(displayCards, dbHelper, keyword, value);
                listview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

        });

    }


}
