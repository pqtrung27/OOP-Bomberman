package uet.oop.bomberman.display;

import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;

/**
 * Lớp cài dặt màn hình hiển thị.
 *
 * @author TTD
 */
public abstract class DisplayScene {
    protected Scene scene;
    protected MediaPlayer bgmPlayer;

    public Scene getScene() {
        return this.scene;
    }

    public void update() {

    }

    public void render() {

    }

    public void reset() {

    }

    public void close() {
        stopBGM();
    }

    public void startBGM() {
        if (bgmPlayer != null)
            bgmPlayer.play();
    }

    public void stopBGM(){
        if (bgmPlayer != null)
            bgmPlayer.stop();
    }
}

