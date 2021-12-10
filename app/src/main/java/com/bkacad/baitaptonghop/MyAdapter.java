package com.bkacad.baitaptonghop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<Custom> custom;
    private Object MainActivity;

    public MyAdapter(Context context, List<Custom> custom) {
        this.context = context;
        this.custom = custom;
    }

    @Override
    public int getCount() {
        return custom.size();
    }

    @Override
    public Object getItem(int position) {
        return custom.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_view,parent,false);
        }
        TextView tvName, tvPhone;
        ImageView imgPopup;
        PopupMenu popupMenu;

        tvName = convertView.findViewById(R.id.tvName);
        tvPhone = convertView.findViewById(R.id.tvPhone);
        imgPopup = convertView.findViewById(R.id.imgPopup);

        //set data
        tvName.setText(custom.get(position).getName());
        tvPhone.setText(custom.get(position).getPhone());

        // Tạo popup menu
        popupMenu = new PopupMenu(context.getApplicationContext(), imgPopup);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());

        // Sự kiện khi click vào button
        imgPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị popup lên
                popupMenu.show();
            }
        });

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.popup_menu_details:
                        Toast.makeText(context.getApplicationContext(),"Details",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.popup_menu_edit:
                        Toast.makeText(context.getApplicationContext(),"Edit",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.popup_menu_delete:
                        Toast.makeText(context.getApplicationContext(),"Delete",Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

        return convertView;
    }
}
