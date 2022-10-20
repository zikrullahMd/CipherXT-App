package zik.myappcompany.cipherxt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChromeSetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrome_setup);
    }

    public void gotoencode(View view){
        Intent intent = new Intent(this,ChromeEncode.class);
        startActivity(intent);
    }
    public void gotodecode(View view){
        Intent intent = new Intent(this,ChromeDecode.class);
        startActivity(intent);
    }
}