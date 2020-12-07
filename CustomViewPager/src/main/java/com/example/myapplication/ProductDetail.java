package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetail extends AppCompatActivity {

    TextView tvPosition, tvUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        tvPosition = findViewById(R.id.tvPosition);
        tvUrl = findViewById(R.id.tvUrl);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            tvPosition.setText(bundle.getString("position"));
            tvUrl.setText(bundle.getString("url"));

        } else {
            Toast.makeText(getApplicationContext(), "numm data", Toast.LENGTH_SHORT).show();
        }

    }
}
