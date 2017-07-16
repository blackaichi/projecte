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

public class Login extends AppCompatActivity {
    private static final String TAG = "main activity";
    Button Login;
    Button GoToRegister;
    EditText User;
    EditText Pass;
    DataBase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.v(TAG, "he afegit algu a la bd");

        User = (EditText) findViewById(R.id.user);
        Pass = (EditText) findViewById(R.id.password);
        Login = (Button) findViewById(R.id.Login);
        GoToRegister = (Button) findViewById(R.id.gotoregister);
        db = new DataBase(getApplicationContext());

        if (isLoggedIn()) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        Login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String user = User.getText().toString();
                String pass = Pass.getText().toString();

                if (!user.isEmpty() && !pass.isEmpty()) {
                    if (db.checklogin(user, pass)) {
                        Vars.loged = true;
                        Vars.thisuser = user;
                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else {
                        toast("XEEEEEIICCCC si t'has equivocat vale, sino registrat o alpargata el la frente");
                        Intent i = new Intent(getApplicationContext(),Bad_Login.class);
                        startActivity(i);
                    }
                } else {
                    toast("Pa mi que no has omplert res amigo");
                }
            }

        });
        GoToRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Register.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void toast(String s) {
        if (Vars.toast != null) {
            Vars.toast.cancel();
        }
        Vars.toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        Vars.toast.show();
    }

    public static boolean isLoggedIn() {
        return Vars.loged;
    }
}
