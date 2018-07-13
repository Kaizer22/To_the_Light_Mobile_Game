package com.mygdx.com.mygdx.gameplay;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by denis on 07.07.18.
 */

public class GameLogic {
    static double score = 1;
    static int scoreFactor = 10;
    public static void checkCollisions(Obstacle[] obstacles, Bug bug){
        for (int i = 0; i < obstacles.length ; i++) {

            if (Math.abs(Math.max(obstacles[i].y,bug.collision.y) - Math.min(obstacles[i].y,bug.collision.y)) < bug.size/2){
                if (collides(obstacles[i],bug)) {
                    if ((obstacles[i].y > bug.collision.y && bug.collision.x < obstacles[i].lp_collision.width) ||
                            (obstacles[i].y > bug.collision.y && (bug.collision.x + bug.collision.width) > obstacles[i].rp_collision.x)) {
                        bug.canMoveUp = false;
                        bug.canMoveDown = true;
                        bug.canMoveLeft = true;
                        bug.canMoveRight = true;
                    } else if (obstacles[i].y + obstacles[i].blockSize > bug.collision.y && bug.collision.x < obstacles[i].lp_collision.width ||
                            (obstacles[i].y + obstacles[i].blockSize > bug.collision.y && bug.collision.x > obstacles[i].rp_collision.x)) {
                        bug.canMoveDown = false;
                        bug.canMoveUp = true;
                        bug.canMoveLeft = true;
                        bug.canMoveRight = true;

                    } else if ((bug.collision.y > obstacles[i].y - bug.collision.height) && (bug.collision.y > obstacles[i].y + obstacles[i].blockSize) && bug.collision.x + bug.collision.width < obstacles[i].rp_collision.x) {
                        bug.canMoveLeft = false;
                        bug.canMoveDown = true;
                        bug.canMoveUp = true;
                        bug.canMoveRight = true;

                    } else if ((bug.collision.y > obstacles[i].y - bug.collision.height) && (bug.collision.y < obstacles[i].y + obstacles[i].blockSize) && bug.collision.x + bug.collision.width > obstacles[i].rp_collision.x) {
                        bug.canMoveRight = false;
                        bug.canMoveDown = true;
                        bug.canMoveUp = true;
                        bug.canMoveLeft = true;

                    }
                } else {
                    bug.canMoveRight = true;
                    bug.canMoveDown = true;
                    bug.canMoveUp = true;
                    bug.canMoveLeft = true;
                }
                //System.out.println(bug.canMoveRight+" " + bug.canMoveLeft + " " + bug.canMoveDown + " " +  bug.canMoveUp );

            }
        }

    }

    public static boolean collides(Obstacle obstacle, Bug bug){
        if (Intersector.overlaps(obstacle.lp_collision,bug.collision) || Intersector.overlaps(obstacle.rp_collision,bug.collision)){
            return true;
        }
        return false;
    }

    public static void updateScore(float shift ){
        score += shift / 100;
    }

    public static float calculateShift(float shift){

        if ((int)score % scoreFactor == 0){
            float s;
            s = shift + (float)0.5;
            scoreFactor = (int)(scoreFactor * 1.5);
            return s;
        }
        return shift;
    }
}
