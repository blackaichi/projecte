package com.projectefinal.blackaichi.projectefinal.LogReg;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projectefinal.blackaichi.projectefinal.Data.DataBase;
import com.projectefinal.blackaichi.projectefinal.Data.Vars;
import com.projectefinal.blackaichi.projectefinal.MainActivity;
import com.projectefinal.blackaichi.projectefinal.R;

public class Register extends AppCompatActivity {
    Button Register;
    Button GoToLogin;
    EditText newuser;
    EditText newpass;
    EditText repeatnewpass;
    DataBase db;
    public static final String TAG = "main activity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.v(TAG, "ie1");


        Register = (Button) findViewById(R.id.Register);
        GoToLogin = (Button) findViewById(R.id.gotologin);
        newuser = (EditText) findViewById(R.id.username);
        newpass = (EditText) findViewById(R.id.password);
        repeatnewpass = (EditText) findViewById(R.id.repeatpassword);
        db = new DataBase(getApplicationContext());

        if (Login.isLoggedIn()) {
            Intent intent = new Intent(Register.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        Register.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String username = newuser.getText().toString().trim();
                String password = newpass.getText().toString().trim();
                String repeatpassword = repeatnewpass.getText().toString().trim();

                if (!username.isEmpty() && !password.isEmpty() && !repeatpassword.isEmpty()) {
                    Log.v(TAG, "he afegit algu a la bd");
                    if (password.equals(repeatpassword)) {
                        db.createuserpass(username, password);
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        toast("Aprèn a puto escriure melon i escriu les 2 passwords iguals");
                    }
                } else {
                    toast("Pon tus putos datos cabrón!");
                }
            }

            private void toast(String s) {
                if (Vars.toast != null) {
                    Vars.toast.cancel();
                }
                Vars.toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
                Vars.toast.show();
            }
        });

        GoToLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
                finish();
            }
        });

    }
}
