package com.example.ashen.sqliteexample.activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.TextView;

import com.example.ashen.sqliteexample.R;
import com.example.ashen.sqliteexample.helpers.InputValidation;
import com.example.ashen.sqliteexample.modal.Content;
import com.example.ashen.sqliteexample.modal.User;
import com.example.ashen.sqliteexample.sql.ContentDbHelper;
import com.example.ashen.sqliteexample.sql.DatabaseHelper;
import com.example.ashen.sqliteexample.sql.DatabaseManager;

public class InsertModules extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = InsertModules.this;

    private NestedScrollView nestedScrollView;
    private TextInputLayout textInputLayoutModuleName;
    private TextInputLayout textInputLayoutModuleDescription;
    private TextInputEditText textInputEditTextmoduleName;
    private TextInputEditText textInputEditTextmoduleDesc;
    private AppCompatButton appCompatButtonAddContents;
    private InputValidation inputValidation;
    private ContentDbHelper contentDbHelper;
    private Content content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_modules);
        initViews();
        initListeners();
        initObjects();
    }

    private void initListeners() {
        appCompatButtonAddContents.setOnClickListener(this);
    }

    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        textInputLayoutModuleName = (TextInputLayout) findViewById(R.id.textInputLayoutModuleName);
        textInputLayoutModuleDescription = (TextInputLayout) findViewById(R.id.textInputLayoutModuleDescription);
        textInputEditTextmoduleName = (TextInputEditText) findViewById(R.id.moduleName);
        textInputEditTextmoduleDesc = (TextInputEditText) findViewById(R.id.moduleDesc);
        appCompatButtonAddContents = (AppCompatButton) findViewById(R.id.appCompatButtonAddContents);
    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        contentDbHelper = new ContentDbHelper();
        content = new Content();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.appCompatButtonAddContents:
                postDataToSQLite();
                Intent intentRegister = new Intent(getApplicationContext(), ViewContents.class);
                startActivity(intentRegister);
                break;
        }
    }

    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextmoduleName, textInputLayoutModuleName,"Module Name is required!" )) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextmoduleDesc, textInputLayoutModuleDescription,"Module Description is required!" )) {
            return;
        }



        content.setModuleName(textInputEditTextmoduleName.getText().toString().trim());
        content.setModuleDescription(textInputEditTextmoduleDesc.getText().toString().trim());

        contentDbHelper.addContent(content);

            // Snack Bar to show success message that record saved successfully
            Snackbar snackbar = Snackbar.make(nestedScrollView, "Record Saved Successfully.", Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            view.setBackgroundColor(getResources().getColor(R.color.colorBackground));
            TextView textView = (TextView)view.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(getResources().getColor(R.color.colorSnackBarTextRegisterSuccess));
            textView.setTextSize(18);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            snackbar.show();
            emptyInputEditText();



    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextmoduleName.setText(null);
        textInputEditTextmoduleDesc.setText(null);
    }
}
