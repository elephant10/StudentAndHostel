package com.example.studigo;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
    public Context context;

    Adapter(Context context, List<Room> rooms) {
        this.rooms = rooms;
        this.context = context;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RoomActivity.class);
                System.out.println("holder.getAdapterPosition() = " + holder.getAdapterPosition());
                intent.putExtra("room", rooms.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView roomNameText;


        ViewHolder(View view) {
            super(view);
            roomNameText = view.findViewById(R.id.roomNameText);

        }


    }
}