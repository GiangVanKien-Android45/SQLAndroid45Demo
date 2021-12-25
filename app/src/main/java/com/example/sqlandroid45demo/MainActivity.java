package com.example.sqlandroid45demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.sqlandroid45demo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    SQLHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        sqlHelper = new SQLHelper(this);

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.etName.getText().toString();
                String number = binding.etNumber.getText().toString();
                String price = binding.etPrice.getText().toString();

                Foods foods = new Foods(1,name, number, price);

                sqlHelper.addFoods(foods);
            }
        });
    }
}