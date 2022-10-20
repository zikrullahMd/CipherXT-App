package zik.myappcompany.cipherxt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}