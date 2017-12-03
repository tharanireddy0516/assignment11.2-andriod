package com.example.tharani.sqliteitemsearch;
/*import is libraries imported for writing the code
* AppCompatActivity is base class for activities
* Bundle handles the orientation of the activity
*/


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;
/*onCreate is the first method in the life cycle of an activity
   * savedInstance passes data to super class,data is pull to store state of application
   * setContentView is used to set layout for the activity
   *  R is a resource and it is auto generate file
   * activity_main assign an integer value

   */

public class MainActivity extends AppCompatActivity {
     //declaring variables
    AutoCompleteTextView autoCompleteTextView;
    TextView textView;
    ArrayList<String> productname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView =  findViewById(R.id.textView);
        //creating arrayList and populating it
        productname =new ArrayList<>();
        productname.add("Hp Injet Printer");
        productname.add("Sony TV HD");
        productname.add("Apple MackBook pro");
        productname.add("Panasonic charger");
        productname.add("Sansui TV");
        productname.add("Philips earphones");
        productname.add("Sony mobiles");
        productname.add("PlayStation 4");
        //creating object of MyHelperClass
        MyHelper myHelper = new MyHelper(this);
        //applying for loop
        /*for loop provides clear information in short way of writing the loop structure and easy to debug*/
        for(int i = 0;i<productname.size();i++){
            //using get method to te get Products which were added
            myHelper.Addproducts(productname.get(i));
        }

         /*By using database set to array is an array where each element is a row of the query result.
        */

        ArrayList arrayList = myHelper.Databasetoarray();



        // //passing id of  autoCompleteTextView
        autoCompleteTextView =  findViewById(R.id.autoCompleteTextView2);
        /*here creating new ArrayAdapter with context this and resource file and to select arrayList item
         * , the array adapter creates a view by calling toString() on each data object in the collection you provide,
         * and places the result in a TextView.*/
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.select_dialog_item,arrayList);
        //setting the minimum word to get suggestion
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(arrayAdapter);



    }
}

