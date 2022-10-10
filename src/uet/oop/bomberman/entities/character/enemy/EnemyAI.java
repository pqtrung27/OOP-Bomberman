/******************************************************************************
 *
 *  Immutable data type that calculates
 *  the best way possible for the enemies
 *  to get to the bomber position.
 *
 ******************************************************************************/

package uet.oop.bomberman.entities.character.enemy;

/**
 * The {@code EnemyAI} class is a data type for computing the running
 * path of the enemy to get to the desired positon.
 * <p>
 * This implementation uses A* algorithm with a min order priority queue to
 * reduce the calculation time and find the best way possible.
 * This technique is due to
 * <a href = "https://en.wikipedia.org/wiki/A*_search_algorithm">Peter Hart, Nils Nilsson and Bertram Raphael</a>.
 * Each operation takes time proportional to the size of the board in the worst case.
 * The data values are stored with priority queue using custom Node class.
 *
 * @author Phu Quoc Trung
 * @author Tran Thuy Duong
 */

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.util.*;

public class EnemyAI {

    private static int bomberPosX;
    private static int bomberPosY;

    private static class Node implements Comparable<Node> {
        private final int x;
        private final int y;
        private final int direction;
        private final Node prev;
        private final int manVal; //Manhattan value of this position.
        private final int step;

        /**
         * Node class constructor.
         *
         * @param x         the x Position of the enemy after the direction turn.
         * @param y         the y Position of the enemy after the direction turn.
         * @param direction the turn to get to this position.
         * @param prev      previous node.
         */
        Node(int x, int y, int direction, Node prev) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.prev = prev;
            if (prev != null) {
                this.step = prev.step + 1;
            } else {
                this.step = 1;
            }
            this.manVal = Math.abs(bomberPosX - x) + Math.abs(bomberPosY - y);
        }

        /**
         * Method override in comparable.
         *
         * @param that the object to be compared.
         * @return the compare result, using manhattan value
         * and the step it took from the first posiotion.
         */
        @Override
        public int compareTo(Node that) {
            int comp = (this.manVal + this.step) - (that.manVal + that.step);
            if (comp != 0) return comp;
            else return this.manVal - that.manVal;
        }
    }

    /**
     * check if one direction is moving backward from the other.
     *
     * @param direction1 input direction1.
     * @param direction2 input direction2.
     * @return result.
     */
    private static boolean movingBackward(int direction1, int direction2) {
        if (direction1 == MovingEntity.directionUp && direction2 == MovingEntity.directionDown) return true;
        if (direction1 == MovingEntity.directionLeft && direction2 == MovingEntity.directionRight) return true;
        if (direction1 == MovingEntity.directionDown && direction2 == MovingEntity.directionUp) return true;
        if (direction1 == MovingEntity.directionRight && direction2 == MovingEntity.directionLeft) return true;
        return false;
    }

    /**
     * find the next step from the enemy's start position in the shortest path to
     * bomber's position, only one step because bomber is always moving.
     *
     * @param enemy  the enemy to consider.
     * @param bomber the bomber to get posiotion.
     * @param preDir the direction enemy took before this.
     * @return the next step.
     */
    public static int find(Enemy enemy, Bomber bomber, int preDir) {
        bomberPosX = Sprite.SCALED_SIZE * ((bomber.getX() / Sprite.SCALED_SIZE));
        bomberPosY = Sprite.SCALED_SIZE * ((bomber.getY() / Sprite.SCALED_SIZE));
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(enemy.getX(), enemy.getY(), preDir, null));
        while (!pq.isEmpty()) {
            Node top = pq.peek();
            if (top.x == bomberPosX && top.y == bomberPosY) {
                break;
            }
            if (top.step >= 20) return 0; // return 0 if it takes too long for the enemy to get to bomber's position
            top = pq.poll();              // Average max step value is 18 in Level 1, so 20 here is appropriate.
            for (int direction = 1; direction <= 4; ++direction) {
                if (movingBackward(direction, top.direction)) continue;
                int addX = 0, addY = 0;
                if (direction == MovingEntity.directionUp) addY -= (Sprite.SCALED_SIZE);
                if (direction == MovingEntity.directionDown) addY += (Sprite.SCALED_SIZE);
                if (direction == MovingEntity.directionLeft) addX -= (Sprite.SCALED_SIZE);
                if (direction == MovingEntity.directionRight) addX += (Sprite.SCALED_SIZE);

                if (Entity.board.getEntity(top.x + addX, top.y + addY) == null) {
                    pq.add(new Node(top.x + addX, top.y + addY, direction, top));
                }
            }
        }
        Node top = pq.peek();
        if (top == null) {
            return 0;
        }
        if (top.step == 1) return 0;
        while (top.step != 2) {
            top = top.prev;
        }
        return top.direction;
    }
}

/******************************************************************************
 *  Copyright 2022, Phu Quoc Trung and Tran Thuy Duong.
 *
 *  This file is part of OOP-Bomberman, which accompanies the course
 *
 *      INT2204 of UET-VNU
 *
 *
 *  OOP-Bomberman is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OOP-Bomberman is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  See http://www.gnu.org/licenses.
 ******************************************************************************/