package activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.a21q4_app_projekt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import model.Account;

public class SignUpActivity extends Activity {

    EditText edt_password;
    EditText edt_password_proof;
    EditText edt_email;
    EditText edt_username;

    Account ac = Account.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edt_password = findViewById(R.id.edt_password);
        edt_password_proof = findViewById(R.id.edt_password_proof);
        edt_email = findViewById(R.id.edt_email);
        edt_username = findViewById(R.id.edt_username);

    }

    FirebaseAuth Auth = FirebaseAuth.getInstance();

    public void continue_click(View view) {
        Auth.createUserWithEmailAndPassword(edt_email.getText().toString(), edt_password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                edt_email.setText("");
                                edt_password.setText("");
                                ac.setUsername(edt_username.getText().toString());
                                ac.setEmail(edt_email.getText().toString());
                                ac.setUserUID(Auth.getCurrentUser().getUid());
                                Toast.makeText(SignUpActivity.this, ac.getUserUID(), Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), SignUpActivity_2.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(SignUpActivity.this, "Registration failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

    }
}