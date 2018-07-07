package com.mygdx.com.mygdx.gameplay;

import java.util.Random;

/**
 * Created by denis on 17.06.18.
 */

public class Background {
    float x1,y1;
    float x2,y2;

    Random r; int random;

    Type section1;
    Type section2;

    float height;

    public Background(float x, float y, float screenHeight){
        x1 = x;
        y1 = y;

        height = screenHeight;

        x2 = x1;
        y2 = y + height;



        r = new Random(); //TODO добавить больше фоновых изображаений

        section1 = getRandomType();
        section2 = getRandomType();




    }

    private Type getRandomType(){
        random = r.nextInt(1);

        switch (random){
            case (0):
                return Type.STONE_WALL;
        }

        return Type.STONE_WALL;
    }

    public void update(float shift){
        y1 -= shift;
        y2 -= shift;

        if ((y1<-height) || (y2<-height)){
            if (y1<-height){
                section1 = getRandomType();
                y1 = height;
            }
            if (y2<-height){
                section2 = getRandomType();
                y2 = height;
            }
        }
    }


    enum Type{
        STONE_WALL, BRICKS, DARKNESS, TEMPLE_WALL, MINE_WALL, UNDERGROUND_CITY, SKY;
    }
}
