package zik.myappcompany.cipherxt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    TextInputEditText email;
    TextInputEditText pass;
    TextView login;
    Button sbtn;


    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email = (TextInputEditText) findViewById(R.id.email);
        pass = (TextInputEditText) findViewById(R.id.pass);
        login = (TextView) findViewById(R.id.loginpage);
        sbtn = (Button) findViewById(R.id.signupBtn);

        mAuth = FirebaseAuth.getInstance();

        sbtn.setOnClickListener(view -> {
            createUser();
        });
        login.setOnClickListener(view->{
            startActivity(new Intent(Signup.this,MainActivity.class));
        });

    }


    public void createUser(){
        String em = email.getText().toString();
        String pa = pass.getText().toString();

        if(TextUtils.isEmpty(em)){
            email.setError("Email cannot be empty");
            email.requestFocus();
        }else if(TextUtils.isEmpty(pa)){
            pass.setError("Password cannot be empty");
            pass.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(em,pa).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Signup.this,"User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Signup.this,MainActivity.class));
                    }else{
                        Toast.makeText(Signup.this,"Sighup Error "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }



}