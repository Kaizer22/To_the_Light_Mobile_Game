package com.mygdx.com.mygdx.gameplay;

import java.util.Random;

/**
 * Created by denis on 17.06.18.
 */

public class GameWorld {
    Bug bug;
    Background background;
    Obstacle[] obstacles;
    Coin left;
    Coin right;
    float shift;
    Random r;

    public GameWorld(float screenWidth, float screenHeight,float pxSize){
        r = new Random();

        bug = new Bug(screenWidth/2,screenHeight/2,pxSize*4);
        background = new Background(0,0,screenHeight);
        shift = 3; //3
        left = new Coin(0,0,pxSize);
        right = new Coin(screenWidth-pxSize,0,pxSize);
        obstacles = new Obstacle[5];
        obstacles[0] = new Obstacle(screenWidth,screenHeight,0,pxSize*4);
        for (int i = 1; i < obstacles.length; i++) {
            obstacles[i] = new Obstacle(screenWidth,screenHeight,0,obstacles[i-1].y+pxSize*5);

        }

        GameLogic.score = 1;
        GameLogic.scoreFactor = 10;
    }

    public GameWorld(float bugX, float bugY, float currentShift,Obstacle[] obstacles, float screenHeight,float pxSize){
        bug = new Bug(bugX,bugY,pxSize*4);
        shift = currentShift;
        this.obstacles = obstacles;
        background = new Background(0,0,screenHeight);
    }

    public void update(){
        shift = GameLogic.calculateShift(shift);
        background.update(shift);
        for (int i = 0; i < obstacles.length; i++) {
            obstacles[i].update(shift);

            if (obstacles[0].y == obstacles[0].blockSize*24){

                if (!left.isVisible){
                    left.isVisible = r.nextBoolean();
                    left.setCost(GameLogic.scoreFactor/10);
                    left.y = obstacles[0].blockSize*25;
                    left.collision.y = obstacles[0].blockSize*(float)25.5;
                }

                if (!right.isVisible){
                    right.isVisible = r.nextBoolean();
                    right.setCost(GameLogic.scoreFactor/2);
                    right.y = obstacles[0].blockSize*25;
                    right.collision.y = obstacles[0].blockSize*(float)25.5;
                }
            }
            
        }
        bug.update();
        if (left.isVisible) {
            left.update(shift);
            GameLogic.collides(left,bug);
        }
        if (right.isVisible) {
            right.update(shift);
            GameLogic.collides(right,bug);
        }
        GameLogic.checkCollisions(obstacles, bug);



        if (!bug.canMoveUp) {
            bug.setY(bug.y - shift);
            if (bug.y < -bug.size) {
                bug.isAlive = false;
            }
        }
       GameLogic.updateScore(shift);
    }


}
