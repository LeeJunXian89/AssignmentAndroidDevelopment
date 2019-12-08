package com.example.assignmentdegree;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener{
    private final AppCompatActivity activity = RegisterPage.this;
    private InputValidation inputValidation;

    private EditText mEditTextName;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private EditText mEditTextConfirmPassword;

    private Button mBtnRegister;
    private TextView mLinkLogin;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        inputValidation = new InputValidation(activity);

        mEditTextName = (EditText)findViewById(R.id.textInputEditTextName);
        mEditTextEmail = (EditText)findViewById(R.id.textInputEditTextEmail);
        mEditTextPassword = (EditText)findViewById(R.id.textInputEditTextPassword);
        mEditTextConfirmPassword = (EditText)findViewById(R.id.textInputEditTextConfirmPassword);

        mBtnRegister = (Button)findViewById(R.id.appCompatButtonRegister);
        mBtnRegister.setOnClickListener(this);

        mLinkLogin = (TextView)findViewById(R.id.appCompatTextViewLoginLink);
        mLinkLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.appCompatButtonRegister:
                postDataToFireBase();
                break;
            case R.id.appCompatTextViewLoginLink:
                Intent i = new Intent(this,LoginPage.class);
                startActivity(i);
                finish();
                break;
        }
    }

    private void postDataToFireBase() {
        if (!inputValidation.isEditTextFilled(mEditTextName, getString(R.string.error_message_name))) {
            return;
        }

        if (!inputValidation.isEditTextFilled(mEditTextEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isEditTextEmail(mEditTextEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isEditTextFilled(mEditTextPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(mEditTextPassword, mEditTextConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }
        if (!inputValidation.isEditTextPassword(mEditTextPassword, getString(R.string.less_than_6)))

        auth = FirebaseAuth.getInstance();

        auth.createUserWithEmailAndPassword(mEditTextEmail.getText().toString(),mEditTextPassword.getText().toString()).addOnCompleteListener(RegisterPage.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(RegisterPage.this,"Register Fail",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(RegisterPage.this,MainActivity.class));
                    finish();
                }
            }
        });
    }
}
