package com.example.a21q4_app_projekt;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class HomeActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }
    TestDB tb = new TestDB();

    EditText text1;
}