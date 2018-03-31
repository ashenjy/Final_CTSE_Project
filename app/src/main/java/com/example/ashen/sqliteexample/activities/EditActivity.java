package com.example.ashen.sqliteexample.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.ashen.sqliteexample.R;
import com.example.ashen.sqliteexample.helpers.InputValidation;
import com.example.ashen.sqliteexample.modal.Content;
import com.example.ashen.sqliteexample.sql.ContentDbHelper;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = EditActivity.this;

    private NestedScrollView nestedScrollView;
    private TextInputLayout textInputLayoutModuleName;
    private TextInputLayout textInputLayoutModuleDescription;
    private TextInputEditText textInputEditTextmoduleName;
    private TextInputEditText textInputEditTextmoduleDesc;
    private AppCompatButton appCompatButtonUpdateContents;
    private InputValidation inputValidation;
    private ContentDbHelper contentDbHelper;
    private Content content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Bundle recdData = getIntent().getExtras();
        try {
            String moduleName = recdData.getString("ClickedItemValue_ModuleName");
            String moduleDesc = recdData.getString("ClickedItemValue_ModuleDesc");

            Log.i("moduleName :" ,moduleName) ;
            textInputEditTextmoduleName = (TextInputEditText) findViewById(R.id.moduleName);
            textInputEditTextmoduleDesc = (TextInputEditText) findViewById(R.id.moduleDesc);
            textInputEditTextmoduleName.setText(moduleName);
            textInputEditTextmoduleDesc.setText(moduleDesc);
        }catch (java.lang.NullPointerException e){
            Log.e("NullPointerException" ,e.getMessage()) ;
        }


        initViews();
        initListeners();
        initObjects();
    }

    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        textInputLayoutModuleName = (TextInputLayout) findViewById(R.id.textInputLayoutModuleName);
        textInputLayoutModuleDescription = (TextInputLayout) findViewById(R.id.textInputLayoutModuleDescription);
        appCompatButtonUpdateContents = (AppCompatButton) findViewById(R.id.appCompatButtonUpdateContents);
    }

    private void initListeners() {
        appCompatButtonUpdateContents.setOnClickListener(this);
    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        contentDbHelper = new ContentDbHelper();
        content = new Content();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.appCompatButtonUpdateContents:
                updateDataToSQLite();
                break;
        }
    }

    private void updateDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextmoduleName, textInputLayoutModuleName,"Module Name is required!" )) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextmoduleDesc, textInputLayoutModuleDescription,"Module Description is required!" )) {
            return;
        }

        Bundle recdData = getIntent().getExtras();
        try {
            int moduleId = recdData.getInt("ClickedItemValue_moduleId");
            content.setId(moduleId);
            content.setModuleName(textInputEditTextmoduleName.getText().toString().trim());
            content.setModuleDescription(textInputEditTextmoduleDesc.getText().toString().trim());
        }catch (java.lang.NullPointerException e){
            Log.e("NullPointerException" ,e.getMessage()) ;
        }

        contentDbHelper.updateContent(content);

        // Snack Bar to show success message that record saved successfully
        Snackbar snackbar = Snackbar.make(nestedScrollView, "Record Updated Successfully.", Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        view.setBackgroundColor(getResources().getColor(R.color.colorBackground));
        TextView textView = (TextView)view.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(getResources().getColor(R.color.colorSnackBarTextRegisterSuccess));
        textView.setTextSize(18);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        snackbar.show();
        emptyInputEditText();

        Intent intentRegister = new Intent(getApplicationContext(), ViewContents.class);
        startActivity(intentRegister);

    }

    private void emptyInputEditText() {
        textInputEditTextmoduleName.setText(null);
        textInputEditTextmoduleDesc.setText(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent startIntent = null;
        switch(item.getItemId()) {
            case R.id.logout:
                startIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(startIntent);
                return(true);
            case R.id.user_details:
                startIntent = new Intent(getApplicationContext(), UsersListActivity.class);
                startActivity(startIntent);
                return(true);
            case R.id.view:
                startIntent = new Intent(getApplicationContext(), ViewContents.class);
                startActivity(startIntent);
                return(true);

        }
        return(super.onOptionsItemSelected(item));
    }

}
