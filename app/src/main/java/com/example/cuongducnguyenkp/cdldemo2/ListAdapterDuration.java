package com.example.cuongducnguyenkp.cdldemo2;

/**
 * Created by cuongducnguyen.kp on 10/11/2017.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapterDuration extends BaseAdapter {

    Context context;
    ArrayList<String> ID;
    ArrayList<String> Name;
    ArrayList<String> DurationStart;
    ArrayList<String> DurationEnd;
    ArrayList<String> Message;


    public ListAdapterDuration(
            Context context2,
            ArrayList<String> id,
            ArrayList<String> name,
            ArrayList<String> start,
            ArrayList<String> end,
            ArrayList<String> message
    ) {

        this.context = context2;
        this.ID = id;
        this.Name = name;
        this.DurationStart = start;
        this.DurationEnd = end;
        this.Message = message;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return ID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            child = layoutInflater.inflate(R.layout.message, null);

            holder = new Holder();

            holder.ID_TextView = (TextView) child.findViewById(R.id.textViewMessageID);
            holder.Name_TextView = (TextView) child.findViewById(R.id.textViewMessageNAME);
            holder.DurationStartTextView = (TextView) child.findViewById(R.id.textViewStart);
            holder.DurationEndTextView = (TextView) child.findViewById(R.id.textViewEnd);
            holder.MessageTextView = (TextView) child.findViewById(R.id.textViewMessage);
            holder.RelativeLayout = (RelativeLayout) child.findViewById(R.id.relativelayoutMessage);
            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.ID_TextView.setText(ID.get(position));
        holder.Name_TextView.setText(Name.get(position));
        holder.DurationStartTextView.setText(DurationStart.get(position));
        holder.DurationEndTextView.setText(DurationEnd.get(position));
        holder.MessageTextView.setText(Message.get(position));


        if (holder.MessageTextView.equals("Unpaid")) {
            holder.RelativeLayout.setBackgroundColor(Color.RED);
        }
        return child;
    }

    public class Holder {

        TextView ID_TextView;
        TextView Name_TextView;
        TextView DurationStartTextView;
        TextView DurationEndTextView;
        TextView MessageTextView;
        RelativeLayout RelativeLayout;
    }

}