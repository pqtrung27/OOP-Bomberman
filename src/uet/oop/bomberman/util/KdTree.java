/******************************************************************************
 *  Compilation:  javac Queue.java.
 *  Execution:    java Queue < input.txt.
 *  Dependencies: Entity.java RectHV.java.
 *
 *  A non-generic Kd-tree, implement using BST.
 *
 ******************************************************************************/

package uet.oop.bomberman.util;

import uet.oop.bomberman.entities.Entity;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Queue;

/**
 *  The {@code KdTree} class represents a data structure storing all
 *  the points in a 2D surface.
 *  It supports the usual <em>insert</em>, <em>range</em>, <em>nearest</em>
 *  operations, along with methods for testing if the tree is empty,
 *  and iterating through the items
 *
 *  @author Phu Quoc Trung
 *
 */

public class KdTree {
    private Node root;
    private int size = 0;

    private class Node {
        private Entity key;
        private Node left, right;
        private int level;

        public Node(Entity key, int level) {
            this.key = key;
            this.level = level;
        }
    }

    public KdTree() {

    }

    public boolean isEmpty() {
        return (root == null);
    }

    public int size() {
        return size;
    }

    public void insert(Entity p) {
        if (p == null) throw new IllegalArgumentException();
        if (root == null) {
            root = new Node(p, 0);
            size++;
        } else if (!contains(p)) {
            root = insert(root, p);
            size++;
        }
    }

    private Node insert(Node cur, Entity p) {
        Comparator<Entity> cmp = (cur.level % 2 == 0) ? Entity.X_ORDER : Entity.Y_ORDER;
        int comp = cmp.compare(p, cur.key);
        if (comp < 0) {
            if (cur.left == null) cur.left = new Node(p, cur.level + 1);
            else cur.left = insert(cur.left, p);
        } else {
            if (cur.right == null) cur.right = new Node(p, cur.level + 1);
            else cur.right = insert(cur.right, p);
        }
        return cur;
    }

    public boolean contains(Entity p) {
        if (p == null) throw new IllegalArgumentException();
        return contains(root, p);
    }

    private boolean contains(Node cur, Entity p) {
        if (cur == null) return false;
        Comparator<Entity> cmp = (cur.level % 2 == 0) ? Entity.X_ORDER : Entity.Y_ORDER;
        if (cur.key.equals(p)) return true;
        if (cmp.compare(p, cur.key) < 0) {
            if (cur.left == null) return false;
            else return contains(cur.left, p);
        } else {
            if (cur.right == null) return false;
            else return contains(cur.right, p);
        }
    }

    public Iterable<Entity> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        Queue<Entity> it = new ArrayDeque<>();
        keys(root, it, rect);
        return it;
    }

    private void keys(Node cur, Queue<Entity> queue, RectHV rect) {
        if (cur == null) return;
        if (rect.contains(cur.key)) queue.add(cur.key);

        if (checkIntersectLeft(cur, rect)) keys(cur.left, queue, rect);
        if (checkIntersectRight(cur, rect)) keys(cur.right, queue, rect);

    }

    private boolean checkIntersectLeft(Node cur, RectHV rect) {
        boolean vertical = (cur.level % 2 == 0);
        if (vertical) {
            return cur.key.x() > rect.xmin();
        } else {
            return cur.key.y() > rect.ymin();
        }
    }

    private boolean checkIntersectRight(Node cur, RectHV rect) {
        boolean vertical = (cur.level % 2 == 0);
        if (vertical) {
            return cur.key.x() <= rect.xmax();
        } else {
            return cur.key.y() <= rect.ymax();
        }
    }

    public Entity nearest(Entity p) {
        if (p == null) throw new IllegalArgumentException();
        if (isEmpty()) return null;
        return nearest(root, p, root.key);
    }

    private Entity nearest(Node cur, Entity p, Entity ans) {
        if (cur == null) {
            return ans;
        }
        boolean vertical = (cur.level % 2 == 0);
        if (vertical) {
            if (p.x() < cur.key.x()) {
                Entity ansLeft = nearest(cur.left, p, cur.key.distanceSquaredTo(p) < ans.distanceSquaredTo(p) ? cur.key : ans);
                if (ansLeft.distanceSquaredTo(p) > Math.pow(cur.key.x() - p.x(), 2)) {
                    Entity ansRight = nearest(cur.right, p, ansLeft);
                    return ansRight.distanceSquaredTo(p) > ansLeft.distanceSquaredTo(p) ? ansLeft : ansRight;
                } else return ansLeft;
            } else {
                Entity ansRight = nearest(cur.right, p, cur.key.distanceSquaredTo(p) < ans.distanceSquaredTo(p) ? cur.key : ans);
                if (ansRight.distanceSquaredTo(p) > Math.pow(cur.key.x() - p.x(), 2)) {
                    Entity ansLeft = nearest(cur.left, p, ansRight);
                    return ansRight.distanceSquaredTo(p) > ansLeft.distanceSquaredTo(p) ? ansLeft : ansRight;
                } else return ansRight;
            }
        } else {
            if (p.y() < cur.key.y()) {
                Entity ansLeft = nearest(cur.left, p, cur.key.distanceSquaredTo(p) < ans.distanceSquaredTo(p) ? cur.key : ans);
                if (ansLeft.distanceSquaredTo(p) > Math.pow(cur.key.y() - p.y(), 2)) {
                    Entity ansRight = nearest(cur.right, p, ansLeft);
                    return ansRight.distanceSquaredTo(p) > ansLeft.distanceSquaredTo(p) ? ansLeft : ansRight;
                } else return ansLeft;
            } else {
                Entity ansRight = nearest(cur.right, p, cur.key.distanceSquaredTo(p) < ans.distanceSquaredTo(p) ? cur.key : ans);
                if (ansRight.distanceSquaredTo(p) > Math.pow(cur.key.y() - p.y(), 2)) {
                    Entity ansLeft = nearest(cur.left, p, ansRight);
                    return ansRight.distanceSquaredTo(p) > ansLeft.distanceSquaredTo(p) ? ansLeft : ansRight;
                } else return ansRight;
            }
        }
    }
}
