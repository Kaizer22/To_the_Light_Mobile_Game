package com.mygdx.com.mygdx.gameplay;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by denis on 07.07.18.
 */

public class GameLogic {
    public static void checkCollisions(Obstacle[] obstacles, Bug bug){
        for (int i = 0; i < obstacles.length ; i++) {
            if (Math.abs(obstacles[i].y - bug.y) < bug.size*1.5){
                if (collides(obstacles[i],bug)){
                    if (obstacles[i].y < bug.collision.y && bug.collision.x< obstacles[i].lp_collision.width ||
                            (obstacles[i].y < bug.collision.y && bug.collision.x> obstacles[i].rp_collision.x)){
                        bug.canMoveDown = false;

                    }else if ((obstacles[i].y > bug.collision.y && bug.collision.x< obstacles[i].lp_collision.width) ||
                            (obstacles[i].y > bug.collision.y && bug.collision.x> obstacles[i].rp_collision.x)){
                        bug.canMoveUp = false;

                    }//else if (obstacles[i].lp_collision.)

                }else
                    bug.update();
            }
        }

    }

    public static boolean collides(Obstacle obstacle, Bug bug){
        if (Intersector.overlaps(obstacle.lp_collision,bug.collision) || Intersector.overlaps(obstacle.rp_collision,bug.collision)){
            return true;
        }
        return false;
    }
}
