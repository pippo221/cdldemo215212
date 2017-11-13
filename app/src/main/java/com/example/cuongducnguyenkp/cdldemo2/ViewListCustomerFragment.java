package com.example.cuongducnguyenkp.cdldemo2;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class ViewListCustomerFragment extends Fragment {
    SQLiteOpenHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter listAdapter;
    ListView LISTVIEW;
    SQLiteDatabase sqLiteDatabaseObj;
    EditText inputSearch;
    private ValueAdapter valueAdapter;
    Spinner spinner;
    String spinnerText, tempStartDate, tempEndDate, tempPayment, SQLiteDataBaseQueryHolder3;
    FloatingActionButton fab;
    Bundle bundle;
    CommunicationInterface listener;
    ArrayList<String> ID_Array;
    ArrayList<String> NAME_Array, NAME_Array_temp;

    ArrayList<String> PHONE_NUMBER_Array;

    ArrayList<String> CONTRACT_DURATION_START_Array;

    ArrayList<String> CONTRACT_DURATION_END_Array;

    ArrayList<String> STATUS_Array; //ON GOING or DELAYED

    ArrayList<String> PAYMENT_Array;

    ArrayList<String> ADDRESS_Array;
    ArrayList<String> SERVICE_TYPE_Array;
    ArrayList<String> CONTRACT_DAYS_Array;
    ArrayList<String> MAC_Array;
    //    ArrayList<ListAdapter> arrayList = new ArrayList<ListAdapter>();
    ArrayList<String> START_DATE;
    ArrayList<String> ListViewClickItemArray = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    String TempHolder;
    Fragment selectedFragment = null;
    ArrayList<Customer> arraylist = new ArrayList<Customer>();
    List<String> DurationListStart = new ArrayList<>();
    List<String> DurationListEnd = new ArrayList<>();

    public static ViewListCustomerFragment newInstance() {
        ViewListCustomerFragment fragment = new ViewListCustomerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    public interface CommunicationInterface {
        void onClickTopFragment(String str);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_view_list_customer_fragment, container, false);

        inputSearch = (EditText) view.findViewById(R.id.inputSearch);

        LISTVIEW = (ListView) view.findViewById(R.id.listView1);

        spinner = (Spinner) view.findViewById(R.id.spinner2);

        fab = (FloatingActionButton) view.findViewById(R.id.float_action_button);

        ID_Array = new ArrayList<String>();

        NAME_Array = new ArrayList<String>();

        ADDRESS_Array = new ArrayList<String>();

        SERVICE_TYPE_Array = new ArrayList<String>();

        MAC_Array = new ArrayList<String>();

        CONTRACT_DURATION_START_Array = new ArrayList<String>();

        CONTRACT_DURATION_END_Array = new ArrayList<String>();

        PAYMENT_Array = new ArrayList<String>();

        STATUS_Array = new ArrayList<String>();

        NAME_Array_temp = new ArrayList<String>();

        PHONE_NUMBER_Array = new ArrayList<String>();

        CONTRACT_DAYS_Array = new ArrayList<String>();

        START_DATE = new ArrayList<String>();

        bundle = new Bundle();

        ArrayList<String> mStringList = new ArrayList<String>();

        for (int i = 0; i < NAME_Array_temp.size(); i++) {
            mStringList = NAME_Array_temp;
        }

        valueAdapter = new ValueAdapter(mStringList, getContext());
        LISTVIEW.setAdapter(valueAdapter);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                OpenSQLiteDataBase();

                String temp3 = "DELETE FROM " + SQLiteHelper.TABLE2_NAME + ";";
                sqLiteDatabaseObj.execSQL(temp3);
                Toast.makeText(getActivity(), "Delete Table " + temp3, Toast.LENGTH_SHORT).show();

            }
        });

        sqLiteHelper = new SQLiteHelper(this.getContext());
        LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
