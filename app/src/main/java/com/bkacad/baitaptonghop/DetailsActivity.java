package com.bkacad.baitaptonghop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private TextView tvName, tvPhone, tvAddress;
    private Button btnBack;
    private ImageView img;

    private void initUI(){
        tvName = findViewById(R.id.tvName);
        tvPhone = findViewById(R.id.tvPhone);
        tvAddress = findViewById(R.id.tvAddress);
        img = findViewById(R.id.img);
        btnBack = findViewById(R.id.btnBack);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initUI();

        Intent intent = getIntent();
        Custom custom = (Custom) intent.getSerializableExtra("data");
        tvName.setText(custom.getName());
        tvPhone.setText(custom.getPhone());
        tvAddress.setText(custom.getAddress());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}