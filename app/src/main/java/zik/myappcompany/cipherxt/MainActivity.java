package zik.myappcompany.cipherxt;

import android.content.Intent;
import androidx.biometric.BiometricPrompt;
import androidx.biometric.BiometricManager;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {
    Button loginBtn;
    ImageView bio;
    private FirebaseAuth mAuth;

    EditText username;
    EditText password;

    BiometricPrompt biometricPrompt;

    BiometricPrompt.PromptInfo promptInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        loginBtn = findViewById(R.id.button);
        bio = findViewById(R.id.thumbBtn);
        TextView sign = findViewById(R.id.sign);
        username = findViewById(R.id.usernameInput);
        password = findViewById(R.id.passwordInput);
        loginBtn.setOnClickListener(view ->{
            loginUser();
        });
        sign.setOnClickListener(view->{
            startActivity(new Intent(MainActivity.this,Signup.class));
        });
        bio.setOnClickListener(view->{
            //Biometric related codes

            //Check if phone has biometric sensor or not
            BiometricManager biometricManager = BiometricManager.from(this);
            switch (biometricManager.canAuthenticate()){
                //No biometric hardware
                case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                    Toast.makeText(this,"Device doesn't have fingerphirt sensor",Toast.LENGTH_SHORT).show();
                    break;
                //HW not working
                case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                    Toast.makeText(this,"Fingerprint hardware not working",Toast.LENGTH_SHORT).show();
                    break;
                //No fingerpring enrolled
                case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                    Toast.makeText(this,"No fingerpring assigned",Toast.LENGTH_SHORT).show();
                    break;

            }
            Executor executor = ContextCompat.getMainExecutor(this);

            biometricPrompt = new BiometricPrompt(MainActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    Toast.makeText(MainActivity.this,"Invalid User",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                }
            });
            promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Cipher Text").setDescription("Use Fingerpring to Login").setDeviceCredentialAllowed(true).build();
            biometricPrompt.authenticate(promptInfo);
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
  public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            startActivity(new Intent(MainActivity.this,Signup.class));
        }
    }




}