package com.melio.cara.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aahuangg on 2018-09-16.
 */

public class RoomsAdapter extends BaseAdapter {
    private List<Room> roomsList;
    private LayoutInflater roomsInflater;

    public RoomsAdapter(Context context, List<Room> rooms) {
        this.roomsList = rooms;
        this.roomsInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.roomsList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.roomsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // map to room layout
        LinearLayout roomLayout = (LinearLayout) this.roomsInflater.inflate(R.layout.item_room, viewGroup, false);
        //get title view
        TextView roomView = (TextView) roomLayout.findViewById(R.id.room_name);
        //get song using position
        Room currRoom = (Room) getItem(i);
        //get title and artist strings
        roomView.setText(currRoom.getTitle());
        return roomLayout;
    }

}
