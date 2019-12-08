package com.example.assignmentdegree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Description extends AppCompatActivity implements View.OnClickListener {
    private ImageView movieCover;
    private TextView movieTitle;

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    Intent intent;
    int image;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        intent=getIntent();
        image = intent.getIntExtra("movie_id",0);
        title = intent.getStringExtra("movie_name");
        setTitle(title);

        movieCover =(ImageView)findViewById(R.id.imageView);
        movieCover.setImageResource(image);

        movieTitle = (TextView)findViewById(R.id.tv_title);
        movieTitle.setText(title);

        btn1 = (Button)findViewById(R.id.btnTime1130);
        btn2 = (Button)findViewById(R.id.btnTime1245);
        btn3 = (Button)findViewById(R.id.btnTime1310);
        btn4 = (Button)findViewById(R.id.btnTime420);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this,SeatSelection.class);
        FirebaseAuth auth;
        auth = FirebaseAuth.getInstance();
        switch (v.getId()){
            case R.id.btnTime1130:
                if( auth.getCurrentUser()==null ) {
                    Intent loginPage = new Intent(this, LoginPage.class);
                    startActivity(loginPage);
                }else{
                    i.putExtra("movie_name",title);
                    i.putExtra("time","11:30");
                    startActivity(i);
                }
                break;
            case R.id.btnTime1245:
                if( auth.getCurrentUser()==null ) {
                    Intent loginPage = new Intent(this, LoginPage.class);
                    startActivity(loginPage);
                }else{
                    i.putExtra("movie_name",title);
                    i.putExtra("time","12:45");
                    startActivity(i);
                }

                break;
            case R.id.btnTime1310:
                if( auth.getCurrentUser()==null ) {
                    Intent loginPage = new Intent(this, LoginPage.class);
                    startActivity(loginPage);
                }else{
                    i.putExtra("movie_name",title);
                    i.putExtra("time","13:10");
                    startActivity(i);
                }

                break;
            case R.id.btnTime420:
                if( auth.getCurrentUser()==null ) {
                    Intent loginPage = new Intent(this, LoginPage.class);
                    startActivity(loginPage);
                }else{
                    i.putExtra("movie_name",title);
                    i.putExtra("time","4:20");
                    startActivity(i);
                }

                break;
        }
    }
}
