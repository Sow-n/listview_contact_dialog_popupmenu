package com.bkacad.baitaptonghop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabAdd;
    private ListView lvCustom;
    private MyAdapter myAdapter;
    private List<Custom> list;
    private CustomDialog customDialog;

    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;

    private  void initUI(){
        fabAdd = findViewById(R.id.fabAdd);
        lvCustom = findViewById(R.id.lvCustom);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        // Fake contact
        list = new ArrayList<>();
        list.add(new Custom("Son 1","HN", "0962027911"));
        list.add(new Custom("Son 2","LC", "0962027912"));
        list.add(new Custom("Thai Son","LC", "0962027913"));

        // Tạo adapter
        myAdapter = new MyAdapter(this,list);
        // Set Adapter cho list view
        lvCustom.setAdapter(myAdapter);

        // Tạo dialog
        customDialog = new CustomDialog(this) {
            @Override
            protected void passData(String name, String address, String phone) {
                list.add(new Custom(name, address, phone));
                myAdapter.notifyDataSetChanged();
            }
        };

        // Click vào Floating Button
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị lên dialog -> xử lý
                customDialog.show();
                Toast.makeText(MainActivity.this,"Show dialog",Toast.LENGTH_SHORT).show();
            }
        });

        lvCustom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("data", list.get(position));
                startActivity(intent);
            }
        });

        lvCustom.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //
                builder = new AlertDialog.Builder(MainActivity.this).
                        setTitle("Delete?").
                        setMessage("Bạn có thực sự muốn xóa người này???").
                        setPositiveButton("Đúng", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int position = lvCustom.getPositionForView(view);
                                list.remove(position);
                                myAdapter.notifyDataSetChanged();
                            }

                        }).setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Cancel deletion", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
                Toast.makeText(getApplicationContext(), "Clickeddd", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}