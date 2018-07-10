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

    boolean canMoveRight;
    boolean canMoveLeft;
    boolean canMoveUp;
    boolean canMoveDown;

    public Bug(float x, float y, float size){
        isAlive = true;
        this.x = x;
        this.y = y;
        this.size = size;
        input_borders = new Rectangle(x,y,size,size);
        collision = new Rectangle(x + size/4,y + size/4,size/2,size/2);
    }

    public void setPosition(float x, float y){
        if (this.y<y && canMoveUp){
            this.y = y-size/2;
        }else if (this.y>y && canMoveDown){
            this.y = y-size/2;
        }

        if (this.x>x && canMoveLeft){
            this.x = x-size/2;
        }else if (this.x<x && canMoveRight){
            this.x = x-size/2;
        }

        input_borders.setPosition(this.x,this.y);
        collision.setPosition(this.x,this.y);
    }

    public void setY(float y){
        this.y = y;
        input_borders.setPosition(x,this.y);
        collision.setPosition(x,this.y);

    }

    public void update(){
        canMoveRight = true;
        canMoveLeft = true;
        canMoveUp = true;
        canMoveDown = true;
    }
}
