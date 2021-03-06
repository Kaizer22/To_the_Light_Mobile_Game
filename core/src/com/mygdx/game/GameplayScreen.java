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
    public MyButton button_watch_ads;
    public MyButton button_exit;
    public MyButton button_restart;

    private BitmapFont scoreFont;
    private BitmapFont highscoreFont;

    private float stateTime = 0f;

    public boolean isPlaying = true;
    public boolean onPause = false;
    public boolean isDeath = false;

    private boolean mustBeLoaded = false;

    private int chosenSkin;

    String bufString;

    GameplayScreen(ToTheLightGame toTheLightGame){
        game = toTheLightGame;
        camera = new OrthographicCamera();

        if(game.sM.isMusicTurnedON)
            game.sM.playMainTheme();

        chosenSkin = game.gP.loadChosenSkin();
        game.iM.initializeGameplay(chosenSkin);
        GameLogic.highscore = game.gP.loadHighscore();
        GameLogic.isEndlessModeOn = game.gP.loadEndlessModeTrigger();


    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        stateTime += Gdx.graphics.getDeltaTime();


        if (isPlaying){
            if (!renderer.update(game.sM)) {
                isPlaying = false;
                isDeath = true;

               saveHighScore();

                button_restart = new MyButton(pxSize*(float)(3)+pxSize*3,screenHeight/2-pxSize*(float)(0.75),pxSize*3,pxSize*3,game.iM.getButton_RestartUp(),game.iM.getButton_RestartDown());
                button_watch_ads = new MyButton(pxSize*(float)(3),screenHeight/2-pxSize*(float)(0.75),pxSize*3,pxSize*3,game.iM.getButton_WatchAdsUp(),game.iM.getButton_WatchAdsDown());

            }else if (!GameLogic.isEndlessModeOn && GameLogic.score >= 1048580) {
                toWinScreen();
            }
        }


        game.batch.begin();

        if (isPlaying) {
            renderer.drawWorld(stateTime,game.iM);
            drawUI();
        }else if (isDeath){
            renderer.drawWorld(0f,game.iM);
            drawUI();
            drawDeathScreen();

        }else if (onPause){
            renderer.drawWorld(0f,game.iM);
            drawUI();
            drawPauseScreen();
        }

        game.batch.end();

    }

    private void drawUI(){
        game.batch.draw(game.iM.getFrameEffect(),0,0,screenWidth,screenHeight);
        button_music.draw(game.batch);
        button_pause.draw(game.batch);

        bufString = "" + (int)GameLogic.score;
        scoreFont.draw(game.batch,bufString,screenWidth/2-(scoreFont.getCapHeight()* bufString.length()/2),screenHeight - (float)(0.5)*pxSize);

        bufString = "max " + GameLogic.highscore;
        highscoreFont.draw(game.batch,bufString,screenWidth/2-(highscoreFont.getCapHeight()* bufString.length()/2),screenHeight-(float)(1.5)*pxSize);

        bufString = "coin cost: " + GameLogic.scoreFactor/10;
        highscoreFont.draw(game.batch,bufString,screenWidth/2-(highscoreFont.getCapHeight()* bufString.length()/2),screenHeight-(float)(2.25)*pxSize);
    }

    private void drawPauseScreen(){
        game.batch.draw(game.iM.getOnPauseBg(),0,0,screenWidth,screenHeight);

        button_resume.draw(game.batch);
        button_exit.draw(game.batch);
    }

    private void drawDeathScreen(){
        game.batch.draw(game.iM.getOnPauseBg(),0,0,screenWidth,screenHeight);
        game.batch.draw(game.iM.getDeathScreenCurrentFrame(stateTime), pxSize*(float)(0.75),screenHeight/2-pxSize*(float)(3.75),pxSize*(float)(10.5),pxSize*(float)(7.5));

        bufString = "" + (int)GameLogic.score;
        scoreFont.draw(game.batch,bufString,screenWidth/2-(scoreFont.getCapHeight()* bufString.length()/2),screenHeight/2+(float)(3.5)*pxSize);

        bufString = "max " + GameLogic.highscore;
        highscoreFont.draw(game.batch,bufString,screenWidth/2-(highscoreFont.getCapHeight()* bufString.length()/2),screenHeight/2-(float)(1.15)*pxSize);

        button_watch_ads.draw(game.batch);
        button_restart.draw(game.batch);

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


        scoreFont = new BitmapFont(Gdx.files.internal("pixel_font.fnt"));
        scoreFont.getData().setScale(width/300);

        highscoreFont = new BitmapFont(Gdx.files.internal("pixel_font.fnt"));
        highscoreFont.getData().setScale(width/600 + 1);


        button_music = new MyButton(0,screenHeight-pxSize*2,pxSize*2,pxSize*2,game.iM.getButton_MusicOn(),game.iM.getButton_MusicOff());
        button_pause = new MyButton(screenWidth - pxSize*2,screenHeight-pxSize*2,pxSize*2,pxSize*2,game.iM.getButton_PauseUp(),game.iM.getButton_PauseDown());

        button_music.isPressed = !game.sM.isPlaying();



        if (mustBeLoaded){
            renderer.setWorld(game.gP.loadWorld(screenWidth,screenHeight,pxSize));
            mustBeLoaded = false;
        }else{
            renderer = new GameRenderer(game.batch,screenWidth,screenHeight,pxSize,chosenSkin);
        }

        game.iH.setCondition_Gameplay(this);
    }

    @Override
    public void pause() {
        game.gP.saveWorld(renderer.getBug(),renderer.getObstacles(),renderer.getShift());
        game.gP.saveMusicOptions(game.sM.isMusicTurnedON);


    }

    @Override
    public void resume() {
        mustBeLoaded = true;
        game.sM.isMusicTurnedON = game.gP.loadMusicOptions();
        button_music.isPressed = !game.sM.isPlaying();

    }

    @Override
    public void hide() {
        game.gP.saveMusicOptions(game.sM.isMusicTurnedON);
    }

    @Override
    public void dispose(){
        game.gP.saveMusicOptions(game.sM.isMusicTurnedON);
        game.iM.dispose();
        highscoreFont.dispose();
        scoreFont.dispose();
    }

    public void setPause() {
        isPlaying = false;
        onPause = true;
        button_resume = new MyButton(screenWidth/2-pxSize*3,screenHeight/2,pxSize*6,pxSize*6,game.iM.getButton_ResumeUp(),game.iM.getButton_ResumeDown());
        button_exit = new MyButton(screenWidth/2-pxSize*3,screenHeight/2-pxSize*6,pxSize*6,pxSize*6,game.iM.getButton_PauseExitUp(),game.iM.getButton_PauseExitDown());
    }

    public void returnToGame(){
        onPause = false;
        isPlaying = true;
    }

    public boolean turnMusic(){
        if (game.sM.isPlaying()){
            game.sM.stopMainTheme();
            return false;
        }else {
            game.sM.playMainTheme();
            return true;
        }
    }

    public void restartGame(){
        isPlaying = true;
        isDeath = false;

        renderer.restart();
        game.iH.setCondition_Gameplay(this);

    }
    private void saveHighScore(){
        if (GameLogic.highscore < (int)GameLogic.score){
            game.gP.saveHighscore((int)GameLogic.score);
            GameLogic.highscore = (int)GameLogic.score;
        }
    }

    public void toMainMenu(){
        dispose();
        MenuScreen m = new MenuScreen(game);
        game.setScreen(m);
    }

    public void toWinScreen(){
        game.gP.saveEndlessModeTrigger(true);
        saveHighScore();
        isPlaying = false;
        dispose();
        WinScreen w = new WinScreen(game,chosenSkin);
        game.setScreen(w);
    }
}
