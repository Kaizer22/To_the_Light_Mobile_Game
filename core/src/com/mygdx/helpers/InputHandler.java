package com.mygdx.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.mygdx.com.mygdx.gameplay.Bug;
import com.mygdx.game.GameplayScreen;
import com.mygdx.game.MenuScreen;
import com.mygdx.game.ShopScreen;

/**
 * Created by denis on 16.06.18.
 */

public class InputHandler implements InputProcessor {  // разные точки отсчета при рисовании и при обработке ввода
    private Condition c;
    private MenuScreen m;
    private ShopScreen s;
    private GameplayScreen g;

    private Bug iBug;
    private boolean isMusicButtonPressed; //костыль


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int bufY;
        switch (c){
            case IN_MAIN_MENU:
            {
                bufY = (int)(m.getScreenHeight()-screenY);
                if (m.button_play.isTouchDown(screenX,bufY)) {
                }else if (m.button_shop.isTouchDown(screenX,bufY)){
                }else if (m.button_exit.isTouchDown(screenX,bufY)){
                }else if (m.button_music.isTouchDown(screenX,bufY)){
                    isMusicButtonPressed = true;
                }

                break;
            }
            case IN_SHOP:
            {
                bufY = (int)(s.getScreenHeight()-screenY);
                if (s.button_exitToMenu.isTouchDown(screenX,bufY)){}
                else if (s.button_music.isTouchDown(screenX,bufY)){
                    isMusicButtonPressed = true;
                }else {
                    for (int i = 0; i < s.yMax; i++) {
                        for (int j = 0; j < s.xMax; j++) {
                            if (s.skins[i][j].inputBorders.contains(screenX, bufY))
                                s.skins[i][j].isClicked = true;
                        }
                    }
                }
                break;
            }
            case IN_GAME:
            {
                bufY = (int)(g.getScreenHeight()-screenY);

                if (g.isPlaying) {
                    if (g.button_pause.isTouchDown(screenX, bufY)) {}
                    else if (g.button_music.isTouchDown(screenX, bufY)) {
                        isMusicButtonPressed = true;
                    }

                }else if(g.isDeath){
                    if (g.button_watch_ads.isTouchDown(screenX,bufY)){}
                    else if (g.button_restart.isTouchDown(screenX,bufY)){}

                }else if (g.onPause){
                    if (g.button_resume.isTouchDown(screenX, bufY)) {}
                    else if (g.button_exit.isTouchDown(screenX, bufY)){}
                }
            }
        }



        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        int bufY;
        switch (c){
            case IN_MAIN_MENU:
            {
                bufY = (int)(m.getScreenHeight()-screenY);
                if (m.button_play.isTouchUp(screenX,bufY)) {
                    m.startGame();
                }else if (m.button_shop.isTouchUp(screenX,bufY)){
                    m.openShop();
                }else if (m.button_exit.isTouchUp(screenX,bufY)) {
                    m.exit();
                }else if (isMusicButtonPressed && m.button_music.isTouchUp(screenX,bufY)){
                    if (m.turnMusic()){
                        m.button_music.isPressed = false;
                    }else{
                        m.button_music.isPressed = true;
                    }
                }
                isMusicButtonPressed = false;
                break;


            }
            case IN_SHOP:
            {
                bufY = (int)(m.getScreenHeight()-screenY);
                if (s.button_exitToMenu.isTouchUp(screenX,bufY)) {
                    s.toMainMenu();
                }else if (isMusicButtonPressed && s.button_music.isTouchUp(screenX,bufY)){
                    if (s.turnMusic()){
                        s.button_music.isPressed = false;
                    }else{
                        s.button_music.isPressed = true;
                    }
                } else {
                    for (int i = 0; i < s.yMax; i++) {
                        for (int j = 0; j < s.xMax; j++) {
                            if (s.skins[i][j].isClicked && s.skins[i][j].inputBorders.contains(screenX,bufY))
                                s.chooseSkin(j,i);
                            s.skins[i][j].isClicked = false;
                        }
                    }

            }
                isMusicButtonPressed = false;
                break;
            }
            case IN_GAME:
            {
                bufY = (int) (g.getScreenHeight() - screenY);
                if (g.isPlaying) {
                    if (g.button_pause.isTouchUp(screenX, bufY)) {
                        g.setPause();
                    }else if (isMusicButtonPressed && g.button_music.isTouchDown(screenX,bufY)){
                        if (g.turnMusic()){
                            g.button_music.isPressed = false;
                        }else{
                            g.button_music.isPressed = true;
                        }
                    }
                    isMusicButtonPressed = false;
                }else if(g.isDeath){
                    if (g.button_watch_ads.isTouchUp(screenX,bufY)){

                    }else if (g.button_restart.isTouchUp(screenX,bufY)){
                        g.restartGame();
                    }
                }else if(g.onPause){
                    if (g.button_resume.isTouchUp(screenX,bufY)){
                        g.returnToGame();
                    }else if (g.button_exit.isTouchUp(screenX,bufY)){
                        g.toMainMenu();
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        int bufY;
        switch (c){
            case IN_MAIN_MENU:
            {}
            case IN_SHOP:
            {break;}
            case IN_GAME:
            {

                if (g.isPlaying){
                    bufY = (int) (g.getScreenHeight()-screenY);
                    if (iBug.input_borders.contains(screenX,bufY)){
                        iBug.setPosition(screenX,bufY);
                    }
                }

            }
        }

        return false;
    }


    @Override
    public boolean scrolled(int amount) {
        return false;
    }


    public void setCondition_Menu(MenuScreen menu_screen){
        c = Condition.IN_MAIN_MENU;
        m = menu_screen;
    }

    public void setCondition_Shop(ShopScreen shop_screen){
        c = Condition.IN_SHOP;
        s = shop_screen;
    }

    public void setCondition_Gameplay(GameplayScreen gameplay_screen){
        c = Condition.IN_GAME;
        g = gameplay_screen;
        iBug =  g.renderer.getBug();
    }

    private enum Condition{
        IN_MAIN_MENU, IN_GAME, IN_SHOP;
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }
    @Override
    public boolean keyTyped(char character) {
        return false;
    }

}
