package com.mygdx.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by denis on 16.06.18.
 */

public class SoundManager {
    private Music main_theme;
    public  boolean isMusicTurnedON = false;
    private Sound picked_coin;

    public SoundManager(){
        main_theme = Gdx.audio.newMusic(Gdx.files.internal("sound/main_theme.mp3"));
        main_theme.setLooping(true);
        picked_coin =  Gdx.audio.newSound(Gdx.files.internal("sound/pickup_coin.mp3"));
    }

    public void playMainTheme() {
            main_theme.play();
            isMusicTurnedON = true;
    }

    public void stopMainTheme() {
        main_theme.stop();
        isMusicTurnedON = false;
    }

    public void playCoinSound() {
        picked_coin.play();
    }

    public  boolean isPlaying(){
        return main_theme.isPlaying();
    }

    public void dispose(){
        main_theme.dispose();
        picked_coin.dispose();
    }
}
