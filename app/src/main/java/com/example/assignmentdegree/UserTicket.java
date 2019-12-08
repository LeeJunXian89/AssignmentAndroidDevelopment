package com.example.assignmentdegree;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserTicket extends AppCompatActivity {
    RecyclerView rcTicketList;
    userTicketAdapter mvAdapter;
    LinearLayoutManager layoutManager;
    ArrayList<Ticket> userTicket = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_ticket);
        setTitle("My Ticket");

        rcTicketList = (RecyclerView) findViewById(R.id.rc_ticketList);
        layoutManager = new LinearLayoutManager(this);
        rcTicketList.setLayoutManager(layoutManager);
        rcTicketList.setHasFixedSize(true);
        getTicketList();

    }

    public void getTicketList(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myreff = database.getReference().child("Ticket").child(auth.getCurrentUser().getUid());
        myreff.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ArrayList<Integer> userSelectedSeat = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    if (ds.getValue() != null) {
                        for (DataSnapshot ds2 : ds.getChildren()) {
                            userSelectedSeat.clear();
                            String date = ds2.child("date").getValue(String.class);
                            String title = ds2.child("movie_name").getValue(String.class);
                            String time = ds2.child("time").getValue(String.class);
                            for (int i = 0; i < ds2.child("seat").getChildrenCount(); i++) {
                                Integer seat = ds2.child("seat").child(i + "").getValue(Integer.class);
                                userSelectedSeat.add(seat);
                            }
                            Ticket temp = new Ticket(title,time,date,userSelectedSeat);
                            userTicket.add(temp);
                            mvAdapter = new userTicketAdapter(UserTicket.this , userTicket);
                            rcTicketList.setAdapter(mvAdapter);
                        }

                    }
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
