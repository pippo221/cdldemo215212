package com.example.cuongducnguyenkp.cdldemo2;

/**
 * Created by cuongducnguyen.kp on 10/11/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME="Customer";

    public static final String TABLE_NAME="Customer";
    public static final String TABLE2_NAME="PaymentInformation";
    public static final String Table_Column_ID="id";

    public static final String Table_Column_1_Name="name";
    public static final String Table_Column_2_PhoneNumber="phone_number";
    public static final String Table_Column_3_Address="address";
    public static final String Table_Column_4_ServiceType="service_type";
    public static final String Table_Column_5_Service="service";
    public static final String Table_Column_6_MonthlyCharge="monthly_charge";
    public static final String Table_Column_7_Box="box";
    public static final String Table_Column_8_Cost="cost";
    public static final String Table_Column_9_StartDate="start_date";
    public static final String Table_Column_10_MAC="MAC_address";
    public static final String Table_Column_11_ExpiredDate="ExpiredDate";
    public static final String Table_Column_12_ContractDays="Contract_Days";
    public static final String Table_Column_13_Message="message";
    public static final String Table_Column_14_EndDate="end_date";
    public static final String Table_Column_15_PaymentStatus="payment_status";

    public static final String Table2_Column_ID="id";
    public static final String Table2_Column_Customer_ID="customer_id";
    public static final String Table2_Column_Name="name";
    public static final String Table2_Column_DurationStart="duration_start";
    public static final String Table2_Column_DurationEnd="duration_end";
    public static final String Table2_Column_Message="message";
    public static final String Table2_Column_Charge="charge";

    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+Table_Column_1_Name+" VARCHAR, "+Table_Column_2_PhoneNumber+" VARCHAR" +
                ", "+Table_Column_3_Address+" VARCHAR" +
                ")"
                ;
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }



}