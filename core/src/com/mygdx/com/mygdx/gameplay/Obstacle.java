package com.mygdx.com.mygdx.gameplay;

import com.badlogic.gdx.math.Rectangle;

import java.util.Random;



/**
 * Created by denis on 17.06.18.
 */

public class Obstacle {

    float x,y;

    Rectangle[] left_plank;
    Rectangle[] right_plank;

    Rectangle lp_collision;
    Rectangle rp_collision;

    Random r;

    float blockSize;

    float width, height;

    Type t;


    public Obstacle(float screenWidth, float screenHeight,float x_pos, float y_pos){
        x = x_pos;
        y = y_pos;

        blockSize = screenWidth /12;
        height = screenHeight;
        width = screenWidth;

        r = new Random();

        int lp = r.nextInt(8)+1;
        int rp = r.nextInt(10-lp)+1;
        left_plank = new Rectangle[lp];
        right_plank = new Rectangle[rp];

        for (int i = 0; i < lp; i++) {
            left_plank[i] = new Rectangle(blockSize*i,y,blockSize,blockSize);
        }
        for (int i = 0; i < rp; i++) {
            right_plank[i] = new Rectangle(width-rp*blockSize+i*blockSize,y,blockSize,blockSize);
        }
        lp_collision = new Rectangle(0,y,lp*blockSize, blockSize);
        rp_collision = new Rectangle(width-rp*blockSize,y,rp*blockSize,blockSize);
        switch (r.nextInt(1)){
            case 0:
                t = Type.WOOD;
                break;
            case 1:
                t = Type.STONE;
                break;
            case 2:
                t = Type.SHARP;
        }
    }



    public void update(float shift){
        y -= shift;


        if (y < -blockSize*2){ //Коэффициент 2 для того, чтобы препятствие могло сдвинуть жука до смертельной отметки ( полное исчезновение с экрана )
            y = blockSize*29;

            switch (r.nextInt(1)){  //TODO добавить больше типов препятствий
                case 0:
                    t = Type.WOOD;
                    break;
                case 1:
                    t = Type.STONE;
                    break;
                case 2:
                    t = Type.SHARP;
            }

            int lp = r.nextInt(8)+1;
            int rp = r.nextInt(10-lp)+1;
            left_plank = new Rectangle[lp];
            right_plank = new Rectangle[rp];

            for (int i = 0; i < lp; i++) {
                left_plank[i] = new Rectangle(blockSize*i,y,blockSize,blockSize);
            }
            for (int i = 0; i < rp; i++) {
                right_plank[i] = new Rectangle(width-rp*blockSize+i*blockSize,y,blockSize,blockSize);
            }
            lp_collision = new Rectangle(0,y,lp*blockSize, blockSize);
            rp_collision = new Rectangle(width-rp*blockSize,y,rp*blockSize,blockSize);

        }else{
            for (int i = 0; i < left_plank.length; i++) {
                left_plank[i].y = y;
            }
            for (int i = 0; i < right_plank.length; i++) {
                right_plank[i].y = y;
            }
            rp_collision.y -=shift;
            lp_collision.y -=shift;
        }
    }

    enum Type {
        WOOD, STONE, SHARP;
    }
}
