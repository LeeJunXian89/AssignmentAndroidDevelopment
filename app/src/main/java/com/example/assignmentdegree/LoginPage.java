package com.example.assignmentdegree;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {
    private EditText mEmail;
    private EditText mPassword;
    private Button mBtnLogin;
    private FirebaseAuth auth;
    private TextView RegisterLink;
    private InputValidation inputValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        auth = FirebaseAuth.getInstance();
        inputValidation = new InputValidation(LoginPage.this);

        mEmail = (EditText) findViewById(R.id.editTextEmail);
        mPassword = (EditText)findViewById(R.id.editTextPassword);
        mBtnLogin = (Button)findViewById(R.id.appCompatButtonLogin);
        RegisterLink = (TextView)findViewById(R.id.textViewLinkRegister);
        RegisterLink.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.appCompatButtonLogin:
                VerifyFromDatabase();
                break;
            case R.id.textViewLinkRegister:
                Intent i = new Intent(this,RegisterPage.class);
                startActivity(i);
                finish();
                break;
        }
    }

    private void VerifyFromDatabase() {
        if (!inputValidation.isEditTextFilled(mEmail, getString(R.string.error_message_email))){
            return;
        }
        if (!inputValidation.isEditTextEmail(mEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isEditTextFilled(mPassword, getString(R.string.error_message_password))){
            return;
        }
        auth = FirebaseAuth.getInstance();

        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(LoginPage.this,"Incorrect email or password",Toast.LENGTH_SHORT).show();
                }else{
                    finish();
                }
            }
        });
    }
}