//                intent.putExtra("ListViewClickedItemValue", ListViewClickItemArray.get(position).toString());
//                getActivity().startActivity(intent);
//                Toast.makeText(getActivity(), ListViewClickItemArray.get(position).toString(), Toast.LENGTH_LONG).show();
//                selectedFragment = ModifyCustomerFragment.newInstance();
//                listener.onClickTopFragment(ListViewClickItemArray.get(position).toString());

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                ItemFourFragment fragment = new ItemFourFragment();
                Bundle bundle2 = new Bundle();

                bundle2.putString("Name", NAME_Array.get(position).toString());
                bundle2.putString("ID", ID_Array.get(position).toString());
                bundle2.putString("Address", ADDRESS_Array.get(position).toString());
                bundle2.putString("TEL", PHONE_NUMBER_Array.get(position).toString());
                bundle2.putString("ServiceType", SERVICE_TYPE_Array.get(position).toString());
                bundle2.putString("MAC", MAC_Array.get(position).toString());
                bundle2.putString("Payment", PAYMENT_Array.get(position).toString());
                bundle2.putString("DurationStart", CONTRACT_DURATION_START_Array.get(position).toString());
                bundle2.putString("DurationEnd", CONTRACT_DURATION_END_Array.get(position).toString());

                fragment.setArguments(bundle2);
                transaction.replace(R.id.frame_layout, fragment);
                transaction.commit();
                String strTemp = NAME_Array.get(position).toString();
                String strTempID = ID_Array.get(position).toString();
                String strTempPayment = PAYMENT_Array.get(position).toString();
                String tempContractDays = CONTRACT_DAYS_Array.get(position).toString();
//                Toast.makeText(getActivity(), tempContractDays, Toast.LENGTH_SHORT).show();
                int intContractDays = Integer.parseInt(tempContractDays);
                Toast.makeText(getActivity(), tempContractDays, Toast.LENGTH_SHORT).show();

                OpenSQLiteDataBase();
//                Toast.makeText(getActivity(), strTemp, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), SERVICE_TYPE_Array.get(position).toString(), Toast.LENGTH_LONG).show();
//                SQLiteDataBaseQueryHolder2 = "DELETE FROM " + SQLiteHelper.TABLE2_NAME + " " +
                //"WHERE name = '" + tempName +"'" +
//                        "";
//                sqLiteDatabase.execSQL(SQLiteDataBaseQueryHolder2);

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Calendar c = new GregorianCalendar();
                String temp = "";
                Calendar today = Calendar.getInstance();
                today.set(Calendar.HOUR_OF_DAY, 0);
                Date startDate = new Date();
                try {
                    startDate = formatter.parse(tempStartDate); //chuyen sang Date time
                    Toast.makeText(getContext(),tempStartDate, Toast.LENGTH_SHORT).show();
                    temp = formatter.format(startDate); //chuyen sang String
                    DurationListStart.add(temp);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                c.setTime(startDate);

                int i = 0;
                int i3 = 30; //Duration
                while (intContractDays > 0) {
                    c.add(Calendar.DATE, i3); //Start Date tang them 30 ngay
                    startDate = c.getTime();
                    temp = formatter.format(startDate);
                    DurationListEnd.add(temp);
//                    Toast.makeText(getContext(),DurationListStart.get(i), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getContext(),DurationListEnd.get(i), Toast.LENGTH_SHORT).show();
                    c.add(Calendar.DATE, 1);
                    startDate = c.getTime();
                    temp = formatter.format(startDate);
                    DurationListStart.add(temp);
                    intContractDays = intContractDays - i3;
                    i++;
                }
                int m = DurationListEnd.size();
                int i2 = 0;
                do {
                    SQLiteDataBaseQueryHolder3 = "INSERT INTO " + SQLiteHelper.TABLE2_NAME + "(" +
                            "name, customer_id, duration_start, duration_end, message, charge" +
                            ")VALUES('" +
                            strTemp + "'" +
                            ",'" + strTempID + "'" +
                            ",'" + DurationListStart.get(i2) + "'" +
                            ",'" + DurationListEnd.get(i2) + "'" +
                            ",'Unpaid'" +
                            ",'" + strTempPayment + "" +
                            "')" +
                            ";"

//                            " WHERE NOT EXISTS ( SELECT * FROM " +SQLiteHelper.TABLE2_NAME +" WHERE name = '"+
//                            tempName+" AND duration_start = "+DurationListStart.get(i2)+"')"
                    ;
                    i2++;
                    sqLiteDatabase.execSQL(SQLiteDataBaseQueryHolder3);
//                    Toast.makeText(getActivity(), strTempPayment+" 123", Toast.LENGTH_LONG).show();
                } while (i2 < m);
                String lastDate = DurationListEnd.get(i2 - 1);
                Date dateLastDate = new Date();
                Date dateTempEndDate = new Date();
                try {
                    dateLastDate = formatter.parse(lastDate);
                    dateTempEndDate = formatter.parse(CONTRACT_DURATION_END_Array.get(position).toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (dateLastDate.before(dateTempEndDate)) {
                    DurationListStart.add(lastDate);
                    DurationListEnd.add(CONTRACT_DURATION_END_Array.get(position).toString());
                    i2++;
                    SQLiteDataBaseQueryHolder3 = "INSERT INTO " + SQLiteHelper.TABLE2_NAME + "(" +
                            "name, customer_id, duration_start, duration_end, message, charge" +
                            ")VALUES('" +
                            strTemp + "'" +
                            ",'" + strTempID + "'" +
                            ",'" + DurationListStart.get(i2 - 1) + "'" +
                            ",'" + DurationListEnd.get(i2 - 1) + "'" +
                            ",'Unpaid'" +
                            ",'" + strTempPayment + "" +
                            "')" +
                            ";"

//                            " WHERE NOT EXISTS ( SELECT * FROM " +SQLiteHelper.TABLE2_NAME +" WHERE name = '"+
//                            tempName+" AND duration_start = "+DurationListStart.get(i2)+"')"
                    ;
                    sqLiteDatabase.execSQL(SQLiteDataBaseQueryHolder3);
                }
                String t = "delete from " + SQLiteHelper.TABLE2_NAME + " where rowid not in (select min(rowid) from " + SQLiteHelper.TABLE2_NAME + " group by customer_id, duration_start);";
                sqLiteDatabase.execSQL(t);


            }
        })
        ;

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

