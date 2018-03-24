package com.example.ashen.sqliteexample.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ashen.sqliteexample.R;

public class descriptionActivity extends AppCompatActivity {

    private TextView textViewModuleDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Bundle recdData = getIntent().getExtras();
        String myVal = recdData.getString("ListViewClickedItemValue");

        textViewModuleDesc = findViewById(R.id.textViewModuleDesc);
        textViewModuleDesc.setText(myVal);
    }
}
