package com.example.assignmentdegree;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class userTicketAdapter extends RecyclerView.Adapter<userTicketAdapter.myViewHolder> {
    Context context;
    ArrayList<Ticket> ticket;

    public userTicketAdapter(Context context, ArrayList<Ticket> ticket) {
        this.context = context;
        this.ticket = ticket;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_ticket_card_view,null);

        myViewHolder holder = new myViewHolder (view);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        String text="";
        final Ticket tickets = ticket.get(position);
        holder.title.setText(tickets.getMovie_name());
        holder.date.setText(tickets.getDate());
        holder.time.setText(tickets.getTime());
        for(int i = 0 ; i < tickets.getSeat().size();i++){
            text += tickets.getSeat().get(i).toString() + "  ";
        }

        holder.seat.setText(text);
    }

    @Override
    public int getItemCount() {
        return ticket.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        TextView title,date,time,seat;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.tv_title);
            date = (TextView)itemView.findViewById(R.id.tv_date);
            time = (TextView)itemView.findViewById(R.id.tv_time);
            seat = (TextView)itemView.findViewById(R.id.tv_seat);
        }
    }
}
