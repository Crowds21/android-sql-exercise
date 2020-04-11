package com.example.exercise5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.LogPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import static com.example.exercise5.Constant.DB_VERSION;
import static com.example.exercise5.DataBaseUtil.*;

public class StaffListAdapter extends ArrayAdapter<ComponentStaffList> {
    private  int resourceId;
    ComponentStaffList staffListCard;
    ViewHolder viewHolder;
    private ListItemClick onClickListener;
    Context mcontext;
    private MyDatabaseHelper dbHelper;

    public StaffListAdapter(@NonNull Context context, int resource,
                            @NonNull List<ComponentStaffList> objects) {
        super(context, resource, objects);
        //listview 布局ID
        resourceId = resource;
        mcontext = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //获取当前组成ListView的实例
        //View item=super.getView(position, convertView, parent);//父类方法返回的View相当于单个item控件

        staffListCard = getItem(position);
        View view = null;
        if (convertView == null){
            //首次加载，用viewHolder获取实例
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            //绑定组件
            viewHolder.imageBody = (ImageView)view.findViewById(R.id.image_body);
            viewHolder.staffId = (TextView)view.findViewById(R.id.text_staff_id);
            viewHolder.staffName= (TextView)view.findViewById(R.id.text_staff_name);
            viewHolder.staffSalary = (TextView)view.findViewById(R.id.text_staff_salary);
            viewHolder.staffDepart= (TextView)view.findViewById(R.id.text_staff_depart);
            viewHolder.staffDeleteBtn = (ImageButton)view.findViewById(R.id.image_btn_delete);
            view.setTag(viewHolder);//将ViewHolder 存储在View中
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag(); //重新获取ViewHolder
        }
        Bitmap bitmap = BitmapFactory.decodeByteArray(staffListCard.getImageBody(),
                0, staffListCard.getImageBody().length);

        viewHolder.imageBody.setImageBitmap(bitmap);
        viewHolder.staffId.setText(staffListCard.getStaffId());
        viewHolder.staffName.setText(staffListCard.getStaffName());
        viewHolder.staffSalary.setText(staffListCard.getStaffSalary());
        viewHolder.staffDepart.setText(staffListCard.getStaffDepart());
        viewHolder.staffDeleteBtn.setOnClickListener(new ListItemClick(position){
            @Override
            public void onClick(View v) {
                ComponentStaffList staffListCard =getItem(position) ;
                String deletedId = staffListCard.getStaffId();
//                MyDatabaseHelper dbHelper = new MyDatabaseHelper(mcontext, "StaffTable.db", null, 3);
//                String[] staffID = new String[]{"3"};
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                db.delete(" Staff", "id>?", staffID);
                dbHelper = new MyDatabaseHelper(mcontext ,"StaffTable.db", null, DB_VERSION);
                deleteSpecificRow(mcontext,dbHelper,
                        "id", deletedId);
                Log.d("SQL",deletedId);
            }
        });
        return view;
    }



    class ViewHolder{
        ImageView imageBody;
        TextView staffId;
        TextView staffName;
        TextView staffSalary;
        TextView staffDepart;
        ImageButton staffDeleteBtn;
    }

}


