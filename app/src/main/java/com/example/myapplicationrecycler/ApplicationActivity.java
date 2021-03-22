package com.example.myapplicationrecycler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ApplicationActivity extends AppCompatActivity {

    private ImageView imageView;
    private EditText etName;
    private EditText etPhone;
    private Button btnSave;
    Uri imageData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        init();
    }

    private void init() {
        imageView = findViewById(R.id.imageView);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();
                ContactModel model = new ContactModel("", "", "");
                String image = imageData.toString();
                model.setName(name);
                model.setPhone(phone);
                model.setImage(image);
                Intent intent =getIntent();
                intent.putExtra(MainActivity.KEY, model);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK) {
            imageData = data.getData();
            Glide.with(this)
                    .load(imageData)
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageView);
        }
    }
}