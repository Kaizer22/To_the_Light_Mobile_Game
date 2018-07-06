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

    public Background(float x, float y, float screenHeight){
        x1 = x;
        y1 = y;

        x2 = x1;
        y2 = y+screenHeight;

        r = new Random();

        random = r.nextInt(1);                //TODO добавить больше фоновых изображаений

        switch (random){
            case (0):
                section1 = Type.STONE_WALL;
        }

        random = r.nextInt(1);

        switch (random){
            case (0):
                section2 = Type.STONE_WALL;
        }




    }

    enum Type{
        STONE_WALL, BRICKS, DARKNESS, TEMPLE_WALL, MINE_WALL, UNDERGROUND_CITY, SKY;
    }
}
