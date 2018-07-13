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

    boolean canMoveRight = true;
    boolean canMoveLeft = true;
    boolean canMoveUp = true;
    boolean canMoveDown = true;

    public Bug(float x, float y, float size){
        isAlive = true;
        this.x = x;
        this.y = y;
        this.size = size;
        input_borders = new Rectangle(x,y,size,size);
        collision = new Rectangle(x + size/3,y + size/4,size/(float)(2.7),size/(float)(2.2)); //Цифры получены экспериментальным путем
    }

    public void setPosition(float x, float y){
        //System.out.println("Bug pos" + this.x + "^" + this.y + " Input " + x + "^" + y);
        if (this.y+size/2>y && canMoveDown){
            this.y = y-size/2;
        }else if (this.y+ size/2<y && canMoveUp){
        this.y = y-size/2;
        }

        if (this.x+size/2>x && canMoveLeft){
            this.x = x-size/2;
        }else if (this.x+size/2 < x && canMoveRight){
            this.x = x-size/2;
        }

        input_borders.setPosition(this.x,this.y);
        collision.setPosition(this.x + size/(float)(3),this.y + size/4);
    }

    public void setY(float y){
        this.y = y;
        input_borders.setPosition(x,this.y);
        collision.setPosition(x + size/(float)(3),this.y + size/4);

    }

    public void update(){
        canMoveRight = true;
        canMoveLeft = true;
        canMoveUp = true;
        canMoveDown = true;
    }
}
