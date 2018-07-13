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
    Condition c;
    MenuScreen m;
    ShopScreen s;
    GameplayScreen g;

    Bug iBug;
    boolean isBugClicked = false;

    public void InputHandler(Screen screen){}

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int bufY;
        switch (c){
            case IN_MAIN_MENU:
            {
                bufY = (int)(m.getScreenHeight()-screenY);
                if (m.button_play.isTouchDown(screenX,bufY)) {
                }else if (m.button_shop.isTouchDown(screenX,bufY)){
                }else if (m.button_exit.isTouchDown(screenX,bufY)) {}

                break;
            }
            case IN_SHOP:
            {
                break;
            }
            case IN_GAME:
            {
                bufY = (int)(g.getScreenHeight()-screenY);
                if (iBug.input_borders.contains(screenX,bufY)){
                    isBugClicked = true;
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
                }
                break;


            }
            case IN_SHOP:
            {
                break;
            }
            case IN_GAME:
            {
                isBugClicked = false;
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
                int bufX = Gdx.input.getX(pointer);

                bufY = (int)(g.getScreenHeight()-Gdx.input.getY(pointer));
                if (iBug.input_borders.contains(bufX,bufY)){ //&& isBugClicked){
                    iBug.setPosition(bufX,bufY);
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
