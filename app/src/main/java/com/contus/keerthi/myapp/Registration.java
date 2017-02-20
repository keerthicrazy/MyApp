package com.contus.keerthi.myapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.contus.keerthi.myapp.Contract.Employee;
import com.contus.keerthi.myapp.POJO.emp;
import com.contus.keerthi.myapp.dbEntry.dbHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by user on 3/2/17.
 */

public class Registration extends AppCompatActivity {

    // defining view objects
    Button reg;
    TextView existing;
    EditText username,password;
    private ProgressDialog progressDialog;

    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        this.setTitle(R.string.registbtn);

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

        reg=(Button)findViewById(R.id.registbtn);
        existing=(TextView)findViewById(R.id.existing);
        username=(EditText)findViewById(R.id.username1);
        password=(EditText)findViewById(R.id.password);

        reg.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   progressDialog.setTitle("Sign IN");
                   progressDialog.setMessage("Registering please wait..");
                   progressDialog.show();

                   String email = username.getText().toString();
                   String pass = password.getText().toString();

                   firebaseAuth.createUserWithEmailAndPassword(email, pass)
                           .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                               @Override
                               public void onComplete(@NonNull Task<AuthResult> task) {

                                   if (task.isSuccessful()) {
                                       Toast.makeText(Registration.this, "Successfully registered !!! Login to Continue ..", Toast.LENGTH_LONG).show();
                                       startActivity(new Intent(Registration.this,LoginActivity.class));
                                   } else {
                                       Toast.makeText(Registration.this, "Registration Error", Toast.LENGTH_LONG).show();
                                   }
                                    if(progressDialog.isShowing())
                                        progressDialog.dismiss();
                               }
                           });


               }
           });

        existing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this,LoginActivity.class));
            }
        });
    }


    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}


// Saving Data in local db
/*emp e=new emp(name.getText().toString(),age.getText().toString(),username.getText().toString(),password.getText().toString());
        dbHelper dbHelper= new dbHelper(Registration.this, Employee.DB_NAME,Employee.DATABASE_VERSION);
        if(dbHelper.addEmployee(e))
             */