package com.mygdx.com.mygdx.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.helpers.ImageManager;

/**
 * Created by denis on 17.06.18.
 */

public class GameRenderer {
    private GameWorld world;

    SpriteBatch batch;
    float screenWidth, screenHeight, pxSize;

    public GameRenderer(SpriteBatch b, float sW, float sH, float pxS) {
        batch = b;
        screenHeight = sH;
        screenWidth = sW;
        pxSize = pxS;

        world = new GameWorld(sW, sH, pxS);

    }


    public boolean update() {
        world.update();
        return world.bug.isAlive;
    }

    public void restart(){
        world = new GameWorld(screenWidth,screenHeight,pxSize);
    }

    public void drawWorld(float stateTime, ImageManager imageManager){

        drawBackground(imageManager);
        drawBug(stateTime,imageManager);
        drawObstacles(imageManager);

    }

    private void drawObstacles(ImageManager imageManager){
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

            //TODO delete this
            //batch.draw(imageManager.getColl(),world.obstacles[i].lp_collision.x,world.obstacles[i].lp_collision.y,world.obstacles[i].lp_collision.width,world.obstacles[i].lp_collision.height);
            //batch.draw(imageManager.getColl(),world.obstacles[i].rp_collision.x,world.obstacles[i].rp_collision.y,world.obstacles[i].rp_collision.width,world.obstacles[i].rp_collision.height);
            //--
        }

    }
    private void drawBug(float stateTime, ImageManager imageManager){
        float bug_x = world.bug.x;
        float bug_y = world.bug.y;
        float bug_size = world.bug.size;
        batch.draw(imageManager.getBugBackground(),bug_x,bug_y,bug_size,bug_size);
        batch.draw(imageManager.getBugCurrentFrame(stateTime),bug_x,bug_y,bug_size,bug_size);


        //TODO del this
        //batch.draw(imageManager.getBColl(),world.bug.collision.x,world.bug.collision.y,world.bug.collision.width,world.bug.collision.height);

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

    public Bug getBug(){
        return world.bug;
    }
}
