package com.example.a21q4_app_projekt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class StartActivity extends Activity {

    EditText edt_username;
    EditText edt_password;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        prefs = this.getPreferences(Context.MODE_PRIVATE);

        //DELETE AFTER INSERTING SIGN OUT BUTTON
        prefs.edit().putBoolean("signedin", false).apply();
        //------------------------------------------

        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);

        if(prefs.getBoolean("signedin", false)){
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }
    }

    public void login_click(View view) {
        if((edt_username.getText().toString().isEmpty())){
            edt_username.setError(getString(R.string.error_no_username));
        }else if((edt_password.getText().toString().isEmpty())){
            edt_password.setError(getString(R.string.error_no_password));
        }else{
            if ((edt_username.getText().toString().equals("aue")) && (edt_password.getText().toString().equals("9"))) {
                prefs.edit().putBoolean("signedin", true).apply();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }else{
                edt_username.setError(null);
                edt_password.setError(getString(R.string.error_wrong_password_username));
            }
        }
    }

    public void signup_click(View view) {
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }
}