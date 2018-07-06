package com.mygdx.com.mygdx.gameplay;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by denis on 17.06.18.
 */

public class Bug {
    float x,y;
    float size;
    Rectangle collision;
    Rectangle input_borders;

    public Bug(float x, float y, float size){
        this.x = x;
        this.y = y;
        this.size = size;
        input_borders = new Rectangle(x,y,size,size);
    }
}
