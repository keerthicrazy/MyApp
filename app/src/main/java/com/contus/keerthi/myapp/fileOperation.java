package com.contus.keerthi.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class fileOperation extends AppCompatActivity {

    Button b;
    EditText tvf,fn;
    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_operation);
        b=(Button)findViewById(R.id.savebtn);
        tvf=(EditText)findViewById(R.id.fileContent);
        fn=(EditText)findViewById(R.id.filename);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    filename=fn.getText().toString().trim();
                    FileOutputStream fileout = openFileOutput(filename, MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    outputWriter.write(tvf.getText().toString());
                    outputWriter.close();
                    Toast.makeText(getBaseContext(), "File saved successfully!",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(fileOperation.this,HomeActivity.class));
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Error!"+e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
