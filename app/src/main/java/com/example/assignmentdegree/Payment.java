package com.example.assignmentdegree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Payment extends AppCompatActivity implements View.OnClickListener {
    private Button mAddAdult,mAddChild,minusAdult,minusChild,mbtnContinues;
    private TextView mTotalPrice,mAdultCount,mChildCount;
    int adultCount,childCount;
    int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        setTitle("Payment");

        mAddAdult = (Button)findViewById(R.id.addAdult);
        minusAdult=(Button)findViewById(R.id.minusAdult);

        mAddChild=(Button)findViewById(R.id.addChild);
        minusChild=(Button)findViewById(R.id.minusChild);

        mAdultCount = (TextView)findViewById(R.id.adultCount);
        mChildCount = (TextView)findViewById(R.id.childCount);

        mTotalPrice=(TextView)findViewById(R.id.totalPrice);

        mbtnContinues =(Button)findViewById(R.id.btnPay);

        mAddChild.setOnClickListener(this);
        minusChild.setOnClickListener(this);
        mAddAdult.setOnClickListener(this);
        minusAdult.setOnClickListener(this);
        mbtnContinues.setOnClickListener(this);

        minusAdult.setEnabled(false);
        minusChild.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addAdult:
                adultCount = Integer.parseInt(mAdultCount.getText().toString());
                adultCount++;

                if (adultCount<1){
                    minusAdult.setEnabled(false);
                }else{
                    minusAdult.setEnabled(true);
                }

                if ((adultCount + childCount) > 9){
                    mAddAdult.setEnabled(false);
                    mAddChild.setEnabled(false);
                }
                mAdultCount.setText(""+adultCount);


                totalPrice = Integer.parseInt(mTotalPrice.getText().toString());
                totalPrice = totalPrice + 16;
                mTotalPrice.setText(""+totalPrice);
                break;
            case R.id.minusAdult:
                adultCount = Integer.parseInt(mAdultCount.getText().toString());
                adultCount--;
                if (adultCount<1){
                    minusAdult.setEnabled(false);
                }else{
                    minusAdult.setEnabled(true);
                }

                if (!mAddChild.isEnabled() && !mAddAdult.isEnabled()){
                    mAddAdult.setEnabled(true);
                    mAddChild.setEnabled(true);
                }
                mAdultCount.setText(""+adultCount);

                totalPrice=Integer.parseInt(mTotalPrice.getText().toString());
                totalPrice-=16.00;
                mTotalPrice.setText(""+totalPrice);
                break;
            case R.id.addChild:
                childCount = Integer.parseInt(mChildCount.getText().toString());
                childCount++;

                if (childCount<1){
                    minusChild.setEnabled(false);
                }else{
                    minusChild.setEnabled(true);
                }

                if ((adultCount + childCount) > 9){
                    mAddAdult.setEnabled(false);
                    mAddChild.setEnabled(false);
                }

                mChildCount.setText(""+childCount);

                totalPrice=Integer.parseInt(mTotalPrice.getText().toString());
                totalPrice+=7.00;
                mTotalPrice.setText(""+totalPrice);
                break;
            case R.id.minusChild:
                childCount = Integer.parseInt(mChildCount.getText().toString());
                childCount --;

                if (childCount<1){
                    minusChild.setEnabled(false);
                }else{
                    minusChild.setEnabled(true);
                }

                if (!mAddChild.isEnabled() && !mAddAdult.isEnabled()){
                    mAddAdult.setEnabled(true);
                    mAddChild.setEnabled(true);
                }
                mChildCount.setText(""+childCount);

                totalPrice=Integer.parseInt(mTotalPrice.getText().toString());
                totalPrice-=7.00;
                mTotalPrice.setText(""+totalPrice);
                break;
            case R.id.btnPay:
                Intent getextra = getIntent();
                int totalTicket = getextra.getIntExtra("number_ticket",0);
                if ((adultCount + childCount) != totalTicket){
                    Toast.makeText(this,"You amount of ticket is not match",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"Thank you, please come again",Toast.LENGTH_SHORT).show();
                    Intent mainActivityPage = new Intent(this,MainActivity.class);

                    String title = getextra.getStringExtra("movie_name");
                    String time = getextra.getStringExtra("time");
                    String date = getextra.getStringExtra("date");
                    ArrayList<Integer> seat = (ArrayList<Integer>)getextra.getSerializableExtra("user_seat");

                    Ticket userTicket = new Ticket(title,time,date,seat);
                    DatabaseReference reff = FirebaseDatabase.getInstance().getReference("Ticket");
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    String user = auth.getCurrentUser().getUid();
                    reff.child(user).child(title).child(date).child(time).setValue(userTicket);

                    startActivity(mainActivityPage);
                }
        }
    }
}
