package com.contus.keerthi.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ReadFile extends AppCompatActivity {

    Button rdbtn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_file);
        rdbtn = (Button) findViewById(R.id.readbtn);
        tv = (TextView) findViewById(R.id.readtxt);

        rdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String filename = tv.getText().toString().trim().toLowerCase();
                    FileInputStream fileIn = openFileInput(filename);
                    InputStreamReader InputRead = new InputStreamReader(fileIn);

                    char[] inputBuffer = new char[100];
                    String s = "";
                    int charRead;

                    while ((charRead = InputRead.read(inputBuffer)) > 0) {

                        String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                        s += readstring;
                    }
                    InputRead.close();
                    tv.setText(s);


                } catch (Exception e) {
                    tv.setText("Wrong File Name");
                    Log.i("TAG", "onClick: " + e.getMessage());
                }
            }
        });
    }
}
