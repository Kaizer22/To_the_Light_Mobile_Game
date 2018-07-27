package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.mygdx.com.mygdx.gameplay.GameLogic;
import com.mygdx.helpers.MyButton;

/**
 * Created by denis on 17.06.18.
 */

public class ShopScreen implements Screen {
    final ToTheLightGame game;

    private float screenWidth;
    private float screenHeight;
    private float pxSize;

    private OrthographicCamera camera;

    public MyButton button_exitToMenu;
    public MyButton button_music;

    private float stateTime = 0f;

    public Skin[][] skins;
    public float skinsX;
    public float skinsY;
    public float iconSize;
    public int yMax = 5;
    public int xMax = 3;

    private int chosenSkin;


    public ShopScreen(ToTheLightGame toTheLightGame){
        game = toTheLightGame;
        camera = new OrthographicCamera();


        game.iH.setCondition_Shop(this);
        game.iM.initializeShop();
        chosenSkin = game.gP.loadChosenSkin();
        GameLogic.highscore = game.gP.loadHighscore();

    }

    private void prepareSkins(){
        skins = new Skin[yMax][xMax];
        int basicCost = 64;
        boolean bufIsOpened;
        int bufN = 1;
        for (int i = 0; i < yMax ; i++) {
            for (int j = 0; j < xMax ; j++) {
                bufIsOpened = GameLogic.highscore >= basicCost;
                skins[i][j] = new Skin(bufIsOpened,bufN,skinsX + iconSize* j,skinsY - iconSize * i,iconSize);
                if (chosenSkin == skins[i][j].number)
                    skins[i][j].isChosen = true;
                basicCost *= 2;
                bufN++;
            }
        }

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

        drawShop();

        game.batch.end();
    }

    private void drawShop(){
        game.batch.draw(game.iM.getMenuBgCurrentFrame(stateTime),0,0,screenWidth,screenHeight);
        button_music.draw(game.batch);
        button_exitToMenu.draw(game.batch);

        drawSkins();
    }

    private void drawSkins(){
        for (int i = 0; i < yMax; i++) {
            for (int j = 0; j < xMax; j++) {
                if (skins[i][j].isOpened) {
                    game.batch.draw(game.iM.getSkinBg(true), skinsX + iconSize* j,skinsY - iconSize * i,iconSize,iconSize);
                    if (skins[i][j].isChosen)
                        game.batch.draw(game.iM.getChosenSkinFrame(), skinsX + iconSize* j,skinsY - iconSize * i,iconSize,iconSize);
                    game.batch.draw(game.iM.getSkinIcon(skins[i][j].number),skinsX + iconSize* j,skinsY - iconSize * i,iconSize,iconSize);
                } else {
                    game.batch.draw(game.iM.getSkinBg(false), skinsX + iconSize* j,skinsY - iconSize * i,iconSize,iconSize);
                    game.batch.draw(game.iM.getSkinLock(), skinsX + iconSize* j,skinsY - iconSize * i,iconSize,iconSize);
                }
            }
        }
    }

    public void toMainMenu(){
        dispose();
        MenuScreen m = new MenuScreen(game);
        game.setScreen(m);
    }

    public void chooseSkin(int x,int y){
        if (skins[y][x].isOpened){
            int lastY = chosenSkin/xMax+(chosenSkin%xMax ==0?-1:0);
            int lastX = (chosenSkin - lastY*xMax)%xMax;
            skins[chosenSkin/xMax+(chosenSkin%xMax ==0?-1:0)][(chosenSkin%xMax==0)?xMax-1:(chosenSkin%xMax-1)].isChosen = false;
            skins[y][x].isChosen = true;
            chosenSkin = skins[y][x].number;
            game.gP.saveChosenSkin(chosenSkin);


        }

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

    public float getScreenHeight() {
        return screenHeight;
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false,width,height);

        screenHeight = height;
        screenWidth = width;
        pxSize = width/12;

        iconSize = pxSize*3;

        skinsX = pxSize * (float) (1.5);
        skinsY = screenHeight - (pxSize * 5);
        prepareSkins();

        button_music = new MyButton(0,screenHeight-pxSize*2,pxSize*2,pxSize*2,game.iM.getButton_MusicOn(),game.iM.getButton_MusicOff());
        button_exitToMenu = new MyButton((float)(screenWidth/2+pxSize*3.5),pxSize*(float)(0.5),(float)(pxSize*2.5),(float)(pxSize*2.5),game.iM.getButton_ExitUp(),game.iM.getButton_ExitDown());

        button_music.isPressed = !game.sM.isPlaying();
    }

    @Override
    public void pause() {
        game.gP.saveMusicOptions(game.sM.isMusicTurnedON);
    }

    @Override
    public void resume() {
        game.sM.isMusicTurnedON = game.gP.loadMusicOptions();
        button_music.isPressed = !game.sM.isPlaying();
    }

    @Override
    public void hide() {
        game.gP.saveMusicOptions(game.sM.isMusicTurnedON);
    }

    @Override
    public void dispose() {
        game.gP.saveMusicOptions(game.sM.isMusicTurnedON);
        game.iM.dispose();
    }
}
