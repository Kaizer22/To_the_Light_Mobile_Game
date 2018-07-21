package com.mygdx.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.com.mygdx.gameplay.Bug;
import com.mygdx.com.mygdx.gameplay.GameLogic;
import com.mygdx.com.mygdx.gameplay.GameWorld;
import com.mygdx.com.mygdx.gameplay.Obstacle;


/**
 * Created by denis on 16.07.18.
 */

public class GamePreferences {
    Preferences preferences;

    public GamePreferences(){
        preferences = Gdx.app.getPreferences("ToTheLight");
    }

    public void saveWorld(Bug bug, Obstacle[] obstacles, float currentShift){
        preferences.putFloat("currentScore", (int)GameLogic.score);
        preferences.putInteger("currentScoreFactor", GameLogic.scoreFactor);
        preferences.putFloat("currentShift", currentShift);
        preferences.putFloat("bug_x",bug.getX());
        preferences.putFloat("bug_y",bug.getY());
        for (int i = 0; i < 5; i++) {  //5 - константа, число препятствий на экране (и за его пределами)
            preferences.putFloat("obstacle_"+ i +"_pos_x",obstacles[i].getX());
            preferences.putFloat("obstacle_"+ i +"_pos_y",obstacles[i].getY());
            preferences.putString("obstacle_"+ i +"_type",obstacles[i].getType());
            preferences.putInteger("obstacle_"+ i +"_lp",obstacles[i].getLp());
            preferences.putInteger("obstacle_"+ i +"_rp",obstacles[i].getRp());
            System.out.println(obstacles[i].getType());
        }
        preferences.flush();
    }
    public GameWorld loadWorld(float screenWidth, float screenHeight, float pxSize){
        GameLogic.score = preferences.getFloat("currentScore");
        GameLogic.scoreFactor = preferences.getInteger("currentScoreFactor");
        float shift = preferences.getFloat("currentShift");
        float bugX = preferences.getFloat("bug_x");
        float bugY = preferences.getFloat("bug_y");
        Obstacle[] obstacles = new Obstacle[5];
        float o_x ;
        float o_y ;
        String type;
        int lp;
        int rp;

        for (int i = 0; i < 5 ; i++) {
            o_x = preferences.getFloat("obstacle_"+ i +"_pos_x");
            o_y = preferences.getFloat("obstacle_"+ i +"_pos_y");
            type = preferences.getString("obstacle_"+ i +"_type");
            System.out.println("_______________________________________________________________"+type);
            lp = preferences.getInteger("obstacle_"+ i +"_lp");
            rp = preferences.getInteger("obstacle_"+ i +"_rp");
            obstacles[i] = new Obstacle(o_x,o_y,type.toUpperCase(),lp,rp,pxSize,screenWidth);
        }
        return new GameWorld(bugX,bugY,shift,obstacles,screenHeight,pxSize) ;
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
