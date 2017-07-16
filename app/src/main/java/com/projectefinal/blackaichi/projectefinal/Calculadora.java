package com.projectefinal.blackaichi.projectefinal;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
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

import com.projectefinal.blackaichi.projectefinal.Data.Vars;
import com.projectefinal.blackaichi.projectefinal.LogReg.Login;

public class Calculadora extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "main";
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    Button buttonANS, buttonresta, buttonsuma, buttonmult, buttondiv, buttonAC, buttoncoma, buttonigual, buttonborra;
    Button plusmem1, minusmem1, plusmem2, minusmem2, plusmem3, minusmem3, showmem1, showmem2, showmem3, delmem;
    TextView texto, result, aux3;
    String control;
    String aux;
    Double resultat = 0d, mem1 = 0d, mem2 = 0d, mem3 = 0d;
    Toast toast;
    HorizontalScrollView horizontalScrollView1, horizontalScrollView2, horizontalScrollView3;
    Toolbar toolbar;
    boolean _toast, portrait;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item11:
                _toast = true;
                break;
            case R.id.item12:
                _toast = false;
                break;
            case R.id.item2:
                Intent trucar;
                if (texto.getText().toString().equals("0"))
                    trucar = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"));
                else
                    trucar = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + texto.getText().toString()));
                startActivity(trucar);
                break;
            case R.id.item3:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.cat/"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.android.chrome");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    intent.setPackage(null);
                    startActivity(intent);
                }
                break;
            case R.id.logout:
                Vars.loged = false;
                Vars.thisuser = "";
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.v(TAG, "Se ha llamado onSaveInstanceState");
        outState.putDouble("resultado", resultat);
        outState.putDouble("mem1", mem1);
        outState.putDouble("mem2", mem2);
        outState.putDouble("mem3", mem3);
        outState.putBoolean("toast", _toast);
        outState.putString("texto", texto.getText().toString());
        outState.putString("resultat", result.getText().toString());
        outState.putString("aux", aux3.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.v(TAG, "Se ha llamado onRestore");
        if (savedInstanceState != null) {
            resultat = savedInstanceState.getDouble("resultado");
            _toast = savedInstanceState.getBoolean("toast");
            mem1 = savedInstanceState.getDouble("mem1");
            mem2 = savedInstanceState.getDouble("mem2");
            mem3 = savedInstanceState.getDouble("mem3");
            texto.setText(savedInstanceState.getString("texto"));
            result.setText(savedInstanceState.getString("resultat"));
            aux3.setText(savedInstanceState.getString("aux"));
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
        getMenuInflater().inflate(R.menu.menu_right, menu);
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
            ultim = aux.substring(aux.length() - n, aux.length() - n + 1);
        }
        return true;
    }

    private boolean operacio_repetida(String text) {
        return operacio(control) && operacio(text) || (texto.getText().toString().length() == 2 && operacio(texto.getText().toString().substring(0, 1))) && operacio(text);
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
            } else if (operacio_repetida(text)) texto.setText(text + " ");
        }
        if (texto.getText().toString().equals(".")) texto.setText("0.");
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

    private void memoria(int n) {
        String s;
        switch (n) {
            case 1:
                if (operacio(texto.getText().toString().substring(0, 1))) {
                    texto.setText(texto.getText().toString() + String.valueOf(resultat));
                } else {
                    s = String.valueOf(resultat);
                    if (s.substring(s.length() - 2).equals(".0"))
                        s = s.substring(0, s.length() - 2);
                    texto.setText(s);
                }
                calcula(texto.getText().toString());
                break;
            case 2:
                if (operacio(texto.getText().toString().substring(0, 1))) {
                    texto.setText(texto.getText().toString() + String.valueOf(mem1));
                } else {
                    s = String.valueOf(mem1);
                    Log.v(TAG, s);
                    if (s.substring(s.length() - 2).equals(".0"))
                        s = s.substring(0, s.length() - 2);
                    Log.v(TAG, s);
                    texto.setText(s);
                }
                calcula(texto.getText().toString());
                break;
            case 3:
                if (operacio(texto.getText().toString().substring(0, 1))) {
                    texto.setText(texto.getText().toString() + String.valueOf(mem2));
                } else {
                    s = String.valueOf(mem2);
                    if (s.substring(s.length() - 2).equals(".0"))
                        s = s.substring(0, s.length() - 2);
                    texto.setText(s);
                }
                calcula(texto.getText().toString());
                break;
            case 4:
                if (operacio(texto.getText().toString().substring(0, 1))) {
                    texto.setText(texto.getText().toString() + String.valueOf(mem3));
                } else {
                    s = String.valueOf(mem3);
                    if (s.substring(s.length() - 2).equals(".0"))
                        s = s.substring(0, s.length() - 2);
                    texto.setText(s);
                }
                calcula(texto.getText().toString());
                break;
        }
    }

    private void resultat() {
        String s;
        if (portrait) {
            result.setTextColor(Color.parseColor("#4682B4"));
            result.setTextSize(70);
        } else {
            result.setTextColor(Color.parseColor("#4682B4"));
            result.setTextSize(35);
        }
        if (!errors()) {
            resultat = Double.parseDouble(result.getText().toString());
            aux3.setText(getString(R.string.buit));
            texto.setText(getString(R.string.button0));
            s = String.valueOf(resultat);
            if (s.substring(s.length() - 2).equals(".0")) s = s.substring(0, s.length() - 2);
            result.setText(s);
        } else {
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
        } else {
            aux = num.substring(0, 1);
            num = num.substring(2, num.length());
            switch (aux) {
                case "+":
                    aux3.setText(String.valueOf(Double.parseDouble(num) + Double.parseDouble(aux3.getText().toString())));
                    break;
                case "-":
                    aux3.setText(String.valueOf(Double.parseDouble(aux3.getText().toString()) - Double.parseDouble(num)));
                    break;
                case "*":
                    aux3.setText(String.valueOf(Double.parseDouble(aux3.getText().toString()) * Double.parseDouble(num)));
                    break;
                case "÷":
                    aux3.setText(String.valueOf(Double.parseDouble(aux3.getText().toString()) / Double.parseDouble(num)));
                    break;
            }
        }
    }

    private void calcula(String num) {
        String s;
        if (aux3.getText().toString().equals("")) result.setText(num);
        else {
            aux = texto.getText().toString().substring(0, 1);
            num = num.substring(2, num.length());
            switch (aux) {
                case "+":
                    s = String.valueOf(Double.parseDouble(aux3.getText().toString()) + Double.parseDouble(num));
                    if (s.substring(s.length() - 2).equals(".0"))
                        s = s.substring(0, s.length() - 2);
                    result.setText(s);
                    break;
                case "-":
                    s = String.valueOf(Double.parseDouble(aux3.getText().toString()) - Double.parseDouble(num));
                    if (s.substring(s.length() - 2).equals(".0"))
                        s = s.substring(0, s.length() - 2);
                    result.setText(s);
                    break;
                case "*":
                    s = String.valueOf(Double.parseDouble(aux3.getText().toString()) * Double.parseDouble(num));
                    if (s.substring(s.length() - 2).equals(".0"))
                        s = s.substring(0, s.length() - 2);
                    result.setText(s);
                    break;
                case "÷":
                    s = String.valueOf(Double.parseDouble(aux3.getText().toString()) / Double.parseDouble(num));
                    if (s.substring(s.length() - 2).equals(".0"))
                        s = s.substring(0, s.length() - 2);
                    result.setText(s);
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
        /*toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Calculadora");
        setSupportActionBar(toolbar);*/
        if (!portrait) {
            plusmem1 = (Button) findViewById(R.id.plusmem1);
            plusmem2 = (Button) findViewById(R.id.plusmem2);
            minusmem1 = (Button) findViewById(R.id.minusmem1);
            minusmem2 = (Button) findViewById(R.id.minusmem2);
            delmem = (Button) findViewById(R.id.resetmem);
            plusmem3 = (Button) findViewById(R.id.plusmem3);
            minusmem3 = (Button) findViewById(R.id.minusmem3);
            showmem1 = (Button) findViewById(R.id.showm1);
            showmem2 = (Button) findViewById(R.id.showm2);
            showmem3 = (Button) findViewById(R.id.showm3);
            plusmem1.setOnClickListener(this);
            plusmem2.setOnClickListener(this);
            minusmem1.setOnClickListener(this);
            minusmem2.setOnClickListener(this);
            delmem.setOnClickListener(this);
            plusmem3.setOnClickListener(this);
            minusmem3.setOnClickListener(this);
            showmem1.setOnClickListener(this);
            showmem2.setOnClickListener(this);
            showmem3.setOnClickListener(this);
        }

        texto.setText(getString(R.string.button0));
        result.setText(getString(R.string.button0));
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
        return R.id.activity1;
    }

    @Override
    public void onClick(View v) {
        if (portrait) {
            result.setTextSize(50);
            result.setTextColor(Color.parseColor("#000000"));
        } else {
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
                memoria(1);
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
                        else statenoti("No puc borrar un 0 inutil!!", 5);
                    }
                    texto.setText(getString(R.string.button0));
                    result.setText(getString(R.string.button0));
                } else if (texto.getText().toString().substring(texto.getText().toString().length() - 1).equals(" ")) {
                    if (_toast) toastnoti("No puc borrar mes :/");
                    else statenoti("No puc borrar mes :/", 4);
                } else {
                    texto.setText(texto.getText().toString().substring(0, texto.getText().toString().length() - 1));
                    if (!texto.getText().toString().substring(texto.getText().toString().length() - 1).equals(" "))
                        calcula(texto.getText().toString());
                }
                break;
            case R.id.buttonAC:
                if (texto.getText().toString().equals("0") && result.getText().toString().equals("0")) {
                    if (_toast) toastnoti("No puc borrar res més caraensaladilla");
                    else statenoti("No puc borrar res més caraensaladilla", 3);
                }
                texto.setText(getString(R.string.button0));
                aux3.setText(getString(R.string.buit));
                result.setText(getString(R.string.button0));
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
        if (!portrait) {
            Log.v(TAG, "notport");
            switch (v.getId()) {
                case R.id.plusmem1:
                    Log.v(TAG, "+m1");
                    Log.v(TAG, result.getText().toString());
                    mem1 = Double.parseDouble(result.getText().toString());
                    Log.v(TAG, String.valueOf(mem1));
                    break;
                case R.id.plusmem2:
                    Log.v(TAG, "+m2");
                    mem2 = Double.parseDouble(result.getText().toString());
                    break;
                case R.id.plusmem3:
                    Log.v(TAG, "+m3");
                    mem3 = Double.parseDouble(result.getText().toString());
                    break;
                case R.id.minusmem1:
                    Log.v(TAG, "-m1");
                    mem1 = 0d;
                    break;
                case R.id.minusmem2:
                    Log.v(TAG, "-m2");
                    mem2 = 0d;
                    break;
                case R.id.minusmem3:
                    Log.v(TAG, "-m3");
                    mem3 = 0d;
                    break;
                case R.id.resetmem:
                    Log.v(TAG, "reset");
                    mem1 = mem2 = mem3 = 0d;
                    break;
                case R.id.showm1:
                    Log.v(TAG, "M1");
                    memoria(2);
                    break;
                case R.id.showm2:
                    Log.v(TAG, "M2");
                    memoria(3);
                    break;
                case R.id.showm3:
                    Log.v(TAG, "M3");
                    memoria(4);
                    break;
            }
        }
    }
}