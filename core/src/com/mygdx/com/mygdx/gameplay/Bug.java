package com.mygdx.com.mygdx.gameplay;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by denis on 17.06.18.
 */

public class Bug {
    float x,y;
    float size;
    public Rectangle collision;
    public Rectangle input_borders;

    public boolean isAlive;

    public Bug(float x, float y, float size){
        this.x = x;
        this.y = y;
        this.size = size;
        input_borders = new Rectangle(x,y,size,size);
    }

    public void setPosition(float x, float y){
        this.x = x-size/2;
        this.y = y-size/2;
        input_borders.setPosition(x-size/2,y-size/2);
        //collision.setPosition(x,y);
    }
}
