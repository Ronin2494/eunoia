package com.example.eunoia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//We need view holder for RecyclerView that's why created an adapter class for activity_journal_2

public class journalAdapter extends RecyclerView.Adapter<journalAdapter.MyViewHolder> {

    Context context;
    ArrayList<usersjournal> usersjournals;

    public journalAdapter(Context c, ArrayList<usersjournal> u){
        context = c;
        usersjournals  = u;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.journal_data, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.journal.setText(usersjournals.get(position).getJournal());

    }

    @Override
    public int getItemCount() {
        return usersjournals.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView journal;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            journal = (TextView) itemView.findViewById(R.id.text1);
        }
    }
}
