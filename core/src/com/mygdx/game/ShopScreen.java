package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by denis on 17.06.18.
 */

public class ShopScreen implements Screen {
    final ToTheLightGame game;

    float screenWidth, screenHeight, pxSize;

    private OrthographicCamera camera;



    public ShopScreen(ToTheLightGame toTheLightGame){
        game = toTheLightGame;
        camera = new OrthographicCamera();


        game.iH.setCondition_Shop(this);
        Gdx.input.setInputProcessor(game.iH);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false,width,height);
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

    }
}
