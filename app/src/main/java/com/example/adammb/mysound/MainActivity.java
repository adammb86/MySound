package com.example.adammb.mysound;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSound;
    Button btnMedia;
    Button btnMediaStop;
    MediaPlayer mediaPlayer;

    SoundPool sp;
    int wav;
    boolean spLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSound = (Button) findViewById(R.id.btn_soundpool);
        btnSound.setOnClickListener(this);

        btnMedia=(Button)findViewById(R.id.btn_mediaplayer);
        btnMedia.setOnClickListener(this);
        btnMediaStop=(Button)findViewById(R.id.btn_mediaplayer_stop);
        btnMediaStop.setOnClickListener(this);
        mediaPlayer=MediaPlayer.create(this,R.raw.guitar_background);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sp = new SoundPool.Builder()
                    .setMaxStreams(10)
                    .build();
        } else {
            sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 1);
        }

        sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                spLoaded = true;
            }
        });

        wav = sp.load(this, R.raw.clinking_glasses, 1);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btn_soundpool:
                if (spLoaded == true) {
                    sp.play(wav, 1, 1, 0, 0, 1);
                }
                break;

            case R.id.btn_mediaplayer:
                mediaPlayer.start();
                break;

            case R.id.btn_mediaplayer_stop:
                mediaPlayer.stop();
                break;

            default:
                break;
        }
    }
}
