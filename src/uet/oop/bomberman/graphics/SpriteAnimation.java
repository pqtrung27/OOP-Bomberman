package uet.oop.bomberman.graphics;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {

    private final ImageView imageView;
    private final int count;
    private final int columns;
    private int offsetX;
    private int offsetY;

    public SpriteAnimation(ImageView imageView, Duration duration, int count,
                           int columns, int offsetX, int offsetY) {

        this.imageView = imageView;
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;

        setCycleDuration(duration);
        setCycleCount(Animation.INDEFINITE);
        setInterpolator(Interpolator.LINEAR);
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE));
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    @Override
    protected void interpolate(double frac) {
        final int index = Math.min((int) Math.floor(count * frac), count - 1);
        final int x = (index%columns) * Sprite.SCALED_SIZE;
    }
}
