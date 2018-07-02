package com.mygdx.helpers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;



/**
 * Created by denis on 16.06.18.
 */

public class MyButton {
    float x,y,w,h;
    Rectangle  bounds;
    TextureRegion buttonUp;
    TextureRegion buttonDown;
    boolean isPressed = false;



    public  MyButton(float x, float y, float width, float height,TextureRegion up,TextureRegion down){
        this.x = x;
        this.y = y;
        w = width;
        h = height;
        bounds = new Rectangle(x,y,w,h);
        buttonUp = up;
        buttonDown = down;

    }

    public boolean isClicked(int screenX, int screenY) {
        return bounds.contains(screenX, screenY);
    }

    public void draw(SpriteBatch batch) {
        if (isPressed) {
            batch.draw(buttonDown, x, y, w, h);
        } else {
            batch.draw(buttonUp, x, y, w, h);

        }


    }

    public boolean isTouchDown(int screenX, int screenY) {

        if (bounds.contains(screenX, screenY)) {
            isPressed = true;
            return true;
        }

        return false;
    }

    public boolean isTouchUp(int screenX, int screenY) {

        // Мы будем учитывать только touchUp в нажатом состоянии.
        if (bounds.contains(screenX, screenY) && isPressed) {
            isPressed = false;
            return true;
        }
        // Когда палец с кнопки уберут, мы очистим флаг, что кнопка нажатая.
        isPressed = false;
        return false;
    }


}
