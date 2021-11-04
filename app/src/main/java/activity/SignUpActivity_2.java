package activity;

import android.app.Activity;
import android.content.Intent;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;

import model.Account;

public class SignUpActivity_2 extends Activity {

    String edt_password;
    String edt_email;
    String edt_username;

    EditText edt_firstName;
    EditText edt_lastName;
    EditText edt_birthday;
    EditText edt_city;
    EditText edt_street;
    EditText edt_houseNumber;
    EditText edt_plz;

    Account ac = Account.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_2);

        if(savedInstanceState == null) {
            Bundle extra = getIntent().getExtras();
            edt_password = extra.getString("password");
            edt_email = extra.getString("email");
            edt_username = extra.getString("username");
        }

        edt_firstName = findViewById(R.id.edt_firstName);
        edt_lastName = findViewById(R.id.edt_LastName);
        edt_birthday = findViewById(R.id.edt_birthday);
        edt_city = findViewById(R.id.edt_city);
        edt_street = findViewById(R.id.edt_street);
        edt_houseNumber = findViewById(R.id.edt_housenumber);
        edt_plz = findViewById(R.id.edt_postalcode);
    }

    FirebaseAuth Auth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void signup_2_click(View view) {
        //Creates User in Firebase database
        Auth.createUserWithEmailAndPassword(edt_email, edt_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                ac.setAccount(
                                        Auth.getCurrentUser().getUid(),
                                        edt_username.toString(),
                                        edt_email.toString(),
                                        edt_firstName.toString(),
                                        edt_lastName.toString(),
                                        LocalDate.parse(edt_birthday.toString()),
                                        edt_city.toString(),
                                        edt_street.toString(),
                                        edt_houseNumber.toString(),
                                        Integer.valueOf(edt_plz.toString()));
                                db.collection("users").add(ac).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(SignUpActivity_2.this, "Registration successful", Toast.LENGTH_LONG).show();
                                            //Switches to the start screen after successful completion of registration
                                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(SignUpActivity_2.this, "Registration failed", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }else{
                                Toast.makeText(SignUpActivity_2.this, "Registration failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}