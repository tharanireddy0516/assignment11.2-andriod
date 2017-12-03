package com.example.tharani.sqliteitemsearch;
/*import is libraries imported for writing the code
* AppCompatActivity is base class for activities
* Bundle handles the orientation of the activity
*/


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Tharani on 12/3/2017.
 */
/*For maximum control over local data, iam using  SQLite
directly by leveraging SQLiteOpenHelper for executing SQL requests and managing a local database.*/
class MyHelper extends SQLiteOpenHelper {
    //here created java class MyHelper which extends SQLiteOpenHelper
     // Context allows access to application-specific resources and classes
    MyHelper(Context context){
        super(context,"productsDB",null,1);
    }
    /*creating onCreate method helps to open a database that will be used for reading and writing. The first time this is called
     the database will be opened and onCreate(SQLiteDatabase) followed by onUpgrade
     exeSQL execute a single SQL statement
     created table with fields integer,product name*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TABLE_PRODUCTS (COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,COLUMN_PRODUCTNAME TEXT)");
    }
     //here by the products displays or drops when we select it
    @Override
    public void onUpgrade(SQLiteDatabase db,  int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS TABLE_PRODUCTS");
        onCreate(db);
    }
    /**getWriteDatabase This method always returns very quickly.
    The database is not actually created or opened until one of getWritableDatabase() or getReadableDatabase() is called
     ContentValues() Creates an empty set of values using the default initial size*/
    void Addproducts(String productname){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values  = new ContentValues();

        values.put("COLUMN_PRODUCTNAME",productname);
        db.insert("TABLE_PRODUCTS",null,values);
    }
    //creating new ArrayList
    ArrayList<String> Databasetoarray(){
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase db =getReadableDatabase();

        //this is the DBMS query used to select the products which i was added
        String query = "SELECT * FROM TABLE_PRODUCTS WHERE 1";
        /*SuppressLint implements Annotation
         * Indicates that Lint should ignore the specified warnings for the annotated element*/
        @SuppressLint("Recycle") Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        /*using while loop to control flow statement*/
        while (!c.isAfterLast()){
            //if statement executes from top to down and decides whether a certain statement will executes or not
            if (c.getString(c.getColumnIndex("COLUMN_PRODUCTNAME"))!=null){
                arrayList.add(c.getString(c.getColumnIndex("COLUMN_PRODUCTNAME")));
                c.moveToNext();
                /*The cursor starts before the first result row, so on the first iteration this moves to the first result if it exists.
                 If the cursor is empty, or the last row has already been processed, then the loop exits neatly.*/

            }

        }
        db.close();//database closes here and returns arrayList
        return arrayList;
        //here returns from method,causes a program control to transfer back to caller of method
    }

}

