package com.example.ashen.sqliteexample.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ashen.sqliteexample.modal.Content;
import com.example.ashen.sqliteexample.modal.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashen on 3/21/18.
 */

public class ContentDbHelper {

    private static final String TABLE_CONTENT = "Content";
    private static final String COLUMN_CONTENT_ID = "module_id";
    private static final String COLUMN_CONTENT_NAME = "module_name";
    private static final String COLUMN_CONTENT_DESC = "module_desc";
    public static final String DROP_CONTENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_CONTENT;

    private Content content;

    public ContentDbHelper() {
        content = new Content();
    }


    public static String createTable(){
        return "CREATE TABLE " + TABLE_CONTENT + "("
                + COLUMN_CONTENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CONTENT_NAME + " TEXT,"
                + COLUMN_CONTENT_DESC + " TEXT" + ")";
    }

    public void addContent(Content content) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTENT_NAME, content.getModuleName());
        values.put(COLUMN_CONTENT_DESC, content.getModuleDescription());

        // Inserting Row
        db.insert(TABLE_CONTENT, null, values);
        DatabaseManager.getInstance().closeDatabase();
    }

    public List<Content> getAllContents() {

        String[] columns = {
                COLUMN_CONTENT_ID,
                COLUMN_CONTENT_NAME,
                COLUMN_CONTENT_DESC
        };

        List<Content> contentList = new ArrayList<Content>();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_CONTENT, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        if (cursor.moveToFirst()) {
            do {
                Content content = new Content();
                content.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT_ID))));
                content.setModuleName(cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT_NAME)));
                content.setModuleDescription(cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT_DESC)));
                // Adding user record to list
                contentList.add(content);
            } while (cursor.moveToNext());
        }
        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        // return user list
        return contentList;
    }

}
