package com.mygdx.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.com.mygdx.gameplay.GameWorld;


/**
 * Created by denis on 16.07.18.
 */

public class GamePreferences {
    Preferences preferences;

    public GamePreferences(){
        preferences = Gdx.app.getPreferences("ToTheLight");
    }

    public void saveWorld(){}
    public GameWorld loadWorld(){
        return null;
    }
    
    public void clearPreferences(){
        preferences.clear();
        preferences.flush();
    }
    
    public void saveHighscore(int score){
        preferences.putInteger("highscore",score);
        preferences.flush();
    }
    public int loadHighscore(){
        return preferences.getInteger("highscore");
    }
}