//
//                try {
//                    ShowSQLiteDBdata();
//                    for (int i = 0; i < NAME_Array_temp.size(); i++) {
//                        Toast.makeText(getActivity(), NAME_Array_temp.get(i), Toast.LENGTH_LONG).show();
//                    }
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
////                for (int i = 0; i < NAME_Array_temp.size(); i++) {
////                    String nameTemp[i] = NAME_Array_temp.get(i);
////                }
//
//                adapter = new ArrayAdapter<String>(getActivity(), R.layout.items, R.id.textViewNAME, NAME_Array_temp);
//                ViewListCustomerFragment.this.valueAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                spinnerText = spinner.getSelectedItem().toString();
                int i = (int) spinner.getSelectedItemId();
//                Toast.makeText(getActivity(),String.valueOf(i), Toast.LENGTH_SHORT).show();
                String text = inputSearch.getText().toString().toLowerCase(Locale.getDefault());
                switch (i) {
                    case 0:
                        listAdapter.filterByName(text);
                        break;
                    case 1:
                        listAdapter.filterByStatus(text);
                        break;
                    case 2:
                        listAdapter.filterByServiceType(text);
                        break;
                }

            }
        });


        return view;
    }

    public void OpenSQLiteDataBase() {

        sqLiteDatabaseObj = getActivity().openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);
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

                ADDRESS_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_3_Address)));

                SERVICE_TYPE_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_4_ServiceType)));

                CONTRACT_DAYS_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_12_ContractDays)));

                MAC_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_10_MAC)));

                CONTRACT_DURATION_START_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_9_StartDate)));

                CONTRACT_DURATION_END_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_14_EndDate)));

                STATUS_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_15_PaymentStatus)));

                PAYMENT_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_6_MonthlyCharge)));
                tempStartDate = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_9_StartDate));
                tempEndDate = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_14_EndDate));
                tempPayment = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_6_MonthlyCharge));

            } while (cursor.moveToNext());
        }
        String[] tempId, tempName, tempPhone;
        for (int i = 0; i < ID_Array.size(); i++) {
            Customer wp = new Customer(ID_Array.get(i).toString(),
                    NAME_Array.get(i).toString(), SERVICE_TYPE_Array.get(i).toString()
                    , CONTRACT_DURATION_START_Array.get(i).toString(), CONTRACT_DURATION_END_Array.get(i).toString(),
                    STATUS_Array.get(i).toString(), PAYMENT_Array.get(i).toString());


            arraylist.add(wp);
        }
        listAdapter = new ListAdapter(this.getContext(), arraylist
        );
//        arrayList.add(listAdapter);
        LISTVIEW.setAdapter(listAdapter);
        NAME_Array_temp = NAME_Array;
//        Toast.makeText(getActivity(), NAME_Array_temp.get(0), Toast.LENGTH_LONG).show();
        cursor.close();
    }

    public static ViewListCustomerFragment newInstance(String value) {
        ViewListCustomerFragment fragmentclass1 = new ViewListCustomerFragment();

        Bundle args = new Bundle();
        args.putString("message", value);
        fragmentclass1.setArguments(args);

        return fragmentclass1;
    }
}
