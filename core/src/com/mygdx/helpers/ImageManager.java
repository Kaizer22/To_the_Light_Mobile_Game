package com.mygdx.helpers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
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
        Animation saw;
        Animation coin;
        Animation death_screen;

        void initializeGameplay(){
            bug_move = initAnim("gameplay/bug.png",64,64,0.02f);
            death_screen = initAnim("gameplay/on_death_bg.png",154,110,0.9f);
            saw = initAnim("gameplay/saw_anim.png",22,22,0.01f);
            coin = initAnim("gameplay/coin.png",22,22,0.06f);
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
            for (TextureRegion[] aTmp : tmp) {
                for (TextureRegion anATmp : aTmp) {
                    tx[k] = anATmp;
                    k++;
                }
            }
            return new Animation(duration,tx);
        }


    }

    private AnimationManger animator;

    private Map<String,TextureRegion> textureRegions;
    private Texture texture1;


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

        texture1 = new Texture("buttons.png");
        TextureRegion tmp[][] = TextureRegion.split(texture1, texture1.getWidth(), texture1.getHeight()/6);
        textureRegions.put("button_shopUp",tmp[0][0]);
        textureRegions.put("button_shopDown",tmp[1][0]);
        textureRegions.put("button_exitUp",tmp[2][0]);
        textureRegions.put("button_exitDown",tmp[3][0]);
        textureRegions.put("button_musicOn",tmp[4][0]);
        textureRegions.put("button_musicOff",tmp[5][0]);

        texture1 = new Texture("button_play.png");
        tmp = TextureRegion.split(texture1, texture1.getWidth(), texture1.getHeight()/2);
        textureRegions.put("button_playUp",tmp[0][0]);
        textureRegions.put("button_playDown",tmp[1][0]);

    }

    public void initializeGameplay(){
        animator.initializeGameplay();

        textureRegions.put("stone_wall",new TextureRegion(new Texture("gameplay/stone_wall.png")));
        textureRegions.put("bricks",new TextureRegion(new Texture("gameplay/bricks.png")));


        texture1 = new Texture("gameplay/blocks.png");
        TextureRegion tmp[][] = TextureRegion.split(texture1, texture1.getWidth(), texture1.getHeight()/6);
        textureRegions.put("wood",tmp[0][0]);
        textureRegions.put("stone",tmp[1][0]);
        textureRegions.put("sharp",tmp[2][0]);
        textureRegions.put("tube_blue",tmp[3][0]);
        textureRegions.put("tube_red",tmp[4][0]);
        textureRegions.put("saw",tmp[5][0]);


        textureRegions.put("frame", new TextureRegion(new Texture("gameplay/frame.png")));
        textureRegions.put("bug_bg", new TextureRegion(new Texture("gameplay/bug_bg.png")));

        texture1 = new Texture("buttons.png");
         tmp = TextureRegion.split(texture1, texture1.getWidth(), texture1.getHeight()/6);
        textureRegions.put("button_musicOn",tmp[4][0]);
        textureRegions.put("button_musicOff",tmp[5][0]);

        texture1 = new Texture("gameplay/button_pause.png");
        tmp = TextureRegion.split(texture1, texture1.getWidth(), texture1.getHeight()/2);
        textureRegions.put("button_pauseUp",tmp[0][0]);
        textureRegions.put("button_pauseDown",tmp[1][0]);

        texture1 = new Texture("gameplay/menu_pause_buttons.png");
        tmp = TextureRegion.split(texture1, texture1.getWidth(), texture1.getHeight()/4);
        textureRegions.put("button_resumeUp",tmp[0][0]);
        textureRegions.put("button_resumeDown",tmp[1][0]);
        textureRegions.put("button_pauseExitUp",tmp[2][0]);
        textureRegions.put("button_pauseExitDown",tmp[3][0]);

        texture1 = new Texture("gameplay/on_death_button_watchAds.png");
        tmp = TextureRegion.split(texture1, texture1.getWidth(), texture1.getHeight()/2);
        textureRegions.put("button_watchAdsUp",tmp[0][0]);
        textureRegions.put("button_watchAdsDown",tmp[1][0]);

        texture1 = new Texture("gameplay/on_death_button_restart.png");
        tmp = TextureRegion.split(texture1, texture1.getWidth(), texture1.getHeight()/2);
        textureRegions.put("button_restartUp",tmp[0][0]);
        textureRegions.put("button_restartDown",tmp[1][0]);



        textureRegions.put("on_pause_bg", new TextureRegion(new Texture("gameplay/on_pause_bg.png")));

        //------------ Debug textures -------------
        textureRegions.put("coll",new TextureRegion(new Texture("gameplay/coll.png")));
        textureRegions.put("bcoll",new TextureRegion(new Texture("gameplay/bcoll.png")));

    }

    public void dispose(){
        textureRegions.clear();
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
    public TextureRegion getButton_PauseUp(){return textureRegions.get("button_pauseUp");}
    public TextureRegion getButton_PauseDown(){return textureRegions.get("button_pauseDown");}
    public TextureRegion getButton_ResumeUp(){return textureRegions.get("button_resumeUp");}
    public TextureRegion getButton_ResumeDown(){return textureRegions.get("button_resumeDown");}
    public TextureRegion getButton_PauseExitUp(){return textureRegions.get("button_pauseExitUp");}
    public TextureRegion getButton_PauseExitDown(){return textureRegions.get("button_pauseExitDown");}
    public TextureRegion getButton_WatchAdsUp(){return textureRegions.get("button_watchAdsUp");}
    public TextureRegion getButton_WatchAdsDown(){return textureRegions.get("button_watchAdsDown");}
    public TextureRegion getButton_RestartUp(){return textureRegions.get("button_restartUp");}
    public TextureRegion getButton_RestartDown(){return textureRegions.get("button_restartDown");}

    public TextureRegion getOnPauseBg(){return textureRegions.get("on_pause_bg");}


    public TextureRegion getColl(){return textureRegions.get("coll");}
    public TextureRegion getBColl(){return textureRegions.get("bcoll");}

    public TextureRegion getFrameEffect(){return textureRegions.get("frame");}


    public TextureRegion getMenuBgCurrentFrame(float stateTime){
        return (TextureRegion) animator.menu_bg.getKeyFrame(stateTime,true);
    }
    public TextureRegion getMenuBgEffectCurrentFrame(float stateTime){
        return (TextureRegion) animator.menu_bg_effect.getKeyFrame(stateTime,true);
    }

    public TextureRegion getDeathScreenCurrentFrame(float stateTime){
        return (TextureRegion) animator.death_screen.getKeyFrame(stateTime,true);
    }

    public TextureRegion getBugCurrentFrame(float stateTime){
        return (TextureRegion) animator.bug_move.getKeyFrame(stateTime,true);
    }
    public TextureRegion getBugBackground(){return textureRegions.get("bug_bg");}

    public TextureRegion getSawCurrentFrame(float stateTime){
        return (TextureRegion) animator.saw.getKeyFrame(stateTime,true);
    }

    public TextureRegion getCoinCurrentFrame(float stateTime){
        return (TextureRegion) animator.coin.getKeyFrame(stateTime,true);
    }

    public TextureRegion getGameplayBackground(String type){
        return textureRegions.get(type);
    }

    public TextureRegion getObstacle(String type){
        return textureRegions.get(type);
    }




}
