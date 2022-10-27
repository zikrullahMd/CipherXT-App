package zik.myappcompany.cipherxt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    Button log;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        log = findViewById(R.id.logoutBtn);
        mAuth = FirebaseAuth.getInstance();

        log.setOnClickListener(view->{

            Toast.makeText(HomeActivity.this,"Logging out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,MainActivity.class));
        });
    }

    public void chromeSetup(View view){
        Intent intent = new Intent(this,ChromeSetupActivity.class);
        startActivity(intent);
    }
    public void nonchromeSetup(View view){
        Intent intent = new Intent(this,NoChromeSetupActivity.class);
        startActivity(intent);
    }

}