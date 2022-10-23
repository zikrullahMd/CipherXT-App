package zik.myappcompany.cipherxt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NoChromeDecode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_chrome_decode);
    }
    public void decode(View view){
        try {
            EditText data = (EditText) findViewById(R.id.decodeInput);
            TextView res = (TextView) findViewById(R.id.result);
            String s = data.getText().toString();
            s = s.trim();
            int sq = sqrRt(s.length());
            int n;
            if (s.length() == sq * sq) {
                n = sqrRt(s.length());
            } else {
                n = sqrRt(getCode(s.length()));
            }
            char[][] mat = new char[n][n];
            int k = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    mat[i][j] = s.charAt(k++);
                }
            }
            char[][] at = new char[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    at[i][j] = mat[j][i];
                }
            }
            StringBuilder de = new StringBuilder();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    de.append(at[i][j]);
                }
            }
            res.setText(de);
        }catch(Exception e){
            Toast t = Toast.makeText(this,"Error decoding",Toast.LENGTH_SHORT);
            t.show();
        }
    }
    public int sqrRt(int n) {
        int low = 0;
        int high = n;
        while(low<=high) {
            int mid = (low+high)/2;
            if(mid*mid==n) {
                return mid;
            }else if(mid*mid<n) {
                low = mid+1;
            }else if(mid*mid>n) {
                high = mid-1;
            }
        }
        return -1;
    }
    public int getCode(int n) {
        int nextN = (int)Math.floor(Math.sqrt(n))+1;
        return nextN * nextN;
    }
}