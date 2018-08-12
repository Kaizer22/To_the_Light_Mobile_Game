package com.mygdx.com.mygdx.gameplay;

import com.mygdx.helpers.SoundManager;

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

    public GameWorld(float screenWidth, float screenHeight,float pxSize,float screenCoeff){
        r = new Random();

        bug = new Bug(screenWidth/2,screenHeight/2,pxSize*4);
        background = new Background(0,0,screenHeight);
        shift = 3*screenCoeff; //3
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

    public GameWorld(float bugX, float bugY, float currentShift,Obstacle[] obstacles, float screenWidth, float screenHeight, float pxSize){
        bug = new Bug(bugX,bugY,pxSize*4);
        shift = currentShift;
        this.obstacles = obstacles;
        background = new Background(0,0,screenHeight);
        r = new Random();
        left = new Coin(0,0,pxSize);
        right = new Coin(screenWidth-pxSize,0,pxSize);
    }

    public void update(SoundManager sM, float screenCoeff){
        shift = GameLogic.calculateShift(shift, screenCoeff);
        background.update(shift);
        for (Obstacle obstacle : obstacles) {
            obstacle.update(shift);

            if (obstacles[0].y == obstacles[0].blockSize * 24) {

                if (!left.isVisible) {
                    left.isVisible = r.nextBoolean();
                    left.setCost(GameLogic.scoreFactor / 15);
                    left.y = obstacles[0].blockSize * 25;
                    left.collision.y = obstacles[0].blockSize * 25;
                }

                if (!right.isVisible) {
                    right.isVisible = r.nextBoolean();
                    right.setCost(GameLogic.scoreFactor / 15);
                    right.y = obstacles[0].blockSize * 25;
                    right.collision.y = obstacles[0].blockSize * 25;
                }
            }

        }
        bug.update();
        if (left.isVisible) {
            left.update(shift);
            if  (GameLogic.collides(left,bug))
                if (sM.isMusicTurnedON)
                    sM.playCoinSound();
        }
        if (right.isVisible) {
            right.update(shift);
            if(GameLogic.collides(right,bug))
                if (sM.isMusicTurnedON)
                    sM.playCoinSound();
        }
        GameLogic.checkCollisions(obstacles, bug);



        if (!bug.canMoveUp) {
            bug.setY(bug.y - shift);
            if (bug.y < -bug.size) {
                bug.isAlive = false;
            }
        }
       GameLogic.updateScore(shift,screenCoeff);

    }


}
