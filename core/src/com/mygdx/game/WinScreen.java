package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by denis on 12.08.18.
 */

public class WinScreen implements Screen {
    final ToTheLightGame game;

    private float screenWidth, screenHeight, pxSize;

    private OrthographicCamera camera;
    private BitmapFont congratulation1; private String c1; private String c2;
    private int fontScaleC;
    private boolean isFontMustBeDrown;
    private TextureRegion sky;
    private TextureRegion bug;
    private float bug_x;
    private float bug_y;
    private float shift;

    private float stateTime = 0f;

    WinScreen(ToTheLightGame toTheLightGame, int chosenSkin) {
        game = toTheLightGame;
        camera = new OrthographicCamera();

        game.iM.initializeWinScreen(chosenSkin);
        sky = this.game.iM.getGameplayBackground("sky");

        fontScaleC = 5000;
        isFontMustBeDrown = true;

        c1 = "Infinity is not a limit!";
        c2 = "You have opened endless mode";
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        stateTime += Gdx.graphics.getDeltaTime();

        update();
        game.batch.begin();
        draw();
        game.batch.end();
    }

    private void update() {
        bug_y +=shift;

        if (bug_y > screenHeight){
            toMainMenu();
        }

        if (fontScaleC<600){
            isFontMustBeDrown = false;

        }else {
            fontScaleC-=5;
            congratulation1.getData().setScale(screenWidth / fontScaleC + 1);
        }




    }

    private void draw() {
        game.batch.draw(sky,0,0,screenWidth,screenHeight);
        bug = game.iM.getBugCurrentFrame(stateTime);
        game.batch.draw(bug,bug_x,bug_y,pxSize*4,pxSize*4);

        if (isFontMustBeDrown) {
            congratulation1.draw(game.batch, c1, screenWidth / 2 - (congratulation1.getCapHeight()* c1.length() / 2), screenHeight / 2);
            congratulation1.draw(game.batch,c2,screenWidth/2-(congratulation1.getCapHeight()* c2.length()/2),screenHeight/2-pxSize );
        }
    }


    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false,width,height);

        screenHeight = height;
        screenWidth = width;
        pxSize = width/12;

        shift = screenHeight / 1000;
        bug_x = screenWidth/2 - pxSize*2;
        bug_y = pxSize*2;

        congratulation1 = new BitmapFont(Gdx.files.internal("pixel_font.fnt"));
        congratulation1.getData().setScale(width/fontScaleC + 1);

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

    private void toMainMenu(){
        dispose();
        MenuScreen m = new MenuScreen(game);
        game.setScreen(m);
    }

    @Override
    public void dispose() {
        game.iM.dispose();
    }
}
