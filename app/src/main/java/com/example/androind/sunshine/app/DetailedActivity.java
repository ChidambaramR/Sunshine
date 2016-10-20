package com.example.androind.sunshine.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by chidr on 10/19/16.
 */

public class DetailedActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_layout);

        Intent intent = getIntent();
        int position = intent.getIntExtra("com.example.android.sunshine.app.DAY", -1);

        if (position == -1)
            throw new RuntimeException("Did not receive data");

        TextView city = (TextView)findViewById(R.id.city);
        city.setText("Mountain View");
    }
}
