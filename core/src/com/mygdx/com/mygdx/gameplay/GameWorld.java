package com.mygdx.com.mygdx.gameplay;

/**
 * Created by denis on 17.06.18.
 */

public class GameWorld {
    Bug bug;
    Background background;
    Obstacle[] obstacles;

    public GameWorld(float screenWidth, float screenHeight,float pxSize){
        bug = new Bug(screenWidth/2,0,pxSize*4);
        background = new Background(0,0,screenHeight);
    }

    public void update(){}
}
