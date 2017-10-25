package com.example.cuongducnguyenkp.bottomnavigationdemo;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.text.ParseException;
import java.util.ArrayList;

public class ViewListCustomerFragment extends Fragment {
    SQLiteOpenHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
        ListAdapter listAdapter;
    ListView LISTVIEW;
    SQLiteDatabase sqLiteDatabaseObj;
    EditText inputSearch;
    Activity context;

    ArrayList<String> ID_Array;
    ArrayList<String> NAME_Array, NAME_Array_temp;
    ArrayList<String> PHONE_NUMBER_Array;
    ArrayList<Integer> CONTRACT_DAYS_Array;
    //    ArrayList<ListAdapter> arrayList = new ArrayList<ListAdapter>();
    ArrayList<String> START_DATE;
    ArrayList<String> ListViewClickItemArray = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    String TempHolder;
    Fragment selectedFragment = null;

    public static ViewListCustomerFragment newInstance() {
        ViewListCustomerFragment fragment = new ViewListCustomerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_view_list_customer_fragment, container, false);

        inputSearch = (EditText) view.findViewById(R.id.inputSearch);

        LISTVIEW = (ListView) view.findViewById(R.id.listView1);

        ID_Array = new ArrayList<String>();

        NAME_Array = new ArrayList<String>();

        NAME_Array_temp = new ArrayList<String>();

        PHONE_NUMBER_Array = new ArrayList<String>();

        CONTRACT_DAYS_Array = new ArrayList<Integer>();

        START_DATE = new ArrayList<String>();

        sqLiteHelper = new SQLiteHelper(this.getContext());
//        LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(context.getApplicationContext(), ModifyCustomerFragment.class);
//
//                intent.putExtra("ListViewClickedItemValue", ListViewClickItemArray.get(position).toString());
////                Toast.makeText(this., NAME_Array_temp.get(position), Toast.LENGTH_LONG).show();
//                selectedFragment = ModifyCustomerFragment.newInstance();
//            }
//        });



        return view;
    }
    public void OpenSQLiteDataBase() {
        sqLiteDatabaseObj = SQLiteDatabase.openDatabase(SQLiteHelper.DATABASE_NAME,null,Context.MODE_PRIVATE);
    }
    public void onResume() {

        try {
            ShowSQLiteDBdata();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        super.onResume();
    }
    public void ShowSQLiteDBdata() throws ParseException {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteHelper.TABLE_NAME + "", null);

        ID_Array.clear();
        NAME_Array.clear();
        PHONE_NUMBER_Array.clear();

        if (cursor.moveToFirst()) {
            do {

                ID_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_ID)));

                //Inserting Column ID into Array to Use at ListView Click Listener Method.
                ListViewClickItemArray.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_ID)));

                NAME_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name)));

                PHONE_NUMBER_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_2_PhoneNumber)));

            } while (cursor.moveToNext());
        }

        listAdapter = new ListAdapter(this.getContext(),

                ID_Array,
                NAME_Array,
                PHONE_NUMBER_Array
        );
//        arrayList.add(listAdapter);
        LISTVIEW.setAdapter(listAdapter);
        NAME_Array_temp = NAME_Array;
        cursor.close();
    }
}
