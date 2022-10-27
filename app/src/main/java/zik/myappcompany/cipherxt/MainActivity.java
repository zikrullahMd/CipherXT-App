package zik.myappcompany.cipherxt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button loginBtn;
    private FirebaseAuth mAuth;

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        loginBtn = findViewById(R.id.button);
        TextView sign = findViewById(R.id.sign);
        username = findViewById(R.id.usernameInput);
        password = findViewById(R.id.passwordInput);
        loginBtn.setOnClickListener(view ->{
            loginUser();
        });
        sign.setOnClickListener(view->{
            startActivity(new Intent(MainActivity.this,Signup.class));
        });
    }
    public void loginUser(){
        String email = username.getText().toString();
        String pass = password.getText().toString();

        if(TextUtils.isEmpty(email)){
            username.setError("Email cannot be empty");
            username.requestFocus();
        }else if(TextUtils.isEmpty(pass)){
            password.setError("Password cannot be empty");
            password.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this,"Hello "+email.toString(),Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,HomeActivity.class));
                    }else{
                        Toast.makeText(MainActivity.this,"User not registered",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,Signup.class));
                    }
                }
            });
        }
    }
    public void authenticate(View view){
        EditText username = (EditText) findViewById(R.id.usernameInput);
        EditText password = (EditText) findViewById(R.id.passwordInput);

        if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("name",username.getText().toString());
            startActivity(intent);
        }else{
            Toast.makeText(MainActivity.this,"Invalid username or password",Toast.LENGTH_SHORT).show();
        }
    }

//    public void signupFun(){
//        try{
//            startActivity(new Intent(MainActivity.this,Signup.class));
//        }catch(Exception e){
//            Toast.makeText(MainActivity.this,"Error "+e.getMessage().toString(),Toast.LENGTH_SHORT).show();
//        }
//    }

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            startActivity(new Intent(MainActivity.this,Signup.class));
        }
    }

}