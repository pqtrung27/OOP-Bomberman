package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import uet.oop.bomberman.util.Controller;
import uet.oop.bomberman.util.LoadLevel;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 13;

    final double initialSceneWidth = WIDTH * Sprite.SCALED_SIZE;

    final double initialSceneHeight = HEIGHT * Sprite.SCALED_SIZE;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root, initialSceneWidth, initialSceneHeight);

        //tao input event handler.
        Controller controller = new Controller();
        scene.setOnKeyPressed(controller::listen);
        scene.setOnKeyReleased(controller::release);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        //bay lac
        String bgmFile = "res/audio/MainBGM.mp3";
        Media bgm = new Media(new File(bgmFile).toURI().toString());
        MediaPlayer bgmPlayer = new MediaPlayer(bgm);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
                bgmPlayer.play();
            }
        };
        timer.start();

        createMap();
    }

    public void createMap() {
        try {
            new LoadLevel(1, stillObjects, entities);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!!!");
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
