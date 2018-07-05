package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.helpers.MyButton;

import java.util.Random;

/**
 * Created by denis on 16.06.18.
 */

public class MenuScreen implements Screen {
    final ToTheLightGame game;

    float screenWidth, screenHeight, pxSize, effect_x,effect_y;
    Random r; //// TODO: 02.07.18  подготовить анимацию эффекта, вместо рандомной тряски

    private OrthographicCamera camera;
    public MyButton button_play;
    public MyButton button_shop;
    public MyButton button_exit;
    public MyButton button_music;

    private float stateTime = 0f;



    public MenuScreen(ToTheLightGame toTheLightGame) {

        game = toTheLightGame;
        camera = new OrthographicCamera();
        r = new Random();

        game.iH.setCondition_Menu(this);
        game.iM.initializeMenu();

        Gdx.input.setInputProcessor(game.iH);
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

        stateTime += Gdx.graphics.getDeltaTime();



        game.batch.begin();

            drawMenu();

        game.batch.end();
    }

    private void drawMenu(){
        game.batch.draw(game.iM.getMenuBgCurrentFrame(stateTime),0,0,screenWidth,screenHeight);
        game.batch.draw(game.iM.getMenuBgEffectCurrentFrame(stateTime),pxSize*3,screenWidth/2-pxSize*5, pxSize*6,pxSize*6);
        game.batch.draw(game.iM.getLogo(),(float)(pxSize*2.5),pxSize*13,pxSize*7,(float)(pxSize*3.5));


        button_exit.draw(game.batch);
        button_play.draw(game.batch);
        button_music.draw(game.batch);
        button_shop.draw(game.batch);


    }



    public float getScreenHeight() {
        return screenHeight;
    }

    public float getScreenWidth() {
        return screenWidth;
    }

    public void exit(){
        dispose();
        game.dispose();
    }
    public void startGame(){
        dispose();
        GameplayScreen g = new GameplayScreen(game);
        game.setScreen(g);
    }
    public void openShop(){
        dispose();
        ShopScreen s = new ShopScreen(game);
        game.setScreen(s);
    }
    public void turnMusic(){}

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false,width,height);
        screenHeight = height;
        screenWidth = width;
        pxSize = width/12;
        button_exit = new MyButton((float)(screenWidth/2+pxSize*1.5),pxSize*6,(float)(pxSize*2.5),(float)(pxSize*2.5),game.iM.getButton_ExitUp(),game.iM.getButton_ExitDown());
        button_music = new MyButton(0,screenHeight-pxSize*2,pxSize*2,pxSize*2,game.iM.getButton_MusicOn(),game.iM.getButton_MusicOff());
        button_play = new MyButton((screenWidth/2-pxSize*2),(float)(pxSize*8.5) ,pxSize*4,pxSize*4,game.iM.getButton_PlayUp(),game.iM.getButton_PlayDown());
        button_shop = new MyButton((float)(screenWidth/2-pxSize*4.5),(float)(pxSize*5.5),pxSize*3,pxSize*3,game.iM.getButton_ShopUp(),game.iM.getButton_ShopDown());





    }

    @Override
    public void dispose() {
        game.iM.dispose();
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





}
