package com.mygdx.com.mygdx.gameplay;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.helpers.ImageManager;

/**
 * Created by denis on 17.06.18.
 */

public class GameRenderer {
    private GameWorld world;
    ImageManager imageManager;
    SpriteBatch batch;
    float screenWidth, screenHeight, pxSize;

    public GameRenderer(ImageManager iM, SpriteBatch b, float sW, float sH, float pxS){
        imageManager = iM;
        batch = b;
        screenHeight = sH;
        screenWidth = sW;
        pxSize = pxS;

        world = new GameWorld(sW,sH,pxS);

    }




    public void drawWorld(float stateTime){
        String bg1 = world.background.section1.toString().toLowerCase();
        String bg2 = world.background.section2.toString().toLowerCase();

        float bg_x1 = world.background.x1;
        float bg_y1 = world.background.y1;

        float bg_x2 = world.background.x2;
        float bg_y2 = world.background.y2;

        batch.draw(imageManager.getGameplayBackground(bg1), bg_x1,bg_y1,screenWidth,screenHeight);
        batch.draw(imageManager.getGameplayBackground(bg2), bg_x2,bg_y2,screenWidth,screenHeight);

        float bug_x = world.bug.x;
        float bug_y = world.bug.y;
        float bug_size = world.bug.size;
        batch.draw(imageManager.getBugCurrentFrame(stateTime),bug_x,bug_y,bug_size,bug_size);
    }
}
