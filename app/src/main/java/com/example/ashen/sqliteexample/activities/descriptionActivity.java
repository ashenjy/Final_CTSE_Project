package com.example.ashen.sqliteexample.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ashen.sqliteexample.R;

public class descriptionActivity extends AppCompatActivity {

    private TextView textViewModuleDesc;
    private TextView textViewModuleName1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Bundle recdData = getIntent().getExtras();
        String moduleName = recdData.getString("ClickedItemValue_ModuleName");
        String moduleDesc = recdData.getString("ClickedItemValue_ModuleDesc");

        textViewModuleDesc = findViewById(R.id.textViewModuleDesc);
        textViewModuleName1 = findViewById(R.id.textViewModuleName1);

        textViewModuleName1.setText(moduleName);
        textViewModuleDesc.setText(moduleDesc);

    }
}
