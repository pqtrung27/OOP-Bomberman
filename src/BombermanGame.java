import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BombermanGame {

    public final int SQUARED_SCREEN_SIZE = 600;
    public final String GAME_TITLE = "Bomberman!";
    public JFrame mainFrame = new JFrame(GAME_TITLE);

    private void initGui() {
        mainFrame.setSize(SQUARED_SCREEN_SIZE, SQUARED_SCREEN_SIZE);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        BombermanGame game = new BombermanGame();
        game.initGui();
    }
}
