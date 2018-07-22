package com.mygdx.com.mygdx.gameplay;

import com.badlogic.gdx.math.Circle;

/**
 * Created by denis on 17.06.18.
 */

public class Coin {
    float x;
    float y;
    float size;
    Circle collision;
    boolean isVisible;

    private int cost;

    public Coin(float x, float y, float pxSize){
        this.x = x;
        this.y = y;
        size = pxSize;
        collision = new Circle(x+size/2,y+size/2,size/2);
        isVisible = false;
    }

    public int getCost(){
        return cost;
    }

    public void setCost(int newCost){
        cost = newCost;
    }
    public void update(float shift){
        y -= shift;
        collision.y -= shift;
        if (y<-size){
            isVisible = false;
        }
    }
}
