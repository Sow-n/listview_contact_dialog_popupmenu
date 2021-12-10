package com.bkacad.baitaptonghop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public abstract class CustomDialog extends Dialog {

    private Button btnSave, btnExit;
    private EditText edtName, edtAddress, edtPhone;
    private Context context;

    public CustomDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void show() {
        super.show();
        edtName.setText("");
        edtAddress.setText("");
        edtPhone.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);

        edtName = findViewById(R.id.edtName);
        edtAddress = findViewById(R.id.edtAddress);
        edtPhone = findViewById(R.id.edtPhone);
        btnSave = findViewById(R.id.btnSave);
        btnExit = findViewById(R.id.btnExit);

        // Sự kiện khi click vào button
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sau
                String name = edtName.getText().toString();
                if(name.isEmpty()) {
                    edtName.setError("Hãy nhập dữ liệu");
                    return;
                }

                String address = edtAddress.getText().toString();
                if(address.isEmpty()){
                    edtAddress.setError("Hãy nhập dữ liệu");
                    return;
                }

                String phone = edtPhone.getText().toString();
                if(phone.isEmpty()){
                    edtPhone.setError("Hãy nhập dữ liệu");
                    return;
                }
                passData(name,address, phone);
                dismiss();
            }

        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Toast.makeText(context, "Thoát",Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected abstract void passData(String name, String address, String phone);
}