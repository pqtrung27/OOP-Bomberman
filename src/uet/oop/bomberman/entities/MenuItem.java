package uet.oop.bomberman.entities;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import uet.oop.bomberman.Main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Tham khảo youtuber Almas Baimagambetov.
 */
public class MenuItem extends HBox {
    private ImageView leftArrow;
    private ImageView rightArrow;
    private Text text;
    private Runnable script;
    public MenuItem(String name) {
        super(15);
        setAlignment(Pos.CENTER);

        try {
            leftArrow = new ImageView(new Image(new FileInputStream("res/textures/leftarrow.png")));
            rightArrow = new ImageView(new Image(new FileInputStream("res/textures/rightarrow.png")));
        } catch (FileNotFoundException e) {
            System.out.println("Image file not Found!!!");
        }

        text = new Text(name);
        text.setFont(Main.FONT);

        getChildren().addAll(leftArrow, text, rightArrow);
        setActive(false);
    }

    public void setActive(boolean active) {
        leftArrow.setVisible(active);
        rightArrow.setVisible(active);
        text.setFill(active ? Color.WHITE : Color.GRAY);
    }

    public void setOnActivate(Runnable r) {
        script = r;
    }

    public void activate() {
        if (script != null) {
            script.run();
        }
    }
}
