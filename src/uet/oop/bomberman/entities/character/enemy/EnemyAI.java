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
 * path of the enemy to get to the desired position.
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
        private final int euclidVal; //Euclid value of this position.
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
            this.euclidVal = (int) Math.sqrt(Math.pow((bomberPosX - x), 2) + Math.pow((bomberPosY - y), 2));
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
            int comp = (this.euclidVal + this.step) - (that.euclidVal + that.step);
            if (comp != 0) return comp;
            else return this.euclidVal - that.euclidVal;
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
        bomberPosX = (bomber.getTopX() / Sprite.SCALED_SIZE);
        bomberPosY = (bomber.getTopY() / Sprite.SCALED_SIZE);

        Node initNode = new Node(enemy.getTopX() / Sprite.SCALED_SIZE, enemy.getTopY() / Sprite.SCALED_SIZE, preDir, null);
        if (Math.abs(initNode.x - bomberPosX) >= 20) return 0;
        if (Math.abs(initNode.y - bomberPosY) >= 15) return 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(initNode);
        int loop_count = 0;
        while (!pq.isEmpty()) {
            loop_count++;
            Node top = pq.peek();
            if (top.x == bomberPosX && top.y == bomberPosY) {
                break;
            }
            if (top.step >= 30 || pq.size() >= 30 || loop_count == 30)  // return 0 if it takes too long for the enemy to get to bomber's position
                return 0;                                               // Average max step value is 18 in Level 1.
            top = pq.poll();
            for (int direction = 1; direction <= 4; ++direction) {
                if (movingBackward(direction, top.direction)) continue;
                int addX = 0, addY = 0;
                if (direction == MovingEntity.directionUp) addY--;
                if (direction == MovingEntity.directionDown) addY++;
                if (direction == MovingEntity.directionLeft) addX--;
                if (direction == MovingEntity.directionRight) addX++;
                int _x = top.x + addX;
                int _y = top.y + addY;
                Enemy enemy1 = new Oneal(_x, _y);
                if (Entity.board.getEntityCollideWith(enemy1, 0, 0) == null) {
                    pq.add(new Node(_x, _y, direction, top));
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