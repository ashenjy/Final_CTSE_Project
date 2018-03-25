package com.example.ashen.sqliteexample.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ashen.sqliteexample.R;
import com.example.ashen.sqliteexample.adapters.ContentRecyclerAdapter;
import com.example.ashen.sqliteexample.adapters.UsersRecyclerAdapter;
import com.example.ashen.sqliteexample.modal.Content;
import com.example.ashen.sqliteexample.modal.User;
import com.example.ashen.sqliteexample.sql.ContentDbHelper;
import com.example.ashen.sqliteexample.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ViewContents extends AppCompatActivity {

    private final AppCompatActivity activity = ViewContents.this;
    private List<Content> listContent;
    private ContentDbHelper contentDbHelper;
    private ListView listView;
    private AppCompatTextView textViewModuleName;
    private RecyclerView recyclerViewUsers;
    private ContentRecyclerAdapter contentRecyclerAdapter;
    private ContentRecyclerAdapter.OnItemClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contents);

        initViews();
        initObjects();

    }


    private void initViews() {
        textViewModuleName = (AppCompatTextView) findViewById(R.id.textViewModuleName);
        recyclerViewUsers = (RecyclerView) findViewById(R.id.recyclerViewUsers);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listContent = new ArrayList<>();
        contentRecyclerAdapter = new ContentRecyclerAdapter(listContent,listener);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsers.setLayoutManager(mLayoutManager);
        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setAdapter(new ContentRecyclerAdapter(listContent, new ContentRecyclerAdapter.OnItemClickListener() {
            @Override public void onItemClick(Content item) {
                Toast.makeText(getApplicationContext(), "Item Clicked :" + item.getModuleName(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),descriptionActivity.class);
                //Passing String values to description activity.
                intent.putExtra("ClickedItemValue_ModuleName", item.getModuleName());
                intent.putExtra("ClickedItemValue_ModuleDesc", item.getModuleDescription());

                startActivity(intent);
            }
        }));
        contentDbHelper = new ContentDbHelper();

        getAllContent();

        Log.w("Contents",listContent.toString());
    }

    public void getAllContent(){
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void,Void,Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listContent.clear();
                listContent.addAll(contentDbHelper.getAllContents());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                contentRecyclerAdapter.notifyDataSetChanged();
            }

        }.execute();
    }
}
