package com.mygdx.com.mygdx.gameplay;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by denis on 07.07.18.
 */

public class GameLogic {
    public static double score = 1;
    public static int scoreFactor = 10;
    public static int highscore;
    public static boolean isEndlessModeOn;

    static void checkCollisions(Obstacle[] obstacles, Bug bug){
        for (Obstacle obstacle : obstacles) {

            if (Math.abs(Math.max(obstacle.y, bug.collision.y) - Math.min(obstacle.y, bug.collision.y)) <= bug.size / 2) {
                if (collides(obstacle, bug)) {
                    if (obstacle.t == Obstacle.Type.SHARP || obstacle.t == Obstacle.Type.SAW) {
                        bug.isAlive = false;
                    } else {
                        if ((obstacle.y > bug.collision.y && bug.collision.x <= obstacle.lp_collision.width - obstacle.blockSize / 3) ||
                                (obstacle.y > bug.collision.y && (bug.collision.x + bug.collision.width) >= obstacle.rp_collision.x + obstacle.blockSize / 3)) {
                            bug.canMoveUp = false;
                            bug.canMoveDown = true;
                            bug.canMoveLeft = true;
                            bug.canMoveRight = true;
                        } else if (obstacle.y + obstacle.blockSize >= bug.collision.y && bug.collision.x <= obstacle.lp_collision.width - obstacle.blockSize / 3 ||
                                (obstacle.y + obstacle.blockSize >= bug.collision.y && (bug.collision.x + bug.collision.width) >= obstacle.rp_collision.x + obstacle.blockSize / 3)) {
                            bug.canMoveDown = false;
                            bug.canMoveUp = true;
                            bug.canMoveLeft = true;
                            bug.canMoveRight = true;

                        } else if ((bug.collision.y > obstacle.y - bug.collision.height) && (bug.collision.y < obstacle.y + obstacle.blockSize + bug.collision.height) && (bug.collision.x <= obstacle.lp_collision.x + obstacle.lp_collision.width)) {
                            bug.canMoveLeft = false;
                            bug.canMoveDown = true;
                            bug.canMoveUp = true;
                            bug.canMoveRight = true;

                        } else if ((bug.collision.y > obstacle.y - bug.collision.height) && (bug.collision.y < obstacle.y + obstacle.blockSize + bug.collision.height) && bug.collision.x + bug.collision.width >= obstacle.rp_collision.x) {
                            bug.canMoveRight = false;
                            bug.canMoveDown = true;
                            bug.canMoveUp = true;
                            bug.canMoveLeft = true;

                        }
                    }
                } else {
                    bug.canMoveRight = true;
                    bug.canMoveDown = true;
                    bug.canMoveUp = true;
                    bug.canMoveLeft = true;
                }
            }
        }

    }

    private static boolean collides(Obstacle obstacle, Bug bug){
        return (Intersector.overlaps(obstacle.lp_collision,bug.collision) || Intersector.overlaps(obstacle.rp_collision,bug.collision));

        }


    static boolean collides(Coin coin, Bug bug) {
        if (Intersector.overlaps(coin.collision,bug.collision)){
            score += coin.getCost();
            coin.isVisible = false;
            return true;
        }
        return false;
    }

    static void updateScore(float shift, float coefficient){
        score += shift / 40 / coefficient ;
    }

    static float calculateShift(float shift, float coefficient){

        if ((int)score > scoreFactor){
            float s;
            s = shift + (float)0.5*coefficient;
            scoreFactor = (int)(scoreFactor * 1.45);
            return s;
        }
        return shift;
    }
}
