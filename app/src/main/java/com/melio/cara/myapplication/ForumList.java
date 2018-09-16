package com.melio.cara.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ForumList extends ArrayAdapter<String> {

    private final Activity context;
    private final String postheader;
    private final String postbody;

    public ForumList(Activity context,
                     String postheader, String postbody) {
        super(context, R.layout.item_posting);
        this.context = context;
        this.postheader = postheader;
        this.postbody = postbody;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_posting, null, true);
        TextView txtHeader = (TextView) rowView.findViewById(R.id.header);
        TextView txtBody = (TextView) rowView.findViewById(R.id.body);
        txtHeader.setText(postheader);
        txtBody.setText(postbody);
        return rowView;
    }
}