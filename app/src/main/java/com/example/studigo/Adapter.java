package com.example.studigo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.studigo.model.Room;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Room> rooms;

    Adapter(Context context, List<Room> rooms) {
        this.rooms = rooms;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.room_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        holder.roomNameText.setText(rooms.get(position).roomNumber/*state.roomNumber*/);
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView roomNameText;

        ViewHolder(View view) {
            super(view);
            roomNameText = view.findViewById(R.id.roomNameText);
        }
    }
}