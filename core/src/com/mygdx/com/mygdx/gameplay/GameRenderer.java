package com.mygdx.com.mygdx.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.helpers.ImageManager;
import com.mygdx.helpers.SoundManager;

/**
 * Created by denis on 17.06.18.
 */

public class GameRenderer {
    private GameWorld world;

    SpriteBatch batch;
    private float screenWidth, screenHeight, pxSize;

    private float screenCoefficient;

    public GameRenderer(SpriteBatch b, float sW, float sH, float pxS, int chosenSkin) {
        batch = b;
        screenHeight = sH;
        screenWidth = sW;
        pxSize = pxS;

        screenCoefficient = screenHeight/1920; //--------------------

        world = new GameWorld(sW, sH, pxS,screenCoefficient);

    }


    public boolean update(SoundManager sM) {
        world.update(sM,screenCoefficient);
        return world.bug.isAlive;
    }

    public void restart(){
        world = new GameWorld(screenWidth,screenHeight,pxSize,screenCoefficient);
    }

    public void setWorld(GameWorld world){
        this.world = world;
    }

    public GameWorld getWorld() {
        return world;
    }

    public void drawWorld(float stateTime, ImageManager imageManager){

        drawBackground(imageManager);
        drawObstacles(imageManager,stateTime);
        drawBug(stateTime,imageManager);
        drawCoins(stateTime,imageManager);


    }

    private void drawObstacles(ImageManager imageManager, float stateTime){
        String type;
        int obstacles = world.obstacles.length;
        int lp,rp;

        float bl_x, bl_y, bl_size = pxSize;

        for (int i = 0; i < obstacles; i++) {
            type = world.obstacles[i].t.toString().toLowerCase();
            lp = world.obstacles[i].left_plank.length;
            rp = world.obstacles[i].right_plank.length;
            for (int j = 0; j < lp ; j++) {
                bl_x = world.obstacles[i].left_plank[j].x;
                bl_y = world.obstacles[i].left_plank[j].y;
                batch.draw(imageManager.getObstacle(type),bl_x,bl_y,bl_size,bl_size);
            }

            for (int j = 0; j < rp ; j++) {
                bl_x = world.obstacles[i].right_plank[j].x;
                bl_y = world.obstacles[i].right_plank[j].y;
                batch.draw(imageManager.getObstacle(type),bl_x,bl_y,bl_size,bl_size);
            }

            if  (world.obstacles[i].t == Obstacle.Type.SAW) {
              batch.draw(imageManager.getSawCurrentFrame(stateTime),world.obstacles[i].x,world.obstacles[i].y,bl_size,bl_size);
            }


        }

    }
    private void drawBug(float stateTime, ImageManager imageManager){
        float bug_x = world.bug.x;
        float bug_y = world.bug.y;
        float bug_size = world.bug.size;
        batch.draw(imageManager.getBugBackground(),bug_x,bug_y,bug_size,bug_size);
        batch.draw(imageManager.getBugCurrentFrame(stateTime),bug_x,bug_y,bug_size,bug_size);


    }
    private void drawBackground(ImageManager imageManager){
        String bg1 = world.background.section1.toString().toLowerCase();
        String bg2 = world.background.section2.toString().toLowerCase();

        float bg_x1 = world.background.x1;
        float bg_y1 = world.background.y1;

        float bg_x2 = world.background.x2;
        float bg_y2 = world.background.y2;

        batch.draw(imageManager.getGameplayBackground(bg1), bg_x1,bg_y1,screenWidth,screenHeight);
        batch.draw(imageManager.getGameplayBackground(bg2), bg_x2,bg_y2,screenWidth,screenHeight);
    }
    private void drawCoins(float stateTime, ImageManager imageManager){
        if (world.left.isVisible) {
            float c_x1 = world.left.x;
            float c_y1 = world.left.y;
            batch.draw(imageManager.getCoinCurrentFrame(stateTime), c_x1, c_y1, pxSize, pxSize);
        }

        if (world.right.isVisible) {
            float c_x2 = world.right.x;
            float c_y2 = world.right.y;
            batch.draw(imageManager.getCoinCurrentFrame(stateTime), c_x2, c_y2, pxSize, pxSize);

        }
    }

    public Bug getBug(){
        return world.bug;
    }
    public Obstacle[] getObstacles(){return world.obstacles;}
    public float getShift(){
        return world.shift;
    }
}
