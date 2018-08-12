package com.mygdx.game;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.VideoView;



public class SplashActivity  extends Activity implements MediaPlayer.OnCompletionListener, View.OnTouchListener {
    VideoView vv;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        String fileName = "android.resource://"+ getPackageName() +"/" + R.raw.intro_vertical;


        vv = (VideoView) this.findViewById(R.id.surface);
        vv.setVideoURI(Uri.parse(fileName));
        vv.setOnCompletionListener(this);
        //vv.resolveAdjustedSize()
        vv.start();

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        closeSplash();
    }

    private void closeSplash(){
         Intent intent = new Intent(this, AndroidLauncher.class);
         startActivity(intent);
         finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        vv.stopPlayback();
        closeSplash();
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
