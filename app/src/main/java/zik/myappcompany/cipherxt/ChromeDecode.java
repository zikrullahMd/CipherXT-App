package zik.myappcompany.cipherxt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.*;
import java.net.URI;

public class ChromeDecode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrome_decode);
    }
    public void Decode(View view) throws Exception{
        EditText data = (EditText) findViewById(R.id.decodeInput);
        TextView res = (TextView) findViewById(R.id.decodeOutput);
        String s = data.getText().toString();
        s = s.trim();
        boolean toClean = false;
        char l = s.charAt(s.length()-1);
        int length = Character.getNumericValue(l);
        StringBuilder sb = new StringBuilder(s);
        s = sb.toString();
        int sq = sqrRt(s.length());
        int n;
        if(s.length()==sq*sq) {
            n = sqrRt(s.length());
        }else {
            toClean = true;
            n = sqrRt(getCode(s.length()));
        }
        char[][] mat = new char[n][n];
        int k = 0;
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<n;j++) {
                mat[i][j] = s.charAt(k++);
            }
        }
        char[][] at = new char[n][n];
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<n;j++) {
                at[i][j] = mat[j][i];
            }
        }
        StringBuilder en = new StringBuilder();
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<n;j++) {
                en.append(at[i][j]);
            }
        }
        String cleaned_code = cleanCode(en);
        chrome(cleaned_code);
    }
    public static int sqrRt(int n) {
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
    public void chrome(String url) throws Exception{
        try {
            Intent i = new Intent("android.intent.action.MAIN");
            i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
            i.addCategory("android.intent.category.LAUNCHER");
            i.setData(Uri.parse(url));
            startActivity(i);
        }
        catch(ActivityNotFoundException e) {
            // Chrome is probably not installed
        }
    }
    public static String cleanCode(StringBuilder s) {
        System.out.println(s);
        int index = s.length()-1;
        for(;index>=0;) {
            if(s.charAt(index)=='.') {
                index--;
            }else {
                break;
            }
        }
        s.delete(index, s.length());
        return s.toString();
    }
}