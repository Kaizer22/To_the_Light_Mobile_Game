package com.mygdx.com.mygdx.gameplay;

/**
 * Created by denis on 17.06.18.
 */

public class GameWorld {
    Bug bug;
    Background background;
    Obstacle[] obstacles;
    float shift;

    public GameWorld(float screenWidth, float screenHeight,float pxSize){
        bug = new Bug(screenWidth/2,screenHeight/2,pxSize*4);
        background = new Background(0,0,screenHeight);
        shift = 3; //3
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
            
        }
        bug.update();
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
