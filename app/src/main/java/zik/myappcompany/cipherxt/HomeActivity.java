package zik.myappcompany.cipherxt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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