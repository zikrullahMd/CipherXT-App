package zik.myappcompany.cipherxt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NoChromeSetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_chrome_setup);
    }

    public void gotoencode(View view){
        Intent intent = new Intent(this,NoChromeEncode.class);
        startActivity(intent);

    }

    public void gotodecode(View view){
        Intent intent = new Intent(this,NoChromeDecode.class);
        startActivity(intent);
    }

}