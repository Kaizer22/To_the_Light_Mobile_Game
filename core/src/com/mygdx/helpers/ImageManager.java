package com.mygdx.helpers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by denis on 16.06.18.
 */

public class ImageManager {
    AnimationManger animator;
    private class AnimationManger{}

    private Map<String,TextureRegion> textureRegions;
    Texture bs;
    Texture bp;

    //TextureAtlas obstacles;
    //TextureAtlas backgrounds;
    //TextureAtlas bug;
    //TextureAtlas coin;

    public ImageManager(){
        textureRegions = new HashMap<String, TextureRegion>();

    }

    public void initializeMenu() {
            textureRegions.put("bg_effect",new TextureRegion(new Texture("bg_effect.png")));
            textureRegions.put("logo",new TextureRegion(new Texture("logo.png")));
            bs = new Texture("buttons.png");
        TextureRegion tmp[][] = TextureRegion.split(bs,bs.getWidth(),bs.getHeight()/6);
            textureRegions.put("button_shopUp",tmp[0][0]);
            textureRegions.put("button_shopDown",tmp[1][0]);
            textureRegions.put("button_exitUp",tmp[2][0]);
            textureRegions.put("button_exitDown",tmp[3][0]);
            textureRegions.put("button_musicOn",tmp[4][0]);
            textureRegions.put("button_musicOff",tmp[5][0]);
            bp = new Texture("button_play.png");
        tmp = TextureRegion.split(bp,bp.getWidth(),bp.getHeight()/2);
            textureRegions.put("button_playUp",tmp[0][0]);
            textureRegions.put("button_playDown",tmp[1][0]);

    }
    public void disposeMenu(){
    } //TODO узнать как TextureRegion s влияют на производительность, и нужен ли здесь .dispose()



    public TextureRegion getBg_effect(){
        return textureRegions.get("bg_effect");
    }
    public TextureRegion getLogo(){
        return textureRegions.get("logo");
    }
    public TextureRegion getButton_ShopUp(){
        return textureRegions.get("button_shopUp");
    }
    public TextureRegion getButton_ShopDown(){
        return textureRegions.get("button_shopDown");
    }
    public TextureRegion getButton_ExitUp(){
        return textureRegions.get("button_exitUp");
    }
    public TextureRegion getButton_ExitDown(){
        return textureRegions.get("button_exitDown");
    }
    public TextureRegion getButton_MusicOn(){
        return textureRegions.get("button_musicOn");
    }
    public TextureRegion getButton_MusicOff(){
        return textureRegions.get("button_musicOff");
    }
    public TextureRegion getButton_PlayUp(){
        return textureRegions.get("button_playUp");
    }
    public TextureRegion getButton_PlayDown(){
        return textureRegions.get("button_playDown");
    }




}
