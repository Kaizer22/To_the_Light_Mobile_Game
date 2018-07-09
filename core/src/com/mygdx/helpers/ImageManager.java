package com.mygdx.helpers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by denis on 16.06.18.
 */

public class ImageManager {


    private class AnimationManger{
        Animation menu_bg;
        Animation menu_bg_effect;
        Animation bug_move;
        Animation coin;

        void initializeGameplay(){
            bug_move = initAnim("gameplay/bug.png",64,64,0.16f);
        }

        void initializeMenu(){
            menu_bg_effect = initAnim(97,"menu_bg_effect/bg_effect",0.15f);
            menu_bg = initAnim(27,"menu_bg/menu_bg",1f);
        }

        private Animation initAnim(int num_frames, String directory_and_prefix,float duration){
            TextureRegion[] tr;
            tr = new TextureRegion[num_frames];
            for (int i = 0; i < num_frames; i++){
                tr[i] = new TextureRegion();
                tr[i].setRegion(new Texture(directory_and_prefix+"_"+i+".png"));
            }
            return new Animation(duration,tr);
        }

        private Animation initAnim(String directory,int width,int height,float duration){
            Texture bug = new Texture(directory);
            TextureRegion[][] tmp = TextureRegion.split(bug,width,height);
            TextureRegion[] tx = new TextureRegion[tmp.length*tmp[0].length];
            int k = 0;
            for (int i = 0; i < tmp.length; i++) {
                for (int j = 0; j < tmp[i].length ; j++) {
                    tx[k] = tmp[i][j];
                    k++;
                }
            }
            return new Animation(duration,tx);
        }

    }

    private AnimationManger animator;

    private Map<String,TextureRegion> textureRegions;
    Texture bs;
    Texture bp;

    //TextureAtlas obstacles;
    //TextureAtlas backgrounds;
    //TextureAtlas bug;
    //TextureAtlas coin;

    public ImageManager(){
        textureRegions = new HashMap<String, TextureRegion>();
        animator = new AnimationManger();
    }

    public void initializeMenu() {
            animator.initializeMenu();

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

    public void initializeGameplay(){
        animator.initializeGameplay();

        textureRegions.put("stone_wall",new TextureRegion(new Texture("gameplay/stone_wall.png")));

        textureRegions.put("wood",new TextureRegion(new Texture("gameplay/wood.png")));

    }

    public void dispose(){
        textureRegions.clear();
    } //TODO узнать как TextureRegion s влияют на производительность, и нужен ли здесь .dispose()




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

    public TextureRegion getMenuBgCurrentFrame(float stateTime){
        return (TextureRegion) animator.menu_bg.getKeyFrame(stateTime,true);
    }
    public TextureRegion getMenuBgEffectCurrentFrame(float stateTime){
        return (TextureRegion) animator.menu_bg_effect.getKeyFrame(stateTime,true);
    }

    public TextureRegion getBugCurrentFrame(float stateTime){
        return (TextureRegion) animator.bug_move.getKeyFrame(stateTime,true);
    }

    public TextureRegion getGameplayBackground(String type){
        return textureRegions.get(type);
    }

    public TextureRegion getObstacle(String type){
        return textureRegions.get(type);
    }




}
