package com.example.ashen.sqliteexample.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashen.sqliteexample.R;
import com.example.ashen.sqliteexample.helpers.InputValidation;
import com.example.ashen.sqliteexample.modal.Content;
import com.example.ashen.sqliteexample.sql.ContentDbHelper;

public class descriptionActivity extends AppCompatActivity  {

    private NestedScrollView nestedScrollView;
    private ContentDbHelper contentDbHelper;
    private Content content;
    private TextView textViewModuleDesc;
    private TextView textViewModuleName1;
    private String moduleName = null;
    private String moduleDesc = null;
    private int moduleID = 0;

    Intent startIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        initViews();
        initObjects();
    }

    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        Bundle recdData = getIntent().getExtras();
        moduleName = recdData.getString("ClickedItemValue_ModuleName");
        moduleDesc = recdData.getString("ClickedItemValue_ModuleDesc");
        moduleID = recdData.getInt("ClickedItemValue_moduleId");
        textViewModuleDesc = findViewById(R.id.textViewModuleDesc);
        textViewModuleName1 = findViewById(R.id.textViewModuleName1);
        textViewModuleName1.setText(moduleName);
        textViewModuleDesc.setText(moduleDesc);
    }

    private void initObjects() {
        contentDbHelper = new ContentDbHelper();
        content = new Content();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.delete:
                AlertDialog.Builder alert = new AlertDialog.Builder(
                        descriptionActivity.this);
                alert.setTitle("Alert");
                alert.setMessage("Are you sure to delete record?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        content.setId(moduleID);
                        contentDbHelper.deleteContent(content);

                        emptyTextView();

                        startIntent = new Intent(getApplicationContext(), ViewContents.class);
                        startActivity(startIntent);

                        dialog.dismiss();

                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });

                alert.show();

                return(true);

            case R.id.edit:
                startIntent = new Intent(getApplicationContext(), EditActivity.class);
                startIntent.putExtra("ClickedItemValue_ModuleName", textViewModuleName1.getText().toString());
                startIntent.putExtra("ClickedItemValue_ModuleDesc",textViewModuleDesc.getText().toString());
                startIntent.putExtra("ClickedItemValue_moduleId",moduleID);
                startActivity(startIntent);
                return(true);
            case R.id.add:
                startIntent = new Intent(getApplicationContext(), InsertModules.class);
                startActivity(startIntent);
                return(true);
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

    private void emptyTextView() {
        textViewModuleDesc.setText(null);
        textViewModuleName1.setText(null);
    }

}
