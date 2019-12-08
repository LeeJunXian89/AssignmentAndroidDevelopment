package com.example.assignmentdegree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcMovieList;
    private ArrayList<Movie> movie;
    private MovieAdapter mvAdapter;
    private LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcMovieList = (RecyclerView)findViewById(R.id.rc_movieList);
        movie=initialObject.getList();
        layoutManager = new LinearLayoutManager(this);
        rcMovieList.setLayoutManager(layoutManager);
        rcMovieList.setHasFixedSize(true);
        mvAdapter=new MovieAdapter(MainActivity.this,movie);
        rcMovieList.setAdapter(mvAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        FirebaseAuth auth;
        auth = FirebaseAuth.getInstance();
        getMenuInflater().inflate(R.menu.menuitem,menu);

        if(auth.getCurrentUser()==null){
            MenuItem item_login= menu.findItem(R.id.login);
            MenuItem item_Register = menu.findItem(R.id.register);
            MenuItem item_logout = menu.findItem(R.id.logout);
            MenuItem item_ticket = menu.findItem(R.id.ticket);

            item_login.setVisible(true);
            item_logout.setVisible(false);
            item_Register.setVisible(true);
            item_ticket.setVisible(false);
        }else{
            MenuItem item_login= menu.findItem(R.id.login);
            MenuItem item_Register = menu.findItem(R.id.register);
            MenuItem item_logout = menu.findItem(R.id.logout);
            MenuItem item_ticket = menu.findItem(R.id.ticket);

            item_login.setVisible(false);
            item_logout.setVisible(true);
            item_Register.setVisible(false);
            item_ticket.setVisible(true);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.login:
                Intent loginPage = new Intent (this,LoginPage.class);
                startActivity(loginPage);
                break;
            case R.id.logout:
                FirebaseAuth auth;
                auth = FirebaseAuth.getInstance();
                auth.signOut();
                finish();
                startActivity(getIntent());
                break;
            case R.id.register:
                Intent registerPage = new Intent (this,RegisterPage.class);
                startActivity(registerPage);
                break;
            case R.id.ticket:
                Intent userTicketPage = new Intent(this,UserTicket.class);
                startActivity(userTicketPage);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
        getActionBar();
    }
}
