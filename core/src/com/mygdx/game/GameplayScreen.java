package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by denis on 17.06.18.
 */

public class GameplayScreen implements Screen {

    final ToTheLightGame game;

    float screenWidth, screenHeight, pxSize;

    private OrthographicCamera camera;

    public GameplayScreen (ToTheLightGame toTheLightGame){
        game = toTheLightGame;
        camera = new OrthographicCamera();

        game.iH.setCondition_Gameplay(this);
        game.iM.initializeGameplay();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);


        game.batch.begin();
        //временно
        game.batch.draw(game.iM.getStone_Wall(),0,0,screenWidth,screenHeight);
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false,width,height);
        screenHeight = height;
        screenWidth = width;
        pxSize = width/12;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.iM.dispose();
    }
}
