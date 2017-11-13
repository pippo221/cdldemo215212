package com.example.cuongducnguyenkp.cdldemo2;

/**
 * Created by cuongducnguyen.kp on 10/11/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    private List<Customer> list = null;
    private ArrayList<Customer> arrayList;

    public ListAdapter(
            Context context2,
            List<Customer> list
    ) {

        this.context = context2;
        this.list = list;
        inflater = LayoutInflater.from(this.context);
        this.arrayList = new ArrayList<Customer>();
        this.arrayList.addAll(list);
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    public Customer getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (view == null) {
            holder = new Holder();
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//            view = layoutInflater.inflate(R.layout.items, null);
            view = inflater.inflate(R.layout.items, null);


            holder.ID_TextView = (TextView) view.findViewById(R.id.textViewID);
            holder.Name_TextView = (TextView) view.findViewById(R.id.textViewEditAddress);
            holder.ServiceTypeTextView = (TextView) view.findViewById(R.id.textViewSERVICE_TYPE);
            holder.ContractDurationStartTextView = (TextView) view.findViewById(R.id.textViewDURATION);
//            holder.ContractDurationEndTextView = (TextView) view.findViewById(R.id.textViewPHONE_NUMBER);
            holder.Status = (TextView) view.findViewById(R.id.textViewSTATUS);
            holder.Payment = (TextView) view.findViewById(R.id.textViewPAYMENT);


            view.setTag(holder);

        } else {

            holder = (Holder) view.getTag();
        }
        // Set the results into TextViews
        holder.ID_TextView.setText(list.get(position).getId());
        holder.Name_TextView.setText(list.get(position).getName());
        holder.ServiceTypeTextView.setText(list.get(position).getServiceType());
        holder.ContractDurationStartTextView.setText(list.get(position).getContractDurationStart()+" ~ "+list.get(position).getContractDurationEnd());
//        holder.ContractDurationEndTextView.setText(list.get(position).getContractDurationEnd());
        holder.Status.setText(list.get(position).getStatus());
        holder.Payment.setText(list.get(position).getPayment());

        return view;
    }

    public class Holder {

        TextView ID_TextView;
        TextView Name_TextView;
        TextView ServiceTypeTextView;
        TextView ContractDurationStartTextView;
        TextView ContractDurationEndTextView;
        TextView Status;
        TextView Payment;
    }

    public void filterByName(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        list.clear();
        if (charText.length() == 0) {
            list.addAll(arrayList);
        } else {
            for (Customer wp : arrayList) {
                if (wp.getName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    list.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
    public void filterByStatus(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        list.clear();
        if (charText.length() == 0) {
            list.addAll(arrayList);
        } else {
            for (Customer wp : arrayList) {
                if (wp.getStatus().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    list.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
    public void filterByServiceType(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        list.clear();
        if (charText.length() == 0) {
            list.addAll(arrayList);
        } else {
            for (Customer wp : arrayList) {
                if (wp.getServiceType().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    list.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}