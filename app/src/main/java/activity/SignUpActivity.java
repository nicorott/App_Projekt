package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a21q4_app_projekt.R;

public class SignUpActivity extends Activity {

    EditText edt_password;
    EditText edt_password_proof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edt_password = findViewById(R.id.edt_password);
        edt_password_proof = findViewById(R.id.edt_password_proof);
    }

    public void continue_click(View view) {
        if (edt_password.getText().toString().equals(edt_password_proof.getText().toString())) {
            Intent intent = new Intent(getApplicationContext(), SignUpActivity_2.class);
            startActivity(intent);
        }else{
            Toast.makeText(SignUpActivity.this, "Passwort stimmt nicht überein", Toast.LENGTH_LONG).show();
        }
    }
}