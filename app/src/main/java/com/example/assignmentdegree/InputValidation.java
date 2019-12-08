package com.example.assignmentdegree;

import android.content.Context;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

public class InputValidation  {
    private Context context;

    public InputValidation (Context context){
        this.context = context;
    }

    public boolean isEditTextFilled(EditText editText,String errorMessage){
        String value = editText.getText().toString().trim();

        if(value.isEmpty()){
            Toast.makeText(context,errorMessage,Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    public boolean isEditTextEmail (EditText editTextEmail, String errorMessage){
        String value = editTextEmail.getText().toString().trim();
        if (value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            Toast.makeText(context,errorMessage,Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    public boolean isInputEditTextMatches (EditText editText1, EditText editText2,String errorMessage){
        String value1 = editText1.getText().toString().trim();
        String value2 = editText2.getText().toString().trim();

        if (!value1.contentEquals(value2)){
            Toast.makeText(context,errorMessage,Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean isEditTextPassword(EditText edpassword,String errormessage){
        String value1 = edpassword.getText().toString().trim();

        if(!(value1.length()<6)){
            Toast.makeText(context,errormessage,Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
