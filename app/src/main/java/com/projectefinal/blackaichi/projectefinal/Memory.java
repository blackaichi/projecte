package com.projectefinal.blackaichi.projectefinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.projectefinal.blackaichi.projectefinal.Data.Ranking_memory;
import com.projectefinal.blackaichi.projectefinal.Data.Vars;
import com.projectefinal.blackaichi.projectefinal.LogReg.Login;

import java.util.ArrayList;
import java.util.Random;

public class Memory extends BaseActivity {

    private static final String TAG = "memory";
    CoolImageFlipper flipper;
    Drawable bb8, c3po, luke, obiwan, r2d2, rey, vader, yoda, baixa;
    boolean [] carta = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
    boolean [] block = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    int [] img = {R.drawable.bb8, R.drawable.c3po, R.drawable.luke, R.drawable.obiwan, R.drawable.r2d2, R.drawable.rey, R.drawable.vader, R.drawable.yoda};
    int [] assig = new int[16];
    int volta = 0, nmov = 0;
    TextView moviments;
    View aux;
    private boolean blocked = false;
    ProgressDialog dialog;
    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    Ranking_memory ranking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        flipper = new CoolImageFlipper(this);
        moviments = (TextView) findViewById(R.id.moviments);

        bb8 = getResources().getDrawable(R.drawable.bb8);
        c3po = getResources().getDrawable(R.drawable.c3po);
        luke = getResources().getDrawable(R.drawable.luke);
        obiwan = getResources().getDrawable(R.drawable.obiwan);
        r2d2 = getResources().getDrawable(R.drawable.r2d2);
        rey = getResources().getDrawable(R.drawable.rey);
        vader = getResources().getDrawable(R.drawable.vader);
        yoda = getResources().getDrawable(R.drawable.yoda);
        baixa = getResources().getDrawable(R.drawable.baixa);
        ranking = new Ranking_memory(getApplicationContext());
        haslorandom();

    }

    private void haslorandom() {
        boolean [] mirat = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
        int [] quant = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Random rand = new Random();
        for (int i = 0; i < 16; ++i) {
            int ran = Math.abs(rand.nextInt() % 8);
            if (!mirat[i] && quant[ran] < 2) {
                assig[i] = img[ran];
                mirat[i] = true;
                quant[ran]++;
            }
            else --i;
        }
    }


    @Override
    protected int whatIsMyId() {
        return R.id.activity2;
    }

    public void fliper1(View view) throws InterruptedException {
        if (carta[0] && !block[0] && !blocked){
            flipper.flipImage(getDrawable(assig[0]), ((ImageView) view));
            volta++;
            carta[0] = !carta[0];
            quehaser(view);
        }
    }

    public void fliper2(View view) throws InterruptedException {
        if (carta[1] && !block[1] && !blocked) {
            flipper.flipImage(getDrawable(assig[1]), ((ImageView) view));
            volta++;
            carta[1] = !carta[1];
            quehaser(view);
        }
    }

    public void fliper3(View view) throws InterruptedException {
        if (carta[2] && !block[2] && !blocked) {
            flipper.flipImage(getDrawable(assig[2]), ((ImageView) view));
            volta++;
            carta[2] = !carta[2];
            quehaser(view);
        }
    }

    public void fliper4(View view) throws InterruptedException {
        if (carta[3] && !block[3] && !blocked) {
            flipper.flipImage(getDrawable(assig[3]), ((ImageView) view));
            volta++;
            carta[3] = !carta[3];
            quehaser(view);
        }
    }

    public void fliper5(View view) throws InterruptedException {
        if (carta[4] && !block[4] && !blocked) {
            flipper.flipImage(getDrawable(assig[4]), ((ImageView) view));
            volta++;
            carta[4] = !carta[4];
            quehaser(view);
        }
    }

    public void fliper6(View view) throws InterruptedException {
        if (carta[5] && !block[5] && !blocked) {
            flipper.flipImage(getDrawable(assig[5]), ((ImageView) view));
            volta++;
            carta[5] = !carta[5];
            quehaser(view);
        }
    }

    public void fliper7(View view) throws InterruptedException {
        if (carta[6] && !block[6] && !blocked) {
            flipper.flipImage(getDrawable(assig[6]), ((ImageView) view));
            volta++;
            carta[6] = !carta[6];
            quehaser(view);
        }
    }

    public void fliper8(View view) throws InterruptedException {
        if (carta[7] && !block[7] && !blocked) {
            flipper.flipImage(getDrawable(assig[7]), ((ImageView) view));
            volta++;
            carta[7] = !carta[7];
            quehaser(view);
        }
    }

    public void fliper9(View view) throws InterruptedException {
        if (carta[8] && !block[8] && !blocked) {
            flipper.flipImage(getDrawable(assig[8]), ((ImageView) view));
            volta++;
            carta[8] = !carta[8];
            quehaser(view);
        }
    }

    public void fliper10(View view) throws InterruptedException {
        if (carta[9] && !block[9] && !blocked) {
            flipper.flipImage(getDrawable(assig[9]), ((ImageView) view));
            volta++;
            carta[9] = !carta[9];
            quehaser(view);
        }
    }

    public void fliper11(View view) throws InterruptedException {
        if (carta[10] && !block[10] && !blocked) {
            flipper.flipImage(getDrawable(assig[10]), ((ImageView) view));
            volta++;
            carta[10] = !carta[10];
            quehaser(view);
        }
    }

    public void fliper12(View view) throws InterruptedException {
        if (carta[11] && !block[11] && !blocked) {
            flipper.flipImage(getDrawable(assig[11]), ((ImageView) view));
            volta++;
            carta[11] = !carta[11];
            quehaser(view);
        }
    }

    public void fliper13(View view) throws InterruptedException {
        if (carta[12] && !block[12] && !blocked) {
            flipper.flipImage(getDrawable(assig[12]), ((ImageView) view));
            volta++;
            carta[12] = !carta[12];
            quehaser(view);
        }
    }

    public void fliper14(View view) throws InterruptedException {
        if (carta[13] && !block[13] && !blocked) {
            flipper.flipImage(getDrawable(assig[13]), ((ImageView) view));
            volta++;
            carta[13] = !carta[13];
            quehaser(view);
        }
    }

    public void fliper15(View view) throws InterruptedException {
        if (carta[14] && !block[14] && !blocked) {
            flipper.flipImage(getDrawable(assig[14]), ((ImageView) view));
            volta++;
            carta[14] = !carta[14];
            quehaser(view);
        }
    }

    public void fliper16(View view) throws InterruptedException {
        if (carta[15] && !block[15] && !blocked) {
            flipper.flipImage(getDrawable(assig[15]), ((ImageView) view));
            volta++;
            carta[15] = !carta[15];
            quehaser(view);
        }
    }

    private void quehaser(final View view) throws InterruptedException {
        int[] parella = new int[2];
        if (volta == 2) {
            nmov++;
            moviments.setText(getString(R.string.moviments) + String.valueOf(nmov));
            if (nmov > 8) moviments.setTextColor(Color.parseColor("#FF0000"));
            volta = 0;
            for (int i = 0, j = 0; i < 16; ++i) {
                if (!carta[i] && !block[i]) {
                    parella[j] = i;
                    ++j;
                }
            }
            carta[0] = carta[1] = carta[2] = carta[3] = carta[4] = carta[5] = carta[6] = carta[7] = carta[8] = carta[9] = carta[10] = carta[11] = carta[12] = carta[13] = carta[14] = carta[15] = true;
            if (assig[parella[0]] == assig[parella[1]]) {
                block[parella[0]] = true;
                block[parella[1]] = true;
            } else {
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        flipper.flipImage(baixa, (ImageView) view);
                        flipper.flipImage(baixa, (ImageView) aux);
                        blocked = false;
                    }
                };
                blocked = true;
                Handler h = new Handler();
                h.removeCallbacks(runnable);
                h.postDelayed(runnable, 2000);
            }
        } else {
            aux = view;
        }
        if (acabat()) {
            dialog = new ProgressDialog(Memory.this);
            dialog.setTitle("Enhorabona!!");
            dialog.setMessage("Has acabat el memory en " + String.valueOf(nmov) + " moviments!");
            dialog.show();
            Log.v(TAG, "abans create puntuacio");
            ranking.createpuntuacio(nmov);
            Log.v(TAG, "dsps create puntuacio");
        }
    }


    private boolean acabat() {
        for (int i = 0; i < 16; ++i) {
            if (!block[i]) return false;
        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_reinicia, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.reinicia:
                i = new Intent(getApplicationContext(), Memory.class);
                startActivity(i);
                overridePendingTransition(0, 0);
                finish();
                break;
            case R.id.logout:
                Vars.loged = false;
                Vars.thisuser = "";
                i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
                finish();
                break;
            case R.id.ranking:
                Log.v(TAG, "obrire el ranking");
                i = new Intent(getApplicationContext(), show_ranking.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
