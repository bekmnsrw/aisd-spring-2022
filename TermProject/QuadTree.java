package QTree;

import java.util.ArrayList;
import java.util.List;

public class QuadTree<T> {
    private final int capacity;
    private final AABB<T> boundary;
    private List<XY<T>> points;
    private boolean divided;

    private QuadTree<T> parent;

    QuadTree<T> northwest;
    QuadTree<T> northeast;
    QuadTree<T> southeast;
    QuadTree<T> southwest;

    public QuadTree(AABB<T> boundary, int capacity, QuadTree<T> parent) {
        this.boundary = boundary;
        this.capacity = capacity;
        points = new ArrayList<>();
        divided = false;
        this.parent = parent;
    }

    public boolean insert(XY<T> p) {
        if (!boundary.contains(p)) { return false; }
        if (!divided) {
            if (points.size() < capacity) {
                points.add(p);
                return true;
            }

            subdivide();
        }

        return northwest.insert(p)
                || northeast.insert(p)
                || southeast.insert(p)
                || southwest.insert(p);
    }

    /**
     * Создание 4 детей/потомков, которые делят
     * исходный квадрант на 4 равных квадранта
     */
    private void subdivide() {
        double x1 = boundary.getX1();
        double y1 = boundary.getY1();
        double x2 = boundary.getX2();
        double y2 = boundary.getY2();

        double xOffset = x1 + (boundary.getX2() - boundary.getX1()) / 2;
        double yOffset = y1 + (boundary.getY2() - boundary.getY1()) / 2;

        AABB<T> nw = new AABB<>(x1, yOffset, xOffset, y2);
        northwest = new QuadTree<>(nw, capacity,this);
        AABB<T> ne = new AABB<>(xOffset, yOffset, x2, y2);
        northeast = new QuadTree<>(ne, capacity,this);
        AABB<T> se = new AABB<>(xOffset, y1, x2, yOffset);
        southeast = new QuadTree<>(se, capacity,this);
        AABB<T> sw = new AABB<>(x1, y1, xOffset, yOffset);
        southwest = new QuadTree<>(sw, capacity,this);

        divided = true;

        for (XY<T> p : points) {
            boolean inserted = northwest.insert(p)
                    || northeast.insert(p)
                    || southwest.insert(p)
                    || southeast.insert(p);
        }

        points = new ArrayList<>();
    }

    /**
     * Поиск всех точек, расположенных внутри области range
     */
    public List<XY<T>> query(AABB<T> range) {
        List<XY<T>> pointsInRange = new ArrayList<>();

        if (!boundary.intersects(range)) { return pointsInRange; }

        for (XY<T> p : points) {
            if (range.contains(p)) {
                pointsInRange.add(p);
            }
        }

        if (!divided) { return pointsInRange; }

        pointsInRange.addAll(northwest.query(range));
        pointsInRange.addAll(northeast.query(range));
        pointsInRange.addAll(southeast.query(range));
        pointsInRange.addAll(southwest.query(range));

        return pointsInRange;
    }

    public int numOfPts() {
        if (divided) {
            return northwest.numOfPts()
                    + northeast.numOfPts()
                    + southwest.numOfPts()
                    + southeast.numOfPts();
        } else { return points.size(); }
    }

    /**
     * clearPoints = true - очистить дерево полностью
     * clearPoints = false - очистить информацию только о детях
     */
    public void clearQTree(boolean clearPoints) {
        if (clearPoints) { points.clear(); }

        northwest = null;
        northeast = null;
        southeast = null;
        southwest = null;
        divided = false;
    }

    /**
     * Подсчет количества точек у всех детей/потомков
     */
    private int getChildrenSum() {
        return northwest.numOfPts()
                + northeast.numOfPts()
                + southwest.numOfPts()
                + southeast.numOfPts();
    }

    /**
     * Забираем (если возможно) все точки детей/потомков
     * и удаляем потомков/детей
     */
    private void collapse() {
        if (getChildrenSum() > capacity) { return; }

        points.addAll(northwest.points);
        points.addAll(northeast.points);
        points.addAll(southeast.points);
        points.addAll(southwest.points);

        clearQTree(false);

        if (parent != null) { parent.collapse(); }
    }

    /**
     * Удаление точки либо у родителя, либо у ребенка/потомка
     */
    public boolean delete(XY<T> p) {
        if (points.contains(p)) {
            points.remove(p);
            if (parent != null) { parent.collapse(); }
            return true;
        } else if (divided) {
            return northwest.delete(p)
                    || northeast.delete(p)
                    || southwest.delete(p)
                    || southeast.delete(p);
        } else
            return false;
    }

    @Override
    public String toString() {
        if (divided) {
            return boundary +"\r\n" +
                    "| NW " + "\r\n" + northwest +
                    "| NE " + "\r\n" + northeast +
                    "| SW " + "\r\n" + southwest +
                    "| SE " + "\r\n" + southeast;
        } else {
            return boundary + "\r\n" +
                    "| Points: " + points + "\r\n";
        }
    }
}