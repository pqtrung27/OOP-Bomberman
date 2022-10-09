import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.Comparator;

import edu.princeton.cs.algs4.Queue;

public class KdTree {

    private Node root;

    private int size = 0;

    private class Node {
        private Point2D key;
        private Node left, right;
        private int level;

        public Node(Point2D key, int level) {
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

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (root == null) {
            root = new Node(p, 0);
            size++;
        } else if (!contains(p)) {
            root = insert(root, p);
            size++;
        }
    }

    private Node insert(Node cur, Point2D p) {
        Comparator<Point2D> cmp = (cur.level % 2 == 0) ? Point2D.X_ORDER : Point2D.Y_ORDER;
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

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return contains(root, p);
    }

    private boolean contains(Node cur, Point2D p) {
        if (cur == null) return false;
        Comparator<Point2D> cmp = (cur.level % 2 == 0) ? Point2D.X_ORDER : Point2D.Y_ORDER;
        if (cur.key.equals(p)) return true;
        if (cmp.compare(p, cur.key) < 0) {
            if (cur.left == null) return false;
            else return contains(cur.left, p);
        } else {
            if (cur.right == null) return false;
            else return contains(cur.right, p);
        }
    }

    public void draw() {
        draw(root);
    }

    private void draw(Node cur) {
        if (cur == null) return;
        cur.key.draw();
        if (cur.left != null) draw(cur.left);
        if (cur.right != null) draw(cur.right);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        Queue<Point2D> it = new Queue<>();
        keys(root, it, rect);
        return it;
    }

    private void keys(Node cur, Queue<Point2D> queue, RectHV rect) {
        if (cur == null) return;
        if (rect.contains(cur.key)) queue.enqueue(cur.key);

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

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (isEmpty()) return null;
        return nearest(root, p, root.key);
    }

    private Point2D nearest(Node cur, Point2D p, Point2D ans) {
        if (cur == null) {
            return ans;
        }
        boolean vertical = (cur.level % 2 == 0);
        if (vertical) {
            if (p.x() < cur.key.x()) {
                Point2D ansLeft = nearest(cur.left, p, cur.key.distanceSquaredTo(p) < ans.distanceSquaredTo(p) ? cur.key : ans);
                if (ansLeft.distanceSquaredTo(p) > Math.pow(cur.key.x() - p.x(), 2)) {
                    Point2D ansRight = nearest(cur.right, p, ansLeft);
                    return ansRight.distanceSquaredTo(p) > ansLeft.distanceSquaredTo(p) ? ansLeft : ansRight;
                } else return ansLeft;
            } else {
                Point2D ansRight = nearest(cur.right, p, cur.key.distanceSquaredTo(p) < ans.distanceSquaredTo(p) ? cur.key : ans);
                if (ansRight.distanceSquaredTo(p) > Math.pow(cur.key.x() - p.x(), 2)) {
                    Point2D ansLeft = nearest(cur.left, p, ansRight);
                    return ansRight.distanceSquaredTo(p) > ansLeft.distanceSquaredTo(p) ? ansLeft : ansRight;
                } else return ansRight;
            }
        } else {
            if (p.y() < cur.key.y()) {
                Point2D ansLeft = nearest(cur.left, p, cur.key.distanceSquaredTo(p) < ans.distanceSquaredTo(p) ? cur.key : ans);
                if (ansLeft.distanceSquaredTo(p) > Math.pow(cur.key.y() - p.y(), 2)) {
                    Point2D ansRight = nearest(cur.right, p, ansLeft);
                    return ansRight.distanceSquaredTo(p) > ansLeft.distanceSquaredTo(p) ? ansLeft : ansRight;
                } else return ansLeft;
            } else {
                Point2D ansRight = nearest(cur.right, p, cur.key.distanceSquaredTo(p) < ans.distanceSquaredTo(p) ? cur.key : ans);
                if (ansRight.distanceSquaredTo(p) > Math.pow(cur.key.y() - p.y(), 2)) {
                    Point2D ansLeft = nearest(cur.left, p, ansRight);
                    return ansRight.distanceSquaredTo(p) > ansLeft.distanceSquaredTo(p) ? ansLeft : ansRight;
                } else return ansRight;
            }
        }
    }
}
