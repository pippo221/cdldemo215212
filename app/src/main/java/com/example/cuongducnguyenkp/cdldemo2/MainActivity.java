package com.example.cuongducnguyenkp.cdldemo2;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabaseObj;

    public void SQLiteDataBaseBuild() {
        sqLiteDatabaseObj =
                openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    public void SQLiteTableBuild() {

        String temp =

//              "DROP TABLE IF EXISTS '"+SQLiteHelper.TABLE_NAME+"'; " +
                "CREATE TABLE IF NOT EXISTS " + SQLiteHelper.TABLE_NAME + "(" + SQLiteHelper.Table_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + SQLiteHelper.Table_Column_1_Name + " VARCHAR, " + SQLiteHelper.Table_Column_2_PhoneNumber + " VARCHAR" +
                        ", " + SQLiteHelper.Table_Column_3_Address + " VARCHAR" +
                        ", " + SQLiteHelper.Table_Column_4_ServiceType + " VARCHAR" +
                        ", " + SQLiteHelper.Table_Column_5_Service + " VARCHAR" +
                        ", " + SQLiteHelper.Table_Column_6_MonthlyCharge + " VARCHAR" +
                        ", " + SQLiteHelper.Table_Column_7_Box + " VARCHAR" +
                        ", " + SQLiteHelper.Table_Column_8_Cost + " VARCHAR" +
                        ", " + SQLiteHelper.Table_Column_9_StartDate + " VARCHAR" +
                        ", " + SQLiteHelper.Table_Column_10_MAC + " VARCHAR" +
                        ", " + SQLiteHelper.Table_Column_11_ExpiredDate + " VARCHAR" +
                        ", " + SQLiteHelper.Table_Column_12_ContractDays + " VARCHAR" +
                        ", " + SQLiteHelper.Table_Column_13_Message + " VARCHAR" +
                        ", " + SQLiteHelper.Table_Column_14_EndDate + " VARCHAR" +
                        ", " + SQLiteHelper.Table_Column_15_PaymentStatus + " VARCHAR" +
                        ");";
        sqLiteDatabaseObj.execSQL(temp);
        String temp3 = "DROP TABLE "+SQLiteHelper.TABLE2_NAME+";";
        String temp2 =

                "CREATE TABLE IF NOT EXISTS " + SQLiteHelper.TABLE2_NAME + "(" + SQLiteHelper.Table2_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL " +
                        ", " + SQLiteHelper.Table2_Column_Customer_ID + " VARCHAR" +
                        ", " + SQLiteHelper.Table2_Column_Name + " VARCHAR" +
                        ", " + SQLiteHelper.Table2_Column_DurationStart + " VARCHAR" +
                        ", " + SQLiteHelper.Table2_Column_DurationEnd + " VARCHAR" +
                        ", " + SQLiteHelper.Table2_Column_Message + " VARCHAR" +
                        ", " + SQLiteHelper.Table2_Column_Charge + " VARCHAR" +
                        ");";
        sqLiteDatabaseObj.execSQL(temp2);
//        sqLiteDatabaseObj.execSQL(temp3);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);
        Intent intent = getIntent();
        String message = intent.getStringExtra("ListViewClickedItemValue");
        Bundle bundle = new Bundle();
        bundle.putString("ListViewClickedItemValue", message);
//set Fragmentclass Arguments
        ItemFourFragment fragobj = new ItemFourFragment();
        fragobj.setArguments(bundle);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item2:
                                selectedFragment = ModifyCustomerFragment.newInstance();
                                break;
                            case R.id.action_item1:
                                selectedFragment = ViewListCustomerFragment.newInstance();
                                break;
                            case R.id.action_item3:
                                selectedFragment = ItemThreeFragment.newInstance();
                                break;
                            case R.id.action_item4:

                                break;
                        }
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, ViewListCustomerFragment.newInstance());
        transaction.commit();
        SQLiteDataBaseBuild();
        SQLiteTableBuild();


        //Used to select an item programmatically
        //bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }

}
