package com.projectefinal.blackaichi.projectefinal;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Reproductor extends BaseActivity implements View.OnClickListener {

    MediaPlayer m;
    Button a, b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);

        //Sento que el reproductor sigui tant cutre pero no he tingut gaire temps...
        m = new MediaPlayer();
        m = MediaPlayer.create(this, R.raw.musica);

        a = (Button) findViewById(R.id.play);
        b = (Button) findViewById(R.id.pause);

        a.setOnClickListener(this);
        b.setOnClickListener(this);
    }

    @Override
    protected int whatIsMyId() {
        return R.id.activity3;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play:
                m.start();
                break;
            case R.id.pause:
                m.pause();
                break;
        }
    }
}
