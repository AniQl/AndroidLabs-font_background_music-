package com.maciej.hw1fixed;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer backgroundPlayer;
    private MediaPlayer buttonPlayer;
    static public Uri[] sounds;
    public int isPlaying = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sounds = new Uri[3];
        sounds[0] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.marmur);
        sounds[1] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.grubo);
        sounds[2] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.zyrandol);
        buttonPlayer = new MediaPlayer();
        buttonPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        buttonPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                backgroundPlayer.pause();
                mp.start();
            }
        });
        buttonPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                backgroundPlayer.reset();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hello there", Snackbar.LENGTH_LONG)
                        .setAction("onResume", null).show();
//                backgroundPlayer.pause();
                if (isPlaying == 0) {
                    backgroundPlayer.start();
                    isPlaying = 1;

                }
                else {
                    backgroundPlayer.pause();
                    isPlaying = 0;

                }
            }
        });

        Button buttonChangeBackground = findViewById(R.id.button1);
        Button buttonChangeButtons = findViewById(R.id.button2);
        Button buttonChangeFont = findViewById(R.id.button3);

        buttonChangeBackground.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent buttonintent;
                buttonintent = new Intent(getApplicationContext(), ChildActivity.class);
                startActivityForResult(buttonintent, 1);

            }
        });

        buttonChangeButtons.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent buttonintent;
                buttonintent = new Intent(getApplicationContext(), ChildActivity.class);
                startActivityForResult(buttonintent, 2);

            }
        });
        buttonChangeFont.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent buttonintent;
                buttonintent = new Intent(getApplicationContext(), ChildActivity.class);
                startActivityForResult(buttonintent, 3);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        backgroundPlayer.pause();
        buttonPlayer.pause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        isPlaying = 1;
        backgroundPlayer = MediaPlayer.create(this, R.raw.marmur);
        backgroundPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mp.start();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        backgroundPlayer.release();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String color = data.getStringExtra("color");


            switch (requestCode) {
                case 1:
                    (findViewById(R.id.Background)).setBackgroundColor(Color.parseColor(color));
                    break;
                case 2:
                    (findViewById(R.id.button1)).setBackgroundColor(Color.parseColor(color));
                    (findViewById(R.id.button2)).setBackgroundColor(Color.parseColor(color));
                    (findViewById(R.id.button3)).setBackgroundColor(Color.parseColor(color));
                    break;
                case 3:
                    ((Button) (findViewById(R.id.button1))).setTextColor(Color.parseColor(color));
                    ((Button) (findViewById(R.id.button2))).setTextColor(Color.parseColor(color));
                    ((Button) (findViewById(R.id.button3))).setTextColor(Color.parseColor(color));
                    break;
            }
        } else if (resultCode == RESULT_CANCELED) ;

    }

}
