package com.example.cuongducnguyenkp.cdldemo2;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ModifyCustomerFragment extends Fragment {

    SQLiteDatabase sqLiteDatabaseObj;
    EditText editTextName, editTextPhoneNumber, editTextAddress, editTextServiceType, editTextMonthlyCharge, editTextBox, editTextCost, editTextStartDate, editTextMAC, editTextContractDays, editTextExpiredDate, editTextMessageHolder;
    String strNameHolder, strNumberHolder, SQLiteDataBaseQueryHolder, SQLiteDataBaseQueryHolder2, strAddressHolder, strServiceTypeHolder, strServiceHolder, strMonthlyChargeHolder, strBoxHolder, strCostholder, strStartDateHolder, strMACHolder, strExpiredDateHolder, strContractDaysHolder, strMessageHolder, strEndDateHolder, strPaymentStatusHolder = "YES";
    FloatingActionButton EnterData, ButtonDisplayData, buttonTest;
    int contractDaysTemp;
    RadioGroup radioGroupService;
    Boolean EditTextEmptyHold;
    List<String> DurationListStart = new ArrayList<>();
    List<String> DurationListEnd = new ArrayList<>();

    public static ModifyCustomerFragment newInstance() {
        ModifyCustomerFragment fragment = new ModifyCustomerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_modify_customer_fragment, container, false);
        EnterData = (FloatingActionButton) view.findViewById(R.id.button);
        ButtonDisplayData = (FloatingActionButton) view.findViewById(R.id.button2);

        editTextName = (EditText) view.findViewById(R.id.editText1_EditName);
        editTextName.setText("abc");
        editTextPhoneNumber = (EditText) view.findViewById(R.id.editText2_NewPhone);
        editTextAddress = (EditText) view.findViewById(R.id.editText3_NewAddress);
        editTextServiceType = (EditText) view.findViewById(R.id.editText4_NewServiceType);
        editTextBox = (EditText) view.findViewById(R.id.editText7_NewBox);
        editTextCost = (EditText) view.findViewById(R.id.editText8_NewCost);
        editTextStartDate = (EditText) view.findViewById(R.id.editText9_NewStartDate);
        editTextMAC = (EditText) view.findViewById(R.id.editText10_NewMACAddress);
        editTextContractDays = (EditText) view.findViewById(R.id.editText12_NewContractDay);
        editTextExpiredDate = (EditText) view.findViewById(R.id.editText11_NewExpiredDate);
        editTextMessageHolder = (EditText) view.findViewById(R.id.editText13_NewtMessage);
        editTextMonthlyCharge = (EditText) view.findViewById(R.id.editTextNew6_MonthlyCharge);
        editTextStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR),
                        mMonth = mcurrentDate.get(Calendar.MONTH),
                        mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                    /*      Your code to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        editTextStartDate.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        editTextExpiredDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR),
                        mMonth = mcurrentDate.get(Calendar.MONTH),
                        mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                    /*      Your code to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        editTextExpiredDate.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });


        EnterData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                SQLiteDataBaseBuild();

//                SQLiteTableBuild();

                CheckEditTextStatus();

                Toast.makeText(getActivity().getApplicationContext(),
                        "Button Insert Click...", Toast.LENGTH_SHORT).show();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Calendar c = new GregorianCalendar();
                Calendar c1 = new GregorianCalendar();
                String temp = "", temp2 = "";
                Calendar today = Calendar.getInstance();
                today.set(Calendar.HOUR_OF_DAY, 0);
                Date startDate = new Date();
                Date endDate = new Date();
                ////
                try {
                    startDate = formatter.parse(strStartDateHolder);//chuyen thanh dinh dang Date
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                c1.setTime(startDate);//Tao ngay hop dong cuoi cung
                c1.add(Calendar.DATE, contractDaysTemp);
                endDate = c1.getTime();
                temp2 = formatter.format(endDate);
                strEndDateHolder = temp2;
                Toast.makeText(getActivity(),
                        String.valueOf(contractDaysTemp), Toast.LENGTH_SHORT).show();
                ///

                try {
                    endDate = formatter.parse(strEndDateHolder);
                    startDate = formatter.parse(strStartDateHolder);//chuyen thanh dinh dang Date
                    temp = formatter.format(startDate);//chuyen sang kieu String
                    DurationListStart.add(temp);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                c.setTime(startDate);
                int i = 0;
                while (c.before(endDate)) {
                    c.add(Calendar.DATE, 14);//bien temp cho StartDate cua hop dong tang them 14 ngay roi add vao list
                    startDate = c.getTime();
                    temp = formatter.format(startDate);
                    DurationListEnd.add(temp);
                    Toast.makeText(getActivity(), DurationListStart.get(i), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), DurationListEnd.get(i), Toast.LENGTH_SHORT).show();
                    c.add(Calendar.DATE, 1);
                    startDate = c.getTime();
                    temp = formatter.format(startDate);
                    DurationListStart.add(temp);

                    i++;
                }


                try {
                    InsertDataIntoSQLiteDatabase();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                EmptyEditTextAfterDataInsert();


            }
        });
//Button display all input record
//        ButtonDisplayData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),
//                        "Redirecting...", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(NewCustomerActivity.this, DisplaySQLiteDataActivity.class);
//                startActivity(intent);
//            }
//        });
        return view;
    }

    public void SQLiteDataBaseBuild() {

        sqLiteDatabaseObj =
                getActivity().openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

//    public void SQLiteTableBuild() {
//
//        sqLiteDatabaseObj.execSQL(
////              "DROP TABLE IF EXISTS '"+SQLiteHelper.TABLE_NAME+"'; " +
//                "CREATE TABLE IF NOT EXISTS " + SQLiteHelper.TABLE_NAME + "(" + SQLiteHelper.Table_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + SQLiteHelper.Table_Column_1_Name + " VARCHAR, " + SQLiteHelper.Table_Column_2_PhoneNumber + " VARCHAR" +
//                        ", " + SQLiteHelper.Table_Column_3_Address + " VARCHAR" +
//                        ", " + SQLiteHelper.Table_Column_4_ServiceType + " VARCHAR" +
//                        ", " + SQLiteHelper.Table_Column_5_Service + " VARCHAR" +
//                        ", " + SQLiteHelper.Table_Column_6_MonthlyCharge + " VARCHAR" +
//                        ", " + SQLiteHelper.Table_Column_7_Box + " VARCHAR" +
//                        ", " + SQLiteHelper.Table_Column_8_Cost + " VARCHAR" +
//                        ", " + SQLiteHelper.Table_Column_9_StartDate + " VARCHAR" +
//                        ", " + SQLiteHelper.Table_Column_10_MAC + " VARCHAR" +
//                        ", " + SQLiteHelper.Table_Column_11_ExpiredDate + " VARCHAR" +
//                        ", " + SQLiteHelper.Table_Column_12_ContractDays + " VARCHAR" +
//                        ", " + SQLiteHelper.Table_Column_13_Message + " VARCHAR" +
//                        ");");
//        sqLiteDatabaseObj.execSQL(
//                //   "DROP TABLE IF EXISTS '"+SQLiteHelper.TABLE2_NAME+"'; " +
//                "CREATE TABLE IF NOT EXISTS " + SQLiteHelper.TABLE2_NAME + "(" + SQLiteHelper.Table2_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL " +
//                        ", " + SQLiteHelper.Table2_Column_Name + " VARCHAR" +
//                        ", " + SQLiteHelper.Table2_Column_DurationStart + " VARCHAR" +
//                        ", " + SQLiteHelper.Table2_Column_DurationEnd + " VARCHAR" +
//                        ", " + SQLiteHelper.Table2_Column_Message + " VARCHAR" +
//                        ");");
//
//    }

    public void InsertDataIntoSQLiteDatabase() throws ParseException {


//        Toast.makeText(getApplicationContext(),
//                strStartDateHolder, Toast.LENGTH_SHORT).show();

        if (EditTextEmptyHold == true) {

            SQLiteDataBaseQueryHolder = "INSERT INTO " + SQLiteHelper.TABLE_NAME + " (name,phone_number" +
                    ",address" +
                    ",service_type" +
                    ",service" +
                    ",monthly_charge" +
                    ",box" +
                    ",cost" +
                    ",start_date" +
                    ",MAC_address" +
                    ",ExpiredDate" +
                    ",Contract_Days" +
                    ",message" +
                    ",end_date" +
                    ",payment_status" +
                    ") VALUES('" + strNameHolder + "', '" + strNumberHolder + "'" +
                    ",'" + strAddressHolder + "'" +
                    ",'" + strServiceTypeHolder + "'" +
                    ",'" + strServiceHolder + "'" +
                    ",'" + strMonthlyChargeHolder + "'" +
                    ",'" + strBoxHolder + "'" +
                    ",'" + strCostholder + "'" +
                    ",'" + strStartDateHolder + "'" +
                    ",'" + strMACHolder + "'" +
                    ",'" + strExpiredDateHolder + "'" +
                    ",'" + strContractDaysHolder + "'" +
                    ",'" + strMessageHolder + "'" +
                    ",'" + strEndDateHolder + "'" +
                    ",'" + strPaymentStatusHolder + "'" +
                    ");"
//                    +"INSERT INTO " + SQLiteHelper.TABLE2_NAME+"(" +
//                    "name"+
//                    ")VALUES('" +
//
//                    "')" +
//                    ""
            ;
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);
//            int m = DurationListEnd.size();
//            int i2 = 0;
//            do {
////                Toast.makeText(getActivity(), DurationListStart.get(i2), Toast.LENGTH_SHORT).show();
////                Toast.makeText(getActivity(), DurationListEnd.get(i2), Toast.LENGTH_SHORT).show();
//                SQLiteDataBaseQueryHolder2 = "INSERT INTO " + SQLiteHelper.TABLE2_NAME + "(" +
//                        "name, duration_start, duration_end, message, charge" +
//                        ")VALUES('" +
//                        strNameHolder + "'" +
//                        ",'" + DurationListStart.get(i2) + "'" +
//                        ",'" + DurationListEnd.get(i2) + "'" +
//                        ",'Unpaid" +
//                        ",'" + strMonthlyChargeHolder + "'" +
//                        "')"
//
//                ;
//                i2++;
//                sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder2);
//            } while (i2 < m);


            sqLiteDatabaseObj.close();

            Toast.makeText(getActivity().getApplicationContext(), "Data Inserted Successfully", Toast.LENGTH_LONG).show();

        } else {

            Toast.makeText(getActivity().getApplicationContext(), "Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();

        }
    }

    public void EmptyEditTextAfterDataInsert() {

        editTextName.getText().clear();
        editTextPhoneNumber.getText().clear();
//        editTextServiceType.getText().clear();
//        editTextMonthlyCharge.getText().clear();
//        editTextBox.getText().clear();
//        editTextCost.getText().clear();
        editTextStartDate.getText().clear();
//        editTextMAC.getText().clear();
        editTextContractDays.getText().clear();
        editTextExpiredDate.getText().clear();
//        editTextAddress.getText().clear();
        editTextMessageHolder.getText().clear();
        DurationListStart.clear();
    }

    public void CheckEditTextStatus() {

        strNameHolder = editTextName.getText().toString();
        strNumberHolder = editTextPhoneNumber.getText().toString();
        strAddressHolder = editTextAddress.getText().toString();
        strServiceTypeHolder = editTextServiceType.getText().toString();
        //  strServiceHolder=editTextService.getText().toString();
        strMonthlyChargeHolder = editTextMonthlyCharge.getText().toString();
        strBoxHolder = editTextBox.getText().toString();
        strCostholder = editTextCost.getText().toString();
        strStartDateHolder = editTextStartDate.getText().toString();
        strMACHolder = editTextMAC.getText().toString();
        strExpiredDateHolder = editTextExpiredDate.getText().toString();
        strContractDaysHolder = editTextContractDays.getText().toString();
        strMessageHolder = editTextMessageHolder.getText().toString();
        contractDaysTemp = Integer.parseInt(strContractDaysHolder);
        radioGroupService = (RadioGroup) getActivity().findViewById(R.id.radioGroupNewService5);
        int intChecked = radioGroupService.getCheckedRadioButtonId();
        switch (intChecked) {
            case R.id.radioButtonBuy:
                strServiceHolder = "Buy";
                break;
            case R.id.radioButtonRent:
                strServiceHolder = "Rent";
                break;
        }
        ;

        if (TextUtils.isEmpty(strNameHolder) || TextUtils.isEmpty(strNumberHolder)
                || TextUtils.isEmpty(strContractDaysHolder)
//                || TextUtils.isEmpty(strBoxHolder)
                || TextUtils.isEmpty(strCostholder)
                || TextUtils.isEmpty(strMACHolder)
                || TextUtils.isEmpty(strMonthlyChargeHolder)
                || TextUtils.isEmpty(strServiceTypeHolder)
                || TextUtils.isEmpty(strAddressHolder)
//                || TextUtils.isEmpty(strExpiredDateHolder)
                || TextUtils.isEmpty(strStartDateHolder)
                ) {

            EditTextEmptyHold = false;

        } else {

            EditTextEmptyHold = true;
        }
    }
}