package uet.oop.bomberman.display;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import uet.oop.bomberman.Main;
import uet.oop.bomberman.entities.Option;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LeaderBoard extends Menu {
    public LeaderBoard() {
        text.setText("LEADERBOARD");
        text.setStyle("-fx-font-size: 40");

        menuItems = new Option[1];
        menuItems[0] = new Option("BACK");
        menuItems[0].setOnActivate(() -> Main.setPlayingStatus(0, "back"));

        root.getChildren().add(text);
        root.getChildren().add(loadLeaderboard());
        root.getChildren().addAll(menuItems);
    }

    private HBox loadLeaderboard() {
        VBox names = new VBox();
        names.setAlignment(Pos.CENTER_LEFT);
        names.setSpacing(10);

        VBox scores = new VBox();
        scores.setAlignment(Pos.CENTER_RIGHT);
        scores.setSpacing(10);

        try {
            Scanner scanner = new Scanner(new FileInputStream("res/leaderboard.txt"));
            while (scanner.hasNext()) {
                // String name = scanner.next();
                // String score = scanner.next();
                names.getChildren().add(createText(scanner.next()));
                scores.getChildren().add(createText(scanner.next()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Leaderboard File Not Found!!!");
        }

        HBox scoreboard = new HBox(names, scores);
        scoreboard.setAlignment(Pos.CENTER);
        scoreboard.setSpacing(500);
        return scoreboard;
    }

    private Text createText(String mes) {
        Text textMes = new Text(mes);
        textMes.setFont(Main.FONT);
        textMes.setFill(Color.WHITE);
        return textMes;
    }
}
