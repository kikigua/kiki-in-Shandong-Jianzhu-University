package tree_01;

import java.util.function.Consumer;

// 将圆的半径乘以10
public class CircleTimesByTen implements Consumer<Circle> {

    public void accept(Circle x) {
        double r = x.getRadius();
        x.setRadius(r * 10);
    }
}
