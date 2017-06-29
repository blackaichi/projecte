package com.projectefinal.blackaichi.projectefinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "main";
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    Button buttonmodul, buttonresta, buttonsuma, buttonmult, buttondiv, buttonAC, buttoncoma, buttonigual, buttonborra;
    TextView texto, result, aux1, aux2, aux3;
    String control;
    String aux;
    int resultat;

    private boolean operacio(String caracter) {
        return caracter.equals(getString(R.string.buttoncoma)) || caracter.equals(getString(R.string.buttonmodul)) || caracter.equals(getString(R.string.buttonresta)) || caracter.equals(getString(R.string.buttonsuma)) || caracter.equals(getString(R.string.buttonmult)) || caracter.equals(getString(R.string.buttondiv));
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
        return (operacio(control) && operacio(text));
    }

    private void setText(String text) {
        aux = texto.getText().toString();
        control = aux.substring(aux.length() - 1);
        if (!primer(text)) {
            if (aux.equals("0")) texto.setText("");
            if (!operacio_repetida(text) && !doble_decimal(text)) {
                if (!operacio(text)) {
                    texto.setText(texto.getText().toString() + text);
                    if (!text.equals(getString(R.string.buttoncoma))) calcula();
                }
                else {
                    aux1.setText(aux2.getText().toString());
                    aux2.setText(aux3.getText().toString());
                    aux3.setText(texto.getText().toString());
                    texto.setText(text + " ");
                }
            }
        }
    }

    private boolean primer(String tu) {
        return (texto.getText().toString().equals(getString(R.string.button0)) && operacio(tu));
    }

    private void calcula() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

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
        buttonmodul = (Button) findViewById(R.id.buttonmodul);
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
        aux1 = (TextView) findViewById(R.id.aux1);
        aux2 = (TextView) findViewById(R.id.aux2);
        aux3 = (TextView) findViewById(R.id.aux3);

        texto.setText(getString(R.string.button0));
        result.setText(getString(R.string.buttonigual));
        aux1.setText(getString(R.string.buit));
        aux2.setText(getString(R.string.buit));
        aux3.setText(getString(R.string.buit));

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
        buttonmodul.setOnClickListener(this);
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
    public void onClick(View v) {
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
            case R.id.buttonmodul:
                setText(getString(R.string.buttonmodul));
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
                    if (!operacio(texto.getText().toString())) texto.setText(getString(R.string.button0));
                    else {

                    }
                }
                else texto.setText(texto.getText().toString().substring(0, texto.getText().toString().length() - 1));
                break;
            case R.id.buttonAC:
                texto.setText(getString(R.string.button0));
                aux1.setText(getString(R.string.buit));
                aux2.setText(getString(R.string.buit));
                aux3.setText(getString(R.string.buit));
                break;
            case R.id.buttoncoma:
                setText(getString(R.string.buttoncoma));
                break;
            case R.id.buttonigual:
                calcula();
                break;
        }
    }
}
