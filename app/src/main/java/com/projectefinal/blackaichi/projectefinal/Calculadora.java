package com.projectefinal.blackaichi.projectefinal;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Calculadora extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "main";
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    Button buttonANS, buttonresta, buttonsuma, buttonmult, buttondiv, buttonAC, buttoncoma, buttonigual, buttonborra;
    TextView texto, result, aux3;
    String control;
    String aux;
    Float resultat = 0f;
    Toast toast;
    HorizontalScrollView horizontalScrollView1, horizontalScrollView2, horizontalScrollView3;
    Toolbar toolbar;
    Intent trucar = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"));
    Intent navegador = new Intent(Intent.ACTION_WEB_SEARCH);
    boolean _toast, portrait;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
        Log.v(TAG,"Se ha llamado onSaveInstanceState");
        outState.putFloat("resultado",resultat);
        outState.putBoolean("toast", _toast);
        outState.putString("texto", texto.getText().toString());
        outState.putString("resultat", result.getText().toString());
        outState.putString("aux", aux3.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.v(TAG, "Se ha llamado onRestore");
        if (savedInstanceState != null) {
            resultat = savedInstanceState.getFloat("resultado");
            _toast = savedInstanceState.getBoolean("toast");
            Log.v(TAG, "1r");
            texto.setText(savedInstanceState.getString("texto"));
            Log.v(TAG, "1r");
            result.setText(savedInstanceState.getString("resultat"));
            Log.v(TAG, "1r");
            aux3.setText(savedInstanceState.getString("aux"));
            Log.v(TAG, "1r");
        }
    }

    private void toastnoti(String s) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void statenoti(String s, int mId) {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Error calculadora")
                        .setContentText("Desplega per més informació");

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("Informació");
        bigTextStyle.bigText(s);

        mBuilder.setStyle(bigTextStyle);

        Notification noti = mBuilder.build();

        mNotificationManager.notify(mId, noti);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_right,menu);
        return true;
    }

    private boolean operacio(String caracter) {
        return caracter.equals(getString(R.string.buttonresta)) || caracter.equals(getString(R.string.buttonsuma)) || caracter.equals(getString(R.string.buttonmult)) || caracter.equals(getString(R.string.buttondiv));
    }

    private boolean doble_decimal(String tu) {
        if (!tu.equals(getString(R.string.buttoncoma))) return false;
        int n = 1;
        String ultim = aux.substring(aux.length() - 1);
        while (!ultim.equals(getString(R.string.buttoncoma))) {
            Log.v(TAG, "1r");
            if (operacio(ultim)) return false;
            Log.v(TAG, "2n");
            if (n == aux.length()) return false;
            Log.v(TAG, "3r");
            ++n;
            ultim = aux.substring(aux.length() - n, aux.length() - n+1);
        }
        return true;
    }

    private boolean operacio_repetida(String text) {
        return operacio(control) && operacio(text) || (texto.getText().toString().length() == 2 && operacio(texto.getText().toString().substring(0,1)))&& operacio(text);
    }

    private void setText(String text) {
        aux = texto.getText().toString();
        control = aux.substring(aux.length() - 1);
        if (!primer(text)) {
            if (aux.equals("0")) texto.setText("");
            if (!operacio_repetida(text) && !doble_decimal(text)) {
                if (!operacio(text)) {
                    texto.setText(texto.getText().toString() + text);
                    if (!errors()) {
                        if (!text.equals(getString(R.string.buttoncoma)))
                            calcula(texto.getText().toString());
                    }
                } else {
                    calculaaux3(texto.getText().toString());
                    texto.setText(text + " ");
                }
            }
        }
    }

    private boolean errors() {
        if (texto.getText().toString().equals("÷ 0")) {
            result.setTextSize(40);
            result.setText("No calculable");
            return true;
        }
        return false;
    }

    private boolean primer(String tu) {
        return (texto.getText().toString().equals(getString(R.string.button0)) && (operacio(tu)));
    }

    private void botoans() {
        if (operacio(texto.getText().toString().substring(0, 1))) {
            texto.setText(texto.getText().toString() + String.valueOf(resultat));
        }
        else texto.setText(String.valueOf(resultat));
        calcula(texto.getText().toString());
    }

    private void resultat() {
        if (portrait) {
            result.setTextColor(Color.parseColor("#4682B4"));
            result.setTextSize(70);
        }
        else {
            result.setTextColor(Color.parseColor("#4682B4"));
            result.setTextSize(35);
        }
        if (!errors()) {
            resultat = Float.parseFloat(result.getText().toString());
            aux3.setText(getString(R.string.buit));
            texto.setText(getString(R.string.button0));
            result.setText(String.valueOf(resultat));
        }
        else {
            result.setText("k ets casper o k?");
            stickynot();
        }
    }

    private void stickynot() {
        int mId = 2;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Has dividit entre 0")
                        .setContentText("Mereixes morir");

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("El meu propietari es subnormal");
        bigTextStyle.bigText(getString(R.string.diventre0));

        mBuilder.setStyle(bigTextStyle);

        Notification noti = mBuilder.build();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            noti.color = Color.RED;
        }

        noti.flags |= Notification.FLAG_INSISTENT;
        noti.flags |= Notification.FLAG_NO_CLEAR;
        noti.flags |= Notification.FLAG_SHOW_LIGHTS;
        mNotificationManager.notify(mId, noti);
    }

    private void calculaaux3(String num) {
        if (aux3.getText().toString().equals(getString(R.string.buit))) {
            aux3.setText(num);
        }
        else {
            aux = num.substring(0, 1);
            num = num.substring(2, num.length());
            switch (aux) {
                case "+":
                    aux3.setText(String.valueOf(Float.parseFloat(num) + Float.parseFloat(aux3.getText().toString())));
                    break;
                case "-":
                    aux3.setText(String.valueOf(Float.parseFloat(aux3.getText().toString()) - Float.parseFloat(num)));
                    break;
                case "*":
                    aux3.setText(String.valueOf(Float.parseFloat(aux3.getText().toString()) * Float.parseFloat(num)));
                    break;
                case "÷":
                    aux3.setText(String.valueOf(Float.parseFloat(aux3.getText().toString()) / Float.parseFloat(num)));
                    break;
            }
        }
    }

    private void calcula(String num) {
        if (aux3.getText().toString().equals("")) result.setText(num);
        else {
            aux = texto.getText().toString().substring(0, 1);
            num = num.substring(2, num.length());
            switch (aux) {
                case "+":
                    result.setText(String.valueOf(Float.parseFloat(aux3.getText().toString()) + Float.parseFloat(num)));
                    break;
                case "-":
                    result.setText(String.valueOf(Float.parseFloat(aux3.getText().toString()) - Float.parseFloat(num)));
                    break;
                case "*":
                    result.setText(String.valueOf(Float.parseFloat(aux3.getText().toString()) * Float.parseFloat(num)));
                    break;
                case "÷":
                    result.setText(String.valueOf(Float.parseFloat(aux3.getText().toString()) / Float.parseFloat(num)));
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        portrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        if (portrait) setContentView(R.layout.activity_calculadora);
        else setContentView(R.layout.activity_calculadora__horizontal);

        _toast = true;
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button0 = (Button) findViewById(R.id.button0);
        buttonANS = (Button) findViewById(R.id.buttonANS);
        buttonresta = (Button) findViewById(R.id.buttonresta);
        buttonsuma = (Button) findViewById(R.id.buttonsuma);
        buttonmult = (Button) findViewById(R.id.buttonmult);
        buttondiv = (Button) findViewById(R.id.buttondiv);
        buttonAC = (Button) findViewById(R.id.buttonAC);
        buttonborra = (Button) findViewById(R.id.buttonborra);
        buttoncoma = (Button) findViewById(R.id.buttoncoma);
        buttonigual = (Button) findViewById(R.id.buttonigual);
        texto = (TextView) findViewById(R.id.texto);
        result = (TextView) findViewById(R.id.result);
        aux3 = (TextView) findViewById(R.id.aux3);
        horizontalScrollView1 = (HorizontalScrollView) findViewById(R.id.horizontalscreenview1);
        horizontalScrollView2 = (HorizontalScrollView) findViewById(R.id.horizontalscreenview2);
        horizontalScrollView3 = (HorizontalScrollView) findViewById(R.id.horizontalscreenview3);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Calculadora");
        setSupportActionBar(toolbar);

        texto.setText(getString(R.string.button0));
        result.setText(getString(R.string.button0) + getString(R.string.buttoncoma) + getString(R.string.button0));
        aux3.setText(getString(R.string.buit));

//        texto.setMovementMethod(new ScrollingMovementMethod());

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonANS.setOnClickListener(this);
        buttonresta.setOnClickListener(this);
        buttonsuma.setOnClickListener(this);
        buttonmult.setOnClickListener(this);
        buttondiv.setOnClickListener(this);
        buttonAC.setOnClickListener(this);
        buttonborra.setOnClickListener(this);
        buttoncoma.setOnClickListener(this);
        buttonigual.setOnClickListener(this);
    }

    @Override
    protected int whatIsMyId() {
        return R.layout.activity_calculadora;
    }

    @Override
    public void onClick(View v) {
        if (portrait) {
            result.setTextSize(50);
            result.setTextColor(Color.parseColor("#000000"));
        }
        else {
            result.setTextSize(30);
            result.setTextColor(Color.parseColor("#000000"));
        }
        horizontalScrollView1.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
        horizontalScrollView2.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
        horizontalScrollView3.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
        switch (v.getId()) {
            case R.id.button0:
                setText(getString(R.string.button0));
                break;
            case R.id.button1:
                setText(getString(R.string.button1));
                break;
            case R.id.button2:
                setText(getString(R.string.button2));
                break;
            case R.id.button3:
                setText(getString(R.string.button3));
                break;
            case R.id.button4:
                setText(getString(R.string.button4));
                break;
            case R.id.button5:
                setText(getString(R.string.button5));
                break;
            case R.id.button6:
                setText(getString(R.string.button6));
                break;
            case R.id.button7:
                setText(getString(R.string.button7));
                break;
            case R.id.button8:
                setText(getString(R.string.button8));
                break;
            case R.id.button9:
                setText(getString(R.string.button9));
                break;
            case R.id.buttonANS:
                botoans();
                break;
            case R.id.buttonresta:
                setText(getString(R.string.buttonresta));
                break;
            case R.id.buttonsuma:
                setText(getString(R.string.buttonsuma));
                break;
            case R.id.buttonmult:
                setText(getString(R.string.buttonmult));
                break;
            case R.id.buttondiv:
                setText(getString(R.string.buttondiv));
                break;
            case R.id.buttonborra:
                if (texto.getText().toString().length() == 1) {
                    if (texto.getText().toString().equals("0")) {
                        if (_toast) toastnoti("No puc borrar un 0 inutil!!");
                        else statenoti("No puc borrar un 0 inutil!!",5);
                    }
                    texto.setText(getString(R.string.button0));
                    result.setText(getString(R.string.button0) + getString(R.string.buttoncoma) + getString(R.string.button0));
                } else if (texto.getText().toString().substring(texto.getText().toString().length() - 1).equals(" ")) {
                    if (_toast) toastnoti("No puc borrar mes :/");
                    else statenoti("No puc borrar mes :/",4);
                }
                else {
                    texto.setText(texto.getText().toString().substring(0, texto.getText().toString().length() - 1));
                    if (!texto.getText().toString().substring(texto.getText().toString().length() - 1).equals(" "))
                        calcula(texto.getText().toString());
                }
                break;
            case R.id.buttonAC:
                if (texto.getText().toString().equals("0") && result.getText().toString().equals("0.0")) {
                    if (_toast) toastnoti("No puc borrar res més caraensaladilla");
                    else statenoti("No puc borrar res més caraensaladilla",3);
                }
                texto.setText(getString(R.string.button0));
                aux3.setText(getString(R.string.buit));
                result.setText(getString(R.string.button0) + getString(R.string.buttoncoma) + getString(R.string.button0));
                break;
            case R.id.buttoncoma:
                setText(getString(R.string.buttoncoma));
                break;
            case R.id.buttonigual:
                resultat();
                break;
            case R.id.item2:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"));
                startActivity(intent);
                break;
        }
    }

    public void trucar(MenuItem item) {
        startActivity(trucar);
    }

    public void navegador(MenuItem item) {
        startActivity(navegador);
    }

    public void booltrue(MenuItem item) {
        _toast = true;
    }

    public void boolfals(MenuItem item) {
        _toast = false;
    }
}