package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.com.mygdx.gameplay.GameLogic;
import com.mygdx.com.mygdx.gameplay.GameRenderer;
import com.mygdx.helpers.MyButton;

/**
 * Created by denis on 17.06.18.
 */

public class GameplayScreen implements Screen {

    final ToTheLightGame game;
    public GameRenderer renderer;

    float screenWidth, screenHeight, pxSize;

    private OrthographicCamera camera;

    public MyButton button_music;
    public MyButton button_pause;
    public MyButton button_resume;
    public MyButton button_continue;
    public MyButton button_exit;

    BitmapFont pixelFont;

    private float stateTime = 0f;

    public boolean isPlaying = true;
    public boolean onPause = false;
    public boolean isDeath = false;

    public GameplayScreen (ToTheLightGame toTheLightGame){
        game = toTheLightGame;
        camera = new OrthographicCamera();




        game.iM.initializeGameplay();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        stateTime += Gdx.graphics.getDeltaTime();


        if (isPlaying){
            if (!renderer.update()) {
                isPlaying = false;
                isDeath = true;
            }
        }


        game.batch.begin();

        if (isPlaying) {
            renderer.drawWorld(stateTime);
            drawUI();
        }else if (isDeath){
            renderer.drawWorld(0f);
            drawUI();
            drawPauseScreen();

        }else if (onPause){
            renderer.drawWorld(0f);
            drawUI();
            drawPauseScreen();
        }

        game.batch.end();

    }

    private void drawUI(){
        game.batch.draw(game.iM.getFrameEffect(),0,0,screenWidth,screenHeight);
        button_music.draw(game.batch);
        button_pause.draw(game.batch);
        pixelFont.draw(game.batch,""+(int)GameLogic.score,screenWidth/2-pxSize*2,screenHeight - pxSize);
    }

    private void drawPauseScreen(){
        game.batch.draw(game.iM.getOnPauseBg(),0,0,screenWidth,screenHeight);
    }

    private void drawDeathScreen(){
        game.batch.draw(game.iM.getOnPauseBg(),0,0,screenWidth,screenHeight);
    }

    public float getScreenHeight() {
        return screenHeight;
    }

    public float getScreenWidth() {
        return screenWidth;
    }
    @Override
    public void resize(int width, int height){
        camera.setToOrtho(false,width,height);

        screenHeight = height;
        screenWidth = width;
        pxSize = width/12;

        pixelFont = new BitmapFont(Gdx.files.internal("pixel_font.fnt"));
        pixelFont.getData().setScale(width/300);

        button_music = new MyButton(0,screenHeight-pxSize*2,pxSize*2,pxSize*2,game.iM.getButton_MusicOn(),game.iM.getButton_MusicOff());
        button_pause = new MyButton(screenWidth - pxSize*2,screenHeight-pxSize*2,pxSize*2,pxSize*2,game.iM.getButton_PauseUp(),game.iM.getButton_PauseDown());

        renderer = new GameRenderer(game.iM,game.batch,screenWidth,screenHeight,pxSize);
        game.iH.setCondition_Gameplay(this);
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

    public void setPause() {
        isPlaying = false;
        onPause = true;
    }
}
