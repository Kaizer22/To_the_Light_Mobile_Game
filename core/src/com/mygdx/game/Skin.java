package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by denis on 24.07.18.
 */

public class Skin {
    boolean isOpened;
    int number;
    boolean isChosen;
    public boolean isClicked;
    public Rectangle inputBorders;

    public Skin(boolean isOpened,int number,float x, float y, float size){
        this.isOpened = isOpened;
        this.number = number;
        inputBorders = new Rectangle(x,y,size,size);
    }
}
