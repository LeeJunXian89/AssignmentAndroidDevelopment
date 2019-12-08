package com.example.assignmentdegree;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SeatSelection extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    LinearLayout containLayout;
    CheckBox seat;
    int seatCount = 0;
    ArrayList<Integer> selectedSeat = new ArrayList<>();
    ArrayList<Integer> UserSelectedSeat = new ArrayList<>();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);
        setTitle("Seat Selection");

        int i, j;
        containLayout = (LinearLayout) findViewById(R.id.container);

        for (i = 0; i < 6; i++) {
            LinearLayout row = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i == 3 || i == 3) {
                params.setMargins(0, 50, 0, 0);
            }
            row.setLayoutParams(params);
            row.setId(i + 200);
            row.setOrientation(LinearLayout.HORIZONTAL);

            for (j = 0; j < 6; j++) {
                CheckBox seat = new CheckBox(this);
                seat.setText("");
                if (j == 2) {
                    seat.setText("...");
                }
                int seat_id = i * 6 + j;
                seat.setId(seat_id);
                seat.setButtonDrawable(R.drawable.seat);
                seat.setOnCheckedChangeListener(this);
                seat.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

                row.addView(seat);
            }
            containLayout.addView(row);
        }

        prepareList();
    }

    private void prepareList() {
        Date c  = Calendar.getInstance().getTime(); ;
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate= df.format(c) ;
        final String childDate = formattedDate ;

        Intent i = getIntent();
        String title = i.getStringExtra("movie_name");
        String time = i.getStringExtra("time");

        String childTitle = title;
        String chidTime = time;

        DatabaseReference myreff = FirebaseDatabase.getInstance().getReference().child("Movie").child(childTitle).child(childDate).child(chidTime).child("Seat Selection");
        myreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue()!=null){
                    for (int i = 0;i < dataSnapshot.getChildrenCount();i++){
                        Integer item = dataSnapshot.child(i+"").getValue(Integer.class);
                        CheckBox cb = findViewById(item);
                        cb.setEnabled(false);
                        selectedSeat.add(item);
                    }
                }else{
                    return;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG", "No Value", databaseError.toException());
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Toast.makeText(this, "Clicked:"+buttonView.getId(), Toast.LENGTH_SHORT).show();
        if(isChecked==true) {
            seatCount++;
            selectedSeat.add(buttonView.getId());
            UserSelectedSeat.add(buttonView.getId());
        }
        else {
            seatCount--;
            selectedSeat.remove(new Integer(buttonView.getId()));
            UserSelectedSeat.remove(new Integer(buttonView.getId()));
        }
        String seatText;
        if(seatCount==0)
            seatText="Please select your seats";
        else
            seatText=seatCount+" seats selected";
        final TextView sCount=(TextView)findViewById(R.id.seatCount);
        sCount.setText(seatText);
    }


    public void bookTickets(View view) {
        if (seatCount > 10){
            Toast.makeText(this,"The booking seat already reach the limit, one user can only booking 10 seat per one times",Toast.LENGTH_SHORT).show();
        }else if(seatCount < 1){
            Toast.makeText(this,"Please select at least one seat",Toast.LENGTH_SHORT).show();
        }else{
            DatabaseReference reff = FirebaseDatabase.getInstance().getReference("Movie");

            Date c  = Calendar.getInstance().getTime(); ;
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate= df.format(c) ;
            String childDate = formattedDate ;

            Intent i = getIntent();
            String title = i.getStringExtra("movie_name");
            String time = i.getStringExtra("time");

            String child = title;
            String chidTime = time;

            reff.child(child).child(childDate).child(chidTime).child("Seat Selection").setValue(selectedSeat);
            Intent ticketPage = new Intent(this, Payment.class);
            ticketPage.putExtra("number_ticket",seatCount);
            ticketPage.putExtra("movie_name",title);
            ticketPage.putExtra("time",time);
            ticketPage.putExtra("user_seat",UserSelectedSeat);
            ticketPage.putExtra("date",childDate);

            startActivity(ticketPage);
        }
    }
}
