package zik.myappcompany.cipherxt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ChromeEncode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrome_encode);
    }
    public void encode(View view){
        EditText data = (EditText) findViewById(R.id.decodeInput);
        TextView res = (TextView) findViewById(R.id.result);
        String s = data.getText().toString();
        s = s.trim();
        int sq = sqrRt(s.length());
        char[][] mat;
        int n;
        if(s.length()==sq*sq) {
            mat = getMat(s,sqrRt(s.length()));
            n = sqrRt(s.length());
        }else {
            mat = getMat(s,sqrRt(getCode(s.length())));
            n = sqrRt(getCode(s.length()));
        }
        char[][] t = new char[n][n];
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<n;j++) {
                t[i][j] = mat[j][i];
            }
        }
        StringBuilder en = new StringBuilder();
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<n;j++) {
                en.append(t[i][j]);
            }
        }
        en.append(s.length());
        res.setText(en.toString());
    }
    public char[][] getMat(String s, int n){
        char[][] mat = new char[n][n];
        int k = 0;
        int i = 0;
        int j = 0;
        for(i = 0;i<n;i++) {
            for(j= 0;j<n;j++) {
                if(k<s.length())
                    mat[i][j] = s.charAt(k++);
                else
                    mat[i][j] = '.';
            }
        }

        return mat;
    }
    public void printMat(char[][] mat, int n) {
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<n;j++) {
                System.out.print(mat[i][j]+" ");
            }
            System.out.println();
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