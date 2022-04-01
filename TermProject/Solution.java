package QTree;

public class Solution {
    public static void main(String[] args) {

         // todo: Попытка реализация аналога навигатора: поиск мест / организаций
         //  (например, магазинов, столовых, аптек и т.д.) внутри заданной пользователем области

        /**
         * map - карта (ограниченная область в виде прямоугольника)
         *
         * (x1, y1) - координаты левого нижнего угла прямоугольника
         * (x2, y2) - координаты правого верхнего угла прямоугольника
         */
        AABB<String> map = new AABB<>(0, 0, 100, 100);

        QuadTree<String> quadTree = new QuadTree<>(map, 4, null);

        /**
         * searchArea - задаваемая пользователем область внутри карты (map),
         * где происходит поиск (ограниченная область в виде прямоугольника)
         *
         * (x1, y1) - координаты левого нижнего угла прямоугольника
         * (x2, y2) - координаты правого верхнего угла прямоугольника
         */
        AABB<String> searchArea = new AABB<>(20, 25, 47, 62);

        /**
         * Объект класса XY - место/организация на карте (map) с координатами (x, y)
         * и некоторой информацией (T value, например, названием организации, временем её работы и т.д.)
         */
        XY<String> point = new XY<>(90,90, "Магнит");

        quadTree.insert(new XY<>(10,10, "Станция метро 'Проспект Победы'"));
        quadTree.insert(new XY<>(20,20, "Копицентр"));
        quadTree.insert(new XY<>(30,30, "Пятёрочка"));
        quadTree.insert(new XY<>(40,40, "Аптека"));
        quadTree.insert(new XY<>(50,50, "Столовушка"));
        quadTree.insert(new XY<>(60,60, "Добрая столовая"));
        quadTree.insert(new XY<>(70,70, "Ozon"));
        quadTree.insert(new XY<>(80,80, "Стоматология"));
        quadTree.insert(point);

        /**
         * Поиск и вывод на экран мест/организаций внутри заданной области поиска (searchArea)
         */
        System.out.println("Результаты поиска: " + quadTree.query(searchArea));
        System.out.println();

        /**
         * Пользователь также может исключить из поиска некоторые места/организации
         * с помощью метода boolean delete(XY<T> point)
         */

        System.out.println("Карта до удаления места/организации:");
        System.out.println();
        System.out.println(quadTree);
        System.out.println();

        quadTree.delete(point);
        System.out.println("Карта после удаления места/организации:");
        System.out.println();
        System.out.println(quadTree);
        System.out.println();

        /**
         * У пользователя есть возможность полностью очистить результаты поиска,
         * применив метод void clearQTree(boolean clearPoints)
         */
        quadTree.clearQTree(true);
        System.out.println("Очищенная пользователем карта: ");
        System.out.println();
        System.out.println(quadTree);
    }
}